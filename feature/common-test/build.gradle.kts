plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
}

java {
    sourceCompatibility = GradleConfig.javaVersion
    targetCompatibility = GradleConfig.javaVersion
}

dependencies {
    implementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":store:base"))
    implementation(project(":store:env"))
    implementation(project(":data:source:remote:contract"))
    implementation(project(":data:source:local:contract"))
    implementation(project(":data:model"))
    implementation(project(":util-logging"))

    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.test)
    implementation(libs.kotlinx.dateTime)
    implementation(libs.kotlin.test)

    implementation(libs.mockito)
    implementation(libs.mockito.kotlin)
}
