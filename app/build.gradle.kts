import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

val signingConfigRelease = "release"

android {
    signingConfigs {
//        create(signingConfigRelease) {
//            storeFile = System.getenv("STORE_FILE")?.let { file(it) }
//            storePassword = System.getenv("STORE_PASSWORD")
//            keyAlias = System.getenv("KEY_ALIAS")
//            keyPassword = System.getenv("KEY_PASSWORD")
//        }
    }

    // More details: https://developer.android.com/guide/topics/resources/app-languages
//    androidResources.generateLocaleConfig = true

    namespace = GradleConfig.Android.applicationId
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        applicationId = GradleConfig.Android.applicationId
        minSdk = GradleConfig.Android.minSdk
        targetSdk = GradleConfig.Android.targetSdk

        versionCode = 1
        versionName = Version(major = 0, minor = 1, patch = 0).name

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "${GradleConfig.Android.applicationId}.runner.DaggerTestRunner"

        bundle.language.enableSplit = false
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isShrinkResources = GradleConfig.Android.isShrinkResourcesDebug
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledDebug

            resValue("string", "app_name", "Tmdb-Debug")
            resValue(
                type = "string",
                name = "file_provider_authority",
                value = "${GradleConfig.Android.applicationId}$applicationIdSuffix.fileProvider"
            )
        }
        release {
            isShrinkResources = GradleConfig.Android.isShrinkResourcesRelease
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledRelease

            // https://stackoverflow.com/questions/60861929/what-is-the-difference-between-consumer-rules-pro-and-proguard-rules-pro-in-andr
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            resValue("string", "app_name", "Tmdb")
            resValue(
                type = "string",
                name = "file_provider_authority",
                value = "${GradleConfig.Android.applicationId}.fileProvider"
            )

//            signingConfig = signingConfigs.getByName(signingConfigRelease)
        }
    }
    compileOptions {
        sourceCompatibility = GradleConfig.javaVersion
        targetCompatibility = GradleConfig.javaVersion
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging.resources {
        excludes.addAll(GradleConfig.Android.excludePackagingResources)
    }
    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            allWarningsAsErrors = false
        }
    }
}

dependencies {
    implementationDependencies()
    kspDependencies()
    debugImplementationDependencies()
    testImplementationDependencies()
    kspAndroidTest(libs.dagger.compiler)
    androidTestImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":ui-core"))
    implementation(project(":store:app-store"))
//
    implementation(project(":data:source:remote:impl-ktor"))
//    implementation(project(":data:source:remote:impl-retrofit"))

    implementation(project(":data:source:local:impl-room"))
//    implementation(project(":data:source:local:impl-objectBox"))
//    implementation(project(":data:source:local:impl-realm"))

    implementation(project(":data:api:impl-ktor"))
//    implementation(project(":data:api:impl-retrofit"))

    implementation(project(":feature:home:ui"))
    implementation(project(":feature:movie:details:ui"))

    implementation(libs.play.services.base)
    implementation(libs.androidx.splashscreen)
    implementation(libs.material.components.android)
    implementation(libs.dagger)
    implementation(libs.androidx.work.runtime)
    implementation(libs.androidx.metrics.performance)
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.dagger.compiler)
}

fun DependencyHandlerScope.debugImplementationDependencies() {
    debugImplementation(libs.leakCanary.debug)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.coroutines.test)
}

fun DependencyHandlerScope.androidTestImplementationDependencies() {
    androidTestImplementation(libs.junit.android.ext)
    androidTestImplementation(libs.espresso)

    androidTestImplementation(libs.kotlin.test)
    androidTestImplementation(libs.kotlin.coroutines.test)

    androidTestImplementation(libs.compose.ui.test.manifest.debug)
    androidTestImplementation(libs.compose.ui.test.junit)
    androidTestImplementation(libs.compose.navigation.test)
}
