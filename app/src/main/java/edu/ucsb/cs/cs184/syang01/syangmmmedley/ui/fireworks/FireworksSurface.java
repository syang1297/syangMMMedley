package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.fireworks;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.DrawingThread;

import java.util.ArrayList;

public class FireworksSurface extends SurfaceView implements SurfaceHolder.Callback {
    public float xPos, yPos;
    public ArrayList<Ball> balls;
    public Bitmap bitmap;
    public Canvas canvas;
    public DisplayMetrics display;
    public DrawingThread drawingThread;
    public Resources resources;
    public SurfaceHolder surfaceHolder;
    public Paint paint;
    private boolean surfaceReady = false;
    private boolean drawingActive = false;


    public FireworksSurface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getHolder().addCallback(this);
    }

    public FireworksSurface(Context context) {
        super(context);
        getHolder().addCallback(this);
        setWillNotDraw(false);

    }

    public FireworksSurface(Context context, AttributeSet attr) {
        super(context, attr);
        resources = getResources();
        display = new DisplayMetrics();
        drawingThread = new DrawingThread(this, 90);

        ((Activity)context).getWindowManager()
                .getDefaultDisplay().getMetrics(display);

        balls = new ArrayList<Ball>();
        for (int i = 0; i < 10; i++) {
            Ball ball = new Ball(0, 0, 0, 0);
            balls.add(ball);
        }

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setWillNotDraw(false);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceReady = true;
        this.surfaceHolder = holder;
        if (drawingThread != null)
        {
            Log.v("testlog", "draw thread still active..");
            drawingActive = false;
//            try
//            {
                drawingThread.stop();
//            } catch (InterruptedException e)
//            { // do nothing
//            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        drawingThread.start();
        Canvas canvas = surfaceHolder.lockCanvas();
        if(canvas == null) {
            Log.v("testlog", "Canvas not available");
        }else{
            Log.v("testlog", "Canvas found, drawing");
            canvas.drawRGB(255, 255, 255);
        }

        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //call when orientation changes
        drawingThread.stop();
        surfaceHolder.getSurface().release();
        this.surfaceHolder = null;
        surfaceReady = false;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(Ball ball : balls){
            paint = new Paint();
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(Color.RED);
            canvas.drawCircle(ball.x, ball.y, 20, paint);
            ball.x = ball.x + ball.xChange;
            ball.y = ball.y + ball.yChange;
        }
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(display);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            xPos = event.getX();
            yPos = event.getY();

            for (Ball ball : balls) {
                ball.x = xPos;
                ball.y = yPos;
                ball.xChange = (float) Math.random() * 3;
                ball.yChange = (float) Math.random() * 3;
            }
            drawingThread.start();
        }
        return true;
    }

}
