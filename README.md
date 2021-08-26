## mvvm_develop

Android技术迭代不休，今天学会的东西明天可能记过时了，不管如何，我们只有在学习中才会成长。

这是一个MVVM快速开发框架，是对目前一些较热门的技术实践，在学习某个新技术的时候，有一个测试环境。日常的Demo也会方在其中，并整理了日常开发中的常用工具，在有新的需求的时候能够快速进行开发。  

本库主要采用Kotlin+Jetpack组件+组件化，采用MVVM模式，目标是搭建一个自己顺手的快速开发框架。

> 由于本人太菜，可能这个库并不适合你，如果有错误和不同的地方，欢迎大家指点。

## 主要技术点

### 基础功能封装

> 这里包含了对BaseActivity、BaseFragment封装设计，比如viewBidning和databinding的处理。

参考：[写一个MVVM快速开发框架（一）基础类封装](https://juejin.cn/post/6989918599007698957)


### 组件化搭建
> 各个module采用统一依赖管理，页面跳转采用ARouter,页面通信采用接口编程思想，module对外提供服务,application初始化采用@AutoService

参考：[写一个MVVM快速开发框架（二）组件化改造](https://juejin.cn/post/6995082240287850527)
<!-- ![组件化架构.jpg](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1cb79735d1be4b1ab88777f78b7e2be9~tplv-k3u1fbpfcp-watermark.image) -->


### 单Activity+多Fragment模式
> 将Activity作为容器，使用Navigation作为导航，可以有效提高页面跳转效率。

参考：[谈一谈“单Activity+多Fragment”模式](https://juejin.cn/post/6997422487654891533)


### 数据处理
> 使用LiveData进行事件通信，Room+mmkv数据处理，网络缓存设计

参考：[写一个MVVM快速开发框架（四）优雅的数据处理](https://juejin.cn/post/7000627451575566373)




---
欢迎大家Star，`master`分支目前没有更新，请切换至`develop`分支
