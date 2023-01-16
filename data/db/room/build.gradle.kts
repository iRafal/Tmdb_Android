plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.tmdb_test.data.db.room"
    compileSdk = Libs.BuildConfig.compileSdk

    defaultConfig {
        minSdk = 24
        minSdk = Libs.BuildConfig.minSdk
        testInstrumentationRunner = "com.tmdb_test.data.db.room.runner.HiltTestRunner"
        consumerProguardFiles("consumer-rules.pro")
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += "room.schemaLocation" to "$projectDir/schemas"
            }
        }
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

        getByName("androidTest").assets.srcDirs(files("$projectDir/schemas"))
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
    implementation(libs.bundles.data.db.room)
    kapt(libs.bundles.data.db.room.kapt)
    kaptTest(libs.bundles.data.db.room.kapt.test)
    kaptAndroidTest(libs.bundles.data.db.room.kapt.test.android)
    testImplementation(libs.bundles.data.db.room.test)
    androidTestImplementation(libs.bundles.data.db.room.test.android)
}

kapt{
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}