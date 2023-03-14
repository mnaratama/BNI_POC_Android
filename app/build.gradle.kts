import java.util.Properties
import java.io.FileInputStream


@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.googleService)
    alias(libs.plugins.firebaseCrashlytics)
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
// Initialize a new Properties() object called keystoreProperties.
val keystoreProperties = Properties()

// Load your keystore.properties file into the keystoreProperties object.
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = "com.ibm.bni"
        minSdk  = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
        versionCode = 2
        versionName = "1.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        defaultConfig {

        }
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {

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
    namespace = "com.ibm.bniapp"

    packagingOptions {
        exclude("META-INF/proguard/androidx-annotations.pro")
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":auth"))
    implementation(project(":home"))

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)
    // Import the BoM for the Firebase platform
    implementation(platform(libs.firebase))

    // Add the dependencies for the Crashlytics and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")



    implementation(libs.bundles.common)
    implementation(libs.androidxCore)
    implementation(libs.appcompat)
    implementation(libs.material1)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycleLivedata)
    implementation(libs.lifecycleViewmode)
    testImplementation(libs.junitCoreTest)
    androidTestImplementation(libs.extJunitTest)
    androidTestImplementation(libs.espressoCoreTest)
    implementation(libs.componentNavigationFragmentKtx)
    implementation(libs.componentNavigationUiKtx)
    implementation(libs.componentNavigationDynamicFeaturesFragmen)

}

