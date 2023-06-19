plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "${GradleConfig.BuildConfig.applicationId}.util"
    compileSdk = GradleConfig.BuildConfig.compileSdk

    defaultConfig {
        minSdk = GradleConfig.BuildConfig.minSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = GradleConfig.BuildConfig.isMinifyEnabledDebug
        }
        release {
            isMinifyEnabled = GradleConfig.BuildConfig.isMinifyEnabledRelease
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = GradleConfig.javaVersion
        targetCompatibility = GradleConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = GradleConfig.javaVersionAsString
    }
    sourceSets {
        this[GradleConfig.SourceSet.Main.name].java.srcDirs(*GradleConfig.SourceSet.Main.sourceSets)
        this[GradleConfig.SourceSet.Test.name].java.srcDirs(*GradleConfig.SourceSet.Test.sourceSets)
    }
}

dependencies {
    implementation(libs.hilt.android)
    apiDependencies()
    kapt(libs.hilt.kapt)
}

fun DependencyHandlerScope.apiDependencies() {
    api(libs.kotlin.stdLib)
    api(libs.kotlin.coroutines.core)
    api(libs.kotlin.coroutines.android)
}
