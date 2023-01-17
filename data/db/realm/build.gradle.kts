plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("realm-android")
}

android {
    namespace = "com.tmdb_test.data.db.realm"
    compileSdk = Libs.BuildConfig.compileSdk

    defaultConfig {
        minSdk = Libs.BuildConfig.minSdk
        testInstrumentationRunner = "com.tmdb_test.data.db.realm.runner.HiltTestRunner"
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
    packagingOptions {
        resources {
            this.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            this.excludes.add("/META-INF/gradle/incremental.annotation.processors")
        }
    }
}

dependencies {
    androidTestImplementation(project(":util"))
    implementation(libs.bundles.data.db.realm)
    kapt(libs.bundles.data.db.realm.kapt)
    kaptTest(libs.bundles.data.db.realm.kapt.test)
    kaptAndroidTest(libs.bundles.data.db.realm.kapt.test.android)
    testImplementation(libs.bundles.data.db.realm.test)
    androidTestImplementation(libs.bundles.data.db.realm.test.android)
}