plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

android {
    namespace = "${GradleConfig.Android.applicationId}.data.db.room"
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        testInstrumentationRunner = "${GradleConfig.Android.applicationId}.data.db.room.runner.DaggerTestRunner"
        consumerProguardFiles("consumer-rules.pro")
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += "room.schemaLocation" to "$projectDir/schemas"
            }
        }
    }
    buildTypes {
        debug {
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledDebug
        }
        release {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }
    sourceSets {
        getByName("androidTest").assets.srcDirs(files("$projectDir/schemas"))
    }
    packaging {
        resources {
            this.excludes.addAll(GradleConfig.Android.excludePackagingResources)
        }
    }
}

dependencies {
    implementationDependencies()
    kspDependencies()
    kspAndroidTest(libs.room.compiler)
    testImplementationDependencies()
    androidTestImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":util"))

    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)

    implementation(libs.dagger)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.kotlinx.dateTime)
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.room.compiler)
    ksp(libs.dagger.compiler)
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
    androidTestImplementation(libs.room.test)
    androidTestImplementation(libs.kotlin.test)
    androidTestImplementation(libs.kotlin.coroutines.test)
    androidTestImplementation(libs.kotlinx.dateTime)
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}
