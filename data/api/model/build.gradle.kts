plugins {
    id("java-library")
    id("kotlin")
    id("org.jetbrains.kotlin.jvm")
    id("kotlinx-serialization")
}

sourceSets {
    this[GradleConfig.SourceSet.Main.name].java.srcDirs(*GradleConfig.SourceSet.Main.sourceSets)
    this[GradleConfig.SourceSet.Test.name].java.srcDirs(*GradleConfig.SourceSet.Test.sourceSets)
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
