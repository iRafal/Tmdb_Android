plugins {
    alias(libs.plugins.kotlin.jvm)
    id(GradleConfig.Plugins.JAVA_LIBRARY)
}

dependencies {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
}
