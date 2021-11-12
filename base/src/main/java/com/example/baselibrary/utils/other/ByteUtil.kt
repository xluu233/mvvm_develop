package com.example.baselibrary.utils.other

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.util.*

/**
 * @ClassName CommonUtil
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/4/9 16:47
 */
object ByteUtil {
    fun bytesToShort(bytes: ByteArray?): ShortArray? {
        if (bytes == null) {
            return null
        }
        val shorts = ShortArray(bytes.size / 2)
        ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer()[shorts]
        return shorts
    }

    fun shortToBytes(shorts: ShortArray?): ByteArray? {
        if (shorts == null) {
            return null
        }
        val bytes = ByteArray(shorts.size * 2)
        ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(shorts)
        return bytes
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val ba = byteArrayOf(21, 32, 45, 98, 46, 85)
        val sa = bytesToShort(ba)
        val bb = shortToBytes(sa)
        println("ba=" + ba.contentToString() + ",sa=" + Arrays.toString(sa) + ",bb=" + Arrays.toString(bb))
    }


    fun byteArrayToFloatArray(bytes: ByteArray): FloatArray {
        val buffer = ByteBuffer.wrap(bytes)
        val fb: FloatBuffer = buffer.asFloatBuffer()
        val floatArray = FloatArray(fb.limit())
        fb.get(floatArray)
        return floatArray
    }


    fun floatMe(pcms: ShortArray): FloatArray {
        val floaters = FloatArray(pcms.size)
        for (i in pcms.indices) {
            floaters[i] = pcms[i].toFloat()/32768
        }
        return floaters
    }

    fun shortMe(bytes: ByteArray): ShortArray {
        val out = ShortArray(bytes.size / 2) // will drop last byte if odd number
        val bb = ByteBuffer.wrap(bytes)
        for (i in out.indices) {
            out[i] = bb.short
        }
        return out
    }

}