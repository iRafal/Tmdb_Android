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
    implementation(project(":store:base"))
    implementation(project(":data:model"))
    implementation(libs.kotlin.stdLib)
}
