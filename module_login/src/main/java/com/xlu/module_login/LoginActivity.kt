package com.xlu.module_login

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseActivity
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.utils.log.xLog
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantParams
import com.xlu.module_login.databinding.ActivityLoginBinding


@Route(path = ConstantARouter.LoginActivity)
class LoginActivity : BaseActivity(R.layout.activity_login){

    private val binding by viewBinding(ActivityLoginBinding::bind)

    @Autowired
    @JvmField var key1 : Long = 0L

    @Autowired(name = ConstantParams.key2)
    @JvmField var test : String = ""

    override fun initData(savedInstanceState: Bundle?) {
        //自动注入,不建议使用
        ARouter.getInstance().inject(this)

        //initParams()
        initView()
    }

    /**
     * 手动获取参数
     */
    private fun initParams() {
        val key1 = intent?.getLongExtra(ConstantParams.key1,0L)
        val key2 = intent?.getStringExtra(ConstantParams.key2)
        val key3 = intent?.getSerializableExtra(ConstantParams.key3)
        xLog.d("key1:${key1},key2:${key2},key3:${key3}")
    }


    /**
     * 获取fragment实例
     */
    private fun initView() {
        val fragment: Fragment = ARouter.getInstance().build(ConstantARouter.ARouterFragment)
            .withLong("id", key1)
            .withString("name", test)
            .navigation() as Fragment

        supportFragmentManager.beginTransaction().add(binding.fragmentContainerViewLogin.id,fragment).commit()
    }


}