plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
}

android {
    namespace = "com.vishal.vishalsinghassignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vishal.vishalsinghassignment"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.github.bumptech.glide:glide:4.13.2")
    kapt ("com.github.bumptech.glide:compiler:4.13.2")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // GSON
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.6")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6")
    implementation("com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0")

    //Room
    implementation ("androidx.room:room-runtime:2.5.2")
    kapt ("androidx.room:room-compiler:2.5.2")

    // glide for image
    implementation ("com.github.bumptech.glide:glide:4.15.1")

    ///for dimens
    implementation ("com.intuit.ssp:ssp-android:1.0.6")
    implementation ("com.intuit.sdp:sdp-android:1.0.6")



}