plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

dependencies {
    implementationDependencies()
    testImplementation(libs.kotlin.test)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlinx.dateTime)
}
