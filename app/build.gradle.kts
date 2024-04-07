import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.awt.SplashScreen

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.expensetrackerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.expensetrackerapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

    }
//    tasks.withType<KotlinCompile>().all {
//        kotlinOptions {
//            jvmTarget = "17"
//        }
//    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Splash
    implementation("androidx.core:core-splashscreen:${Versions.SplashScreen}")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-android-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore:1.0.0")

    // Security
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha06")
    implementation("androidx.security:security-crypto:1.1.0-alpha06")


    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Coil
    implementation("io.coil-kt:coil:2.5.0")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Compose Destinations
    implementation("io.github.raamcosta.compose-destinations:core:1.9.54")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.9.54")
    implementation("io.github.raamcosta.compose-destinations:animations-core:1.9.54")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")

    implementation("com.google.accompanist:accompanist-systemuicontroller:${Versions.Accompanist}")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:${Versions.FirebaseBom}"))
    // versions for Firebase SDKs are chosen automatically by the BoM
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.android.gms:play-services-base:${Versions.FirebaseBase}")
    implementation("com.google.firebase:firebase-config-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx:${Versions.FirebasePush}")
//    implementation("com.google.firebase:firebase-crashlytics-ktx:${Versions.FirebaseCrashlytics}")
    implementation("com.google.firebase:firebase-messaging-ktx:${Versions.FirebaseMessaging}")
    implementation("com.google.firebase:firebase-auth-ktx:${Versions.FirebaseAuth}")


}

object Versions {
    const val SplashScreen = "1.0.1"
    const val Accompanist = "0.32.0"
    const val FirebaseBom = "32.8.0"
    const val FirebaseBase = "18.3.0"
    const val FirebaseConfig = "22.0.0"
    const val FirebasePush = "23.4.1"
    const val FirebaseAnalytic = "21.6.1"
    const val FirebaseCrashlytics = "18.6.3"
    const val FirebaseMessaging = "23.4.1"
    const val FirebaseAuth = "22.3.1"
}


