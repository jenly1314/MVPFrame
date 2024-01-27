# MVPFrame

![Image](app/src/main/ic_launcher-web.png)

[![Download](https://img.shields.io/badge/download-App-blue.svg)](https://raw.githubusercontent.com/jenly1314/MVPFrame/master/app/release/app-release.apk)
[![JitPack](https://jitpack.io/v/jenly1314/MVPFrame.svg)](https://jitpack.io/#jenly1314/MVPFrame)
[![CI](https://travis-ci.org/jenly1314/MVPFrame.svg?branch=master)](https://travis-ci.org/jenly1314/MVPFrame)
[![CircleCI](https://circleci.com/gh/jenly1314/MVPFrame.svg?style=svg)](https://circleci.com/gh/jenly1314/MVPFrame)
[![API](https://img.shields.io/badge/API-15%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/mit-license.php)

MVPFrame for Android 是一个集合了 Retrofit2 + RXJava2 + OkHttp3 + Mosby3 二次封装的MVP快速开发框架，为敏捷开发而生。

> 你可以直接下载 [演示App](https://raw.githubusercontent.com/jenly1314/MVPFrame/master/app/release/app-release.apk) 体验效果

## 架构
![Image](image/mvp_architecture.jpg)

## 引入

### Gradle:

1. 在Project的 **build.gradle** 或 **setting.gradle** 中添加远程仓库

    ```gradle
    repositories {
        //...
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
    ```

2. 在Module的 **build.gradle** 里面添加引入依赖项

    ```gradle
    implementation 'com.github.jenly1314:MVPFrame:1.1.3'
    ```

## 使用

### 集成步骤

集成步骤代码示例 （示例出自于[app](app)中）

Step.1 启用DataBinding，在你项目中的build.gradle的android{}中添加配置：
```gradle
dataBinding {
    enabled true
}
```

Step.2 通过ApiManager初始化（在你项目的Application中的onCreate方法里面初始化）
```Java
//初始化：第一个参数是基本的Url地址，第二个参数是超时时间（单位为秒，可不填，默认为15s）
ApiManager.init(String baseUrl,int timeout);
```

Step.3 定义一个类似于[app](app)中的Api来管理接口
```Java
    public class Api {

        private ApiService mApiService;

        private static Api sInstance;

        public static Api getInstance(){
            if(sInstance == null){
                synchronized (Api.class){
                    if(sInstance == null){
                        sInstance = new Api();
                    }
                }
            }
            return sInstance;
        }

        private Api(){
            mApiService = ApiManager.getInstance().getApiService(ApiService.class);
        }

        /**
         * 获取推荐诗词
         * @param callback
         */
        public void getRecommendPoetry(SimpleCallback<Result<PoetryInfo>> callback){
            ApiObserver.subscribe(mApiService.getRecommendPoetry(),callback);
        }

    }

```
更多使用详情，请查看[app](app)中的源码使用示例或直接查看[API帮助文档](https://jitpack.io/com/github/jenly1314/MVPFrame/latest/javadoc/)

## 相关推荐

#### [MVVMFrame](https://github.com/jenly1314/MVVMFrame) 一个基于JetPack构建的MVVM快速开发框架

## 版本记录

#### v1.1.3：2019-6-21
*  优化部分细节
*  Retrofit更新至v2.6.0
*  RxJava更新至v2.2.9

#### v1.1.2：2019-5-27
*  完善BindingFragment相关子类

#### v1.1.1：2019-4-29
*  新增DataBinding支持
*  Retrofit更新至v2.6.0
*  RxJava更新至v2.2.8
*  RxAndroid更新至v2.1.1

#### v1.1.0：2018-7-23
*  Retrofit更新至v2.4.0
*  RxJava更新至v2.1.16
*  RxAndroid更新至v2.0.2

#### v1.0.6：2018-5-17
*  暴露Dialog更多参数，让配置更加灵活

#### v1.0.5：2018-1-29
*  新增默认信任SSL证书，支持https

#### v1.0.4：2018-1-18
*  优化BaseProgressDialog

#### v1.0.3：2017-12-4
*  新增请求相应Log日志信息方便调试。

#### v1.0.2：2017-9-21
*  新增QuickActivity、QuickFragment。

#### v1.0.1：2017-8-18
*  将retrofit中的adapter修改为adapter-rxjava2 v1.0

#### v1.0.0：2017-7-5
*  MVPFrame初始版本

## 赞赏
如果您喜欢MVPFrame，或感觉MVPFrame帮助到了您，可以点右上角“Star”支持一下，您的支持就是我的动力，谢谢 :smiley:
<p>您也可以扫描下面的二维码，请作者喝杯咖啡 :coffee:

<div>
   <img src="https://jenly1314.github.io/image/page/rewardcode.png">
</div>

## 关于我

| 我的博客                                                                                | GitHub                                                                                  | Gitee                                                                                  | CSDN                                                                                 | 博客园                                                                            |
|:------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------|
| <a title="我的博客" href="https://jenly1314.github.io" target="_blank">Jenly's Blog</a> | <a title="GitHub开源项目" href="https://github.com/jenly1314" target="_blank">jenly1314</a> | <a title="Gitee开源项目" href="https://gitee.com/jenly1314" target="_blank">jenly1314</a>  | <a title="CSDN博客" href="http://blog.csdn.net/jenly121" target="_blank">jenly121</a>  | <a title="博客园" href="https://www.cnblogs.com/jenly" target="_blank">jenly</a>  |

## 联系我

| 微信公众号        | Gmail邮箱                                                                          | QQ邮箱                                                                              | QQ群                                                                                                                       | QQ群                                                                                                                       |
|:-------------|:---------------------------------------------------------------------------------|:----------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------|
| [Jenly666](http://weixin.qq.com/r/wzpWTuPEQL4-ract92-R) | <a title="给我发邮件" href="mailto:jenly1314@gmail.com" target="_blank">jenly1314</a> | <a title="给我发邮件" href="mailto:jenly1314@vip.qq.com" target="_blank">jenly1314</a> | <a title="点击加入QQ群" href="https://qm.qq.com/cgi-bin/qm/qr?k=6_RukjAhwjAdDHEk2G7nph-o8fBFFzZz" target="_blank">20867961</a> | <a title="点击加入QQ群" href="https://qm.qq.com/cgi-bin/qm/qr?k=Z9pobM8bzAW7tM_8xC31W8IcbIl0A-zT" target="_blank">64020761</a> |

<div>
   <img src="https://jenly1314.github.io/image/page/footer.png">
</div>
