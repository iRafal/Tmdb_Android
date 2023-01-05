plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlinx-serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(project(":api:model"))

    implementation(libs.bundles.api.impl.impl)
    testImplementation(libs.bundles.api.impl.impl.test)
}