package common_module.db

import com.example.common_module.utils.ConfUtil
import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.logging.ConsoleLogger
import me.liuwj.ktorm.logging.LogLevel

/**
 * @Description TODO
 * @author 裴晨栋
 * @date 2021/4/8-10:15
 */
class DB {

    companion object {
        val database = Database.connect(
                url = ConfUtil.dbUrl.toString(),
                driver = ConfUtil.dbDriver.toString(),
                user = ConfUtil.dbUser.toString(),
                password = ConfUtil.dbPWD.toString(),
                logger = ConsoleLogger(threshold = LogLevel.INFO)
        )
    }


}