package com.lay.kotlinstudy.base;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//使用反射思路来写这个BaseActivity
public abstract class BaseVBJActivity<VB extends ViewBinding> extends AppCompatActivity {
    protected VB bind;
    protected String TAG = this.getClass().getSimpleName();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Type genericSuperclass = getClass().getGenericSuperclass();
        Class<?> tClass = (Class<?>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];//VB 的类型
        try {
            Method inflate = tClass.getDeclaredMethod("inflate", LayoutInflater.class);
            //invoke第一个参数是类的实例，或者类的对象，或者null.
            bind = (VB) inflate.invoke(null, getLayoutInflater());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //注意：在一个类里使用反射，此类里必须可以 找的到 反射类的位置，也就是说此类和反射类是存在依赖关系的，两个毫无关联的类是无法反射的
        setContentView(bind.getRoot());
        initUI();
    }

    protected abstract void initUI();
}

//问题一：为什么我们要拿到在反射的时候是通过父类来拿泛型信息的?
//回答：因为类型擦除。

//问题二：PECS原则为什么成立？
//要从JVM的实现上进行理解，不要从逻辑上理解。
//生产者 - extends - out T - 只取不能放 - 上界通配符 Plate<？ extends Fruit>
//总结一：为了让"子类的泛型是泛型的子类"成立。我们不会知道泛型类传递进来的符合out T 规矩的东西是什么，因此，我们只能取，不能往里面放。

//消费者 - super - in T - 只放不能取 - 下界通配符 Plate<？ super Fruit>
//总结二：为了让"子类的泛型是泛型的父类"成立。我们可以往里面存，但是无法从里面取，因为取出来的东西不知道是哪个类，
// 所以只能赋值给Object,这样就失去了原类型的意义。


//反射得到对应方法：
//getDeclaredMethod("方法名"，参数的class类) 获取的是类自身声明的所有方法，包含public、protected和private方法。
// getMethod () 获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。



//参考：https://www.cnblogs.com/wuqinglong/p/9456193.html - Java泛型类型擦除以及类型擦除带来的问题

//子类的泛型是泛型的子类：协变out
//子类的泛型是泛型的父类：逆变in

//类型投影
//out 和 in 直接在已经定义好的类上面使用就是类型投射。

//星号投影
//星号投影用来表明“不知道关于泛型实参的任何信息”。
//类似于 Java 中的无界类型通配符?, Kotlin 使用星号投影*。
//*代指了所有类型，相当于Any?。