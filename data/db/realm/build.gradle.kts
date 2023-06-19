plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("realm-android")
}

android {
    namespace = "${GradleConfig.BuildConfig.applicationId}.data.db.realm"
    compileSdk = GradleConfig.BuildConfig.compileSdk

    defaultConfig {
        minSdk = GradleConfig.BuildConfig.minSdk
        testInstrumentationRunner = "${GradleConfig.BuildConfig.applicationId}.data.db.realm.runner.HiltTestRunner"
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
    packagingOptions {
        resources {
            this.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            this.excludes.add("/META-INF/gradle/incremental.annotation.processors")
        }
    }
}

dependencies {
    implementationDependencies()

    kapt(libs.hilt.kapt)
    kaptTest(libs.hilt.kapt)
    kaptAndroidTest(libs.hilt.kapt)

    testImplementationDependencies()

    androidTestImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":util"))

    implementation(libs.hilt.android)
    implementation(libs.realm.plugin)
    implementation(libs.kotlinx.dateTime)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.hilt.test)
    testImplementation(libs.kotlinx.dateTime)
}

fun DependencyHandlerScope.androidTestImplementationDependencies() {
    androidTestImplementation(project(":util"))

    androidTestImplementation(libs.junit.android.ext)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.kotlin.coroutines.test)
    androidTestImplementation(libs.hilt.test)
    androidTestImplementation(libs.hilt.kapt)
    androidTestImplementation(libs.kotlinx.dateTime)
    androidTestImplementation(libs.realm.plugin)
}
