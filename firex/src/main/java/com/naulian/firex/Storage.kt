@file:Suppress("unused")

package com.naulian.firex

import android.content.Context
import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import com.naulian.anhance.fileExtension
import kotlinx.coroutines.tasks.await

fun storagePath(name: String) = Firebase.storage.getReference(name)

object Storage {

    private val root get() = storagePath("users")
    private fun usersRef(uid: String) = root.child(uid)

    fun uploadProfilePic(
        context: Context, uid: String, uri: Uri,
        onComplete: (Result<String>) -> Unit
    ) {
        val ref = usersRef(uid).createImagePath(context, "profile", uri)
        uploadImage(ref, uri, onComplete)
    }

    fun deleteImage(url: String) {
        Firebase.storage.getReferenceFromUrl(url).delete()
    }

    fun createName(context: Context, prefix: String, uri: Uri): String {
        val extension = uri.fileExtension(context)
        return "$prefix.$extension"
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun StorageReference.createImagePath(
        context: Context, name: String, uri: Uri
    ): StorageReference {
        val extension = uri.fileExtension(context)
        val filename = "$name.$extension"
        return child(filename)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun uploadImage(
        storageRef: StorageReference, imageUri: Uri,
        onComplete: (Result<String>) -> Unit
    ) = storageRef.putFile(imageUri).addOnSuccessListener {
        getDownloadUrl(storageRef, onComplete)
    }.addOnFailureListener { onComplete(Result.failure(it)) }

    suspend fun uploadImage(storageRef: StorageReference, imageUri: Uri): Result<String> {
        val upload = storageRef.putFile(imageUri).await()
        if (upload.task.isSuccessful) {
            val uri: Uri = storageRef.downloadUrl.await()
            return Result.success(uri.toString())
        } else {
            return Result.failure(upload.task.exception!!)
        }
    }

    private fun getDownloadUrl(
        storageRef: StorageReference,
        onComplete: (Result<String>) -> Unit
    ) = storageRef.downloadUrl.addOnSuccessListener {
        onComplete(Result.success(it.toString()))
    }.addOnFailureListener { onComplete(Result.failure(it)) }
}
