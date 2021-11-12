package com.example.baselibrary.utils.other

import android.net.Uri
import androidx.core.app.ShareCompat
import com.example.baselibrary.lifecycle.ActivityStack



inline fun shareText(content: String, title: String? = null) =
  share("text/plain") {
    setText(content)
    setChooserTitle(title)
  }

inline fun shareImage(imageUri: Uri, title: String? = null) =
  shareTextAndImage(null, imageUri, title)

inline fun shareImages(imageUris: List<Uri>, title: String? = null) =
  shareTextAndImages(null, imageUris, title)

inline fun shareTextAndImage(content: String?, imageUri: Uri, title: String? = null) =
  share("image/*") {
    setText(content)
    setStream(imageUri)
    setChooserTitle(title)
  }

inline fun shareTextAndImages(content: String?, imageUris: List<Uri>, title: String? = null) =
  share("image/*") {
    setText(content)
    imageUris.forEach { addStream(it) }
    setChooserTitle(title)
  }


inline fun share(mimeType: String, crossinline block: ShareCompat.IntentBuilder.() -> Unit) =
  ActivityStack.currentActivity?.let {
    ShareCompat.IntentBuilder(it).apply { setType(mimeType) }.apply(block).startChooser()
  }

