package com.naulian.firex

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.naulian.anhance.formatWith

val firebaseStore get() = Firebase.firestore
fun storeCollection(path: String) = Firebase.firestore.collection(path)

val Timestamp.millis get() = seconds * 1000
val Timestamp.dateString get() = toDate().toString()

fun Timestamp.formatWith(pattern: String) = millis.formatWith(pattern)
fun Timestamp.formattedDate() = formatWith("dd/MM/yy")

fun handleError(error: Exception?, tag: String) =
    error?.let { Log.e(tag, "${it.message}") }

fun <T> handleValue(value: T?, action: (value: T) -> Unit) =
    value?.let { action(it) }