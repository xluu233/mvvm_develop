package com.example.baselibrary.utils.other

import java.util.concurrent.*



/**
 * @ClassName ThreadPoolManager
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/24 11:43
 */
object ThreadPoolManager{

    private var threadPoolMap = hashMapOf<String, ThreadPoolExecutor>()

    /**
     * cpu核心数量
     * */
    private val CPU_COUNT = Runtime.getRuntime().availableProcessors()

    /**
     * 核心线程数
     * */
    private val CORE_POOL_SIZE:Int = CPU_COUNT/2

    /**
     * 最大线程数
     * */
    private val MAXIMUM_POOL_SIZE = CPU_COUNT

    /**
     * 线程活跃时间 秒，超时线程会被回收
     * */
    private val KEEP_ALIVE_TIME: Long = 0

    /**
     * 等待队列大小
     * */
    private val QUEUE_SIZE = 128


    /**
     *   @param tag 针对每个TAG 获取对应的线程池
     *   @param corePoolSize  线程池中核心线程的数量
     *   @param maximumPoolSize  线程池中最大线程数量
     *   @param keepAliveTime 非核心线程的超时时长，
     *   当系统中非核心线程闲置时间超过keepAliveTime之后，则会被回收
     *   如果ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true，则该参数也表示核心线程的超时时长
     *   @param unit 第三个参数的单位，有纳秒、微秒、毫秒、秒、分、时、天等
     *   @param queueSize 等待队列的长度 一般128 (参考 AsyncTask)
     *   workQueue 线程池中的任务队列，该队列主要用来存储已经被提交但是尚未执行的任务。存储在这里的任务是由ThreadPoolExecutor的execute方法提交来的。
     *   threadFactory  为线程池提供创建新线程的功能，这个我们一般使用默认即可
     *
     *   1.ArrayBlockingQueue：这个表示一个规定了大小的BlockingQueue，ArrayBlockingQueue的构造函数接受一个int类型的数据，
     *              该数据表示BlockingQueue的大小，存储在ArrayBlockingQueue中的元素按照FIFO（先进先出）的方式来进行存取。
                    ArrayBlockingQueue<Runnable>(QUEUE_SIZE),
     *   2.LinkedBlockingQueue：这个表示一个大小不确定的BlockingQueue，在LinkedBlockingQueue的构造方法中可以传
     *          一个int类型的数据，这样创建出来的LinkedBlockingQueue是有大小的，也可以不传，不传的话，
     *          LinkedBlockingQueue的大小就为Integer.MAX_VALUE
     * */
    private fun getThreadPool(tag: String): ThreadPoolExecutor {
        var threadPoolExecutor = threadPoolMap[tag]
        if (threadPoolExecutor == null) {
            threadPoolExecutor = ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                LinkedBlockingQueue(),
                Executors.defaultThreadFactory(),
            )
            //允许核心线程闲置超时时被回收
            //threadPoolExecutor.allowCoreThreadTimeOut(true)
            threadPoolMap[tag] = threadPoolExecutor
        }
        return threadPoolExecutor
    }

    /**
     *  @param tag 针对每个TAG 获取对应的线程池
     *  @param runnable 对应的 runnable 任务
     * */
    fun removeTask(tag: String, runnable: Runnable) {
        getThreadPool(tag).queue?.remove(runnable)
    }

    /**
     *  @param tag 针对每个TAG 获取对应的线程池
     *  @param runnable 对应的 runnable 任务
     * */
    fun addTask(tag: String, runnable: Runnable) {
        getThreadPool(tag).execute(runnable)
    }

    fun exitThreadPool(tag: String) {
        val threadPoolExecutor = threadPoolMap[tag]
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdownNow()
            threadPoolMap.remove(tag)
        }
    }
}