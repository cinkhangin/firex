@file:Suppress("unused")

package com.example.firex

import com.naulian.firex.FireSync

object Sync : FireSync() {
    override fun onSync(pathId: String) {}
    override fun onDetach() {}
}