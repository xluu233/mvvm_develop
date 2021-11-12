package com.example.baselibrary.utils.other

import android.text.format.Formatter
import android.util.Patterns
import androidx.core.util.PatternsCompat
import com.example.baselibrary.utils.activity.application
import org.json.JSONObject
import java.util.*



const val REGEX_ID_CARD_15: String =
  "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"

const val REGEX_ID_CARD_18: String =
  "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$"

inline val randomUUIDString: String
  get() = UUID.randomUUID().toString()

fun Long.toFileSizeString(): String =
  Formatter.formatFileSize(application, this)

fun Long.toShortFileSizeString(): String =
  Formatter.formatShortFileSize(application, this)

fun String.limitLength(length: Int): String =
  if (this.length <= length) this else substring(0, length)

fun String.isPhone(): Boolean =
  Patterns.PHONE.matcher(this).matches()

fun String.isDomainName(): Boolean =
  PatternsCompat.DOMAIN_NAME.matcher(this).matches()

fun String.isEmail(): Boolean =
  PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String.isIP(): Boolean =
  PatternsCompat.IP_ADDRESS.matcher(this).matches()

/**
 *  Regular expression pattern to match most part of RFC 3987
 *  Internationalized URLs, aka IRIs.
 */
fun String.isWebUrl(): Boolean =
  PatternsCompat.WEB_URL.matcher(this).matches()

fun String.isIDCard15(): Boolean =
  REGEX_ID_CARD_15.toRegex().matches(this)

fun String.isIDCard18(): Boolean =
  REGEX_ID_CARD_18.toRegex().matches(this)

fun String.isJson(): Boolean =
  try {
    JSONObject(this)
    true
  } catch (e: Exception) {
    false
  }
