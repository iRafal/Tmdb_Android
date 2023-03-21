plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "${Libs.BuildConfig.applicationId}.data.source.remote.impl_ktor"
    compileSdk = Libs.BuildConfig.compileSdk

    defaultConfig {
        minSdk = Libs.BuildConfig.minSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = Libs.BuildConfig.isMinifyEnabledDebug
        }
        release {
            isMinifyEnabled = Libs.BuildConfig.isMinifyEnabledRelease
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Libs.BuildConfig.CompileOptions.sourceCompatibility
        targetCompatibility = Libs.BuildConfig.CompileOptions.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Libs.BuildConfig.KotlinOptions.jvmTarget
    }
    sourceSets {
        this[Libs.SourceSet.Main.name].java.srcDirs(*Libs.SourceSet.Main.sourceSets)
        this[Libs.SourceSet.Test.name].java.srcDirs(*Libs.SourceSet.Test.sourceSets)
    }
}

dependencies {
    api(project(":data:source:remote:contract"))
    implementation(project(":data:api:impl-ktor"))

    implementation(libs.bundles.data.source.remote.impl.ktor)
    kapt(libs.bundles.data.source.remote.impl.ktor.kapt)
    testImplementation(libs.bundles.data.source.remote.impl.ktor.test)
}