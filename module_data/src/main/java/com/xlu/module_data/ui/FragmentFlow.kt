package com.xlu.module_data.ui

import androidx.lifecycle.lifecycleScope
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.module_data.R
import com.xlu.module_data.databinding.FragmentFlowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FragmentFlow : BaseFragment(R.layout.fragment_flow) {

    private val binding by viewBinding(FragmentFlowBinding::bind)

    override suspend fun initData() {

        binding.flow1.click(scope = lifecycleScope){

//            testIntFlow()
//            testStringFlow()
            testChannelFlow()

        }
    }

    suspend fun testIntFlow(){
        flow<Int> {
            for (i in 0..3){
                emit(i)
                delay(1000)
            }
        }.collect {
            showLog(it.toString())
        }


        flowOf(1,2,3)

        flow<Int> {
            emit(1)
            emit(2)
            emitAll(flowOf(1,2,3))
        }
    }

    suspend fun testStringFlow(){
        val flow = flow<String> {
            for (i in 0..3){
                emit(System.currentTimeMillis().toString())
                delay(100)
            }
        }
        flow.collect {
            showLog(it)
        }
    }

    suspend fun testChannelFlow(){
        val channelFlow = channelFlow<Int> {
            send(1)
            withContext(Dispatchers.IO){
                send(2)
            }
            send(3)
        }

        channelFlow.collect {
            println(it)
        }
    }

    private suspend fun showLog(text:String?){
        text?.let {
            binding.consoleLog.text = "${binding.consoleLog.text}\n$it"
        }
    }

}