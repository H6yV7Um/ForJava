## JAVA八种基本数据类型
&emsp;&emsp;JAVA中的八中基本数据类型包括：byte、char、short、int、float、long、double、boolean，它们所占的位数和字节数如下表所示。

数据类型|所占位数|字节数
:-----:|:-----:|:---:
byte |8位|1字节
char |16位|2字节
short|16位|2字节
int  |32位|4字节
float|32位|4字节
long |64位|8字节
double|64位|8字节

&emsp;&emsp;关于boolean数据类型占几个字节，众说纷纭。但在JVM规范中，boolean变量作为int处理，也就是4个字节；boolean数组当作byte数组处理。

## JAVA四种引用类型
* 强引用（StrongReference）<br/>
使用`new`关键字创建的对象都是强引用。当内存空间不足时，JAVA虚拟机宁愿抛出OOM错误，也不会靠回收具有强引用的对象来解决内存不足的问题。通过`obj = null`来协助GC。
* 软引用（SoftReference）<br/>
当内存不足时，会回收具有软引用的对象。软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，JAVA虚拟机就会把这个软引用加入到与之关联的引用队列中。
* 弱引用（WeakReference）<br/>
在垃圾回收器扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会将其回收。弱引用也可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收器回收，JAVA虚拟机就会把这个弱引用加入到与之关联的引用队列中。
* 虚引用（PhantomReference）<br/>
虚引用主要用来跟踪对象被GC的活动。虚引用不会决定对象的生命周期，用虚引用引用的对象在任何时候都可能被GC。虚引用必须和引用队列（ReferenceQueue）联合使用，当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中。
* 总结<br/>
四种引用的级别：强引用 > 软引用 > 弱引用 > 虚引用<br/>

引用类型|被GC的时间|生存时间|用途
:-----:|:-------:|:-----:|:--:
强引用|从来不会|JVM停止运行时终止|对象的一般状态
软引用|在内存不足时|内存不足时终止|对象缓存
弱引用|GC时|GC运行后终止|对象缓存
虚引用|unknown|unknown|unknown

## Object中的公共方法
* protected - clone()：对象浅拷贝
* protected - finalize()：GC时回调的方法
* public - equals()：判断两个对象是否相等
* public - hashCode()：equals()方法的协助方法，计算哈希码
* public - toString()：将对象转化成字符串
* public - getClass()：反射时通过这个方法获取到Class对象
* public - wait()：多线程环境下让当前线程等待
* public - notify()：多线程环境下通知当前线程取消等待
* public - notifyAll()：多线程环境下通知所有线程取消等待

## String源码解析
* String类实现了Serializable、CharSequence、Comparable三个接口，用final关键字修饰，表示String类可以被序列化、可以用来比较、不能被继承
* String类中有一个final修饰的char数组，用来存储String中的所有字符，说明String中的内容不可以改变，且String是线程安全的
* String对象之间使用"+"来拼接，会产生新的字符串；使用concat()方法拼接，也会产生新的字符串
* `String s1 = "abc";`和`String s2 = new String("abc");`创建的s1和s2字符串不相等，因为，s1的创建方式是直接去方法区常量池中寻找字符串，并将栈内指针直接指向常量池中的变量；s2的创建方式是在堆中创建字符串，将栈内的指针指向堆中的变量
* `String s1 = "abc";`，创建了0个或一个对象；`String s2 = new String("abc");`，创建了1个或2个对象

## "=="、equals()和compareTo()的区别；hashCode的作用
* "=="：简单数据类型的数据用于直接比较，复杂数据类型的数据用于比较内存地址是否相同
* equals()：比较复杂数据类型数据是否相等，equals()方法可以自定义，通常与hashCode()方法同时出现，判断结果有两种情况：true表示相等，false表示不相等
* compareTo()：比较两个对象的大小，需要实现Comparable接口，判断结果有三种：-1表示较小，0表示相等，1表示较大
* hashCode的作用：hashCode用于返回该对象的哈希码值，通常用于进行比较，或为了便于查找，如equals()方法中就常常用到hashCode()方法返回的哈希码进行比较；HashMap、HashSet等集合类中通常用hashCode进行查询

## Overload与Override的区别
* Override：重写，一般用于接口或继承关系中。一个类从接口或抽象类中继承或实现了一个方法，并编写了自己的实现，就叫做重写
* Overload：重载，一般用于同一个类中的多个方法中。多个方法的方法名相同，但参数个数不同、参数类型不同、参数顺序不同，就叫做重载

