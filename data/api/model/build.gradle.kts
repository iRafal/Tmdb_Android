plugins {
    alias(libs.plugins.kotlin.jvm)
    id(GradleConfig.Plugins.JAVA_LIBRARY)
    id(GradleConfig.Plugins.KOTLINX_SERIALIZATION)
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
