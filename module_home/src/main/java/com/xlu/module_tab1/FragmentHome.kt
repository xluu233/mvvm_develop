package com.xlu.module_tab1

import android.content.Context
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.navigation.NavHostFragment
import com.xlu.common.constants.ConstantARouter
import com.xlu.module_tab1.databinding.FragmentHomeBinding

@Route(path = ConstantARouter.FragmentTab1_Main)
class FragmentHome : BaseFragment(R.layout.fragment_home) {

    companion object{
        @JvmStatic
        fun newInstance(): Fragment = FragmentHome()
    }

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by activityViewModels()

/*    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val navHostFragment = childFragmentManager.findFragmentById(R.id.home_fragment_container) as NavHostFragment
                val currentFragment = navHostFragment.childFragmentManager.primaryNavigationFragment
                Log.d(TAG, "handleOnBackPressed: ${currentFragment?.javaClass?.name}")
                if (currentFragment is FragmentHome){
                    requireActivity().finish()
                }else{
                    //return
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }*/

    override fun initData() {

    }

}