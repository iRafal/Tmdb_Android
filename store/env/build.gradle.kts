plugins {
    id(GradleConfig.Plugins.ANDROID_LIBRARY)
    id(GradleConfig.Plugins.KOTLIN_ANDROID)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
    id(GradleConfig.Plugins.HILT)
}

android {
    namespace = "${GradleConfig.Android.applicationId}.store.env"
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
    implementationDependencies()
    kapt(libs.hilt.kapt)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":data:source:remote:contract"))
    implementation(project(":data:source:local:contract"))

    // INFO: choose one of networking libraries
    //    api(project(":data:source:remote:impl"))
    api(project(":data:source:remote:impl-ktor"))

    // INFO: choose one of data caching libraries
    api(project(":data:source:local:impl"))
//    api(project(":data:source:local:impl-objectBox"))
//    api(project(":data:source:local:impl-realm"))

    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)

    implementation(libs.hilt.android)
}
