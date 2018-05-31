## 外观模式（Facade）
&emsp;&emsp;外观模式将多个操作封装起来，对外提供一个接口，外界通过访问这个接口来实现与内部的多个操作的交互。

## 单例模式（Singleton）
&emsp;&emsp;单例模式是一种对象创建模式，它用于产生一个对象的具体实例，它可以确保系统中一个类只产生一个实例。单例模式可以省略创建对象所花费的时间，且由于new操作的次数减少，因而对系统内存的使用频率也会降低，减少GC压力。
* 饿汉方法创建单例对象：<br/>
  ```java
  public class Singleton {
      private static final Singleton instance = new Singleton();
      private Singleton() {}
      public static Singleton getInstance() {
          return instance;
      }
  }
  ```
  饿汉模式的不足之处是：无法对instance实例做延时加载。
* 懒汉方法创建单例对象：<br/>
  ```java
  public class Singleton {
      private static final Singleton instance;
      private Singleton() {}
      public static Singleton getInstance() {
          if (instance == null) instance = new Singleton();
          return instance;
      }
  }
  ```
  懒汉模式的不足之处是：无法保证多线程情况下只产生一个实例。
* 多线程懒汉方法创建单例对象：<br/>
  ```java
  public class Singleton {
      private static final volatile Singleton instance;
      private Singleton() {}
      public static Singleton getInstance() {
          if (instance == null) {
              synchronized (Singleton.class) {
                  if (instance == null) instance = new Singleton();
              }
          }
          return instance;
      }
  }
  ```
* 静态内部类方法创建单例对象：<br/>
  ```java
  public class Singleton {
      private Singleton() {}
      public static Singleton getInstance() {
          return SingletonHelper.instance;
      }
      private static class SingletonHelper {
          private static final Singleton instance = new Singleton();
      }
  }
  ```
  静态内部类方法的优点是：第一，利用JVM本身的static和final特性保证线程安全；第二，没有使用synchronized关键字，多个线程可以同时读取实例，保证了性能。推荐使用这种方法。
* 枚举方法创建单例对象：<br/>
  ```java
  public enum Singleton {
      SINGLETON;
      private Singleton() {}
  }
  ```
  枚举方法的特点：写法简单、线程安全。线程安全的前提：如果枚举类中有其他属性，则需要保证这些属性也是线程安全的。推荐使用这种方法。

## 观察者模式（Observer）

## 策略模式（Strategy）
&emsp;&emsp;策略模式为同一动作的实现提供不同的策略，以提高代码的弹性，降低维护成本。策略模式区别于工厂模式的地方在于，工厂模式侧重于对象的创建，策略模式侧重于对象内方法的具体实现。

## 工厂模式（Factory）
&emsp;&emsp;工厂模式为创建对象提供统一的接口，将创建对象的具体过程屏蔽起来，以达到灵活性的目的。

## 构建者模式（Builder）
&emsp;&emsp;构建者模式通常是通过Builder内部类来实现的，内部类中提供了对目标类进行配置的方法，最终调用Builder类中的build()方法来实现最终的对象创建。<br/>
&emsp;&emsp;构建者模式将对象的创建和表示分离开来，避免在构造方法中构建对象时由于参数过多而引起混乱；用户可以自由选择配置信息的组成部分；构建者模式通常是链式编程风格，通过一行代码就可以清晰、灵活地创建对象。

## 适配器模式（Adapter）
&emsp;&emsp;适配器模式作为桥梁，让原本因为接口不兼容而不能一起工作的类可以一起工作。

## 代理模式（Proxy）
#### 代理模式简介
&emsp;&emsp;代理模式为其他对象提供一种代理，用以控制对这个对象的访问。最常见的一种代理模式的例子就是代购，国内的客户可以通过他们购买到国外的商品，而不需要知道他们具体是怎样购买的。<br/>
&emsp;&emsp;代理对象将客户端的调用委派给了目标对象，而在调用目标对象之前或之后，都可以插入一些其他的操作。
#### 静态代理
&emsp;&emsp;静态代理是在程序运行之前，通过JAVA代码来编写代理类，在代理类的方法中调用目标类中的方法，也可以在目标类方法的前后插入自定义的其他操作。
#### 动态代理
&emsp;&emsp;动态代理是代理类在程序运行时创建的代理方式。动态代理不是通过JAVA代码来实现的，而是通过配置，在程序运行期间动态生成的。<br/>
&emsp;&emsp;动态代理相对于静态代理的优点是：动态代理可以很方便的对代理类中的函数进行统一的处理，而不用根据相应的业务逻辑去修改每一个代理类中的函数。<br/>
&emsp;&emsp;动态代理通常是通过JDK的动态代理来实现的。JDK动态代理通过Proxy.newProxyInstance()方法为目标方法创建代理，每当调用代理中的方法时，都会回调InvocationHandler中的invoke()方法，在invoke()方法中可以调用目标方法，还可以在目标方法调用前后插入自定义的操作。