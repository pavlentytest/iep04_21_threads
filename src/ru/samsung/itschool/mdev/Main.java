package ru.samsung.itschool.mdev;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Процесс (программа)
        // Поток
        // Синхронизация
        //new MyThread("+").start();  // 1
        //new MyThread("-").start();  // 2
        MyThread myThread1 = new MyThread("+");
        myThread1.start();
        MyThread myThread2 = new MyThread("-");
        myThread2.start();
        MyThread.sleep(4500);
        myThread1.flag = false;
        myThread1.join(); //ждет завершения потока
        test("1Stopped!");

        // [+][-][+][-][+][-]
    }

    public static Object key = new Object();

    public static void test(String message) {

       // synchronized (key) {
            try {
                System.out.print("[");
                Thread.sleep(2000);
                System.out.print(message);
                Thread.sleep(2000);
                System.out.print("]");
          //      key.notify(); // возобновляет работу потока, наход. в режиме ожидания
           //     key.wait(); // выставляет потоку режим ожидания
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
      //  }


    }

}

class MyThread extends Thread {
    private String mess;
    public boolean flag = true;
    MyThread(String mess) {
        this.mess = mess;
    }
    @Override
    public void run() {
       while(flag) {
           Main.test(this.mess);
       }
    }
}

