plugins {
    id("com.android.application") version "8.11.1" apply false
    id("com.android.library") version "8.11.1" apply false
    id("org.jetbrains.kotlin.android") version "2.2.0" apply false
    id("com.vanniktech.maven.publish") version "0.34.0"

    alias(libs.plugins.compose) apply false
    alias(libs.plugins.google.services) apply false
}