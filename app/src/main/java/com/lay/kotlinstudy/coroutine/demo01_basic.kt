package com.lay.kotlinstudy.coroutine

//多线程编程基础

//相关概念梳理：并发和并行
//并发：是指多个任务同时进行，至于使用什么方式，并发是无所谓的。（单核心：时间片切换。多核心：多任务同时执行。两种方案都属于并发。但是第二种方案才是并行。）
//并行：是指在多核心的前提条件下，同时多核心执行多个任务。强调每时每刻都是同时执行的。是真正的"并行"。

//典型例子：
//你吃饭吃到一半，电话来了，你一直到吃完了以后才去接，这就说明你不支持并发也不支持并行。
//你吃饭吃到一半，电话来了，你停了下来接了电话，接完后继续吃饭，这说明你支持并发。
//你吃饭吃到一半，电话来了，你一边打电话一边吃饭，这说明你支持并行。
//并发的关键是你有处理多个任务的能力，不一定要同时。
//并行的关键是你有同时处理多个任务的能力。
//所以我认为它们最关键的点就是：是否是『同时』。


//相关概念梳理：同步和异步
//同步就是一系列指令（任务）顺序执行。（所以同步绝对不存在并发和并行。）
//异步就是很多指令（任务）发出之后不需要等执行结果返回，就继续执行下一条指令，等到对应的指令返回结果再继续执行。
//实现异步的手段通常就是——多线程编程。

//对于多核心CPU，多线程就会让不同的核心进行处理，此时就是并行。
//对于单核心CPU，多线程就会让不同的线程分配同一个CPU的时间片资源，此时就是并发。

//补充：对于JavaScript，这种语言是没有多线程的。但是通过函数回调这种编程方式依然可以做到异步编程。但是实质上他们还是在同一个线程中执行的。

//多线程 vs (单线程)异步编程
//结论：
// 1、单线程异步编程适合于（网络请求、数据库操作、文件系统读取）这些IO密集型的需求。
// 2、多线程编程适合于计算量密集型的需求（视频图像处理、科学计算）

//多线程编程不适合IO密集型编程的原因
//1-因为大部分时间都是在等待IO操作的结果。
//2-线程本身会占据内存（运行时数据区 - 线程独享区域【栈、本地方法区、PC寄存器】）
//3-线程切换会产生开销。
//4-线程之间的资源竞争问题（对于IO密集型需求，我们应该集中力量给执行IO操作的部分。所以多线程编程不适合。）

//补充：
//IR： 指令寄存器
//PC： 程序（指令）计数器
//IR保存一条 指令，用于发后续信号以便于进行真正的执行
//PC用于保存下一条指令的地址

//相关概念梳理：进程和线程
//程序：保存在硬盘中的菜谱。
//进程：程序在内存中执行的实例。CPU从硬盘拿到内存中。所有的进程都认为内存里面只有自己在使用。

//内存中的每个位置都有"地址"，方便访问。
//每个进程都有虚拟独立的地址空间，方便进程内访问。
//进程间通信（IPC）：不同的操作系统的方案不同

//进程包括①执行的程序、②程序计数器（通常是一个）、③一个执行流就是一个线程
//④多个线程需要多个程序计数器，线程独自运行。⑤线程有共享区域和独享区域。

//为什么我们有了进程还需要线程？
//回答：为了执行异步需求。例如：读取、渲染展示、IO流写入保存。线程是并发的最小单位。

//CPU时间片：线程执行的资源。收到操作系统调度算法的控制。（Linux:5ms到800ms）

//问题出现：既然线程是CPU并发的目标单位。那么一个进程的线程越多是不是这个进程占用CPU资源的时间就越多。
//回答：不是。第一，线程的确是CPU并发的执行单位，是占用CPU时间片的单位。但是CPU的线程数和核心数决定了电脑同时执行线程的上限。
// 而且系统对于线程优先级设置更加直观的影响线程对于CPU的占用。在默认情况下，CPU时间片通过调度算法进行控制。

//补充：核心数就是CPU的大脑数量。一个核心就是一个物理线程。但是intel的超线程技术可以把一个物理线程模拟成两个线程来使用。
// 六核心十二线程，就是物理核心是6，通过技术达到的模拟线程数是12。


//线程的状态
//①正在执行：运行状态（运行态指的就是进程实际占用 CPU 时间片运行时）
//②正在等待（CPU在执行其他线程）：就绪状态（就绪态指的是可运行，但因为其他进程正在运行而处于就绪状态）
//③阻塞状态，除非某种外部事件发生，否则进程不能运行。（外部硬盘读取、网络请求等IO操作）

//什么是阻塞IO和非阻塞IO？
//阻塞和非阻塞关注的是程序在等待调用结果（消息，返回值）时的状态。
//同步和异步关注的是消息通信机制

//什么是死锁？
//例子：A线程有1号资源。B线程有2号资源。但是A和B都需要1和2号资源。此时产生死锁。

//如何避免多线程出现脏读脏写？

//什么叫线程安全？
//定义：多个线程访问同一个对象时，
// 如果不用考虑这些线程在运行时环境下的调度和交替执行，也不需要进行额外的同步，或者在调用方进行任何其他操作，
// 调用这个对象的行为都可以获得正确的结果，那么这个对象就是线程安全的。

//一个类、对象、代码块如果不进行相关线程安全处理，就会因为线程和内存的交互问题（多个线程数据更新的同时同步给内存），百分百实现不了正确的结果。

//什么是线程同步机制？
fun main() {
    //Java 线程写法
    Thread(Runnable {
            run {

        }
    }).start()
}