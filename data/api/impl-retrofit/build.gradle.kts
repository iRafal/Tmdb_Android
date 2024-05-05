plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
    id(GradleConfig.Plugins.KOTLINX_SERIALIZATION)
}

android {
    namespace = "${GradleConfig.Android.applicationId}.data.api.implRetrofit"
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
    kotlinOptions.jvmTarget = GradleConfig.javaVersionAsString
}

dependencies {
    apiDependencies()
    implementationDependencies()
    kapt(libs.dagger.compiler)
    testImplementationDependencies()
    implementation(libs.dagger.compiler)
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":data:api:model"))
    implementation(project(":data:api:config"))
    implementation(project(":util"))
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.kotlin.serialization.json)

    implementation(libs.retrofit2)
    implementation(libs.retrofit2.scalars)
    implementation(libs.retrofit2.serializationConverter)
    implementation(libs.okHttp3.loggingInterceptor)

    implementation(libs.dagger)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.kotlin.stdLib)
    testImplementation(libs.kotlin.test)
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
