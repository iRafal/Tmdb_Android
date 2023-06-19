plugins {
    id(GradleConfig.Plugins.JAVA_LIBRARY)
    id(GradleConfig.Plugins.KOTLIN)
    id(GradleConfig.Plugins.KOTLIN_JVM)
    id(GradleConfig.Plugins.KOTLINX_SERIALIZATION)
}

java {
    sourceCompatibility = GradleConfig.javaVersion
    targetCompatibility = GradleConfig.javaVersion
}

dependencies {
    implementationDependencies()
    testImplementation(libs.junit)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.kotlinx.dateTime)
}
