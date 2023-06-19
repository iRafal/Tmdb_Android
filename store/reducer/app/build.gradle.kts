plugins {
    id(GradleConfig.Plugins.ANDROID_LIBRARY)
    id(GradleConfig.Plugins.KOTLIN_ANDROID)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
    id(GradleConfig.Plugins.HILT)
}

android {
    namespace = "${GradleConfig.Android.applicationId}.store.reducer.app"
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
    implementation(project(":feature:home:reducer"))
    implementation(project(":feature:home:action"))

    implementation(project(":feature:movie:details:reducer"))
    implementation(project(":feature:movie:details:action"))

    implementation(project(":store:base"))
    implementation(project(":store:env"))
    implementation(project(":store:feature"))
    implementation(project(":store:state"))
    implementation(project(":data:source:remote:contract"))
    implementation(project(":data:source:local:contract"))
    implementation(project(":data:model"))
    implementation(project(":util"))

    implementation(libs.hilt.android)
}