## try/catch/finally + return
* try中有return，finally中有return，则先执行try中的代码，然后保留try中return的值，再去执行finally中的代码，最终返回finally中return的值，而不返回try中return的值
* try中有return，finally中没有return，则先执行try中的代码，然后保留try中return的值，再去执行finally中的代码，最后再返回try中return的值

## Socket
* 什么是Socket：<br/>
  Socket，套接字，包含一个IP地址和一个端口号。Socket是网络通信的一种方式，使用Socket可以实现分布在不同主机的相关进程之间的数据交换。总之，Socket是一个对TCP/IP协议进行了封装的API。
* Socket客户端：<br/>
  ```java
  Socket socket = null;
  OutputStream os = null;
  InputStream is = null;
  try {
      socket = new Socket("192.168.1.100", 8888);
      os = socket.getOutputStream();
      os.write(msg.getBytes());
      socket.shutdownOutput();
      is = socket.getInputStream();
      byte[] b = new byte[1024];
      int len = -1;
      StringBuffer sb = new StringBuffer();
      while ((len = input.read(b) != -1)) {
          sb.append(new String(b, 0, len, Charset.forName("gbk")));
      }
  } catch (Exception e) {
      e.printStackTrace();
  } finally {
      try {
          if (socket != null) socket.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  ```
* Socket服务端：<br/>
  ```java
  ServerSocket server = null;
  Socket client = null;
  try {
      server = new ServerSocket(8888);
      while (true) {
          client = server.accept();
          BufferedInputStream bis = new BufferedInputStream(client.getInputStream());
          byte[] b = new byte[1024];
          int len = -1;
          while ((len = bis.read(b) != -1)) {
              System.out.println(new String(b, 0, len, "UTF-8"));
          }
          client.shutdownInput();
          OutputStream os = client.getOutputStream();
          os.write("收到".getBytes());
          bis.close();
          client.close();
      }
  } catch (Exception e) {
      e.printStackTrace();
  }
  ```
* Socket、HttpClient和HttpUrlConnection：<br/>
HttpClient和HttpUrlConnection统称Android中的Http通信。Http通信是基于“请求-访问”方式的，属于应用层，解决的是如何包装数据的问题；Socket通信是基于TCP/IP协议的，属于传输层，解决的是如何传输数据的问题。Http通信底层是通过Socket实现的。（HttpClient在Android6.0版本后被移除，使用时需手动添加）

## Error与Exception的区别、常见Exception
&emsp;&emsp;Error是指不可以预先预测到的问题，如OutOfMemory、StackOverFlow等，当遇到这类问题时，我们无法在程序中编写相应的补救措施；而Exception是指可以预先预测到的问题，如IOException、NullPointerException等，当遇到这类问题时，我们可以在程序中通过try/catch/finally来捕获它们，并提供补救措施。<br/>
&emsp;&emsp;常见的Exception有IOException、SQLException和RuntimeException，其中，RuntimeException中又包括ArithmeticException、ArrayIndexOutOfBoundsException、NullPointerException等。

## Java多线程下载
* 多线程下载原理：<br/>
开启多个线程下载文件，每个线程负责文件的一部分。
* 多线程下载实现：<br/>
（1）先通过HttpUrlConnection对象的getContentLength()方法获取到文件的总长度，然后除以线程数得到每个线程需要下载的大小；<br/>
（2）创建一个RandomAccessFile文件，通过setLength()方法设置文件的占位大小；<br/>
（3）为请求添加头部信息，通过RandomAccessFile的seek()方法定位到应该开始下载的位置，然后去请求网络，如果返回code=206（请求部分数据成功），则写入到文件中。

## Java多线程使用
* Java线程创建方式：<br/>
（1）继承Thread类，重写run()方法，外界调用这个类对象的start()方法即可；<br/>
（2）实现Runnable接口，实现run()方法；创建一个Thread类包裹这个Runnable类，调用Thread类对象的start()方法。<br/>
（3）两种方法的区别：实现Runnable接口的方法比继承Thread类的方法更灵活，代码更健壮，有利于代码复用。
* start()和run()的区别：<br/>
如果调用run()方法，则会作为一个普通方法来执行，没有多线程的效果；如果调用start()方法，则会启动线程，有多线程的效果。

