学习笔记
# 用尽量多的方法，实现在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？
* ExitMainDemo01 使用 wait 和 notify
* ExitMainDemo02 使用 join
* ExitMainDemo03 使用 Lock 和 condition
* ExitMainDemo04 使用 Executor#submit 
* ExitMainDemo05 使用 CountDownLatch
* ExitMainDemo06 使用 CyclicBarrier
* ExitMainDemo07 使用 Phaser
* ExitMainDemo08 使用 Exchanger
* ExitMainDemo09 使用 LockSupport.park()
* ExitMainDemo10 使用 BlockingQueue
* ExitMainDemo11 使用 Semaphore

# 总结多线程
* Java 多线程并发.pdf