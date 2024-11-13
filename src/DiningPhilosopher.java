import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosopher {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        Lock[] forks = new ReentrantLock[numPhilosophers];
        Philosopher[] philosophers=new Philosopher[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new ReentrantLock();

        }
        for (int i = 0; i < numPhilosophers; i++) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i+1)%numPhilosophers];
            if(i==numPhilosophers-1){
                philosophers[i]=new Philosopher(i,rightFork,leftFork);
            }else {
                philosophers[i]=new Philosopher(i,leftFork,rightFork);
            }
        }
        for(Philosopher philosopher: philosophers) {
            philosopher.start();
        }
        for(Philosopher philosopher: philosophers) {
            try {
                philosopher.join();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Все философы поели три раза.");
    }
}
