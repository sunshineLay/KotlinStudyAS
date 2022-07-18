package com.lay.kotlinstudy.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.lay.kolinstudy.EasyVBActivity

//ViewBinding的BaseActivity封装实现 - Kotlin
//思路一：反射（作用一：在父类调用运行过程中子类实例）+泛型
//思路二：泛型 + by lazy
//思路三：泛型 + Lifecycle [在Base框架中使用]
//从性能角度最好使用思路2和3

//思路二
abstract class BaseActivity<VB:ViewBinding>(open val block:(LayoutInflater)->VB):AppCompatActivity() {
    protected val TAG = this::class.simpleName
    protected val binding by lazy { block(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI();
    }

    protected abstract fun initUI()
}

//思路二：类型约束<VB:ViewBinding>
//ViewBinding的子类目录：Project模式下app/build/generated/data_binding_base_class_source_out/
//通过这个目录可以找到生成的ViewBinding 辅助类，直接Ctrl+ 左键点击会进入对应的xml界面
//通过对应辅助类我们可以发现所有的辅助类都是interface ViewBinding 的子类。
//因此我们可以进行类型约束抽象得到对应的BaseActivity和BaseFragment。

//public interface ViewBinding {
//    /**
//     * Returns the outermost {@link View} in the associated layout file. If this binding is for a
//     * {@code <merge>} layout, this will return the first view inside of the merge tag.
//     */
//    @NonNull
//    View getRoot();
//}

//open val block:(LayoutInflater)->VB
//子类Vb2ActivityBinding::inflate
//从这个写法中可以看出，Kotlin函数类型和Java中对应类型的类static方法在使用层面可以等效。
//Kotlin的函数应用等效作用于类调用静态方法

//复习可见性修饰符：
//private 意味着只在这个类内部（包含其所有成员）可见；
//protected—— 和 private一样 + 在子类中可见。
//internal —— 能见到类声明的 本模块内 的任何客户端都可见其 internal 成员；
//public —— 能见到类声明的任何客户端都可见其 public 成员。