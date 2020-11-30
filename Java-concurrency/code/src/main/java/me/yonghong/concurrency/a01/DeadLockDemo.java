package me.yonghong.concurrency.a01;

public class DeadLockDemo {
    private static final String resource_a = "A";
    private static final String resource_b = "B";

    public static void main(String[] args) {
        deadLock();
    }

    public static void deadLock() {
        Thread threadA = new Thread(() -> {
            synchronized (resource_a) {
                System.out.println("get resource a");
                try {
                    Thread.sleep(3000);
                    synchronized (resource_b) {
                        System.out.println("get resource b");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadB = new Thread(() -> {
            synchronized (resource_b) {
                System.out.println("get resource b");
                synchronized (resource_a) {
                    System.out.println("get resource a");
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
