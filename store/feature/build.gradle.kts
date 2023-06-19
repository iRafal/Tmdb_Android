plugins {
    id("java-library")
    id("kotlin")
    id("org.jetbrains.kotlin.jvm")
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
    implementation(project(":store:base"))
    implementation(libs.kotlin.stdLib)
}
