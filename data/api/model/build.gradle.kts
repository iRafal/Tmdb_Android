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
    implementation(libs.bundles.data.api.model.impl)
    testImplementation(libs.bundles.data.api.model.impl.test)
}