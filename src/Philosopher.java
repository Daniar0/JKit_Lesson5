import java.util.concurrent.locks.Lock;

/**
 * Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * Вилки лежат на столе между каждой парой ближайших философов.
 * Каждый философ может либо есть, либо размышлять.
 * Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */


public class Philosopher extends Thread {
    private final  int id;
    private final  Lock leftFork;
    private final Lock rightFork;
    private static  final int EAT_TIMES=3;

    public Philosopher(final int id, final Lock leftFork, final Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;

    }
    private void think() {
        System.out.println("Философ" + id + " размышляет.");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
    private void eat() {
        System.out.println("Философ" + id + " размышляет.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        }
    }
    public void run() {
        int eatCount=0;
        while (eatCount<EAT_TIMES) {
            think();
            leftFork.lock();
            rightFork.lock();
            try {
                eat();
                eatCount++;
                System.out.println("Философ" + id +" поел " + eatCount + " раз.");
            }finally {
                rightFork.unlock();
                leftFork.unlock();
            }
        }
        System.out.println("Философ" + id + " закончил есть.");
    }

}
