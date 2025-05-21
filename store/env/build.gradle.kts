plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

dependencies {
    implementationDependencies()
}

java {
    sourceCompatibility = GradleConfig.javaVersion
    targetCompatibility = GradleConfig.javaVersion
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":data:source:remote:contract"))
    implementation(project(":data:source:local:contract"))
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
}
