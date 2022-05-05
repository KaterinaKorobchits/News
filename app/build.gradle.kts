import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

val prop = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        versionCode = 1
        versionName = "1.0"

        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "NEWS_KEY", prop.getProperty("newsApiKey"))
        buildConfigField("String", "NEWS_REST_URL", prop.getProperty("newsRestUrl"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        buildConfig = true
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(projects.presentation)
    implementation(projects.boundary)
    implementation(projects.interactor)
    implementation(projects.domain)
    implementation(projects.data)

    implementation(libs.androidx.appcompat)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.retrofit2)

    debugImplementation(libs.leak.canary)

    implementation(libs.dagger.runtime)
    kapt(libs.dagger.compiler)
}