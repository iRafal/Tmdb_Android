plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Libs.BuildConfig.compileSdk

    defaultConfig {
        applicationId = Libs.BuildConfig.applicationId
        minSdk = Libs.BuildConfig.minSdk
        targetSdk = Libs.BuildConfig.targetSdk

        versionCode = 1
        versionName = Version(major = 1, minor = 0, patch = 0).name

        testInstrumentationRunner = "com.tmdb_test.runner.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isShrinkResources = Libs.BuildConfig.isShrinkResourcesDebug
            isMinifyEnabled = Libs.BuildConfig.isMinifyEnabledDebug
        }
        getByName("release") {
            isShrinkResources = Libs.BuildConfig.isShrinkResourcesRelease
            isMinifyEnabled = Libs.BuildConfig.isMinifyEnabledRelease

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Libs.BuildConfig.CompileOptions.sourceCompatibility
        targetCompatibility = Libs.BuildConfig.CompileOptions.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Libs.BuildConfig.KotlinOptions.jvmTarget
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
        this[Libs.SourceSet.Main.name].java.srcDirs(*Libs.SourceSet.Main.sourceSets)
        this[Libs.SourceSet.Test.name].java.srcDirs(*Libs.SourceSet.Test.sourceSets)
        this[Libs.SourceSet.AndroidTest.name].java.srcDirs(*Libs.SourceSet.AndroidTest.sourceSets)
    }
    namespace = "com.tmdb_test"
}

dependencies {
    implementation(project(":ui"))
    implementation(libs.bundles.app)
    debugImplementation(libs.bundles.app.impl.debug)
    kapt(libs.bundles.app.kapt)
    kaptTest(libs.bundles.app.kapt.test)
    kaptAndroidTest(libs.bundles.app.kapt.test.android)
    testImplementation(libs.bundles.app.test)
    androidTestImplementation(libs.bundles.app.test.android)
}