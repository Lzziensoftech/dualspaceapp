plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.ajay.dual.space.dual.parallelspace"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ajay.dual.space.dual.parallelspace"
        minSdk = 22
        targetSdk = 34
        versionCode = 111
        versionName = "1.8.933"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.firebase.firestore.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Custom Dialog Dependency
    implementation("com.github.f0ris.sweetalert:library:1.6.0")
    // Custom Toast Dependency
    implementation("io.github.shashank02051997:FancyToast:2.0.2")
    // Firebase Dependency
    implementation("com.google.firebase:firebase-database:21.0.0")
    // Glide Dependency
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // To Open Browser
    implementation("androidx.browser:browser:1.5.0")
    implementation("com.google.firebase:firebase-database:21.0.0")
    // Facebook Ads
    implementation("com.facebook.android:audience-network-sdk:6.17.0")
    // Google Admob Ads
    implementation(libs.play.services.ads)
}