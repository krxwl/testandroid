plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.github.krxwl.testandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.krxwl.testandroid"
        minSdk = 30
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
    // DATASTORE
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-preferences-rxjava2:1.0.0")
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.0.0")

    // SUPABASE
    implementation(platform("io.github.jan-tennert.supabase:bom:2.1.2"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.ktor:ktor-client-android:2.3.8")

    // INTUIT
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    val room_version = "2.6.1"

    // ROOM
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")

    // FRAGMENT
    val fragment_version = "1.6.2"

    implementation("androidx.fragment:fragment:$fragment_version")
    implementation("androidx.fragment:fragment-ktx:$fragment_version")
    debugImplementation("androidx.fragment:fragment-testing:$fragment_version")

    // KASPRESSO
    androidTestImplementation("com.kaspersky.android-components:kaspresso:1.5.4")
    androidTestImplementation("org.hamcrest:hamcrest:2.2")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}