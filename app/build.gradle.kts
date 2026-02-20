import org.jetbrains.kotlin.gradle.dsl.JvmTarget // 1. Add this import

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.devcraft.pceaimani"
    compileSdk = 36 // Note: SDK 36 is Android 16 (very new!)

    defaultConfig {
        applicationId = "com.devcraft.pceaimani"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // 2. The old 'kotlinOptions' block was removed from here

    buildFeatures {
        compose = true
    }
}

// 3. Add this new block to set the Kotlin JVM target
kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    // Add Firebase BoM
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.material.icons.extended)
    // create a navigation dependency
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material3)

    implementation(libs.font.awesome.icons)

    implementation(libs.ui) // Very questionable
    debugImplementation(libs.androidx.ui.tooling)
}