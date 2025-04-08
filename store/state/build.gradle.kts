plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.java.library.get().pluginId)
}

dependencies {
    implementation(project(":data:model"))
    implementation(libs.kotlin.stdLib)
}
