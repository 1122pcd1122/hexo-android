package fileListener_module


import com.example.common_module.db.dao.LabelDao
import com.example.common_module.db.table.LabelData
import com.example.fileListener_module.fileListener.FileAlterListener
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.slf4j.LoggerFactory
import java.io.File

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/7/28-20:50
 */
class TagsListener : FileAlterListener {


    companion object {
        private val logger = LoggerFactory.getLogger(TagsListener::class.java.name)

    }

    override fun start(file: File?) {
        logger.info("Tags开始监听")
        file?.listFiles()?.forEach {
            if (it.isDirectory){

                val index = it?.listFiles()?.first()
                val tagFile = index?.parentFile
                val indexHtml = getIndexHtml(index)

                val listTitle = listTitle(indexHtml)
                val lastTitleList = mutableListOf<String>()
                listTitle.forEach { title ->
                    if (!LabelDao.isContain(title)){
                        LabelDao.insertTag(LabelData(title,tagFile?.name))
                    }
                    lastTitleList.add(title)
                }

            }
        }


    }


    override fun fileCreate(file: File?) {
        val tag = file?.parentFile?.name
        logger.info("文件创建 $tag")
        val indexHtml = getIndexHtml(file)

        val listTitle = listTitle(indexHtml)
        listTitle.forEach {
            if (!LabelDao.isContain(it)){
                LabelDao.insertTag(LabelData(it,tag))
            }
        }
    }

    private fun listTitle(document: Document?): List<String> {
        val listTitle = mutableListOf<String>()
        val postListClass = document?.getElementsByClass("post-list")
        val postListItemClass = postListClass?.get(0)?.getElementsByClass("post-list-item")
        postListItemClass?.forEach {
            val title = it.getElementsByClass("title").text()
            listTitle.add(title)
        }

        return listTitle
    }

    /**
     * 获取index.html
     */
    private fun getIndexHtml(file: File?): Document? {
        if (!file?.name.equals("index.html")) {

            return null
        }
        return Jsoup.parse(file, "UTF-8")
    }

    override fun fileChange(file: File?) {
        val tag = file?.parentFile?.name
        logger.info("文件变化 $tag")
        val indexHtml = getIndexHtml(file)

        val listTitle = listTitle(indexHtml)
        listTitle.forEach {
            if (!LabelDao.isContain(it)){
                LabelDao.insertTag(LabelData(it,tag))
            }else{
                LabelDao.updateTag(LabelData(it,tag))
            }
        }
    }

    override fun stop(file: File?) {

        logger?.info("${file?.name} --- 监听结束")
    }


    override fun fileDelete(file: File?) {
        val tag = file?.parentFile?.name
        logger.info("文件删除 $tag")
        val indexHtml = getIndexHtml(file)

        val listTitle = listTitle(indexHtml)
        listTitle.forEach {
            LabelDao.deleteTag(LabelData(it,tag))
        }

    }

    override fun fileDirectoryCreate(file: File?) {
    }

    override fun fileDirectoryDelete(file: File?) {

    }

    override fun fileDirectoryChange(file: File?) {

    }
}


