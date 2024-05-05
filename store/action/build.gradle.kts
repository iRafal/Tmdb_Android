plugins {
    id(GradleConfig.Plugins.JAVA_LIBRARY)
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":store:base"))
    implementation(project(":data:model"))
    implementation(libs.kotlin.stdLib)
}
