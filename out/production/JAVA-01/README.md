学习笔记
# Lesson 03 必做题
1. 使用 GCLogAnalysis.java 演练一边串行/并行/CMS/G1 的案例。
2. 使用压测工具，演练 gateway-server-0.0.1-SNAPSHOT.jar 事例
3. 根据自己对 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结。

## 串行 GC（Serial GC）
Minor GC
* GC 表示一次小型 GC（Minor GC）
* Allocation failure 触发 GC 的原因。
* DefNew 垃圾回收器名称。
* 139776K->17472K 垃圾收集之前->之后年轻代的使用量。
* (157248K) 年轻代的总空间
* 139776K->43804K 垃圾收集之前->之后对内存的使用情况。
* (506816K) 可用堆内存总空间大小。
* 0.0646895 GC 事件持续的时间，以秒为单位。

Full GC
* Tenured 用于清理老年代空间的垃圾收集器名称。
* 336939K->268160K 垃圾收集之前和之后老年代的使用量。
* (349568K) 老年代的总空间

## 并行 GC（Parallel GC）
* PSYoungGen 收集器名称：并行的标记复制（mark-copy），全线暂停（STW）垃圾收集器
* Full GC 表明本次清理的是年轻代和老年代。

## CMS（Concurrent Mark and Sweep）
* ParNew 垃圾收集器的名称。表示年轻代使用的是 标记-复制（mark-copy），全线暂停（STW）垃圾收集器
* CMS Initial Mark 初始标记 STW，标记所有的 GC Root
* CMS-concurrent-mark 并发标记 遍历老年代并标记所有存活的对象。
* CMS-concurrent-preclean 并行预清理 标记脏区
* CMS-concurrent-abortable-preclean 病情可取消的预清理 继续做更多的事情
* CMS Final Remark 最终标记 STW
* CMS-concurrent-sweep 并发清理
* CMS-concurrent-reset 并发重置 为下次 GC 作准备

## G1（Garbage First）
* 与 CMS 类似


## 总结
* 先说，堆内存设置在 4G 条件下，并行 GC 和 CMS 从日志中看，性能相当，在 这种场景下，并行 GC，性能略优
* 在上述条件下，G1 GC 拥有较低的延迟，相对的垃圾回收测试会变多。
* 在堆内存设置在 512M 条件下，并行 GC 比 串行 GC 垃圾收集频率高，延迟小。
* 上述条件下，CMS 比 并行 GC 发生 full GC 的频率少，并且延迟小。
* 上述条件下，G1 GC 比 CMS 收集操作更加频繁，见 g1.tmp gc 工作有 175 条之多，而 CMS 仅有 75 条

# Lesson 04 必做题
1. 写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801
