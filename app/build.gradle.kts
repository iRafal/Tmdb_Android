plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

val apiKey = if (hasProperty("api.key")) {
    property("api.key").toString()
} else {
    throw IllegalStateException("""[api.key] property is missed""")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.tmdb_test"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "API_KEY", """"$apiKey"""")
        buildConfigField(
            "String",
            "API_BASE_URL",
            "\"https://api.themoviedb.org/3/\""
        )
        buildConfigField(
            "String",
            "API_IMAGE_URL",
            "\"https://image.tmdb.org/t/p/\""
        )
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isShrinkResources = false
            isMinifyEnabled = false
        }
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true

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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.extension.get()
    }
    packagingOptions {
        resources {
            this.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
//    kotlinDependencies()
//    androidDependencies()
//    composeDependencies()
//    imagesDependencies()
//    utilsDependencies()
//    networkDependencies()
//    testingDependencies()

    implementation(libs.bundles.app.impl)
    debugImplementation(libs.bundles.app.impl.debug)
    testImplementation(libs.bundles.app.impl.test)
    androidTestImplementation(libs.bundles.app.impl.test.android)
}


//val compose_version = "1.2.1"
//val kotlin_version = "1.7.10"
//
//fun DependencyHandlerScope.kotlinDependencies() {
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
//
//    // https://github.com/Kotlin/kotlinx.coroutines
//    val kotlinxCoroutinesVersion = "1.6.4"
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinxCoroutinesVersion")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$$kotlinxCoroutinesVersion")

//
//    // https://github.com/Kotlin/kotlinx.serialization
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0-RC")
//}
//
//fun DependencyHandlerScope.androidDependencies() {
//    // https://developer.android.com/jetpack/androidx/releases/appcompat
////    implementation("androidx.appcompat:appcompat:1.4.1")
//
//    implementation("androidx.core:core-ktx:1.8.0")
//
//    // https://developer.android.com/jetpack/androidx/releases/lifecycle
//    val lifecycleVersion = "2.5.1"
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
//}
//
//fun DependencyHandlerScope.composeDependencies() {
//    // https://developer.android.com/jetpack/androidx/releases/activity
//    implementation("androidx.activity:activity-compose:1.5.1")
//
//    // https://developer.android.com/jetpack/compose/layouts/constraintlayout
//    // https://mvnrepository.com/artifact/androidx.constraintlayout/constraintlayout-compose?repo=google
//    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
//
//    // https://developer.android.com/jetpack/androidx/releases/navigation
//    implementation("androidx.navigation:navigation-compose:2.5.1")
//
//    implementation("androidx.compose.ui:ui:$compose_version")
//    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
//
//    // https://developer.android.com/jetpack/androidx/releases/compose-material
//    implementation("androidx.compose.material:material:$compose_version")
//
//    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
//    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
//}
//
//fun DependencyHandlerScope.utilsDependencies() {
//    // https://square.github.io/leakcanary/getting_started/
//    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.9.1")
//
//    // https://github.com/MicroUtils/kotlin-logging
//    implementation("io.github.microutils:kotlin-logging-jvm:2.1.23")
//}
//
//fun DependencyHandlerScope.imagesDependencies() {
//    // https://github.com/coil-kt/coil
//    implementation("io.coil-kt:coil-compose:2.1.0")
//}
//
//fun DependencyHandlerScope.networkDependencies() {
//    // https://github.com/ktorio/ktor
//    val ktor = "2.0.3"
//    implementation("io.ktor:ktor-client-core:$ktor")
//    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor")
//    implementation("io.ktor:ktor-client-android:$ktor")
//    implementation("io.ktor:ktor-client-content-negotiation:$ktor")
//    implementation("io.ktor:ktor-client-logging:$ktor")
//    // testImplementation("io.ktor:ktor-client-mock:$ktor")
//}
//
//fun DependencyHandlerScope.testingDependencies() {
//    // https://mvnrepository.com/artifact/junit/junit
//    testImplementation("junit:junit:4.13.2")
//    testImplementation("org.mockito:mockito-core:4.6.1") // https://github.com/mockito/mockito
//    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0") // https://github.com/mockito/mockito-kotlin
//
//    // https://developer.android.com/training/testing/set-up-project
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//
//    // https://mvnrepository.com/artifact/androidx.test.espresso/espresso-core
//    // https://developer.android.com/training/testing/set-up-project
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
//}