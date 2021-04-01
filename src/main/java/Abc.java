public class Abc {
    static volatile char currentChar = 'A';
    static Object monitor = new Object();

    public static void main(String[] args) {

        Abc w = new Abc();
        Thread t1 = new Thread(() -> {
            w.printA();
        });

        Thread t2 = new Thread(() -> {
            w.printB();
        });

        Thread t3 = new Thread(() -> {
            w.printC();
        });

        t1.start();
        t2.start();
        t3.start();

    }

    public void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar != 'A'){
                        monitor.wait();
                    }
                    System.out.print('A');
                    currentChar = 'B';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
        }

    }

    public void printB() {
        synchronized (monitor) {
            for (int i = 0; i < 5; i++) {
                try {
                    while (currentChar != 'B'){
                        monitor.wait();
                    }
                    System.out.print('B');
                    currentChar = 'C';
                    monitor.notifyAll();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public void printC() {
        synchronized (monitor) {
            for (int i = 0; i < 5; i++) {
                try {
                    while (currentChar != 'C') {
                        monitor.wait();
                    }
                    System.out.print('C');
                    currentChar = 'A';
                    monitor.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
