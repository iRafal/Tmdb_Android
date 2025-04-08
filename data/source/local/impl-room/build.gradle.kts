import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.ksp)
}

android {
    namespace = "${GradleConfig.Android.applicationId}.data.source.local.impl"
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
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            allWarningsAsErrors = false
        }
    }
}

dependencies {
    apiDependencies()
    implementationDependencies()
    ksp(libs.dagger.compiler)
    testImplementation(libs.bundles.data.source.local.impl.test)
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":data:source:local:contract"))
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.dagger)
    implementation(project(":data:db:room"))
    implementation(project(":util"))
    implementation(libs.bundles.data.source.local.impl)
}
