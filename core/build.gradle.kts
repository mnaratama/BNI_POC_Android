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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    namespace = "com.ibm.bni.core"
}

dependencies {
    // Hilt
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)
    implementation(libs.roomRuntime)
    kapt(libs.roomCompiler)
    implementation(libs.roomKtx)
    implementation(libs.roomPaging)
    implementation(libs.bundles.common)
    implementation(libs.androidxCore)
    implementation(libs.appcompat)
    implementation(libs.material1)
    testImplementation(libs.junitCoreTest)
    testImplementation(libs.roomTesting)
    androidTestImplementation(libs.extJunitTest)
    androidTestImplementation(libs.espressoCoreTest)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.gsonConverter)

}
