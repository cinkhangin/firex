@file:Suppress("unused")

package com.naulian.firex

import android.content.Context
import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.naulian.anhance.fileExtension

object Storage {

    private val root get() = Firebase.storage.getReference("users")
    private fun usersRef(uid: String) = root.child(uid)

    fun uploadProfilePic(
        context: Context, uid: String, uri: Uri,
        onComplete: (Result<String>) -> Unit
    ) {
        val ref = createPath(context, uid, uri)
        uploadImage(ref, uri, onComplete)
    }

    fun deleteImage(url: String) {
        Firebase.storage.getReferenceFromUrl(url).delete()
    }


    private fun createPath(context: Context, uid: String, uri: Uri): StorageReference {
        val extension = uri.fileExtension(context)
        val filename = "$uid.$extension"
        return usersRef(uid).child(filename)
    }

    private fun uploadImage(
        storageRef: StorageReference, imageUri: Uri,
        onComplete: (Result<String>) -> Unit
    ) {
        storageRef.putFile(imageUri).addOnSuccessListener { getDownloadUrl(storageRef, onComplete) }
            .addOnFailureListener { onComplete(Result.failure(it)) }
    }

    private fun getDownloadUrl(
        storageRef: StorageReference,
        onComplete: (Result<String>) -> Unit
    ) {
        storageRef.downloadUrl.addOnSuccessListener { onComplete(Result.success(it.toString())) }
            .addOnFailureListener { onComplete(Result.failure(it)) }
    }
}