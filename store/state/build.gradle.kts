plugins {
    id(GradleConfig.Plugins.JAVA_LIBRARY)
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":data:model"))
    implementation(libs.kotlin.stdLib)
}
