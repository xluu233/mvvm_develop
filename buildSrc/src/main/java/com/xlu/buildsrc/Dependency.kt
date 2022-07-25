package com.xlu.buildsrc


object Dependency {

    /*-------TODO:添加第三方库时请加上文档链接------*/

    //AndroidX 版本更新参考：https://developer.android.com/jetpack/androidx/versions
    const val junit = "junit:junit:4.12"
    const val android_junit = "androidx.test.ext:junit:1.1.2"
    const val android_espresso_core = "androidx.test.espresso:espresso-core:3.3.0"
    const val AppCompat = "androidx.appcompat:appcompat:1.4.1"
    const val Material = "com.google.android.material:material:1.5.0"
    const val AndroidXLegacy = "androidx.legacy:legacy-support-v4:1.0.0"
    const val ActivityKtx = "androidx.activity:activity-ktx:1.4.0"
    const val FragmentKtx = "androidx.fragment:fragment-ktx:1.4.1"
    const val MultiDex = "androidx.multidex:multidex:2.0.1"
    const val supportAnnotations = "com.android.support:support-annotations:27.1.0"
    const val AndroidXAnnotations = "androidx.annotation:annotation:1.3.0"


    //navigation
    const val NavigationVersion = "2.4.0"
    const val NavigationFragment = "androidx.navigation:navigation-fragment-ktx:${NavigationVersion}"
    const val NavigationUI = "androidx.navigation:navigation-ui-ktx:${NavigationVersion}"

    //jetpack
    const val LifecycleVersion = "2.4.0"
    const val Lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${LifecycleVersion}"
    const val LifecycleService = "androidx.lifecycle:lifecycle-service:${LifecycleVersion}"
    const val LifecycleCompilerAPT = "androidx.lifecycle:lifecycle-compiler:${LifecycleVersion}"
    const val SaveState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${LifecycleVersion}"
    const val LiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${LifecycleVersion}"
    const val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${LifecycleVersion}"
    const val StartUp = "androidx.startup:startup-runtime:1.1.0"

    //Kotlin
    const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib:1.6.10"
    const val CoreKtx = "androidx.core:core-ktx:1.7.0"
    const val CoroutinesVersion = "1.6.0"
    const val CoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${CoroutinesVersion}"
    const val CoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${CoroutinesVersion}"

    //Android视图相关
    const val RecyclerView = "androidx.recyclerview:recyclerview:1.2.1"
    const val Viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
    const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:2.1.3"
    const val SwipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
    //cardView
    const val CardView = "androidx.cardview:cardview:1.0.0"
    //flexbox  https://github.com/google/flexbox-layout
    const val FlexBox = "com.google.android.flexbox:flexbox:3.0.0"

    /*----------工具-----------*/

    //ARouter https://github.com/alibaba/ARouter/blob/master/README_CN.md
    const val ARoute = "com.alibaba:arouter-api:1.5.2"
    const val ARouteCompiler = "com.alibaba:arouter-compiler:1.5.2"
    const val ARouteRegister = "com.alibaba:arouter-register:1.5.2"
    const val ARouterAnnotation = "com.alibaba:arouter-annotation:1.0.6"

    // 自动生成SPI暴露服务文件
    const val AutoServiceVersion = "1.0"
    const val AutoService = "com.google.auto.service:auto-service:${AutoServiceVersion}"
    const val AutoServiceAnnotations = "com.google.auto.service:auto-service-annotations:${AutoServiceVersion}"

    const val LeakCanary = "com.squareup.leakcanary:leakcanary-android:2.7"
    // mmkv https://github.com/Tencent/MMKV
    const val MMKV = "com.tencent:mmkv-static:1.2.10"
    //room
    const val Room = "androidx.room:room-runtime:2.3.0"
    const val RoomAnnotationProcessor = "androidx.room:room-compiler:2.3.0"
    // Glide
    const val Glide = "com.github.bumptech.glide:glide:4.12.0"
    const val GlideKapt = "com.github.bumptech.glide:compiler:4.12.0"
    //glide图片变换库  https://github.com/wasabeef/glide-transformations
    const val GlideTran = "jp.wasabeef:glide-transformations:4.3.0"
    //rxjava
    const val Rxjava3 = "io.reactivex.rxjava3:rxjava:3.0.13"
    const val RxAndroid3  = "io.reactivex.rxjava3:rxandroid:3.0.0"

