buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}

plugins {
    id("com.android.application") version "8.7.3" apply false
    id("com.android.library") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("com.vanniktech.maven.publish") version "0.30.0"
}