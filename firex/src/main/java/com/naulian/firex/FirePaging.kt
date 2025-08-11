package com.naulian.firex

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

private abstract class FirePaging {
    private var pageKey : Timestamp? = null

    private var synced = false
    private var detached = true

    private var previousPathId = ""

    // use this when you only want to run sync when the path id changes
    fun smartSync(pathId: String) {
        if (pathId == previousPathId) return
        forceSync(pathId)
    }

    // use this when you want to run sync no matter what the path id is
    fun forceSync(pathId: String) {
        if (synced) detach()
        sync(pathId)
    }

    // use this when you want to re-sync the current path
    fun refresh(){
        if (synced) detach()
        sync(previousPathId)
    }

    fun sync(pathId: String = "") {
        if (synced) return

        synced = true
        detached = false
        previousPathId = pathId

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
    open fun onDataChanged(document: DocumentSnapshot) {}
    open fun onDataChanged(query: QuerySnapshot) {}
}