    // Coil-Kotlin图片加载框架 https://coil-kt.github.io/coil/README-zh/
    const val CoilVersion = "1.4.0"
    const val Coil = "io.coil-kt:coil:${CoilVersion}"
    const val CoilGIF = "io.coil-kt:coil-gif:${CoilVersion}"
    const val CoilSVG = "io.coil-kt:coil-svg:${CoilVersion}"
    const val CoilVideo = "io.coil-kt:coil-video:${CoilVersion}"


    /*-----------网络---------------*/

    //okhttp
    const val OkHttp = "com.squareup.okhttp3:okhttp:4.9.0"
    const val OkHttpInterceptorLogging = "com.squareup.okhttp3:logging-interceptor:4.9.0"
    //Retrofit
    const val RetrofitVersion = "2.9.0"
    const val Retrofit = "com.squareup.retrofit2:retrofit:${RetrofitVersion}"
    const val Retrofitscalars = "com.squareup.retrofit2:converter-scalars:${RetrofitVersion}"
    const val Retrofitadapter = "com.squareup.retrofit2:adapter-rxjava3:${RetrofitVersion}"
    const val RetrofitConverter = "com.squareup.retrofit2:converter-gson:2.9.0"
    //gson
    const val Gson = "com.google.code.gson:gson:2.8.9"
    const val Butterknife = "com.jakewharton:butterknife:9.0.0"
    const val ButterknifeCompiler = "butterknife-compiler"



    /*-----第三方库，TODO:添加第三方库时请加上文档链接------*/

    //下拉刷新布局 https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_property.md
    const val SmartRefreshKernel = "com.scwang.smart:refresh-layout-kernel:2.0.3"       //核心必须依赖
    const val SmartRefreshClass = "com.scwang.smart:refresh-header-classics:2.0.3"      //经典刷新头
    const val SmartRefreshRadar = "com.scwang.smart:refresh-header-radar:2.0.3"         //雷达刷新头
    const val SmartRefreshfalsify = "com.scwang.smart:refresh-header-falsify:2.0.3"     //虚拟刷新头
    const val SmartRefreshmaterial = "com.scwang.smart:refresh-header-material:2.0.3"   //谷歌刷新头
    const val SmartRefreshtwolevel = "com.scwang.smart:refresh-header-two-level:2.0.3"  //二级刷新头
    //base-recyclerview https://github.com/CymChad/BaseRecyclerViewAdapterHelper/blob/master/readme/0-BaseRecyclerViewAdapterHelper.md
    const val BRVA3 = "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.7"
    //阴影    https://github.com/lihangleo2/ShadowLayout
    const val ShadowLayout  = "com.github.lihangleo2:ShadowLayout:3.2.4"
    //动画库 https://lottiefiles.com/
    const val Lottie  = "com.airbnb.android:lottie:3.7.0"
    //toast工具   https://github.com/xluu233/ToastBox
    const val ToastBox = "com.github.xluu233:ToastBox:0.7.5"
    //状态栏工具   https://liangjingkanji.github.io/StatusBar/
    const val StatusBar = "com.github.liangjingkanji:StatusBar:2.0.1"
    //ViewBinding工具   https://github.com/hi-dhl/Binding/blob/main/doc/README_CN.md
    const val Binding = "com.hi-dhl:binding:1.1.3"
    //https://github.com/st235/ExpandableBottomBar
    const val ExpandableBottomBar = "com.github.st235:expandablebottombar:1.4.0"
    //Android通用工具库  https://github.com/Blankj/AndroidUtilCode/blob/master/lib/utilcode/README-CN.md
    const val AndroidUtilCode = "com.blankj:utilcodex:1.31.0"
    //AndroidAutoSize适配方案   https://github.com/JessYanCoding/AndroidAutoSize/blob/master/README-zh.md
    const val AndroidAutoSize = "com.github.JessYanCoding:AndroidAutoSize:v1.2.1"
    //Spannable https://github.com/liangjingkanji/spannable
    const val Spannable = "com.github.liangjingkanji:spannable:1.1.4"
    //引导 https://github.com/xluu233/NewbieGuide
    const val GuideView = "com.github.xluu233:NewbieGuide:v3.0.0"
    //https://github.com/Baseflow/PhotoView
    const val PhotoView = "com.github.chrisbanes:PhotoView:2.3.0"



}