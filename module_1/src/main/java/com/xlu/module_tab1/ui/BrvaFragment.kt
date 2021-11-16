package com.xlu.module_tab1.ui

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.utils.other.TimeUtil.getCurrentTime
import com.xlu.module_tab1.R
import com.xlu.module_tab1.adapter.Book
import com.xlu.module_tab1.adapter.QuickBiningAdapter
import com.xlu.module_tab1.databinding.FragmentBrvaListBinding

class BrvaFragment : BaseFragment(R.layout.fragment_brva_list) {

    private var columnCount = 1
    private val binding by viewBinding(FragmentBrvaListBinding::bind)
    private val bindingAdapter = QuickBiningAdapter()

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


    override fun initData() {
        with(binding.list){
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = bindingAdapter
        }

        val list = mutableListOf<Book>()
        for (i in 0..100){
            list.add(Book(i,getCurrentTime()))
        }

        bindingAdapter.data = list
    }

}