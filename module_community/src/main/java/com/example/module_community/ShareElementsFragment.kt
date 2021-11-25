package com.example.module_community

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.example.module_community.adapter.ShareAdapter2
import com.example.module_community.databinding.FragmentRecyclerListBinding
import com.example.module_community.vm.CommunityViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.baselibrary.utils.log.xLog


class ShareElementsFragment : BaseFragment(R.layout.fragment_recycler_list) {

    companion object {
        @JvmStatic
        fun newInstance() = ShareElementsFragment()
    }

    private val binding by viewBinding(FragmentRecyclerListBinding::bind)
    private val viewModel:CommunityViewModel by activityViewModels()
    private val listAdapter by lazy {
        ShareAdapter2()
    }

    private var page = 0
    override fun initData() {

        binding.recyclerList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            refreshList()
        }

        //滑动监听
        binding.recyclerList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_DRAGGING -> {
                        /*正在拖拽*/
                        xLog.d("正在拖拽")
                    }
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        /*滑动停止*/
                        xLog.d("滑动停止")
                    }
                    RecyclerView.SCROLL_STATE_SETTLING -> {
                        /*惯性滑动中*/
                        xLog.d("惯性滑动中")
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView.layoutManager as LinearLayoutManager?
                //经测试第一个完整的可见的item位置，若为0则是最上方那个;在item超过屏幕高度的时候只有第一个item出现的时候为0 ，其他时候会是一个负的值
                //此方法常用作判断是否能下拉刷新，来解决滑动冲突
                val findFirstCompletelyVisibleItemPosition = (manager as LinearLayoutManager?)!!.findFirstCompletelyVisibleItemPosition()
                //最后一个完整的可见的item位置
                val findLastCompletelyVisibleItemPosition = (manager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                //第一个可见的位置
                val findFirstVisibleItemPosition = (manager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
                //最后一个可见的位置
                val findLastVisibleItemPosition = (manager as LinearLayoutManager?)!!.findLastVisibleItemPosition()

                xLog.d("onScroll, " +
                        "findFirstCompletelyVisibleItemPosition:$findFirstCompletelyVisibleItemPosition," +
                        "findLastCompletelyVisibleItemPosition:$findLastCompletelyVisibleItemPosition," +
                        "findFirstVisibleItemPosition:$findFirstVisibleItemPosition," +
                        "findLastVisibleItemPosition:$findLastVisibleItemPosition")

                //如果 dx>0 则表示 右滑 ,dx<0 表示 左滑
                // dy <0 表示 上滑, dy>0 表示下滑
                if (dy > 5){
                    viewModel.hideCommunityBottomBar(true)
                }else if (dy < -5){
                    viewModel.hideCommunityBottomBar(false)
                }
                xLog.d("dx:$dx,dy:$dy")
            }

        })


    }

    private fun refreshList(){
        listAdapter.setList(viewModel.createShareElementData(page))
    }

    private fun loadMoreList(){
        page++
        listAdapter.addData(viewModel.createShareElementData(page))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        page = 0
    }

}