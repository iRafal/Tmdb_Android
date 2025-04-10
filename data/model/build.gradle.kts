plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
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
