package com.example.baselibrary.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<VB : ViewBinding>(
    context: Context,
    themeResId: Int,
    private val inflate: (LayoutInflater) -> VB
) : Dialog(context, themeResId) {

    lateinit var binding: VB

    constructor(context: Context, inflate: (LayoutInflater) -> VB) : this(context, 0, inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }
}