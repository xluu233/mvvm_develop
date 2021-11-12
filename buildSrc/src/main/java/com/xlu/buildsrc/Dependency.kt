package com.xlu.buildsrc

/**
 * @ClassName DependencyConfig
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/5 10:02
 */
object Dependency  {

    //是否允许module独立允许
    object RunAlone{
        const val base = false
        const val app_test = true
    }

    object ProjectConfig {
        const val applicationId = "com.example.mvvm_develop"
        const val applicationId_home = "com.xlu.test"

        const val compileSdkVersion = 30
        const val buildToolsVersion = "30.0.2"
        const val minSdkVersion = 23
        const val targetSdkVersion = 30

        const val versionCode = 57
        const val versionName = "2.6.0"
        const val isAppMode = false
    }

    object Version{

        // Android--------------------------------------------------------------
        const val Material = "1.4.0"
        const val AppCompat = "1.3.1"
        const val ConstraintLayout = "2.0.4"
        const val ActivityKtx = "1.1.0"
        const val FragmentKtx = "1.2.5"
        const val MultiDex = "2.0.1"
        const val legacySupport = "1.0.0"

        // Kotlin----------------------------------------------------------------
        const val Kotlin = "1.5.20"
        const val Coroutines = "1.5.0"
        const val CoreKtx = "1.6.0"
        
        // JetPack---------------------------------------------------------------
        const val Lifecycle = "2.3.1"
        const val Navigation = "2.4.0-alpha05"

        // GitHub----------------------------------------------------------------
        const val OkHttp = "4.9.0"                          // OkHttp
        const val OkHttpInterceptorLogging = "4.9.1"        // OkHttp 请求Log拦截器
        const val Retrofit = "2.9.0"                        // Retrofit
        const val RetrofitConverterGson = "2.9.0"           // Retrofit Gson 转换器
        const val Gson = "2.8.7"                            // Gson
        const val MMKV = "1.2.10"                            // 腾讯 MMKV 替代SP
        const val ARoute = "1.5.2"                          // 阿里路由
        const val ARouteCompiler = "1.5.2"                  // 阿里路由 APT
        const val RecyclerViewAdapter = "3.0.4"             // RecyclerViewAdapter
        const val EventBus = "3.2.0"                        // 事件总线
        const val Permission = "0.12"                       // 权限申请
        const val LeakCanary = "2.7"                        // 检测内存泄漏
        const val AutoService = "1.0"                       // 自动生成SPI暴露服务文件
        const val Coil = "1.3.2"                            // Kotlin图片加载框架
        const val Room = "2.3.0"                            //数据库
        const val Glide = "4.12.0"

    }

    object DependencyImp{
        //AndroidX
        const val AppCompat = "androidx.appcompat:appcompat:${Version.AppCompat}"
        const val Material = "com.google.android.material:material:${Version.Material}"
        const val AndroidXLeagcy = "androidx.legacy:legacy-support-v4:${Version.legacySupport}"
        const val ActivityKtx = "androidx.activity:activity-ktx:${Version.ActivityKtx}"
        const val FragmentKtx = "androidx.fragment:fragment-ktx:${Version.FragmentKtx}"
        const val MultiDex = "androidx.multidex:multidex:${Version.MultiDex}"

