package com.xlu.module_tab1.ui

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.baselibrary.base_databinding.DataBindingBaseFragment
import com.example.baselibrary.http.NetState
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentRoomBinding
import com.xlu.module_tab1.db.HomeDatabase
import kotlinx.coroutines.*

class RoomFragment : DataBindingBaseFragment<FragmentRoomBinding>(R.layout.fragment_room) {

    private val viewModel: HomeViewModel by activityViewModels()

    private val homeDao by lazy {
        HomeDatabase.getInstance().homeDao()
    }

    override fun initData() {
        initClick()

        viewModel.articleLiveData.observe(this, Observer {
            //对于不同状态的处理
            when(it.state) {
                NetState.STATE_SUCCESS -> {

                }
                NetState.STATE_ERROR -> {

                }
                else -> {

                }
            }

            it.data?.datas?.forEach {
                Log.d(TAG, "articleLiveData: ${it.toString()}")
            }
        })

    }

    private fun initClick() {
        mBinding.getArticle.setOnClickListener {
            //viewModel.getHomeArticle(1)
            viewModel.test()
        }

        mBinding.query.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                val allData = homeDao.getAllData()
                allData.forEach {
                    Log.d(TAG, " ---------------------------")
                    it.datas.forEach {
                        Log.d(TAG, "${it.toString()}")
                    }
                }
            }

        }

        mBinding.delete.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                homeDao.deleteAll()
            }
        }
    }

}