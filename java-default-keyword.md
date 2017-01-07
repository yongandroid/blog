<!--
author: 浣溪沙
head: http://tva1.sinaimg.cn/crop.0.0.180.180.180/6f9c394fjw1e8qgp5bmzyj2050050aa8.jpg
date: 2016-11-09
title: java8关键字之default 
tags: java default java8 关键字 java8关键字
images: http://a.hiphotos.baidu.com/image/pic/item/960a304e251f95ca897ef9bccb177f3e67095234.jpg
category: java
status: publish
summary: 看 java 代码的时候看到了一个default关键字，有点眼生，特地去查了一波，特此记录
-->

# 起因
在看 java8 集合框架的时候，在``` Iterable<T> ```接口里看到了看下了下面一段
```java
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
```
这个```default```看着很眼生啊。
# 简单例子
在 jdk 1.8 之前，```default``` 主要用在```switch```中。现在它可以用在接口的方法中了，做为这个接口的默认方法。比如说下面的代码
```java
public class Main {

    public static void main(String[] args) {
        Cat c = new Cat();
        c.speak();
    }

}

interface Animal{
    void walk();
    default void speak(){
        System.out.println("啊呜~~~");
    }
}

class Cat implements Animal{
    @Override
    public void walk() {
        System.out.println("I'm working");
    }
}
```
可以看到这里 ```Cat``` 类并没有报错
![Cat类][1]
运行它的话就可以得到
![运行结果][2]

# 作用
这东西有什么用呢？在 ```JDK 1.8``` 以前，如果我要给```Animal```接口添加一个方法，那所有的子类都要去实现这个方法(这个说法不严谨)，这样的话并不能很好的向后兼容，于是乎JDK的开发组就想出了这么一个办法。现在这样的话```Animal```在添加新方法就不用全部子类重新写东西了，只要给个默认方法就好了。

# 当冲突的时候
我们知道```Java```的一个特性是 ***单继承，多实现*** ,这个特性很大一部分程度上是为了避免冲突。但是现在接口可以写 ``` default ``` 方法了，这就很尴尬了。这个时候我们不禁要问，要是两个接口都有一个相同名称的```default```方法那怎么办。
比如下面```A,B```这两个接口
```java
interface A{
    default void whoIsRun(){
        System.out.println("A is running");
    }
}
interface B{
    default void whoIsRun(){
        System.out.println("B is running");
    }
}
``` 
这个时候如果有个类要继承他们，嗯。。。 会报错。
![报错][3]
这个时候我们可以通过改方法名称，或者让B继承A解决这个问题，像这样
```java
interface A{
    default void whoIsRun(){
        System.out.println("A is running");
    }
}
interface B extends A{
    default void whoIsRun(){
        System.out.println("B is running");
    }
}
```
这个时候，如果执行的话，会执行更具体的```default```方法，就是B中的```whoIsRun```方法恩，然后还有个问题，当抽象类和接口有同名方法的时候怎么办，比如说像下面这样
```java
interface A {
    default void whoIsRun() {
        System.out.println("A is running");
    }
}

abstract class B {
    void whoIsRun() {
        System.out.println("B is running");
    }
}
class Cat extends B implements A {

}
```
这个时候他是报错的。
![报错][4]
根据提示，我们给B类加上```public```，运行一下，可以看到
```
B is running
```
这体现了当接口方法和类的方法冲突的时候，以类的方法为准( class always win)。恩很好。

# 跟抽象类的区别
现在接口可以可以有方法体了，这让抽象类怎么办.....
除去一些乱七八糟的概念性上的东西（比如一个like，一个is之类的），现在抽象类和接口的区别好像就剩下：
- 一个变量是```public static final```的一个不是
- 抽象类只能单继承，接口可以多实现
上面这两个了。
# 个人总结
为了给实现给java的接口弄默认的方法，java里面有很多乱七八糟的抽象类，嗯。。。 不优雅，有了```default```之后似乎优雅多了，挺好。然后接口里的方法也可以用```static```修饰了，恩。。。 也挺好。

转载请注明出处，[原文链接](http://huanshaxiaozhu.me/blog/java-default-keyword.html)


  [1]: http://static.zybuluo.com/SeanWu/a0erfu9u9yx2fjfo7ky8w29e/image_1b14k8de91nrn39p10fp1sl0qnf9.png
  [2]: http://static.zybuluo.com/SeanWu/y0450digw3crp16eyl2awgd1/image_1b14k9eld1pmc5tmctjbrf42pm.png
  [3]: http://static.zybuluo.com/SeanWu/yhr4pwbahdsorf4okihymlss/image_1b14kp2cnl4n1e3j48tuplsrq13.png
  [4]: http://static.zybuluo.com/SeanWu/xmsdzjl261s1otfes65bhit9/image_1b14lb60t6ls1tnoftiml103r1g.png

