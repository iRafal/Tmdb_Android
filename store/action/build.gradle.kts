plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

dependencies {
    implementation(project(":store:base"))
    implementation(project(":data:model"))
    implementation(libs.kotlin.stdLib)
}
