plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
    id(GradleConfig.Plugins.REALM)
}

android {
    namespace = "${GradleConfig.Android.applicationId}.data.db.realm"
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        testInstrumentationRunner = "${GradleConfig.Android.applicationId}.data.db.realm.runner.DaggerTestRunner"
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
    kotlinOptions.jvmTarget = GradleConfig.javaVersionAsString
    packaging {
        resources {
            this.excludes.addAll(GradleConfig.Android.excludePackagingResources)
        }
    }
}

dependencies {
    implementationDependencies()
    kaptDependencies()
    kaptAndroidTest(libs.dagger.compiler)

    testImplementationDependencies()

    androidTestImplementationDependencies()
}

fun DependencyHandlerScope.kaptDependencies() {
    kapt(libs.dagger.compiler)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":util"))

    implementation(libs.dagger)

    implementation(libs.realm.plugin)
    implementation(libs.kotlinx.dateTime)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlinx.dateTime)
}

fun DependencyHandlerScope.androidTestImplementationDependencies() {
    androidTestImplementation(project(":util"))

    androidTestImplementation(libs.junit.android.ext)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.kotlin.test)
    androidTestImplementation(libs.kotlin.coroutines.test)
    androidTestImplementation(libs.kotlinx.dateTime)
    androidTestImplementation(libs.realm.plugin)
}
