package com.xlu.module_tab1.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.recyclerview.test.*
import com.example.baselibrary.viewbinding.viewBinding
import com.example.baselibrary.utils.other.TimeUtil.getCurrentTime
import com.xlu.module_tab1.R
import com.xlu.module_tab1.adapter.QuickBiningBRVAdapter
import com.xlu.module_tab1.databinding.FragmentBrvaListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class BrvaFragment : BaseFragment(R.layout.fragment_brva_list) {

    private var columnCount = 1
    private val binding by viewBinding(FragmentBrvaListBinding::bind)

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            BrvaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    private val bindingAdapter by lazy {
        //QuickBiningBRVAdapter()
        //AdapterDemo1()
        //AdapterDemo2()
        AdapterDemo4()
    }

    override fun initData() {

        val list = mutableListOf<Book>()
        for (i in 0..100){
            list.add(Book(i,getCurrentTime()+"   - $i"))
        }

        with(binding.list){
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = bindingAdapter
        }

        //bindingAdapter.addData(list)

        lifecycleScope.launch(Dispatchers.IO){
            delay(500)
            withContext(Dispatchers.Main){
                bindingAdapter.addData(list)
            }
        }
    }

}