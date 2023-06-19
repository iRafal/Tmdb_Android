plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "${GradleConfig.BuildConfig.applicationId}.feature.movie.details.reducer"
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
    implementationDependencies()
    kapt(libs.hilt.kapt)
    testImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":store:base"))
    implementation(project(":store:env"))
    implementation(project(":store:feature"))
    implementation(project(":store:state"))

    implementation(project(":data:source:remote:contract"))
    implementation(project(":data:source:local:contract"))
    implementation(project(":data:model"))

    implementation(project(":feature:movie:details:action"))

    implementation(project(":util"))

    implementation(libs.hilt.android)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.hilt.test)
    testImplementation(libs.kotlinx.dateTime)
}
