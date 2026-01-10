plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.kover)
    jacoco
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

    namespace = GradleConfig.Android.NAMESPACE
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = GradleConfig.App.ID
        minSdk = libs.versions.android.sdk.min.get().toInt()
        targetSdk = libs.versions.android.sdk.target.get().toInt()

        versionCode = GradleConfig.App.VERSION_CODE
        versionName = GradleConfig.App.version.name

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "${GradleConfig.Android.NAMESPACE}.runner.TestRunner"

        bundle.language.enableSplit = false
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isShrinkResources = false
            isMinifyEnabled = false

            enableUnitTestCoverage = true

            resValue("string", "app_name", "Tmdb-Debug")
            resValue(
                type = "string",
                name = "file_provider_authority",
                value = "${GradleConfig.Android.NAMESPACE}.$applicationIdSuffix.fileProvider"
            )
        }
        release {
            isShrinkResources = true
            isMinifyEnabled = true

            // https://stackoverflow.com/questions/60861929/what-is-the-difference-between-consumer-rules-pro-and-proguard-rules-pro-in-andr
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            resValue("string", "app_name", "Tmdb")
            resValue(
                type = "string",
                name = "file_provider_authority",
                value = "${GradleConfig.App.ID}.fileProvider"
            )

//            signingConfig = signingConfigs.getByName(signingConfigRelease)
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging.resources {
        excludes.addAll(GradleConfig.excludePackagingResources)
    }
    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    coreLibraryDesugaring(libs.android.tools.desugaring)
    implementationDependencies()
    debugImplementationDependencies()
    testImplementationDependencies()
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

    implementation(project(":data:api:impl-ktor"))
//    implementation(project(":data:api:impl-retrofit"))

    implementation(project(":feature:home:ui"))
    implementation(project(":feature:movie:details:ui"))

    implementation(libs.play.services.base)
    implementation(libs.androidx.splashscreen)
    implementation(libs.material.components.android)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compose)
    implementation(libs.koin.compose.viewModel)
    implementation(libs.koin.android.worker)

    implementation(libs.androidx.metrics)
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

    androidTestImplementation(libs.koin.test.junit4)

    androidTestImplementation(libs.kotlin.test)
    androidTestImplementation(libs.kotlin.coroutines.test)

    androidTestImplementation(libs.compose.ui.test.manifest.debug)
    androidTestImplementation(libs.compose.ui.test.junit)
    androidTestImplementation(libs.compose.navigation.test)
}

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}
