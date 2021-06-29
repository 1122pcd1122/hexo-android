# Android博客后端
> ktor kotlin 后端

## 模块
- 文章管理模块 article_module
    1. 获取文章的html
    2. 获取所有文章名
    3. 根据年份获取文章信息
    4. 文章数
    5. 根据标签获取文章
- 标签管理模块 label_module
    1.标签数量
    2. 获取所有标签
- 用户管理模块 user_module
    1. 用户所有信息
    2. 用户icon
- 文件监听模块 fileListener_module
    1. 监听指定文章存放文件夹中文件变化
 ## Ktor介绍
> 用Ktor官方(ktor.io/)一句话来介绍: Ktor是一个用于创建微服务、web应用程序等异步框架，它很简单、有趣并且免费开源。它是由jetbrains官方开源，目前已经有8.2K+ star (github.com/ktorio/ktor),该框架在国内大家可能比较陌生但是在国外还是很受欢迎的，Ktor可以说是为Kotlin中异步而生的框架，它最底层基于Kotlin Coroutine协程框架，支持了Client、Server双端异步特性并且在Client、Server双端上对WebSocket、Socket有了很好的支持。

>  介绍语及图片来自于:   作者：熊喵先生 链接：https://juejin.cn/post/6955132053532704804 来源：掘金

![介绍图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/7efcf708afe74d8f811c713ef0963918~tplv-k3u1fbpfcp-zoom-1.image)

### 本项目的启动方式 
Application.kt
```

//服务器使用Tomact
fun main(args: Array<String>): Unit = io.ktor.server.tomcat.EngineMain.main(args)

fun Application.module() {
    
    //加载gson组件
    install(ContentNegotiation) {
        gson {
        }
    }
    
    //路由
    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

    }

    //自定义方法 读取.conf中的内容
    conf(environment)
    
}
```
application.conf文件
```
ktor {

    //运行环境 LOCAL PRE ONLINE
    env = LOCAL

    //是否处于开发模式
    development = true
    deployment {
        host = 0.0.0.0
        port = 8080
        watch = [blogServer]
    }
    
    //所有模块
    application {
        modules = [ "com.example.ApplicationKt.module", "com.example.article_module.ArticleModuleKt.article","com.example.label_module.LabelModuleKt.label", "com.example.user_module.UserModuleKt.user","com.example.fileListener_module.FileListenModuleKt.fileListen"]
    }
    
    //自定义配置内容
    security {
        md = "C:\\Users\\peichendong\\Desktop\\blog\\article_md"
        html = "C:\\Users\\peichendong\\Desktop\\blog\\article_html"

        //把db相关配置放入security，日志输出会对该部分内容用*进行隐藏处理
        localDb {//自定义属性localDb
            url = "jdbc:mysql://localhost:3306/blog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
            driver = "com.mysql.cj.jdbc.Driver"
            user = "root"
            password = "peichendong17"
        }

    }

}

```
### 架构设计

#### 应用模块化 

划分为四个模块 每个模块又若干条Request/Response管道
```
application {
        modules = [ "com.example.ApplicationKt.module", "com.example.article_module.ArticleModuleKt.article","com.example.label_module.LabelModuleKt.label", "com.example.user_module.UserModuleKt.user","com.example.fileListener_module.FileListenModuleKt.fileListen"]
    }
```

## 难点1 文件监听模块

> 目前解决的难点就是 文件监听功能 
> 借鉴 Common-io 库 中的文件监听功能 实现了Kotlin + 协程 的文件监听 
> 使用**观察者模式**来通知更新  设置自定义时间间隔来**轮询**监听文件夹
   
FileListener类 使用Kotlin的高阶函数来自定义 触发事件后的响应逻辑
```kotlin
    //开始监听
    var start: (() -> Unit)? = null
    //停止监听
    var stop: (() -> Unit)? = null
    var fileCreate: ((file: File) -> Unit)? = null
    var fileDelete: ((file: File) -> Unit)? = null
    var fileChange: ( (file:File) -> Unit)? = null
    var fileDirectoryCreate : ( (file:File) -> Unit)? = null
    var fileDirectoryDelete : ( (file:File) -> Unit)? = null
    var fileDirectoryChange : ( (file:File) -> Unit)? = null
```
FileObserver类 实现具体的监听逻辑 是如何来判断发生了 文件/文件夹 创建,变化事件
- initialize() 初始化状态
- checkAndNotify() 检查状态并通知
- doCreate() 向注册的侦听器触发目录文件创建事件。
- doDelete() 向注册的侦听器触发目录文件删除事件。
- doMatch() 向注册的侦听器触发目录文件更改事件。

接下来具体看一看 它们的主要实现逻辑 
```kotlin
    /**
     * 初始化观察者
     *
     * @throws Exception if an error occurs
     */
    @Throws(Exception::class)
    fun initialize() {
        //初始化 当前文件或者目录的状态 
        rootEntry!!.refresh(rootEntry!!.getFile())
        
        //如果是目录 将目录中的文件创建对应的MyFile实例
        val children: Array<MyFile> = doListFiles(rootEntry!!.getFile(), rootEntry!!)
        
        //设置目录中的文件为自己的子文件
        rootEntry!!.setChildren(children)
    }
    
     /**
         * 比较已创建、修改或删除的文件的两个文件列表。
         *
         * @param parent 父条目
         * @param previous 原始文件列表 初始化文件状态
         * @param files  当前文件列表  获取当前的文件状态
         */
        private fun checkAndNotify(parent: MyFile, previous: Array<MyFile>, files: Array<File>) {
            var c = 0
            val current: Array<MyFile> = if (files.isNotEmpty()) Array(files.size) {
                MyFile()
            } else MyFile.EMPTY_FILE_ENTRY_ARRAY
            for (entry in previous) {
    
                // 如果出现 与初始化时获取的文件夹中对应不上的文件名 说明 创建了新文件
                while (c < files.size && comparator!!.compare(entry.getFile(), files[c]) > 0) {
                    current[c] = createFileEntry(parent, files[c])
                    doCreate(current[c])
                    c++
                }
    
                if (c < files.size && comparator!!.compare(entry.getFile(), files[c]) == 0) {
                    doMatch(entry, files[c])
                    checkAndNotify(entry, entry.getChildren(), listFiles(files[c]))
                    current[c] = entry
                    c++
                } else {
                    checkAndNotify(entry, entry.getChildren(), FileUtil.EMPTY_FILE_ARRAY)
                    doDelete(entry)
                }
            }
            while (c < files.size) {
                current[c] = createFileEntry(parent, files[c])
                doCreate(current[c])
                c++
            }
            parent.setChildren(current)
        }

    
```
MyFile类 描述文件/目录的状态 
核心方法
```kotlin
    /**
     * 刷新文件属性 并指示是否更改
     */
    fun refresh(file: File?):Boolean{

        //缓存上一次修改/变化的文件/文件夹属性
        val origExist = exists
        val origLastModified = lastModified
        val origDirectory = directory
        val origLength = length

        name = file?.name
        exists =file?.exists()!!

        directory = exists && file.isDirectory

        lastModified = try {

            if (exists){
                FileUtil.lastModified(file)
            }else{
                0
            }
        }catch (e: IOException){
            0
        }

        length = if (exists && !directory){
            file.length()
        }else{
            0
        }

        //返回是否有更改
        return exists != origExist || lastModified != origLastModified || directory != origDirectory || length != origLength
    }
```
