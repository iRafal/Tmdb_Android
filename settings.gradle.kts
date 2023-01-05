enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/libraries.versions.toml"))
        }
    }
}

rootProject.name = "Tmdb_Test"
rootProject.buildFileName = "build.gradle.kts"
include(":app")
include(":api:model")
