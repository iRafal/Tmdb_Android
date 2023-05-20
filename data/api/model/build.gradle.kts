plugins {
    id("java-library")
    id("kotlin")
    id("org.jetbrains.kotlin.jvm")
    id("kotlinx-serialization")
}

sourceSets {
    this[Libs.SourceSet.Main.name].java.srcDirs(*Libs.SourceSet.Main.sourceSets)
    this[Libs.SourceSet.Test.name].java.srcDirs(*Libs.SourceSet.Test.sourceSets)
}

java {
    sourceCompatibility = Libs.BuildConfig.CompileOptions.sourceCompatibility
    targetCompatibility = Libs.BuildConfig.CompileOptions.targetCompatibility
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
