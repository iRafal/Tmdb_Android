plugins {
    id(GradleConfig.Plugins.ANDROID_LIBRARY)
    id(GradleConfig.Plugins.KOTLIN_ANDROID)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
    id(GradleConfig.Plugins.HILT)
}

android {
    val nameSpace = "${GradleConfig.Android.applicationId}.feature.home.ui"
    namespace = nameSpace
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        testInstrumentationRunner = "$nameSpace.runner.HiltTestRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.extension.get()
    }
    packaging {
        resources {
            this.excludes.addAll(GradleConfig.Android.excludePackagingResources)
        }
    }
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementationDependencies()

    kapt(libs.hilt.kapt)
    kaptTest(libs.hilt.kapt)
    kaptAndroidTest(libs.hilt.kapt)

    testImplementation(libs.bundles.feature.ui.impl.test)

    androidTestImplementation(libs.bundles.ui.test.android)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":ui-core"))
    implementation(project(":util"))
    implementation(project(":store:app"))

    implementation(libs.hilt.android)
}
