plugins {
    id(GradleConfig.Plugins.ANDROID_LIBRARY)
    id(GradleConfig.Plugins.HILT)
    id(GradleConfig.Plugins.KOTLIN_ANDROID)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
    id(GradleConfig.Plugins.KOTLINX_SERIALIZATION)
}

android {
    namespace = "${GradleConfig.Android.applicationId}.data.api.implKtor"
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
    kotlinOptions {
        jvmTarget = GradleConfig.javaVersionAsString
    }
}

dependencies {
    api(project(":data:api:model"))
    implementation(project(":data:api:config"))

    implementation(libs.bundles.data.api.impl.ktor)
    kapt(libs.hilt.kapt)

    testImplementation(libs.junit)

    testImplementation(libs.kotlin.stdLib)
    testImplementation(libs.kotlin.coroutines.core)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.serialization.json)

    testImplementation(libs.kotlinx.dateTime)

    testImplementation(libs.ktor.client.mock)
}
