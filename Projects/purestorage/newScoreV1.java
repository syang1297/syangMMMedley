public class newScoreV1 {
    public static int compute(int input){
        
    }

    public int foundSeven(int input){
        int score = 0;
        while(input >= 1){
            int current = input%10;
            if(current == 7){
                score++;
            }
            input /= 10;
        }
        return score;
    }

    public int consecFives(int input){
        int score = 0;
        // int x = 3;
        String in = Integer.toString(input);
        for(int i = 0; i < in.length(); i++){
            if(in.charAt(i) == '5' && i!=0 && in.charAt(i-1) == '5'){
                score+=3;
            }
        //         score += x;
        //         if(x==3){
        //             x += 3;
        //         }
        //     }
        }
        return score;
    }

    public int consec(int input){
        int score= 0;
        String in = Integer.toString(input);
        for(int i = 0; i < in.length(); i++){
            int len = 1;
            while((i+1 < in.length()) && (Character.getNumericValue(in.charAt(i))+1) == Character.getNumericValue(in.charAt(i+1)){
                len++;
                i++;
            }
            score += len*len;
        }
        return score;
    }

    public int mult3(int input){
        int score = 0;
        if(input%3==0){
            score+=2;
        }
        return score;
    }

    public int evenDig(int input){
        int score = 0;
        while(input > 0){
            int i = input % 10;
            if(i % 2 == 0){
                score+=4;
            }
            input = input / 10;
        }
        return score;
    }





}