plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
    id(libs.plugins.realm.android.get().pluginId)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

val packageName = "${GradleConfig.Android.NAMESPACE}.data.db.realm"
android {
    namespace = packageName
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
        testInstrumentationRunner = "$packageName.runner.DaggerTestRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }
    packaging {
        resources {
            this.excludes.addAll(GradleConfig.excludePackagingResources)
        }
    }
}

dependencies {
    implementationDependencies()
    kspDependencies()
    kspAndroidTest(libs.dagger.compiler)

    testImplementationDependencies()

    androidTestImplementationDependencies()
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.dagger.compiler)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":util"))

    implementation(libs.dagger)

    implementation(libs.realm.plugin)
    implementation(libs.kotlinx.dateTime)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlinx.dateTime)
}

fun DependencyHandlerScope.androidTestImplementationDependencies() {
    androidTestImplementation(project(":util"))

    androidTestImplementation(libs.junit.android.ext)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.kotlin.test)
    androidTestImplementation(libs.kotlin.coroutines.test)
    androidTestImplementation(libs.kotlinx.dateTime)
    androidTestImplementation(libs.realm.plugin)
}
