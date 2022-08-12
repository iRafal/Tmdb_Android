buildscript {
    repositories {
        google()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()
        google()
    }

    dependencies {
//        // https://developer.android.com/studio/releases/gradle-plugin
//        classpath("com.android.tools.build:gradle:7.2.2")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
//        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.10")

        classpath(libs.gradle)
        classpath(libs.kotlin.gradle)
        classpath(libs.kotlin.serialization)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
