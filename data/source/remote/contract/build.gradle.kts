plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
}

java {
    sourceCompatibility = GradleConfig.javaVersion
    targetCompatibility = GradleConfig.javaVersion
}

dependencies {
    apiDependencies()
    implementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":data:api:model"))
    api(project(":data:model"))
}
