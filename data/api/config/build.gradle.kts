import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
}

val apiProperties = Properties().apply { load(rootProject.file("api.properties").reader()) }
val apiKey = apiProperties["api.key"].toString()
val apiUrlBase = apiProperties["api.url.base"].toString()
val apiUrlImage = apiProperties["api.url.image"].toString()

val buildConfigApiBaseUrl = "API_BASE_URL"

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
            isDefault = true
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
    kotlinOptions.jvmTarget = GradleConfig.javaVersionAsString
    buildFeatures.buildConfig = true
}

dependencies {
    implementationDependencies()
    kaptDependencies()
    testImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.dagger)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.kotlin.test)
}

fun DependencyHandlerScope.kaptDependencies() {
    kapt(libs.dagger.compiler)
}
