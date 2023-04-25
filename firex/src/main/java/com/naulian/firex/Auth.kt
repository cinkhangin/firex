@file:Suppress("unused")

package com.naulian.firex

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.naulian.anhance.failure
import com.naulian.anhance.success

val firebaseAuth get() = Firebase.auth
val firebaseUser get() = firebaseAuth.currentUser
val nonNullUser get() = firebaseUser!!
val nullableUid get() = firebaseUser?.uid
val nonNullUid get() = firebaseUser?.uid!!

fun reloadAuth() = firebaseUser?.reload()
fun signOut() = firebaseAuth.signOut()

fun ifLogin(action: (uid: String) -> Unit) =
    nullableUid?.let(action)

fun signUpWithEmailAndPassword(
    email: String, password: String,
    onComplete: (result: Result<String>) -> Unit
) = firebaseAuth.createUserWithEmailAndPassword(email, password)
    .addOnFailureListener { onComplete(failure(it)) }
    .addOnSuccessListener { onComplete(success(nonNullUid)) }

fun signInWithEmailAndPassword(
    email: String, password: String,
    onComplete: (result: Result<String>) -> Unit
) = firebaseAuth.signInWithEmailAndPassword(email, password)
    .addOnFailureListener { onComplete(failure(it)) }
    .addOnSuccessListener { onComplete(success(nonNullUid)) }

fun signUpWithGoogle() {

}

