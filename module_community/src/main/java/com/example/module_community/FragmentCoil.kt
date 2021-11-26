package com.example.module_community

import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.recyclerview.AdapterClickListener
import com.example.baselibrary.recyclerview.GridSpaceItemDecoration
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.utils.view.dp
import com.example.baselibrary.viewbinding.viewBinding
import com.example.module_community.adapter.ImageListAdapter
import com.example.module_community.adapter.ImageListAdapter2
import com.example.module_community.adapter.ImageListAdapter3
import com.example.module_community.bean.Image
import com.example.module_community.databinding.FragmentCoilBinding
import com.example.module_community.vm.CommunityViewModel
import com.example.xlulibrary.ToastBox
import com.xlu.common.constants.ConstantParams
import com.xlu.common.hideBottomNav

/**
 * Coil图片加载框架测试
 */
class FragmentCoil : BaseFragment(R.layout.fragment_coil,lazyInit = true) {

    private val viewModel by activityViewModels<CommunityViewModel>()
    private val binding by viewBinding(FragmentCoilBinding::bind)

    private val coilAdapter by lazy {
        ImageListAdapter2()
    }


    companion object {
        @JvmStatic
        fun newInstance() = FragmentCoil()
    }


    private val clickListener by lazy {
        object : AdapterClickListener<Image>{
            override fun click(position: Int, view: View, data: Image) {
                ToastBox().show("position:$position, id:${data.id}")

            }

            override fun longClick(position: Int, view: View, data: Image) {
                xLog.d("longClick")
            }
        }
    }

    /**
     * 这里采用三种不同方式的Adapter加载图片
     * Glide、Coil、DataBinding、原生Adapter实现
     */
    override suspend fun initData() {
        viewModel.getImageData()

        binding.coilList.apply {
            val spanCount = 3
            val gridLayoutManager = GridLayoutManager(context,spanCount)
            addItemDecoration(GridSpaceItemDecoration(spanCount,3.dp.toInt(),3.dp.toInt()))
            layoutManager = gridLayoutManager
            //adapter = ImageListAdapter3(list)
            adapter = coilAdapter
        }

        coilAdapter.setClickListener(listener = clickListener)

        viewModel.lolImageList.observe(viewLifecycleOwner, Observer {
            coilAdapter.setList(it)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        hideBottomNav(false)
    }
}