package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.multitouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.HashMap;
import java.util.jar.Attributes;

public class MultitouchSurface extends SurfaceView implements SurfaceHolder.Callback {
    public Canvas drawCanvas;
    public Paint drawPaint, canvasPaint;
    public Bitmap canvasBitmap;
    private HashMap<Integer, Path> paths = new HashMap<Integer, Path>();
    public SurfaceHolder surfaceHolder;
    public int[] colors = { Color.BLUE, Color.GREEN, Color.MAGENTA,
            Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,
            Color.LTGRAY, Color.YELLOW };

    public MultitouchSurface(Context context){
        super(context);
        if(surfaceHolder == null){
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
        }
        if(drawPaint == null){
            this.drawPaint = new Paint();
            this.drawPaint.setStrokeWidth(10);
            this.drawPaint.setStyle(Paint.Style.STROKE);
            this.drawPaint.setColor(100);
        }

        if(canvasPaint == null) {
            this.canvasPaint = new Paint(Paint.DITHER_FLAG);
        }
        setWillNotDraw(false);
    }


    public MultitouchSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(surfaceHolder == null){
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
        }
        if(drawPaint == null){
            this.drawPaint = new Paint();
            this.drawPaint.setStrokeWidth(10);
            this.drawPaint.setStyle(Paint.Style.STROKE);
        }

        if(canvasPaint == null) {
            this.canvasPaint = new Paint(Paint.DITHER_FLAG);
        }
        setWillNotDraw(false);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
    }

    @Override
    public	void surfaceChanged(SurfaceHolder holder, int format,	int width,	int height)	{
        canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        drawCanvas.drawRGB(255,255,255);
    }

    @Override
    public	void surfaceDestroyed(SurfaceHolder holder)	{
    //call when orientation changes

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path;
        for (int size = paths.size(), i = 0; i < size; i++) {
            path = paths.get(i);
            if (path != null) {
                this.drawPaint.setColor(colors[(i % 9)]);
                canvas.drawBitmap(canvasBitmap, 0f, 0f, null);
                canvas.drawPath(path, drawPaint);
            }
        }
    }

    //handles multitouch events
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int index = event.getActionIndex();
        int id = event.getPointerId(index);
        Path path;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                path.moveTo(event.getX(index), event.getY(index));
                paths.put(id, path);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                path = new Path();
                path.moveTo(event.getX(index), event.getY(index));
                paths.put(id, path);
                break;

            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    id = event.getPointerId(i);
                    path = paths.get(id);
                    if (path != null) path.lineTo(event.getX(i), event.getY(i));
                }
                break;

            case MotionEvent.ACTION_UP:
                path = paths.get(id);
                if (path != null) {
                    drawCanvas.drawPath(path, drawPaint);
                    paths.remove(id);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                path = paths.get(id);
                if (path != null) {
                    drawCanvas.drawPath(path, drawPaint);
                    paths.remove(id);
                }
                break;
        }
        invalidate();
        return true;
    }


}
