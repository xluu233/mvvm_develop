package com.example.mvvm_develop.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_develop.CommonViewModel
import com.example.mvvm_develop.R
import com.example.mvvm_develop.databinding.FragmentMainBinding


class PlaceholderFragment : Fragment() {

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    private lateinit var commonViewModel: CommonViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonViewModel = ViewModelProvider(this).get(CommonViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        commonViewModel.load()

        commonViewModel.liveData.observe(viewLifecycleOwner, Observer {
            it.data?.forEach {
                Log.d("BannerData",it.url)
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}