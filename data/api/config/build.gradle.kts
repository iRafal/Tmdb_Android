import java.util.Properties

plugins {
    id(GradleConfig.Plugins.ANDROID_LIBRARY)
    id(GradleConfig.Plugins.HILT)
    id(GradleConfig.Plugins.KOTLIN_ANDROID)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
}

val apiProperties = Properties().apply { load(rootProject.file("api.properties").reader()) }
val apiKey = apiProperties["api.key"].toString()
val apiUrlBase = apiProperties["api.url.base"].toString()
val apiUrlImage = apiProperties["api.url.image"].toString()

android {
    namespace = "${GradleConfig.Android.applicationId}.api.config"
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "API_KEY", apiKey)
        buildConfigField("String", "API_BASE_URL", apiUrlBase)
        buildConfigField("String", "API_IMAGE_URL", apiUrlImage)
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
    testImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.hilt.android)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
}
