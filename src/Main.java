import java.util.concurrent.Semaphore;

class Table
{
    private Semaphore semaphore;

    public Table(int places ) {
        this.semaphore = new Semaphore(places);
    }
    public void eatingLunch(String name)
    {

        try {
            System.out.println(name+" Намагається пообідати");
            semaphore.acquire();
            System.out.println(name+" обідає");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println(name+" пообідав");
            semaphore.release();
        }
    }
}

class Philosopher implements Runnable
{
    Table table;
    private String name;

    public Philosopher(Table table, String name) {
        this.table = table;
        this.name = name;
    }

    @Override
    public void run() {
        table.eatingLunch(name);
    }
}


public class Main {
    public static void main(String[] args) {

        int places=3;
        Table table=new Table(places);
        Philosopher car1=new Philosopher(table,"Aristotle");
        Philosopher car2=new Philosopher(table,"Al-Kindi");
        Philosopher car3=new Philosopher(table,"Confucius");
        Philosopher car4=new Philosopher(table," Kyoto");
        Philosopher car5=new Philosopher(table,"Socrate");

        Thread thread1=new Thread(car1);
        Thread thread2=new Thread(car2);
        Thread thread3=new Thread(car3);
        Thread thread4=new Thread(car4);
        Thread thread5=new Thread(car5);


        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread3.setDaemon(true);
        thread4.setDaemon(true);
        thread5.setDaemon(true);


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();


        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}