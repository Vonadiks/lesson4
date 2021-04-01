public class MultiFunc {
    Object pLock = new Object();
    Object sLock = new Object();

    public void print(String doc, int n) {
        synchronized (pLock) {
            System.out.println("Начало печати документа " + doc);
            for (int i = 0; i < n; i++) {
                System.out.println(i);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Конец печати");
        }
    }

    public void scan(String doc, int n, int source) {
        synchronized (sLock) {
            switch (source) {
                case 1 :
                    System.out.println("Начало сканирования в сеть документа " + doc);
                    for (int i = 0; i < n; i++) {
                        System.out.println(i);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Конец сканирования");
                    break;
                case 2 :
                    synchronized(pLock) {
                        System.out.println("Начало копирования документа " + doc);
                        for (int i = 0; i < n; i++) {
                            System.out.println(i);
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Конец копирования");
                        break;
                    }
            }
        }
    }

    public static void main(String[] args) {
        MultiFunc mfu = new MultiFunc();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 1", 7);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 2", 5);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("Doc 3", 4, 1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("Doc 4", 3, 2);
            }
        }).start();

    }


}
