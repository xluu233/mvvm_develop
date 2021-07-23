package com.example.mvvm_develop.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.delegate.viewBinding
import com.example.mvvm_develop.R
import com.example.mvvm_develop.databinding.FragmentBindBinding

class BindFragment : Fragment(R.layout.fragment_bind) {


    private val binding by viewBinding(FragmentBindBinding::bind)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bind, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = BindFragment()
    }

}