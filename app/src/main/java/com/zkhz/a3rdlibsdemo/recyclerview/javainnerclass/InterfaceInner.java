package com.zkhz.a3rdlibsdemo.recyclerview.javainnerclass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/19 0019.
 *
 * Java从入门到精通 P207
 *
 * 内部类可以使用外部类成员 方法 private也行,但是外部类不能直接访问内部类成员变量,可以通过内部类对象引用调用内部类成员
 *
 */

public class InterfaceInner extends AppCompatActivity {

    private static final String TAG = "InterfaceInner";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OutClass outClass=new OutClass();
        OutInterface outInterface=outClass.doit();
//        OutClass.InnerClass innerClass=new InnerClass();???????????

        outInterface.f();


    }


    class OutClass{


        /*
        * 定义private内部类,除了OutClass类之外,其他类都不能访问
        * 可以访问doit()方法,返回值为外部接口,这个接口可以作为外部使用的接口,它包含一个f()方法,在继承者中实现该方法
        * 若果某个类继承了外部类InterfaceInner,由于内部的权限不可以向下转型为内部类InnerClass,同时也不能访问f()方法
        * 例如InterfaceInner中最后一条语句,调用f()方法,从执行结果来看,这条语句执行的是InnerClass的f()方法,
        * 很好的对继承该类的子类 隐藏实现细节,仅为编写子类的人员留下一个接口和一个外部类,同时还能调用f()方法
        */
        private class InnerClass implements OutInterface{

            public InnerClass(String s) {
                Log.d(TAG, s);

            }

            @Override
            public void f() {

                Toast.makeText(InterfaceInner.this, "访问内部类中的f()方法", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "访问内部类中的f()方法");

            }
        }

        public OutInterface doit(){
            return new InnerClass("访问内部类构造方法");
        }
    }
}
