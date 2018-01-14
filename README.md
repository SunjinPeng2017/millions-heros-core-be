## millions-heros-core-be(百万英雄、冲顶大会,答题辅助器)
### 效果展示
![baiwan](http://ozfuaes8n.bkt.clouddn.com/IMG_0393_1.png) ![baiwan_jietu](http://ozfuaes8n.bkt.clouddn.com/IMG_1515936066642_1.png) ![baiwan_yunxing](http://ozfuaes8n.bkt.clouddn.com/wukong_1.png)</br>
![chongding](http://ozfuaes8n.bkt.clouddn.com/IMG_0386_2.png) 
![chongding_jietu](http://ozfuaes8n.bkt.clouddn.com/IMG_1515936234597_2.png) 
![chongding_yunxing](http://ozfuaes8n.bkt.clouddn.com/kongchengji_2.png)</br>
</br>
### 原理解析
* 原理上Android、IOS;所有相关应用都支持，因为只需要截取问题和答案即可
* 利用截图工具将题目截图至电脑指定文件夹
* spring定时任务每隔1秒钟扫描一次本地文件是否有截图
* 裁剪截图中题目及答案部分
* 利用OCR识别图中的问题和答案
* 调用百度 `简单搜索` APP接口搜索答案，实测比百度搜索结果更准确
* 将搜索出的结果与答案进行匹配，找出能匹配出最多条数的答案
* 给出建议答案
</br>
### 不足及优化
* 需要手工截图，有点耽误时间
* 只能百度搜索第一页相关结果，而没有去遍历相关链接，答案可能不准确
* OCR有时识别较慢，需3S
* 匹配算法还有待优化，可考虑用多线程去匹配结果(目前这部分需要时间较少)
* 需要安装较多插件才能运行该项目，不易上手
</br>
### 使用教程
* 该项目IDE为IDEA，eclipse未测试
* 安装截图工具`PP助手`用于截取Android、IOS图片
* 安装tesseract用于识别图片文字
    * 下载该[压缩包](https://pan.baidu.com/s/1smAQneP)
    * 在path中新增环境变量，例如:`D:\Softwares\tesseract\tesseract`
    * 新建`TESSDATA_PREFIX`环境变量，例如:`D:\Softwares\tesseract\tessdata`
* 配置`application.properties`中相关参数
* 运行`Application.java`中的`main`方法
</br>
### TIPS
* 截图保存的文件名可随意命名，在答题时建议`CTRL+V` + `ENTER`键可减少时间
* 如在运行中出现BUG，可提交issue
</br>
### FINALLY
* 该项目仅供学习参考，切勿用于商业用途
* 如有问题，可加本人QQ:386007796