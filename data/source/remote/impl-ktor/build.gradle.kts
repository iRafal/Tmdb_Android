plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "${GradleConfig.BuildConfig.applicationId}.data.source.remote.implKtor"
    compileSdk = GradleConfig.BuildConfig.compileSdk

    defaultConfig {
        minSdk = GradleConfig.BuildConfig.minSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = GradleConfig.BuildConfig.isMinifyEnabledDebug
        }
        release {
            isMinifyEnabled = GradleConfig.BuildConfig.isMinifyEnabledRelease
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
    kotlinOptions {
        jvmTarget = GradleConfig.javaVersionAsString
    }
    sourceSets {
        this[GradleConfig.SourceSet.Main.name].java.srcDirs(*GradleConfig.SourceSet.Main.sourceSets)
        this[GradleConfig.SourceSet.Test.name].java.srcDirs(*GradleConfig.SourceSet.Test.sourceSets)
    }
}

dependencies {
    api(project(":data:source:remote:contract"))
    implementationDependencies()
    kapt(libs.hilt.kapt)
    testImplementation(libs.bundles.data.source.remote.impl.test)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":data:api:impl-ktor"))
    implementation(libs.bundles.data.source.remote.impl)
}
