plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

android {
    val packageName = "com.tmdb.ui.gallery"
    namespace = packageName
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        applicationId = packageName
        minSdk = GradleConfig.Android.minSdk
        targetSdk = GradleConfig.Android.targetSdk


        versionCode = 1
        versionName = Version(major = 0, minor = 1, patch = 0, suffix = Version.Suffix.Snapshot).name

        vectorDrawables.useSupportLibrary = true

        setProperty("archivesBaseName", "$applicationId-$versionName-$versionCode")
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isShrinkResources = GradleConfig.Android.isShrinkResourcesDebug
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledDebug

            resValue("string", "app_name", "Tmdb-Ui-Gallery-Debug")
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging.resources {
        this.excludes.addAll(GradleConfig.Android.excludePackagingResources)
    }
    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }
}

dependencies {
    implementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":ui-core"))
    implementation(libs.material.components.android)
}
