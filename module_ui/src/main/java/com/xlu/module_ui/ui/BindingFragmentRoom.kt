package com.xlu.module_ui.ui

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.baselibrary.base_databinding.BaseBindingFragment
import com.xlu.module_ui.HomeViewModel
import com.xlu.module_ui.R
import com.xlu.module_ui.databinding.FragmentRoomBinding
import com.xlu.module_ui.db.HomeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BindingFragmentRoom : BaseBindingFragment<FragmentRoomBinding>(R.layout.fragment_room) {

    private val viewModel: HomeViewModel by activityViewModels()

    private val homeDao by lazy {
        HomeDatabase.getInstance().homeDao()
    }

    override suspend fun initData() {
        initClick()

    }

    private fun initClick() {


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