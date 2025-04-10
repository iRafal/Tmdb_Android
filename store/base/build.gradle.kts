plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
    jacoco
}

dependencies {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
}
