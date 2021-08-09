package com.example.mvvm_develop.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * @ClassName RouterBeanParcable
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/9 14:25
 */
data class RouterBeanParcable(val id:Int, val name:String): Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.apply {
            writeInt(id)
            writeString(name)
        }
    }

    companion object CREATOR : Parcelable.Creator<RouterBeanParcable> {
        override fun createFromParcel(parcel: Parcel): RouterBeanParcable {
            return RouterBeanParcable(parcel)
        }

        override fun newArray(size: Int): Array<RouterBeanParcable?> {
            return arrayOfNulls(size)
        }
    }

}