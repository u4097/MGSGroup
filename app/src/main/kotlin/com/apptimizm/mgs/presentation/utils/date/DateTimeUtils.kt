package com.apptimizm.mgs.presentation.utils.date

import com.apptimizm.mgs.presentation.utils.Constants
import timber.log.Timber
import java.text.DateFormat
import java.text.DateFormatSymbols

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.function.Supplier


class DateTimeUtils {

    private val mSupplier: Supplier<LocalDateTime>? = null


    companion object {

        private val FORMAT_DATETIME_REMOTE = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale("ru"))

        private val FORMAT_DATETIME_LOCAL = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale("ru"))

        private val FORMAT_DATETIME = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")

        private val FORMAT_DATE = DateTimeFormat.forPattern("yyyy-MM-dd")


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

        fun getLocalTime(time: String): LocalTime {
            val fmt = DateTimeFormat.forPattern("HH:mm:ss")
            return fmt.parseLocalTime(time)
        }



        fun getStringFromLocalTime(time: String): String {
            val fmt = DateTimeFormat.forPattern("HH:mm:ss")
            var localTime: LocalTime? = null
            try {
                localTime = fmt.parseLocalTime(time)
            } catch (e: Exception) {
                     Timber.d(e)
            }

            return format(localTime?.hourOfDay!!) + "-" + format(localTime.minuteOfHour)
        }

        fun format(num: Int): String {
            return String.format("%02d", num)
        }


        val currentDate: String
            get() = FORMAT_DATE.print(LocalDate.now())

        //    DateTimeZone tz = DateTimeZone.forID("Europe/Moscow");
        val currentDateTime: String
            get() {
                val utc = DateTime(DateTimeZone.UTC)
                var moskowDateTime: DateTime? = null
                var tz: DateTimeZone? = null
                try {
                    tz = DateTimeZone.forTimeZone(TimeZone.getDefault())
                    moskowDateTime = utc.toDateTime(tz)
                    Timber.tag("$$$").d("Time zone: %s", tz)

                } catch (throwable: Throwable) {
                    Timber.tag("$$$").d("Time zone error: %s", throwable.message)
                }

                return FORMAT_DATETIME.print(moskowDateTime)
            }


        fun getFactTime(dateTime: String): String {
            val localTime = getLocalTimeFinish(dateTime)
            return format(localTime.getHourOfDay()) + "-" + format(localTime.getMinuteOfHour())
        }

        fun getLocalTimeFinish(dateTime: String): LocalTime {
            var date = Date()
            if (dateTime.contains("+0300")) {
                try {
                    date = FORMAT_DATETIME_REMOTE.parse(dateTime)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

            } else if (dateTime.contains("+03:00")) {
                try {
                    date = FORMAT_DATETIME_LOCAL.parse(dateTime)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

            }
            return LocalTime.fromDateFields(date)
        }
    }

}