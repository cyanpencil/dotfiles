package gapp.ulg.test.ThreadTests;

/**
 * Created by Edoardo on 22/07/2016.
 */

public class ObjectDemo{

    public class MoveCiuser{
        String move = null;

        String getMove(){
            return this.move;
        }

        void calculateMove(){
            synchronized (this){

                Long dicksInLucasAsshole = new Long(0);
                for(int blackdick = 0; blackdick < 10_000_000; blackdick++) dicksInLucasAsshole++;
                this.move = "Move";
                this.notifyAll();
            }
        }
    }

    MoveCiuser ChooseDick;

    public ObjectDemo() {
        this.ChooseDick = new MoveCiuser();
    }

    public String getDick(){
        synchronized (ChooseDick){
            while(this.ChooseDick.getMove() == null){
                try {
                    this.ChooseDick.wait();
                } catch(InterruptedException e){}
            }
            return ChooseDick.getMove();
        }
    }


    public static void main(String[] args) {
        final ObjectDemo dickUser = new ObjectDemo();
        final MoveCiuser dickFactory = dickUser.ChooseDick;

        Runnable runA = new Runnable() {

            public void run() {
                try {
                    dickUser.getDick();
                } catch (Exception x) {
                }
            }
        };

        Runnable runB = new Runnable() {

            // run adds an element in the list and starts the loop
            public void run() {
                dickFactory.calculateMove();
            }
        };

        try {
            Thread threadA1 = new Thread(() -> {
                try {
                    dickUser.getDick();
                } catch (Exception x) {
                }
            });
            threadA1.start();


            Thread.sleep(1000);

            Thread threadA2 = new Thread(runA, "B");
            threadA2.start();




            Thread.sleep(1000);

            Thread threadB = new Thread(runB, "C");
            threadB.start();




            Thread.sleep(1000);



            threadA1.interrupt();
            threadA2.interrupt();
        } catch (InterruptedException x) {
        }
    }
}
