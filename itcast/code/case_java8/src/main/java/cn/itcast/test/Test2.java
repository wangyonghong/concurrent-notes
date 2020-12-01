package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test2")
public class Test2 {

    public static void main(String[] args) {
        Runnable r = () -> {
            log.debug("running");
        };

        Thread t = new Thread(r, "t2");

        t.start();

        // 创建任务对象
        FutureTask<Integer> task3 = new FutureTask<>(() -> {
            log.debug("hello");
            return 100;
        });

        // 参数1 是任务对象; 参数2 是线程名字
        new Thread(task3, "t3").start();

        // 主线程阻塞，同步等待 task 执行完毕的结果
        Integer result = null;
        try {
            result = task3.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        log.debug("结果是:{}", result);
    }
}
