plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.hilt)
    jacoco
}

android {
    val nameSpace = "${GradleConfig.Android.applicationId}.store"
    namespace = nameSpace
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementationDependencies()
    apiDependencies()
    kspDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.hilt)
    implementation(project(":util"))
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.hilt.compiler)
}

fun DependencyHandlerScope.apiDependencies() {
    implementation(project(":data:source:remote:contract"))
    implementation(project(":data:source:local:contract"))

    api(project(":feature:common"))
    api(project(":feature:home:reducer"))
    api(project(":feature:movie:details:reducer"))

    api(project(":store:base"))
    api(project(":store:state"))
    api(project(":store:env"))
    api(project(":store:feature"))
    api(project(":store:action"))
    api(project(":data:model"))
}
