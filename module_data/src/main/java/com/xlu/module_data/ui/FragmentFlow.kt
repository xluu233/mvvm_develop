package com.xlu.module_data.ui

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.module_collection.R
import com.xlu.module_collection.databinding.FragmentFlowBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FragmentFlow : BaseFragment(R.layout.fragment_flow) {

    private val binding by viewBinding(FragmentFlowBinding::bind)

    override suspend fun initData() {

        binding.flow1.click(scope = lifecycleScope){
            flow<Int> {
                for (i in 0..3){
                    emit(i)
                }
            }.collect {
                showLog(it.toString())
            }
        }
    }


    @SuppressLint("SetTextI18n")
    fun showLog(text:String?){
        text?.let {
            binding.consoleLog.text = "${binding.consoleLog.text}\n$it"
        }
    }

}