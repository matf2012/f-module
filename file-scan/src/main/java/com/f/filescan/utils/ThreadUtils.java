package com.f.filescan.utils;

import com.f.filescan.constant.FileScanConstant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class ThreadUtils {

    private static ExecutorService executors = executors = Executors.newFixedThreadPool(FileScanConstant.THREAD_COUNT);



    public static void execute(Job job){
        if(job != null){
            executors.execute(()->job.exec());
        }
    }

    public static void shutdown(){
        executors.shutdown();
    }

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static interface Job{
        void exec();
    }
}
