plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

android {
    namespace = "${GradleConfig.Android.NAMESPACE}.data.source.local.impl"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }
}

dependencies {
    apiDependencies()
    implementationDependencies()
    testImplementation(libs.bundles.data.source.local.impl.test)
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":data:source:local:contract"))
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.koin.core)
    implementation(project(":data:db:room"))
    implementation(project(":util"))
    implementation(libs.bundles.data.source.local.impl)
}
