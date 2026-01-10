plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

android {
    namespace = "${GradleConfig.Android.NAMESPACE}.util"
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
    kspDependencies()
    apiDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.dagger)
    implementation(libs.kotlinx.dateTime)
    implementation(libs.logging)
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.dagger.compiler)
}

fun DependencyHandlerScope.apiDependencies() {
    api(libs.kotlin.stdLib)
    api(libs.kotlin.coroutines.core)
    api(libs.kotlin.coroutines.android)
    api(project(":util-logging"))
}

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}
