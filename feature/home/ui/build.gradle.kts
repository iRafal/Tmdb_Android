plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

android {
    val nameSpace = "${GradleConfig.Android.NAMESPACE}.feature.home.ui"
    namespace = nameSpace
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
        testInstrumentationRunner = "$nameSpace.runner.DaggerTestRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    buildFeatures.compose = true

    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }

    packaging {
        resources {
            this.excludes.addAll(GradleConfig.excludePackagingResources)
        }
    }
}

dependencies {
    implementationDependencies()
    kspDependencies()
    kspAndroidTest(libs.dagger.compiler)
    testImplementation(libs.bundles.feature.ui.impl.test)
    androidTestImplementation(libs.bundles.ui.test.android)
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.dagger.compiler)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":ui-core"))
    implementation(project(":util"))
    implementation(project(":store:app-store"))

    implementation(libs.dagger)
    implementation(libs.compose.material)
}
