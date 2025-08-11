@file:Suppress("unused")

package com.naulian.firex

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.naulian.anhance.failure
import com.naulian.anhance.success

val firebaseAuth get() = Firebase.auth
val firebaseUser get() = firebaseAuth.currentUser
val nullableUid get() = firebaseUser?.uid

//2025.08.02
@Deprecated(
    "Use firebaseUid instead",
    ReplaceWith("firebaseUid"),
    DeprecationLevel.WARNING
)
val nonNullUid get() = firebaseUid
val firebaseUid get() = firebaseUser?.uid.orEmpty()

val isLoggedIn get() = firebaseUser != null
val isLoggedOut get() = firebaseUser == null

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

fun signInAnonymously(
    onComplete: (result: Result<String>) -> Unit
) = firebaseAuth.signInAnonymously()
    .addOnFailureListener { onComplete(failure(it)) }
    .addOnSuccessListener { onComplete(success(nonNullUid)) }

fun signUpWithGoogle() {

}

