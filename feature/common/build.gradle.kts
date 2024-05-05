plugins {
    id(GradleConfig.Plugins.JAVA_LIBRARY)
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = GradleConfig.javaVersion
    targetCompatibility = GradleConfig.javaVersion
}

dependencies {
    implementationDependencies()
    apiDependencies()
    testImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":store:base"))
    implementation(project(":store:env"))
    implementation(project(":store:feature"))
    implementation(project(":store:state"))
    implementation(project(":store:action"))
    implementation(project(":data:source:remote:contract"))
    implementation(project(":data:source:local:contract"))
    implementation(project(":data:model"))
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":util-logging"))
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(project(":feature:common-test"))
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlinx.dateTime)
}
