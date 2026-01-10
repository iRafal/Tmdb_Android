plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

android {
    namespace = "${GradleConfig.Android.NAMESPACE}.data.local.impl.objectBox"
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
    implementationDependencies()
    api(project(":data:source:local:contract"))
    testImplementation(libs.bundles.data.source.local.impl.test)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.bundles.data.source.local.impl)
    implementation(libs.koin.core)
    implementation(project(":util"))
    implementation(project(":data:db:object-box"))
}
