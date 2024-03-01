@file:Suppress("unused")

package com.naulian.firex

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.naulian.anhance.formatWith

val firebaseStore get() = Firebase.firestore
fun storeCollection(path: String) = Firebase.firestore.collection(path)

//non_null
val Timestamp.millis get() = seconds * 1000L
val Timestamp.dateString get() = toDate().toString()

fun Timestamp.formatWith(pattern: String) = millis.formatWith(pattern)
fun Timestamp.formattedDate() = formatWith("dd/MM/yy")

//nullable
val Timestamp?.millis get() = (this?.seconds ?: 0L) * 1000L
val Timestamp?.dateString get() = this?.toDate().toString()

fun Timestamp?.formatWith(pattern: String) = millis.formatWith(pattern)
fun Timestamp?.formattedDate() = formatWith("dd/MM/yy")

fun handleError(error: Exception?, tag: String) =
    error?.let { Log.e(tag, "${it.message}") }

fun <T> handleValue(value: T?, action: (value: T) -> Unit) =
    value?.let { action(it) }

fun CollectionReference.addListener(
    tag: String = "SnapshotListener",
    onValue: (QuerySnapshot) -> Unit
) = addSnapshotListener { value, error ->
    handleError(error, tag)
    value?.let(onValue)
}

fun Query.addListener(
    tag: String = "SnapshotListener",
    onValue: (QuerySnapshot) -> Unit
) = addSnapshotListener { value, error ->
    handleError(error, tag)
    value?.let(onValue)
}