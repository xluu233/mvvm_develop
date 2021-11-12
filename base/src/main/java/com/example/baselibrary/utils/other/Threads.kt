/*
 * Copyright (c) 2021. Dylan Cai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("unused", "NOTHING_TO_INLINE")

package com.example.baselibrary.utils.other

import android.os.Handler
import android.os.Looper

/**
 * @author Dylan Cai
 */

val mainHandler = lazy { Handler(Looper.getMainLooper()) }

inline fun mainThread(noinline block: () -> Unit) =
  mainHandler.value.post(block)

inline fun mainThread(delayMillis: Long, noinline block: () -> Unit) =
  mainHandler.value.postDelayed(block, delayMillis)
