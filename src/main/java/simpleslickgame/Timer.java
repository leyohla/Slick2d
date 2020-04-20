package simpleslickgame;

public class Timer implements Runnable{

        private long startTime, runTime;


    public Timer(long runTime){//set runtime
            this.runTime = runTime;
        }

        public void run(){
            startTime = System.currentTimeMillis();//gets system's current time in milliseconds
            while(System.currentTimeMillis() - startTime < runTime ){ //loops until the current time - the start time is less then the specified run time.
                //Runs this
            }
    }
}

