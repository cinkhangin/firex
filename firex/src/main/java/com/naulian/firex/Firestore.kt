package com.naulian.firex

import com.google.firebase.Timestamp
import com.naulian.anhance.formatWith

val Timestamp.millis get() = seconds * 1000
val Timestamp.dateString get() = toDate().toString()

fun Timestamp.formatWith(pattern: String) = millis.formatWith(pattern)
fun Timestamp.formattedDate() = millis.formatWith("mm/DD/yy")