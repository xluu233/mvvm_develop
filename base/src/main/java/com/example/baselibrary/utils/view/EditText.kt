package com.example.baselibrary.utils.view

import android.widget.EditText
import androidx.core.widget.addTextChangedListener

/**
 * @ClassName EditText
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/17 9:59
 */


fun <T: EditText> T.onChange(block: (T) -> Unit){
    addTextChangedListener {
        block(this)
    }
}
