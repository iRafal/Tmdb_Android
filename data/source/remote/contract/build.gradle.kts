plugins {
    id(GradleConfig.Plugins.JAVA_LIBRARY)
    alias(libs.plugins.kotlin.jvm)
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
