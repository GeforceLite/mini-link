package com.minilink.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 单机异步Task队列
 *
 * @author : Barry.chen
 * @date : 2024-12-28
 **/
@Slf4j
public class AsyncExecutionQueueHelper {

    private static final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(Integer.MAX_VALUE);


    private AsyncExecutionQueueHelper() {
    }

    static {
        // TODO 使用线程池
        final Thread asyncExecutionThread = new Thread(() -> {
            while (true) {
                final Runnable task = queue.poll();
                if (task != null) {
                    try {
                        task.run();
                        log.info("AsyncExecutionQueue: task executed successfully, queue size:{}", queue.size());
                    } catch (final Exception e) {
                        log.error("AsyncExecutionQueue: task executed failed, queue size:{}, exception:{}",
                                queue.size(), ExceptionUtils.getStackTrace(e));
                    }
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (final InterruptedException ignored) {
                        // 任务为空，sleep，等待下一次处理
                    }
                }
            }
        });
        // 守护线程同步
        asyncExecutionThread.setDaemon(true);
        // 启动
        asyncExecutionThread.start();
    }

    /**
     * 外部调用，加入队列
     * @param task Task加入
     * @return
     */
    public static boolean offer(final Runnable task) {
        final boolean isSuccess = queue.offer(task);
        if (isSuccess)
            log.info("AsyncExecutionQueue:join queue successfully, queue size:{}", queue.size());
        return isSuccess;
    }

    /**
     * 积压Task数量取得，可以用Controller调用，手动监控
     *
     * @return 当前积压Task件数
     */
    public static int undoneCount() {
        return queue.size();
    }
}
