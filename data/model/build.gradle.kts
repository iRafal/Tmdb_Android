plugins {
    alias(libs.plugins.kotlin.jvm)
    id(GradleConfig.Plugins.JAVA_LIBRARY)
}

dependencies {
    implementationDependencies()
    testImplementation(libs.kotlin.test)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlinx.dateTime)
}
