buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}

plugins {
    id("com.android.application") version "8.10.0" apply false
    id("com.android.library") version "8.10.0" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("com.vanniktech.maven.publish") version "0.30.0"
    alias(libs.plugins.compose) apply false
}