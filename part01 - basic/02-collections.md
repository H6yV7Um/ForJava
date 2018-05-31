## JAVA集合框架
![JAVA集合框架](https://camo.githubusercontent.com/3e7e32f74829d2adcd72143e356c39ccb6c995e8/687474703a2f2f696d672e626c6f672e6373646e2e6e65742f3230313430363238313434323035363235)

## Collections作用
&emsp;&emsp;Collections是一个工具类，提供了一系列的静态方法来辅助容器操作，包括对容器的搜索、排序、线程安全化等。

## ArrayList
* ArrayList序列化问题：<br/>
&emsp;&emsp;ArrayList中的`elementData`数组是定义为`transient`的，说明在ArrayList进行序列化的时候，elementData不能被序列化。这是因为，elementData的长度总是大于ArrayList中存储的数据个数的，例如，现在elementData的长度为100，但只存储了一个元素，因此，如果将elementData序列化，会序列化很多null。这就是为什么elementData被定义为transient。<br/>
&emsp;&emsp;ArrayList实现了`Serializable`接口，并实现了`readObject()`和`writeObject()`方法，这两个方法让类在进行序列化的时候，可以按照自定义的代码来执行动作。ArrayList中的writeObject()方法中，就是将elementData中存储的每一条真实数据写入到序列化流中。
* modCount的作用：<br/>
&emsp;&emsp;在ArrayList中，所有对元素个数进行操作的方法中都对`modCount`属性进行了操作。modCount是从`AbstractList`类中继承的属性，这个属性是为了防止在一个线程正在迭代的时候，另一个线程对数据进行增删操作。例如，线程A正在迭代时，线程B删除了列表中的最后一个元素，此时当线程A遍历到最后一个元素时，发现最后一个元素不存在，就会抛出`ArrayIndexOutOfBoundsException`异常。而有了modCount，则会抛出`ConcurrentModificationException`异常，这是JDK的逻辑性设计。
* ArrayList扩容方法：<br/>
&emsp;&emsp;ArrayList的扩容是在`grow()`方法中完成的。每次扩容都会扩充成当前长度的1.5倍，如果扩充之后还是无法达到目标长度，则直接将目标长度作为扩充后的长度。最终使用`Arrays.copyOf()`方法将原数组复制到新长度的数组中，然后在后续操作中添加新的元素。

## Vector
* Vector简介：<br/>
&emsp;&emsp;Vector中的方法与ArrayList中的方法大致相同，底层实现也大致相同，只是Vector中的方法都是用`synchronized`关键字修饰的，说明Vector是线程安全的。
* Vector扩容：<br/>
&emsp;&emsp;Vector的扩容方法也是在`grow()`方法中完成的。Vector中有一个`capacityIncrement`属性，这个属性可以通过Vector的构造方法传入，定义Vector每次扩容的长度。在grow()方法中，先判断capacityIncrement属性值是否等于0，如果不为0，则在当前Vector的长度基础上增加capacityIncrement长度，否则将长度扩展为原来的两倍。<br/>
&emsp;&emsp;因此，在扩容上，ArrayList是扩容1.5倍，Vector是扩容2倍。grow()方法的其他操作同ArrayList。

## Stack
&emsp;&emsp;Stack类继承自Vector类，是Vector类的子类。<br/>
&emsp;&emsp;Stack类中提供了`push()`、`pop()`、`peek()`、`search`等方法，这些方法的底层实现都是借用Vector类中的方法，说明Stack类也是线程安全的。

## Queue和Deque
* Queue：<br/>
队列接口，可以看作一种特殊的线性表，先进先出。<br/>
常用方法：`offer()`、`poll()`、`peek`等。<br/>
使用方法：`Queue<String> queue = new LinkedList<String>();`
* Deque：<br/>
双向队列接口，是Queue接口的一个子接口，该队列两端的元素既能入队，也能出队。<br/>
如果将Deque限制为只能从一端入队和出队，则可以实现栈的数据结构。当Deque作为栈使用时，提供了`push()`、`pop()`方法。<br/>
如果将Deque作为双向队列使用，提供了`offerFirst()`、`offerLast()`、`pollFirst()`、`pollLast()`等方法。<br/>
总之，Deque接口中提供了队列、双向队列、栈的基本操作。

## LinkedList
* LinkedList简介：<br/>
&emsp;&emsp;LinkedList是链表，即链式存储的线性表，其中的每个元素都是一个节点，节点之间通过指针连接。也就是说，链表中的节点存储位置是不连续的。<br/>
&emsp;&emsp;LinkedList在Java1.6版本中是环形的结构，但在java1.7版本中是直线型结构。
* LinkedList的Node<br/>
&emsp;&emsp;Node代表LinkedList中的每一个节点的数据结构。每个节点有三块区域：item数据区域、prev前驱节点指针、next后续节点指针。<br/>
* LinkedList源码解析<br/>
全局变量：size、first、last。<br/>
方法（底层）：linkFirst()、linkLast()、linkBefore()、unlink()、unlinkFirst()、unlinkLast()等。
* 常见面试题：<br/>
  * 获取单链表的倒数第n个元素：<br/>
  设置两个指针p1和p2，初始的时候p1和p2都在第一个节点处，先让p2走到第n个元素，即p2向前走(n-1)个元素，然后p1和p2一起向前走，每次都走一个元素，当判断p2的下一个元素为null时，p1指向的元素即为倒数第n个元素。<br/>
  * 获取单链表的中间元素：<br/>
  设置两个指针p1和p2，初始的时候p1和p2都在第一个节点处，p1每次走一个元素，p2每次走两个元素，当判断p2的下一个元素或下下个元素为null时，p1指向的元素即为中间元素。<br/>
  * 判断单链表是否有环：<br/>
  设置快慢指针p1和p2，初始的时候p1和p2都在第一个节点处，p1每次走一个元素，p2每次走两个元素，如果某一时刻p1和p2同时指向了同一个元素（这个元素点称为碰撞点），则说明链表有环；如果p2的下个指针或下下个指针为null，则说明链表没有环。<br/>
  * 获取单链表环的长度：<br/>
  记录下碰撞点的元素，然后设置一个指针从碰撞点继续走，每次走一个元素，当某个时刻走回碰撞点元素时，走过的元素个数就是环的长度。<br/>
  * 判断两个链表是否相交：<br/>
  分三种情况。第一种情况：两个链表都没有环，则获取到两个链表的最后一个元素，如果这两个元素相等，则两个链表相交；第二种情况：一个链表有环，一个链表无环，则这两个链表不可能相交；第三种情况：两个链表都没有环，先用快慢指针法找到第一个链表的碰撞点，再遍历第二个链表，如果发现碰撞点在第二个链表上，则说明两个链表相交。<br/>
  * 获取有环链表的环入口点：<br/>
  先通过快慢指针法找到链表的碰撞点，然后设置两个指针p1和p2，p1从链表头指针开始走，p2从碰撞点开始走，p1和p2每次都走一个节点，当p1和p2相遇时，相遇点就是链表的环入口点。<br/>
  * 寻找两个相交链表的第一个公共节点：<br/>
  分三种情况。第一种情况：两个链表都没有环，则先求出两个链表的长度l1和l2，设置两个指针p1和p2，p1在短链表的头节点位置，p2在长链表的(l2-l1)位置，即让两个链表剩余长度相等，然后同时移动p1和p2，每次都走一个元素，如果某一时刻p1和p2第一次指向同一个元素，则这个元素就是第一个公共点；第二种情况：两个链表都有环，且第一个公共点在环入口点之前，则以环的入口点为链表的最后一个元素，将问题转化为两个无环链表的问题；第三种情况：两个链表都有环，且第一个公共点在环中，则第一个公共点只可能在两个链表的环入口点处。<br/>

## HashMap
* HashMap简介：<br/>
HashMap底层通过数组和链表实现，通过计算key的hashCode来决定存储的位置。HashMap中维护了一个Node数组，数组中的每一个元素都是一个链表的头节点，如果不同的key映射成了相同的hashCode，就将value放到相应的链表中。
* HashMap的put()源码解析：<br/>
（1）通过hash()方法计算key的哈希码，然后将key的哈希码对(HashMap当前长度-1)取余，求出新元素应该添加到数组的哪个链表中；<br/>
（2）如果目标链表为null，则直接创建一个链表节点后添加；<br/>
（3）如果链表已经存在，则遍历链表，匹配key，并记录链表长度；<br/>
（4）在遍历过程中，如果发现已经存在该key的节点，则直接更换节点的value属性即可；如果遍历结束后仍未找到该key的节点，则创建一个新的节点存储key和value，并放到链表的开始位置；<br/>
（5）在遍历过程中，如果发现链表长度超过一个特定值（8），则将链表存储结构转化为树存储结构；<br/>
（6）最后，判断HashMap中存储的元素个数是否超过threshold值，如果超过了，则重新规划分配空间。<br/>
* 哈希码计算方法：<br/>
HashMap的哈希码计算在hash()方法中完成。首先通过key的hashCode()方法获取到key的哈希码，然后将这个值与它右移16位后的值做异或操作（即将key的hashCode的高16位与低16位进行异或操作），得到最终的哈希码。<br/>
这样做的原因：让高位和低位都能参与到最后结果的计算中。<br/>
* HashMap扩容机制：<br/>
（1）将HashMap中的容量扩大到原来的两倍，将threshold属性值也扩大到原来的两倍；<br/>
（2）将原来的所有节点都重新散列，散列到新的数组中。<br/>
* HashMap树形存储结构：<br/>
如果HashMap中的元素总个数大于某个值（默认是64），且某个链表的长度超过某个值（默认是8），则会将链表结构转化为树形存储结构；当链表中的元素数量小于某个值（默认是6），则会将树形结构重新还原成链表结构。这里的树形结构是红黑树。<br/>
红黑树是一种便于查找的二叉树结构，其查找的时间复杂度为O(log<sub>2</sub><sup>n</sup>)。树中的元素按哈希码大小排列，方便查找。
* 总结HashMap流程图：<br/>
![](https://user-gold-cdn.xitu.io/2016/11/29/f4b4ce0080649808815e6979d55b2915)

## HashTable
HashTable与HashMap的区别与联系：<br/>
（1）继承关系不同：HashMap和HashTable虽然都实现了Map接口，但HashMap继承自AbstractMap抽象类，HashTable继承自Dictionary抽象类。<br/>
（2）线程安全性不同：HashTable中的方法都是synchronized修饰的，即HashTable是线程安全的，而HashMap是线程不安全的。<br/>
（3）是否允许NULL：HashMap中key和value都可以是null，且key最多有一个为null，value可以有多个为null；HashTable中key和value都不能为null，否则编译可以通过，但运行时报错NullPointerException。<br/>

## HashMap线程安全解决方案
* 为什么HashMap是线程不安全的：<br/>
HashMap在多线程并发执行put()方法操作时可能会导致HashMap的Node链表形成环形数据结构，导致Node的next指针永远不为空，导致死循环。
* HashTable解决线程安全问题：<br/>
HashTable中的方法与HashMap大致相同，它保证线程安全的方式就是在方法上添加了`synchronized`关键字。<br/>
当一个线程访问HashTable的synchronized方法时，其他访问的线程会被阻塞，尤其是数据量很大时，每次存取都会阻塞很长时间，因此这种方法效率低下。<br/>
HashTable已经不常用了。
* ConcurrentHashMap解决线程安全问题：<br/>
ConcurrentHashMap中使用了`CAS + synchronized`方法，保证并发更新的安全。<br/>
CAS（Compare and Swap），是一种乐观锁，即不加锁去完成操作，如果因为冲突失败就重试，直到成功。CAS由CPU硬件实现，所以执行相当快。<br/>
CAS有三个操作参数：内存地址、期望值、要修改的新值，当期望值和内存中的值比较不相等时，表示内存中的值已经被别的线程修改过，失败返回；当相等的时候，将内存中的值改为新值，并返回成功。
* Collections.synchronizedMap()解决线程安全问题：<br/>
使用Collections.synchronizedMap()方法返回了Collections类的一个内部类SynchronizedMap类对象，这个类中的方法中都有synchronized代码块来保证Map的操作是线程安全的。

## TreeMap
TreeMap与HashMap的区别与联系：<br/>
（1）HashMap基于数组存储键值对，TreeMap基于红黑二叉树存储键值对；<br/>
（2）HashMap线程不安全，TreeMap也是线程不安全；<br/>
（3）HashMap允许null，TreeMap不允许null；<br/>
（4）存入HashMap中的数据不需要实现任何接口，但存储TreeMap中的数据需要实现Comparable接口；<br/>
（5）在向TreeMap中存入数据后会自动排序，且迭代时会按顺序迭代，HashMap不会。<br/>

## HashSet
HashMap与HashSet的区别与联系：<br/>
（1）HashSet里面封装了一个HashMap，HashSet中的方法都是调用HashMap中的方法执行的；<br/>
（2）HashMap实现了Map接口，HashSet实现了Set接口；<br/>
（3）HashMap中存储键值对，HashSet中仅仅存储对象（其底层调用的是HashMap的put()方法，将要存储的对象作为key，将一个固定的Object对象作为value存储的）；<br/>
（4）HashMap和HashSet中都可以存储null；<br/>
（5）HashSet中存储的对象需要重写equals()和hashCode()方法。<br/>

## SparseArray
SparseArray的特点：<br/>
（1）SparseArray是android.util包中的集合类，用于存储key-value对，但key值只能是int类型的；<br/>
（2）SparseArray中使用两个数组，分别存储key和value：`int[] mKeys; Object[] mValues`；<br/>
（3）SparseArray中的值是按照key从小到大的顺序排列的；<br/>
（4）SparseArray查找使用的是二分查找；添加、删除数据前也是先通过二分查找找到元素位置；<br/>
（5）SparseArray中适合存储数据量较少的数据，否则性能会下降。

## BlockingQueue
BlockingQueue位于java.util.concurrent包下，即阻塞队列，是一种线程安全的集合API。它被阻塞的情况有两种：当队列满了的时候进行入队列会被阻塞；当队列空了的时候进行出队列会被阻塞。因此，当一个线程试图对一个已经满了的队列进行入队列操作时，它将会被阻塞，除非有另一个线程做了出队列操作；同样，当一个线程试图对一个空队列进行出队列操作时，它将会被阻塞，除非有另一个线程进行了入队列操作。BlockingQueue中不可以存入NULL值，否则会报错：NullPointerException。