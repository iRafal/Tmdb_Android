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
include(":data:api:model")
include(":data:api:impl-retrofit")
include(":data:source:remote:contract")
include(":data:source:remote:impl")
include(":data:api:config")
include(":data:api:impl-ktor")
include(":data:source:local:contract")
include(":data:source:local:impl")
include(":store")
include(":data:source:model")
