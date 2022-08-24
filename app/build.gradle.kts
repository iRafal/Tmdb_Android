plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
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

        testInstrumentationRunner = "com.tmdb_test.runner.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += "room.schemaLocation" to "$projectDir/schemas"
            }
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
            this.excludes.add("/META-INF/gradle/incremental.annotation.processors")
        }
    }
    sourceSets {
        getByName("androidTest").assets.srcDirs(files("$projectDir/schemas"))
    }
}

dependencies {
    implementation(libs.bundles.app.impl)
    debugImplementation(libs.bundles.app.impl.debug)
    kapt(libs.bundles.app.kapt)
    kaptTest(libs.bundles.app.kapt.test)
    kaptAndroidTest(libs.bundles.app.kapt.test.android)
    testImplementation(libs.bundles.app.impl.test)
    androidTestImplementation(libs.bundles.app.impl.test.android)
}

kapt {
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}