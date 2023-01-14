plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.tmdb_test.ui"
    compileSdk = Libs.BuildConfig.compileSdk

    defaultConfig {
        minSdk = Libs.BuildConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = Libs.BuildConfig.isMinifyEnabledDebug
        }
        release {
            isMinifyEnabled = Libs.BuildConfig.isMinifyEnabledRelease
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            consumerProguardFiles("consumer-rules.pro")
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
    sourceSets {
        this[Libs.SourceSet.Main.name].java.srcDirs(*Libs.SourceSet.Main.sourceSets)
        this[Libs.SourceSet.Test.name].java.srcDirs(*Libs.SourceSet.Test.sourceSets)
        this[Libs.SourceSet.AndroidTest.name].java.srcDirs(*Libs.SourceSet.AndroidTest.sourceSets)
    }
}

dependencies {
    implementation(project(":store:base"))
    implementation(project(":store:action"))
    implementation(project(":store:feature"))
    implementation(project(":store:env"))
    implementation(project(":store:state"))
    implementation(project(":store:app"))

    implementation(project(":data:source:remote:impl"))
//    implementation(project(":data:source:remote:impl-ktor"))
    implementation(project(":data:model"))

    implementation(libs.bundles.ui)
    debugImplementation(libs.bundles.ui.impl.debug)
    kapt(libs.bundles.ui.kapt)
    kaptTest(libs.bundles.ui.kapt.test)
    kaptAndroidTest(libs.bundles.ui.kapt.test.android)
    testImplementation(libs.bundles.ui.test)
    androidTestImplementation(libs.bundles.ui.test.android)
}