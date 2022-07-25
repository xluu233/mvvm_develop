package com.example.module_community

import android.app.SharedElementCallback
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.recyclerview.AdapterClickListener
import com.example.baselibrary.recyclerview.GridSpaceItemDecoration
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.utils.view.dp
import com.example.baselibrary.viewbinding.viewBinding
import com.example.module_community.adapter.ImageListAdapter
import com.example.module_community.bean.Image
import com.example.module_community.databinding.FragmentCoilBinding
import com.example.module_community.vm.CommunityViewModel
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantParams
import com.xlu.common.hideBottomNav


const val COIL_POSITION = "COIL_POSITION"

/**
 * Coil图片加载框架测试
 */
class FragmentCoil : BaseFragment(R.layout.fragment_coil, lazyInit = true) {

    private val viewModel by activityViewModels<CommunityViewModel>()
    private val binding by viewBinding(FragmentCoilBinding::bind)

    private val coilAdapter by lazy {
        ImageListAdapter()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentCoil()
    }


    private val clickListener by lazy {
        object : AdapterClickListener<Image> {
            override fun click(position: Int, view: View, data: Image) {
                //ToastBox.showToast("position:$position, id:${data.id}")

/*                //通过tag找到需要共享的view
                val transitionName = data.url
                //val transitionName = view.transitionName
                xLog.d(TAG,"transitionName:$transitionName")

                ViewCompat.setTransitionName(view, transitionName)*/
                val makeSceneTransitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(),
                        view,
                        data.url
                    )

                //val makeSceneTransitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), view, "shareElement")
                //val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), view,transitionName).toBundle()

                ARouter.getInstance().build(ConstantARouter.ImagePreviewActivity)
                    .withStringArrayList(ConstantParams.PreviewImageList, viewModel.lolImageList)
                    .withInt(ConstantParams.PreviewImagePosition, position)
                    .withOptionsCompat(makeSceneTransitionAnimation)
                    .navigation(requireContext())
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
        val list = viewModel.getImageData()

        binding.coilList.apply {
            val spanCount = 3
            val gridLayoutManager = GridLayoutManager(context, spanCount)
            addItemDecoration(GridSpaceItemDecoration(spanCount, 3.dp.toInt(), 3.dp.toInt()))
            layoutManager = gridLayoutManager
            //adapter = ImageListAdapter3(list)
            adapter = coilAdapter
        }

        coilAdapter.setClickListener(listener = clickListener)
        coilAdapter.setList(list)

        requireActivity().setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {
                val position = (requireActivity() as CommunityActivity).intent.getIntExtra(COIL_POSITION,-1)
                if (position != -1){
                    val tag = list[position].url
                    xLog.d(TAG,"return coil,position:$position,tag:$tag")

                    sharedElements?.clear()
                    names?.clear()
                    val view = binding.coilList.layoutManager?.findViewByPosition(position)

                    //注意这里第二个参数，如果放置的是条目的item则动画不自然。放置对应的imageView则完美
                    view?.let {
                        sharedElements?.set(tag, it)
                    }
                }
            }
        })

    }

    override fun onDestroyView() {
        hideBottomNav(false)
        binding.coilList.adapter = null
        super.onDestroyView()
    }
}