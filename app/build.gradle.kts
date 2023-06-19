plugins {
    id(GradleConfig.Plugins.ANDROID_APPLICATION)
    id(GradleConfig.Plugins.KOTLIN_ANDROID)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
    id(GradleConfig.Plugins.HILT)
}

android {
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        applicationId = GradleConfig.Android.applicationId
        minSdk = GradleConfig.Android.minSdk
        targetSdk = GradleConfig.Android.targetSdk

        versionCode = 1
        versionName = Version(major = 1, minor = 0, patch = 0).name

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isShrinkResources = GradleConfig.Android.isShrinkResourcesDebug
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledDebug
        }
        getByName("release") {
            isShrinkResources = GradleConfig.Android.isShrinkResourcesRelease
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledRelease

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    namespace = GradleConfig.Android.applicationId
    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }
}

// Ktlint manual integration
// val ktlint by configurations.creating

dependencies {

// Ktlint manual integration
//    ktlint(libs.ktlint) {
//        attributes {
//            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
//        }
//        // ktlint(project(":custom-ktlint-ruleset")) // in case of custom ruleset
//    }
    implementationDependencies()

    kaptDependencies()

    // debugImplementationDependencies() //INFO: not used
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":ui"))

    implementation(libs.hilt.android)
    implementation(libs.hilt.work)

    implementation(libs.androidx.work.runtime)
}

fun DependencyHandlerScope.kaptDependencies() {
    kapt(libs.hilt.kapt)
    kapt(libs.hilt.work.kapt)
}

fun DependencyHandlerScope.debugImplementationDependencies() {
    debugImplementation(libs.leakCanary.debug)
}

// Ktlint manual integration
// val outputDir = "${project.buildDir}/reports/ktlint/"
// val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))
//
// val ktlintCheck by tasks.creating(JavaExec::class) {
//    inputs.files(inputFiles)
//    outputs.dir(outputDir)
//
//    description = "Check Kotlin code style."
//    classpath = ktlint
//    mainClass.set("com.pinterest.ktlint.Main")
//    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
//    args = listOf("src/**/*.kt")
// }
//
// val ktlintFormat by tasks.creating(JavaExec::class) {
//    inputs.files(inputFiles)
//    outputs.dir(outputDir)
//
//    description = "Fix Kotlin code style deviations."
//    classpath = ktlint
//    mainClass.set("com.pinterest.ktlint.Main")
//    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
//    args = listOf("-F", "src/**/*.kt")
// }
