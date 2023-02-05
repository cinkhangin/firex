package com.naulian.firex

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

abstract class FireSync {
    protected var synced = false
    protected var detached = true

    fun sync(pathId: String) {
        if (synced) return

        synced = true
        detached = false

        onSync(pathId)
    }

    fun detach() {
        if (detached) return

        synced = false
        detached = true

        onDetach()
    }

    protected abstract fun onSync(pathId: String)
    protected abstract fun onDetach()
    open protected fun onDataChanged(document: DocumentSnapshot) {}
    open protected fun onDataChanged(query: QuerySnapshot) {}

}