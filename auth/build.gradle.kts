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

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    namespace = "com.ibm.bni.auth"
}

dependencies {
    implementation(project(":core"))
    
// Hilt
    implementation(libs.hilt)
    implementation(libs.navGraph)
    implementation(libs.navUI)
    kapt(libs.hiltCompiler)
    implementation(libs.biometricKtx)
    implementation(libs.eventbus)
    implementation(libs.constraintlayout)
    implementation(libs.material.icons.extended)
    implementation(libs.componentNavigationFragmentKtx)
    implementation(libs.componentNavigationUiKtx)
    implementation(libs.componentNavigationDynamicFeaturesFragmen)
    implementation(libs.autoImageSlider)
    implementation(libs.retrofit)
    implementation(libs.androidxCore)
    implementation(libs.appcompat)
    implementation(libs.material1)
    testImplementation(libs.junitCoreTest)
    androidTestImplementation(libs.extJunitTest)
    androidTestImplementation(libs.espressoCoreTest)
}