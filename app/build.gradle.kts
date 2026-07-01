plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Tambahkan plugin parcelize untuk passing object data antar halaman
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.appbook2"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.appbook2"
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
    kotlinOptions {
        jvmTarget = "11"
    }

    // Aktifkan viewBinding untuk mempermudah pemanggilan ID dari XML
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

    // --- DEPENDENSI BARU UNTUK FASE SELANJUTNYA ---

    // 1. Navigation Component (Standar untuk pindah halaman)
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // 2. Retrofit & Gson (Untuk memanggil API Buku Open Source)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // 3. Glide (Untuk meload gambar dari internet ke ImageView)
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // 4. Coroutines (Untuk menjalankan API dan Database di background thread)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // 5. Osmdroid & Google Location (Untuk fitur LBS)
    implementation("org.osmdroid:osmdroid-android:6.1.18")
    implementation("com.google.android.gms:play-services-location:21.2.0")

    // 6. Room Database (Simulasi penyimpanan real-time progress di lokal)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    // annotationProcessor("androidx.room:room-compiler:2.6.1") // Gunakan ksp di project nyata

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}