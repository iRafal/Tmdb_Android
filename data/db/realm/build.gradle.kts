plugins {
    id(GradleConfig.Plugins.ANDROID_LIBRARY)
    id(GradleConfig.Plugins.KOTLIN_ANDROID)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
    id(GradleConfig.Plugins.HILT)
    id(GradleConfig.Plugins.REALM)
}

android {
    namespace = "${GradleConfig.Android.applicationId}.data.db.realm"
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        testInstrumentationRunner = "${GradleConfig.Android.applicationId}.data.db.realm.runner.HiltTestRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    packaging {
        resources {
            this.excludes.addAll(GradleConfig.Android.excludePackagingResources)
        }
    }
}

dependencies {
    implementationDependencies()

    kapt(libs.hilt.kapt)
    kaptTest(libs.hilt.kapt)
    kaptAndroidTest(libs.hilt.kapt)

    testImplementationDependencies()

    androidTestImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":util"))

    implementation(libs.hilt.android)
    implementation(libs.realm.plugin)
    implementation(libs.kotlinx.dateTime)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.hilt.test)
    testImplementation(libs.kotlinx.dateTime)
}

fun DependencyHandlerScope.androidTestImplementationDependencies() {
    androidTestImplementation(project(":util"))

    androidTestImplementation(libs.junit.android.ext)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.kotlin.coroutines.test)
    androidTestImplementation(libs.hilt.test)
    androidTestImplementation(libs.hilt.kapt)
    androidTestImplementation(libs.kotlinx.dateTime)
    androidTestImplementation(libs.realm.plugin)
}
