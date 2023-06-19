plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "${Libs.BuildConfig.applicationId}.store.app"
    compileSdk = Libs.BuildConfig.compileSdk

    defaultConfig {
        minSdk = Libs.BuildConfig.minSdk
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        debug {
            isMinifyEnabled = Libs.BuildConfig.isMinifyEnabledDebug
        }
        release {
            isMinifyEnabled = Libs.BuildConfig.isMinifyEnabledRelease
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Libs.BuildConfig.CompileOptions.sourceCompatibility
        targetCompatibility = Libs.BuildConfig.CompileOptions.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Libs.BuildConfig.KotlinOptions.jvmTarget
    }
    sourceSets {
        this[Libs.SourceSet.Main.name].java.srcDirs(*Libs.SourceSet.Main.sourceSets)
        this[Libs.SourceSet.Test.name].java.srcDirs(*Libs.SourceSet.Test.sourceSets)
    }
}

dependencies {
    implementationDependencies()
    apiDependencies()
    kapt(libs.hilt.kapt)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":store:reducer:app"))
    implementation(project(":util"))

    implementation(libs.hilt.android)
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":store:base"))
    api(project(":store:env"))
    api(project(":store:state"))
    api(project(":store:action"))
    api(project(":store:feature"))

    api(project(":feature:home:action"))

    api(project(":data:model"))
}
