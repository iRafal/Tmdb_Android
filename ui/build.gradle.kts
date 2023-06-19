plugins {
    id(GradleConfig.Plugins.ANDROID_LIBRARY)
    id(GradleConfig.Plugins.KOTLIN_ANDROID)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
    id(GradleConfig.Plugins.HILT)
}

android {
    namespace = "${GradleConfig.Android.applicationId}.ui"
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        testInstrumentationRunner = "${GradleConfig.Android.applicationId}.ui.core.runner.HiltTestRunner"
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
}

dependencies {
    apiDependencies()

    implementationDependencies()

    kaptDependencies()

    kaptTest(libs.hilt.kapt)
    kaptAndroidTest(libs.hilt.kapt)

    testImplementationDependencies()

    androidTestImplementationDependencies()
}

fun DependencyHandlerScope.apiDependencies() {
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.viewmodel.compose)

    api(project(":ui-core"))
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.hilt.android)

    implementation(project(":feature:home:ui"))
    implementation(project(":feature:movie:details:ui"))
}

fun DependencyHandlerScope.kaptDependencies() {
    kapt(libs.hilt.kapt)
    kapt(libs.hilt.work.kapt)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.junit)

    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)

    testImplementation(libs.kotlin.coroutines.test)

    testImplementation(libs.hilt.test)
}

fun DependencyHandlerScope.androidTestImplementationDependencies() {
    androidTestImplementation(libs.junit.android.ext)

    androidTestImplementation(libs.espresso)

    androidTestImplementation(libs.kotlin.coroutines.test)

    androidTestImplementation(libs.hilt.test)
    androidTestImplementation(libs.hilt.kapt)

    androidTestImplementation(libs.compose.ui.test.manifest.debug)
    androidTestImplementation(libs.compose.ui.test.junit)
}
