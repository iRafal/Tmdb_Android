plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
    jacoco
}

java {
    sourceCompatibility = GradleConfig.javaVersion
    targetCompatibility = GradleConfig.javaVersion
}
