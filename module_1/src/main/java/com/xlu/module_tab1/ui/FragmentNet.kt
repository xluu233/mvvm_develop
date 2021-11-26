package com.xlu.module_tab1.ui

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.utils.view.click
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentNetBinding

class FragmentNet : BaseFragment(R.layout.fragment_net) {

    private val binding by viewBinding(FragmentNetBinding::bind)
    private val viewModel: HomeViewModel by activityViewModels()

    override suspend fun initData() {

        viewModel.articleLiveData.observe(this, Observer {
            //对于不同状态的处理
            xLog.d(TAG, "articleLiveData: ${it.netState}")

            it.data?.datas?.forEach {
                xLog.d(TAG, "articleLiveData: ${it}")
            }
        })

        binding.getArticle.click {
            viewModel.getHomeArticle(1)
        }

        binding.getArticleExcute.click {
            viewModel.getHomeArticleExcute(2)
        }

        binding.jsonTest.click {
            viewModel.getHomeArticleWithJson()
        }


    }

}