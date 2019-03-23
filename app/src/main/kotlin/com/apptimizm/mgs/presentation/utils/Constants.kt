package com.apptimizm.mgs.presentation.utils

import android.annotation.SuppressLint

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

object Constants {

    val DOMAIN = "http://178.57.222.1:8000/"
    val API_VERSION = "0"
    val URL_REST_API = String.format("http://%s/v%s/", DOMAIN, API_VERSION)
    val URL_REST = "http://178.57.222.1:8000/"

    const val SIMPLE_BASE_URL = "http://www.google.com"

    val HEADER_AUTHORIZATION = "Authorization"
    val TOKEN_TYPE_BEARER = "Bearer"

    val BUG_06 = "0.66"
    val BUG_07 = "0.7"
    val BUG_08 = "0.8"
    val BUG_11 = "1.1"
    val BUG_3m3 = "3м3"
    val BUG_5m3 = "5м3"
    val BUG_8m3 = "8м3"
    val BUG_20m3 = "20м3"
    val BUG_27m3 = "27м3"
    val BUG_32m3 = "32м3"
    val BUG_35m3 = "35м3"

    val BUG_PackagedCollection = "ПАКЕТИРОВАННЫЙ СБОР"
    val BUG_MeshkovCollection = "МЕШКОВОЙ СБОР"

    const val LOGIN_LENGTH = 4
    const val PASSWORD_LENGTH = 6

    val TIME_UPDATE = TimeUnit.MINUTES.toMillis(5)

    val SHORT_DATE_FORMAT = "yyyy-MM-dd"
    @SuppressLint("SimpleDateFormat")
    val SIMPLE_LOCAL_DATE_FORMAT = SimpleDateFormat(SHORT_DATE_FORMAT, Locale("ru"))

    val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.ZZZZ"
    @SuppressLint("SimpleDateFormat")
    val SIMPLE_DATE_FORMAT = SimpleDateFormat(DATE_FORMAT)

    val TIME_FORMAT = "HH:mm:ss"
    @SuppressLint("SimpleDateFormat")
    val SIMPLE_TIME_FORMAT = SimpleDateFormat(TIME_FORMAT)

    /* константы времени */
    val SECOND = 1000
    val MINUTE = 60 * SECOND
    val HOUR = 60 * MINUTE
    val DAY = 24 * HOUR
    val WEEK = 7 * DAY

}