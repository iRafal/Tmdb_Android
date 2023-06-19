plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "${GradleConfig.Android.applicationId}.ui.core"
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledDebug
        }
        release {
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledRelease
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.extension.get()
    }
    sourceSets {
        this[GradleConfig.SourceSet.Main.name].java.srcDirs(*GradleConfig.SourceSet.Main.sourceSets)
        this[GradleConfig.SourceSet.Test.name].java.srcDirs(*GradleConfig.SourceSet.Test.sourceSets)
        this[GradleConfig.SourceSet.AndroidTest.name].java.srcDirs(*GradleConfig.SourceSet.AndroidTest.sourceSets)
    }
}

dependencies {
    implementationDependencies()
    apiDependencies()
    debugApiDependencies()
    kapt(libs.hilt.kapt)
    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.ui.test.android)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":store:state"))
    implementation(libs.hilt.android)
}

fun DependencyHandlerScope.apiDependencies() {
    implementation(project(":util"))

    api(libs.androidx.core)

    api(libs.compose.animation)
    api(libs.compose.compiler)
    api(libs.compose.foundation)
    api(libs.compose.runtime)
    api(libs.compose.activity)
    api(libs.compose.constraintLayout)
    api(libs.compose.navigation)
    api(libs.compose.ui)
    api(libs.compose.ui.preview)
    api(libs.compose.material)
    api(libs.compose.material3)

    api(libs.coil)
    api(libs.coil.compose)
    api(libs.okHttp3.loggingInterceptor)

    api(libs.logging)
    api(libs.kotlinx.dateTime)

    api(libs.hilt.navigation.compose)
}

fun DependencyHandlerScope.debugApiDependencies() {
    debugApi(libs.compose.ui.tooling.debug)
    debugImplementation(libs.compose.ui.test.manifest.debug)
}
