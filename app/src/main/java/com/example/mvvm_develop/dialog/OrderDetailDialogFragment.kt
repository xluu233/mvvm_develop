package com.example.mvvm_develop.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.baselibrary.delegate.viewBinding
import com.example.mvvm_develop.R
import com.example.mvvm_develop.databinding.FragmentOrderDetailBinding

/**
 * Created by pengxr on 11/5/2021
 */
class OrderDetailDialogFragment : DialogFragment() {

    private val binding by viewBinding(FragmentOrderDetailBinding::bind)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDisplay.text = "Hello Dialog."
    }
}