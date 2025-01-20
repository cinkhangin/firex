import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.vanniktech.maven.publish")
}

android {
    namespace = "com.naulian.firex"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.play.services.auth)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.database.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.firebase.config.ktx)

    // Anhance
    implementation(libs.anhance)
}


mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)

    signAllPublications()

    coordinates(
        groupId = "com.naulian",
        artifactId = "firex",
        version = "1.4.0-alpha01"
    )
    //./gradlew publishAndReleaseToMavenCentral --no-configuration-cache

    pom {
        name.set("Firex")
        description.set("Easily implement firebase in your android app")
        inceptionYear.set("2023")
        url.set("https://github.com/cinkhangin/firex/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("naulian")
                name.set("Naulian")
                url.set("https://github.com/cinkhangin/")
            }
        }
        scm {
            url.set("https://github.com/cinkhangin/firex/")
            connection.set("scm:git:git://github.com/cinkhangin/firex.git")
            developerConnection.set("scm:git:ssh://git@github.com/cinkhangin/firex.git")
        }
    }
}