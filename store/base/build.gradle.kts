plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

java {
    sourceCompatibility = GradleConfig.javaVersion
    targetCompatibility = GradleConfig.javaVersion
}

dependencies {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
}
