package com.angcyo.oaschool.components;

import com.angcyo.oaschool.mode.TaskRunnable;
import com.angcyo.oaschool.util.OkioUtil;
import com.angcyo.oaschool.util.Util;

import java.util.Vector;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class RWorkThread extends Thread {

    private Vector<TaskRunnable> workTask;

    public RWorkThread() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);
        workTask = new Vector<>();
        OkioUtil.writeToFile("工作线程已启动" + Util.callMethodAndLine());
    }

    public void addTask(TaskRunnable task) {
        workTask.add(task);
//        OkioUtil.writeToFile("收到任务,取消等待" + Util.callMethodAndLine());
        synchronized (RWorkThread.this) {//如果没有正在等待的线程,将不会往下执行
            RWorkThread.this.notify();
        }
    }

    private TaskRunnable getTask() {
        if (workTask.size() > 0) {
//            OkioUtil.writeToFile("获取到任务" + Util.callMethodAndLine());
            return workTask.remove(0);
        }
        return null;
    }

    @Override
    public void run() {
        TaskRunnable task;
        synchronized (RWorkThread.this) {
            while (!isInterrupted()) {
                task = getTask();//取到任务
                if (task == null) {
                    try {
//                        Thread.sleep(1000);
                        OkioUtil.writeToFile("无任务,等待中" + Util.callMethodAndLine());
                        RWorkThread.this.wait();//任务为空,等待添加任务
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    OkioUtil.writeToFile("有任务,执行中" + Util.callMethodAndLine());
                    try {
                        task.run();//否则执行任务
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
