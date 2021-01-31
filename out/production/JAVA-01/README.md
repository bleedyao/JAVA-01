学习笔记

# 网关作业
* Gateway 实现了 网关转发，2.0 请求过滤器和响应过滤器，3.0 路由的简单实现

# Join 代码分析
* oo 是主线程和子线程的锁对象，主线程 oo.wait
  * 结果是主线程 waited 状态
  
* oo 是主线程和子线程的锁对象，主线程 thread1.join()
  * 结果是程序发生死锁，主线程 waiting，子线程，blocking 状态，抢不到 oo 锁
  * 实际上这里存在两把锁，join 是一个 synchronized 方法，它的锁对象是线程对象
  * wait 的特性可以完全体现出来：wait 会挂起调用的线程（这里是主线程），然后将前面对象的锁信息释放掉。
  * 这个 case 中，oo 始终没有释放掉锁信息，而主线程又进入了 waiting 状态，等待唤醒，所以子线程不能获得锁，子线程一直等待。
  * 这个 case 有悖于以往的观念，wait 会释放锁对象，不影响其他线程执行，但实际上这里的锁对象只使用与调用的那个对象的锁。
    
* thread1 是主线程锁对象，oo 是子线程锁对象，主线程调用 thread1.join()
  * 结果是一开始乱序执行，执行 thread1.join 时，主线程挂起进入 waiting 状态，然后，thread1 运行结束，主线程继续执行
  * 验证 main 进入 waiting 状态
  * 如果调用 thread1.join(1000) 加上延时时间，结果没有变化，原因是，join 方法虽然本质是调用线程的 wait 方法，但是 join 方法会在调用 wait 方法之前判断，线程是否存活(isAlive)，只要线程还在运行就是 true，线程结束了就是 false。
    
# wait 和 sleep
* wait 是 Object 的方法，sleep 是 Thread 的方法
* wait 和 sleep 延时时间大于 0 时，线程会进入 timed_waiting 状态
* wait 释放锁，sleep 不释放锁。
* wait 延时时间为 0 时，锁对象所在线程进入 waiting 状态