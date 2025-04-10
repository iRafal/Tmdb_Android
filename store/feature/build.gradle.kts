plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
    jacoco
}

dependencies {
    implementation(project(":store:base"))
    implementation(libs.kotlin.stdLib)
}