        //Kotlin
        const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Version.Kotlin}"
        const val CoreKtx = "androidx.core:core-ktx:${Version.CoreKtx}"
        const val CoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Coroutines}"
        const val CoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Coroutines}"

        //navigation
        const val NavigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.Navigation}"
        const val NavigationUI = "androidx.navigation:navigation-ui-ktx:${Version.Navigation}"

        //lifecycle
        const val Lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Lifecycle}"
        const val LifecycleService = "androidx.lifecycle:lifecycle-service:${Version.Lifecycle}"
        const val LifecycleCompilerAPT = "androidx.lifecycle:lifecycle-compiler:${Version.Lifecycle}"
        const val SaveState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.Lifecycle}"
        const val Livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Lifecycle}"
        const val Viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Lifecycle}"
        //recyclerView
        const val RecyclerView = "androidx.recyclerview:recyclerview:1.2.1"
        //viewPager
        const val Viewpager = "androidx.viewpager:viewpager:1.0.0"
        const val Viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
        //cardView
        const val CardView = "androidx.cardview:cardview:1.0.0"
        //flexbox  https://github.com/google/flexbox-layout
        const val FlexBox = "com.google.android.flexbox:flexbox:3.0.0"
        //ConstraintLayout
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Version.ConstraintLayout}"


        /*----------JetPack-----------*/
        const val StartUp = "androidx.startup:startup-runtime:1.1.0"




        /*----------通信----------*/
        //ARouter https://github.com/alibaba/ARouter/blob/master/README_CN.md
        const val ARoute = "com.alibaba:arouter-api:${Version.ARoute}"
        const val ARouteCompiler = "com.alibaba:arouter-compiler:${Version.ARouteCompiler}"
        const val ARouteRegister = "com.alibaba:arouter-register:${Version.ARoute}"
        //eventBus
        const val EventBus = "org.greenrobot:eventbus:${Version.EventBus}"
        const val EventBusAPT = "org.greenrobot:eventbus-annotation-processor:${Version.EventBus}"

        /*----------工具-----------*/
        const val LeakCanary = "com.squareup.leakcanary:leakcanary-android:${Version.LeakCanary}"
        //Bugly https://bugly.qq.com/docs/user-guide/instruction-manual-android/?v=20200622202242
        const val BuglyCrashReport  = "com.tencent.bugly:crashreport:3.3.92" //Bugly SDK
        const val BuglyNativeCrash = "com.tencent.bugly:nativecrashreport:3.9.1" //Bugly NDK

        const val AutoService = "com.google.auto.service:auto-service:${Version.AutoService}"
        const val AutoServiceAnnotations = "com.google.auto.service:auto-service-annotations:${Version.AutoService}"

        //Coil https://coil-kt.github.io/coil/README-zh/
        const val Coil = "io.coil-kt:coil:${Version.Coil}"
        const val CoilGIF = "io.coil-kt:coil-gif:${Version.Coil}"
        const val CoilSVG = "io.coil-kt:coil-svg:${Version.Coil}"
        const val CoilVideo = "io.coil-kt:coil-video:${Version.Coil}"

        // mmkv https://github.com/Tencent/MMKV
        const val MMKV = "com.tencent:mmkv-static:${Version.MMKV}"
        //room
        const val Room = "androidx.room:room-runtime:${Version.Room}"
        const val RoomAnnotationProcessor = "androidx.room:room-compiler:${Version.Room}"

        // Glide
        const val Glide = "com.github.bumptech.glide:glide:${Version.Glide}"
        const val GlideKapt = "com.github.bumptech.glide:compiler:${Version.Glide}"
        //glide图片变换库  https://github.com/wasabeef/glide-transformations
        const val GlideTran  = "jp.wasabeef:glide-transformations:4.3.0"
        //rxjava
        const val Rxjava2 = "io.reactivex.rxjava2:rxjava:2.2.18"
        const val Rxjava3 = "io.reactivex.rxjava3:rxjava:3.0.13"
        const val RxAndroid2  = "io.reactivex.rxjava2:rxandroid:2.1.1"
        const val RxAndroid3  = "io.reactivex.rxjava3:rxandroid:3.0.0"
        const val Rxkotlin = "io.reactivex.rxjava2:rxkotlin:2.2.0"
        //Rxpermissions  https://github.com/tbruyelle/RxPermissions
        const val Rxpermissions  = "com.github.tbruyelle:rxpermissions:${Version.Permission}"
        //rxBinding   https://github.com/JakeWharton/RxBinding
        const val Rxbinding  = "com.jakewharton.rxbinding4:rxbinding:4.0.0"

        /*-----------网络---------------*/
        //okhttp
        const val OkHttp = "com.squareup.okhttp3:okhttp:${Version.OkHttp}"
        const val OkHttpInterceptorLogging = "com.squareup.okhttp3:logging-interceptor:${Version.OkHttpInterceptorLogging}"
        //Retrofit
        const val Retrofit = "com.squareup.retrofit2:retrofit:${Version.Retrofit}"
        const val RetrofitConverter = "com.squareup.retrofit2:converter-gson:${Version.RetrofitConverterGson}"
        const val Retrofitscalars = "com.squareup.retrofit2:converter-scalars:${Version.Retrofit}"
        const val Retrofitadapter = "com.squareup.retrofit2:adapter-rxjava3:${Version.Retrofit}"
        //gson
        const val Gson = "com.google.code.gson:gson:${Version.Gson}"


        /*------------视图-----------------*/
        //banner  https://github.com/youth5201314/banner
        const val YouthBanner = "io.github.youth5201314:banner:2.2.2"
        //base-recyclerview
        const val BaseRecyclerViewAdapter  = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Version.RecyclerViewAdapter}"
        //likeButton
        const val LikeButton  = "com.github.jd-alexander:LikeButton:0.2.3"
        //阴影    https://github.com/lihangleo2/ShadowLayout
        const val ShadowLayout  = "com.github.lihangleo2:ShadowLayout:3.1.4"
        //加载中进度条
        const val Mkloader  = "com.tuyenmonkey:mkloader:1.4.0"
        //动画库 https://lottiefiles.com/
        const val Lottie  = "com.airbnb.android:lottie:3.7.0"
        const val ToastBox = "com.github.xluu233:ToastBox:0.6.1"
    }

}