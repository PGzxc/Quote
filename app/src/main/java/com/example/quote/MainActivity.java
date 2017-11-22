package com.example.quote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //强引用
        // strongReference();

        //弱引用
        weakReference();

        //软引用
        //softReference();

    }

    /**
     * 强引用
     */
    private void strongReference() {
        Reference ref = new Reference();
        Reference secondRef = ref;
        ref = null;
    }

    /**
     * 软引用
     */
    private void weakReference() {
        System.out.println("开始");
        Reference ref = new Reference();
        WeakReference<Reference> weakRef = new WeakReference<Reference>(ref);
        //引用置为null，只是把引用设置为null，指向的对象是否被回收需要看情况，如果没有任何强引用指向，
        //那么当发生GC的时候，无论如何该对象都会被回收。（如果有软引用指向该对象，视情况是否回收）
        ref = null;
        System.gc();// 强制对系统进行GC,因为GC是不固定的，这个需要JVM调度，如果没发生GC，那么虚引用所指向的对象还是不会被回收，直到程序运行结束。
        // 此时该对象没有强引用指向它，只有虚引用指向这个对象，所以可以直接回收这个对象
        // 如果此时是软引用指向这个对象，然后发生GC，视情况决定是否回收这个对象，内存不足，回收，否则不回收
        // SoftReference<A> weakA = new SoftReference<A>(a);
        //<span style="color:#33CC00;">即使调用System.gc();JVM也不一定会发生回收，不同JVM有不同的实现，这里恰好发生了GC，所以产生了如下打印结果</span>。
        Reference anoRef = weakRef.get();
        if (anoRef == null) {
            //说明上面new的Reference已经JVM的GC回收
            System.out.println("anoRef is null");
        } else {
            //对象没有被回收
            System.out.println(anoRef.getString().toString());
        }
        System.out.println("结束");
    }

    /**
     * 弱引用
     */
    private void softReference() {
        System.out.println("开始");
        Reference ref = new Reference();
        SoftReference<Reference> softRef = new SoftReference<Reference>(ref);
        //引用置为null，只是把引用设置为null，指向的对象是否被回收需要看情况，如果没有任何强引用指向，
        //那么当发生GC的时候，无论如何该对象都会被回收。（如果有软引用指向该对象，视情况是否回收）
        ref = null;
        System.gc();// 强制对系统进行GC,因为GC是不固定的，这个需要JVM调度，如果没发生GC，那么虚引用所指向的对象还是不会被回收，直到程序运行结束。
        // 此时该对象没有强引用指向它，只有虚引用指向这个对象，所以可以直接回收这个对象
        // 如果此时是软引用指向这个对象，然后发生GC，视情况决定是否回收这个对象，内存不足，回收，否则不回收
        // SoftReference<A> weakA = new SoftReference<A>(a);
        //<span style="color:#33CC00;">即使调用System.gc();JVM也不一定会发生回收，不同JVM有不同的实现，这里恰好发生了GC，所以产生了如下打印结果</span>。
        Reference anoRef = softRef.get();
        if (anoRef == null) {
            //说明上面new的Reference已经JVM的GC回收
            System.out.println("anoRef is null");
        } else {
            //对象没有被回收
            System.out.println(anoRef.getString().toString());
    }
        System.out.println("结束");

    }

}
