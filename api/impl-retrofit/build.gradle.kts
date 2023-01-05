plugins {
    id("java-library")
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
    api(project(":api:model"))

    implementation(libs.bundles.api.impl.impl)
    testImplementation(libs.bundles.api.impl.impl.test)
}