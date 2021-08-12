package com.example.user_module

import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@KtorExperimentalAPI
fun Application.module() {


    routing {

        TimerManager()
//        clockIn()
        userInfo()
        userIcon()
        btnNight()
        btnWhite()

    }
}

class TimerManager {

    init {
        clock(2)
        clock(13)
        clock(15)
    }

    private fun clock(hour: Int) {

        when (hour) {
            2 -> {
                //早上
                logger.info("开启早上打卡功能")
            }
            13 -> {
                //中午
                logger.info("开启中午打开功能")
            }
            15 -> {
                //下午
                logger.info("开启下午打卡功能")
            }
        }

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        var time = calendar.time

        if (time.before(Date())) {
            time = this.addDay(date = time, 1)
        }

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                clockIn()
            }
        }, time, PERIOD_DAY)
    }

    private fun addDay(date: Date, num:Int):Date{
        val startDT = Calendar.getInstance()
        startDT.time = date
        startDT.add(Calendar.DAY_OF_MONTH,num)
        return startDT.time
    }


    companion object{
        const val PERIOD_DAY:Long = 24 * 60 * 60 * 1000
    }

}





private fun clockIn() {
    UserFunction.clockIn().forEach {

        val clock = it.split(",")
        val temperature = String.format("%.1f", Math.random() + 36)
        val clockIn = ResponseInfo.ClockIn(
            mobile = clock[0],
            temperature = temperature,
            city = clock[1],
            district = clock[2],
            address = clock[3]
        )

        logger.info(clock[3])


        CoroutineScope(Dispatchers.IO).launch {

            val httpClient = HttpClient(CIO)
            val submitForm = httpClient.submitForm<String>(
                url = "http://yx.ty-ke.com/Home/Monitor/monitor_add",
                formParameters = Parameters.build {
                    append("mobile", clockIn.mobile!!)
                    append("title", clockIn.temperature!!)
                    append("jk_type", "健康")
                    append("wc_type", "否")
                    append("jc_type", "否")
                    append("province", "山西省")
                    append("city", clockIn.city!!)
                    append("district", clockIn.district!!)
                    append("address", clockIn.address!!)
                    append("is_verify", "0")
                },
                //使用post
                encodeInQuery = false
            )

            logger.info("${clockIn.mobile}打卡$submitForm")
        }


    }
}