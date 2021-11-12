package com.example.baselibrary.utils.other;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * edittext字符长度限制
 *  etName.setFilters(arrayOf<InputFilter>(NameLengthFilter(30)));
 */
public class NameLengthFilter implements InputFilter {

    int MAX_EN;// 最大英文/数字长度 一个汉字算两个字母
    String regEx = "[\\u4e00-\\u9fa5]"; // unicode编码，判断是否为汉字
    Context context;

    public NameLengthFilter(int mAX_EN, Context context) {
        super();
        MAX_EN = mAX_EN;
        this.context = context;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int destCount = dest.toString().length()+ getChineseCount(dest.toString());
        int sourceCount = source.toString().length() + getChineseCount(source.toString());
        if (destCount + sourceCount > MAX_EN) {
            Toast.makeText(context,"最大长度为"+MAX_EN+"字符",Toast.LENGTH_SHORT).show();
            return "";
        } else {
            return source;
        }
    }

    private int getChineseCount(String str) {
        int count = 0;
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count = count + 1;
            }
        }
        return count;
    }


}
