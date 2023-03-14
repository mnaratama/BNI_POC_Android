@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.hilt)
}

android {
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        minSdk  = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    namespace = "com.ibm.bni.home"
}

dependencies {
    implementation(project(":core"))

// Hilt
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)
    implementation(libs.retrofit)
    implementation(libs.androidxCore)
    implementation(libs.appcompat)
    implementation(libs.material1)
    implementation(libs.autoImageSlider)
    implementation(libs.eventbus)
    testImplementation(libs.junitCoreTest)
    androidTestImplementation(libs.extJunitTest)
    androidTestImplementation(libs.espressoCoreTest)
}