## Java线程同步方式
* synchronized方法：<br/>
&emsp;&emsp;使用synchronized关键字来修饰方法，用以锁住整个方法，保证这个方法在同一时间只能被一个线程调用；如果使用synchronized关键字修饰静态方法，则将锁住整个类。
* synchronized代码块：<br/>
&emsp;&emsp;使用synchronized关键字来修饰一块代码块，将代码块中的代码锁住。如果没有必要锁住整个类或整个代码，则可以考虑通过synchronized代码块的方法，锁住关键代码即可。synchronized代码块锁住的是一个对象。
* wait()、notify()和notifyAll()：<br/>
&emsp;&emsp;这三个方法最终调用的都是JVM级别的native方法。当调用wait()方法时，会使持有该对象的线程将该对象的控制权交出去，然后处于等待状态；当调用notify()方法时，会通知某个正在等待该对象控制权的线程可以继续运行；当调用notifyAll()方法时，会通知所有等待这个对象控制权的线程继续运行。
* 特殊域变量volatile：<br/>
&emsp;&emsp;Java内存中有一块主内存，其他线程都是从主内存中拷贝信息到自己的内存中进行缓存，因此容易导致多线程操作同一个变量时得到的结果不一致的现象。volatile关键字强制阻止线程从主内存中拷贝数据，线程只能操作主内存中的数据。当volatile对象变化之后，会在所有线程中进行同步。volatile在存储数据的时候非常高效，但它会对性能有所损耗。<br/>
&emsp;&emsp;volatile与synchronized关键字的区别有两点：第一，synchronized关键字会对对象加锁，同一时间只能有一个线程访问这个对象，而volatile不锁对象，只是让所有线程操作同一段内存中的数据；第二，synchronized可以对类、方法或变量使用，而volatile只能对变量使用。
* 重入锁：<br/>
&emsp;&emsp;重入锁Lock是一个接口，其实现类是ReentrantLock，其中有lock()和unlock()方法，用于上锁和解锁，它实现的功能与synchronized关键字的功能相似，与synchronized关键字有三点不同：第一，用法上，synchronized只需要在要上锁的元素上标记即可，而Lock需要通过lock()和unlock()方法来实现；第二，性能上，synchronized是托管给JVM来执行，是一个非常重量级的操作，而Lock是Java代码，更加高效；第三，机制上，synchronized是悲观锁，而Lock是乐观锁。
* ThreadLocal：<br/>
&emsp;&emsp;ThreadLocal使每个使用变量的线程都获取该变量的副本，副本之间相互独立，这样每一个线程都可以随意修改自己的变量副本，而不会对其他线程产生影响。
* 阻塞队列：<br/>
&emsp;&emsp;BlockingQueue阻塞队列。BlockingQueue位于java.util.concurrent包下，是一种线程安全的集合API。它被阻塞的情况有两种：当队列满了的时候进行入队列会被阻塞；当队列空了的时候进行出队列会被阻塞。因此，当一个线程试图对一个已经满了的队列进行入队列操作时，它将会被阻塞，除非有另一个线程做了出队列操作；同样，当一个线程试图对一个空队列进行出队列操作时，它将会被阻塞，除非有另一个线程进行了入队列操作。
* 一些原子操作的API：<br/>
&emsp;&emsp;如：如果只需要对Integer类型的变量进行线程同步，则可以使用AtomicInteger。

## Java线程池
* 线程池的好处：<br/>
（1）降低系统的资源消耗，通过重用线程来降低线程创建和销毁所造成的消耗；<br/>
（2）提高响应速度，任务可以不经过线程的创建而立即执行；<br/>
（3）提高线程的可管理性，对线程进行统一的分配。 
* ThreadPoolExecutor：<br/>
ThreadPoolExecutor是一种常见的线程池，它在创建的时候设置了最大线程数、工作线程数等，并通过一个BlockingQueue阻塞队列来进行线程的调度。
* 线程池的工作流程：<br/>
（1）判断工作队列是否已满，如果还没满则创建一个新线程并添加到工作队列中执行任务；<br/>
（2）如果工作队列满了，则判断整个线程池是否已满，如果还没满则创建一个新线程到等待队列中等待执行；<br/>
（3）如果整个线程池都满了，则交给饱和策略Handler来执行，如抛出异常、忽略任务等。<br/>