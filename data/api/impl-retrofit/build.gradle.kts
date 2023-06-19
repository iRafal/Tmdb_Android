plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

android {
    namespace = "${GradleConfig.BuildConfig.applicationId}.data.api.implRetrofit"
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
    api(project(":data:api:model"))
    implementation(project(":data:api:config"))

    implementation(libs.bundles.data.api.impl.retrofit)
    kapt(libs.hilt.kapt)

    testImplementation(libs.junit)

    testImplementation(libs.kotlin.stdLib)
    testImplementation(libs.kotlin.coroutines.core)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.serialization.json)
    testImplementation(libs.kotlinx.dateTime)

    testImplementation(libs.retrofit2)
    testImplementation(libs.retrofit2.scalars)
    testImplementation(libs.retrofit2.serializationConverter)
    testImplementation(libs.okHttp3.loggingInterceptor)
    testImplementation(libs.okHttp3.mockwebserver)
}
