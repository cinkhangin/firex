package com.naulian.firex

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings


//We use class instead of object because remoteConfig has a context
class FireConfig {
    private val remoteConfig = Firebase.remoteConfig

    private fun setup(defaultParam: Map<String, Any>, fetchInterval: Long = 36000) {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = fetchInterval
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(defaultParam)
    }

    fun listenRemoteConfig(
        defaultParam: Map<String, Any>,
        fetchInterval: Long = 1,
        action: FirebaseRemoteConfig.(Result<Boolean>) -> Unit
    ) {
        setup(defaultParam, fetchInterval)
        listenRemoteConfig(action)
    }

    fun fetchRemoteConfig(
        defaultParam: Map<String, Any>,
        fetchInterval: Long = 36000,
        action: FirebaseRemoteConfig.(Result<Boolean>) -> Unit
    ) {
        setup(defaultParam, fetchInterval)
        fetchRemoteConfig(action)
    }

    private fun listenRemoteConfig(action: FirebaseRemoteConfig.(Result<Boolean>) -> Unit) {
        val listener = object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                fetchRemoteConfig(action)
            }

            override fun onError(error: FirebaseRemoteConfigException) {}
        }

        remoteConfig.addOnConfigUpdateListener(listener)
        fetchRemoteConfig(action)
    }

    private fun fetchRemoteConfig(action: FirebaseRemoteConfig.(Result<Boolean>) -> Unit) {
        remoteConfig.fetchAndActivate().addOnSuccessListener {
            action(remoteConfig, Result.success(it))
        }.addOnFailureListener { action(remoteConfig, Result.failure(it)) }
    }
}