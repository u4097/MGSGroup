package com.apptimizm.mgs.presentation.utils.date

import android.annotation.SuppressLint
import com.apptimizm.mgs.presentation.utils.Constants
import java.text.DateFormat
import java.text.DateFormatSymbols

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*


class DateTimeUtils {


    companion object {

        // Simple Date Format
        private val FORMAT_DATE = SimpleDateFormat("yyyy-MM-dd", Locale("RU"))

        private val FORMAT_DATETIME = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ", Locale("RU"))


        private val FORMAT_DATETIME_REMOTE = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale("ru"))
        private val FORMAT_DATETIME_LOCAL = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale("ru"))
        private val FORMAT_HH_MM_SS = SimpleDateFormat("HH:mm:ss", Locale("ru"))


        fun getUpdateDate(date: String): String {
            val russian = Locale("ru")
            val newMonths = arrayOf(
                "января",
                "февраля",
                "марта",
                "апреля",
                "мая",
                "июня",
                "июля",
                "августа",
                "сентября",
                "октября",
                "ноября",
                "декабря"
            )
            val dfs = DateFormatSymbols.getInstance(russian)
            dfs.months = newMonths
            val df = DateFormat.getDateInstance(DateFormat.LONG, russian)
            val sdf = df as SimpleDateFormat
            sdf.dateFormatSymbols = dfs

            var jud: Date? = null
            try {
                jud = Constants.SIMPLE_LOCAL_DATE_FORMAT.parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            val month = sdf.format(jud)
            println(month) // output: 28 февраля 2014 г.
            return month
        }


        fun formatTime(time: String): String {
            val hh = time.substringBefore(":").trim()
            val mm = time.substringAfter(":").substringAfter(":").trim()
            return "$hh-$mm"
        }


        val currentDate: String
            get() {
                val currentDate = Calendar.getInstance().time
                return FORMAT_DATE.format(currentDate)
            }

        val currentDateTime: String
            get() {
                val currentDate = Calendar.getInstance().time
                return FORMAT_DATETIME.format(currentDate)
            }


        val factTime: String
            @SuppressLint("NewApi")
            get() {
                val localTime = LocalTime.now()
                return format(localTime.hour) + "-" + format(localTime.minute)
            }

        private fun format(num: Int): String {
            return String.format("%02d", num)
        }

    }

}