plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
    id(libs.plugins.kotlin.serialization.get().pluginId)
    jacoco
}

dependencies {
    implementationDependencies()
    testImplementation(libs.kotlin.test)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.kotlinx.dateTime)
}
