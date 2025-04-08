plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
}

dependencies {
    implementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":data:source:remote:contract"))
    implementation(project(":data:source:local:contract"))
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
}
