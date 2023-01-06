plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

val apiKey = if (hasProperty("api.key")) {
    property("api.key").toString()
} else {
    throw IllegalStateException("""[api.key] property is missed""")
}

android {
    namespace = "com.tmdb_test.api.config"
    compileSdk = Libs.BuildConfig.compileSdk

    defaultConfig {
        minSdk = Libs.BuildConfig.minSdk
        targetSdk = Libs.BuildConfig.targetSdk

        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "API_KEY", """"$apiKey"""")
        buildConfigField(
            "String",
            "API_BASE_URL",
            "\"https://api.themoviedb.org/3/\""
        )
        buildConfigField(
            "String",
            "API_IMAGE_URL",
            "\"https://image.tmdb.org/t/p/\""
        )
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
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

    implementation(libs.bundles.data.api.config)
    kapt(libs.bundles.data.api.config.kapt)
}