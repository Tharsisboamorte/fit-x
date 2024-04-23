package com.project.fitx.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("d'/'MM'/'yyyy", Locale.ENGLISH)
    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date!!)
}