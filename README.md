# MVPFrame
[![Download](https://img.shields.io/badge/download-App-blue.svg)](https://raw.githubusercontent.com/jenly1314/MVPFrame/master/app/release/app-release.apk)
[![Jitpack](https://jitpack.io/v/jenly1314/MVPFrame.svg)](https://jitpack.io/#jenly1314/MVPFrame)
[![CI](https://travis-ci.org/jenly1314/MVPFrame.svg?branch=master)](https://travis-ci.org/jenly1314/MVPFrame)
[![API](https://img.shields.io/badge/API-15%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/mit-license.php)
[![Blog](https://img.shields.io/badge/blog-Jenly-9933CC.svg)](http://blog.csdn.net/jenly121)
[![QQGroup](https://img.shields.io/badge/QQGroup-20867961-blue.svg)](http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1411582c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad)

MVPFrame for Android 是一个集合了 Retrofit2 + RXJava2 + OkHttp3 + Mosby3 二次封装的MVP架构基类库，为敏捷开发而生。

## 架构
![Image](image/mvvm_architecture.jpg)

## 引入

### Maven：
```maven
<dependency>
  <groupId>com.king.frame</groupId>
  <artifactId>mvpframe</artifactId>
  <version>1.1.1</version>
  <type>pom</type>
</dependency>
```
### Gradle:
```gradle
implementation 'com.king.frame:mvpframe:1.1.1'
```
### Lvy:
```lvy
<dependency org='com.king.frame' name='mvpframe' rev='1.1.1'>
  <artifact name='$AID' ext='pom'></artifact>
</dependency>
```

###### 如果Gradle出现compile失败的情况，可以在Project的build.gradle里面添加如下：（也可以使用上面的GitPack来complie）
```gradle
allprojects {
    repositories {
        maven { url 'https://dl.bintray.com/jenly/maven' }
    }
}
```

## 引入的库：
```gradle
    compileOnly 'com.android.support:support-v4:28.0.0'
    compileOnly 'com.android.support:appcompat-v7:28.0.0'

    // Model-View-Intent
    api 'com.hannesdorfmann.mosby3:mvi:3.1.1'
    // Plain MVP
    api 'com.hannesdorfmann.mosby3:mvp:3.1.1'
    // MVP + ViewState support
    api 'com.hannesdorfmann.mosby3:viewstate:3.1.1'

    api 'com.squareup.retrofit2:retrofit:2.5.0'
    api 'com.squareup.retrofit2:converter-gson:2.5.0'
    api 'com.squareup.retrofit2:adapter-rxjava2:2.5.0'

    api 'io.reactivex.rxjava2:rxjava:2.2.8'
    api 'io.reactivex.rxjava2:rxandroid:2.1.1'

    api 'com.jakewharton.timber:timber:4.7.1'
```

## 示例

核心代码（在你项目的Application中的onCreate方法里面初始化）
```Java
//初始化：第一个参数是基本的Url地址，第二个参数是超时时间（单位为秒，可不填，默认为20s）
ApiManager.init(String baseUrl,int timeout);
```

代码示例 （示例出自于[app](app)中的Api.java）
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
更多使用详情，请查看[app](app)中的源码使用示例

## 版本记录

#### v1.1.1：2019-4-29
*  新增DataBinding支持
*  Retrofit更新至v2.5.0
*  RxJava更新至v2.2.8
*  RxAndroid更新至v2.1.1

#### v1.1.0：2018-5-17
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
如果您喜欢MVPFrame，或感觉MVPFrame帮助到了您，可以点右上角“Star”支持一下，您的支持就是我的动力，谢谢 :smiley:<p>
也可以扫描下面的二维码，请作者喝杯咖啡 :coffee:
    <div>
        <img src="https://image-1252383324.cos.ap-guangzhou.myqcloud.com/pay/wxpay.png" width="280" heght="350">
        <img src="https://image-1252383324.cos.ap-guangzhou.myqcloud.com/pay/alipay.png" width="280" heght="350">
        <img src="https://image-1252383324.cos.ap-guangzhou.myqcloud.com/pay/qqpay.png" width="280" heght="350">
    </div>

## 关于我
   Name: <a title="关于作者" href="https://about.me/jenly1314" target="_blank">Jenly</a>

   Email: <a title="欢迎邮件与我交流" href="mailto:jenly1314@gmail.com" target="_blank">jenly1314#gmail.com</a> / <a title="给我发邮件" href="mailto:jenly1314@vip.qq.com" target="_blank">jenly1314#vip.qq.com</a>

   CSDN: <a title="CSDN博客" href="http://blog.csdn.net/jenly121" target="_blank">jenly121</a>

   Github: <a title="Github开源项目" href="https://github.com/jenly1314" target="_blank">jenly1314</a>

   加入QQ群: <a title="点击加入QQ群" href="http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1411582c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad" target="_blank">20867961</a>
   <div>
       <img src="https://image-1252383324.cos.ap-guangzhou.myqcloud.com/jenly666.png">
       <img src="https://image-1252383324.cos.ap-guangzhou.myqcloud.com/qqgourp.png">
   </div>
