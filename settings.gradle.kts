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

include(":store:base")
include(":store:feature")
include(":store:action")
include(":store:env")
include(":store:reducer")
include(":store:state")
include(":store:app")

include(":data:model")

include(":data:source:local:contract")
include(":data:source:local:impl")

include(":data:source:remote:contract")
include(":data:source:remote:impl")

include(":data:api:impl-ktor")
include(":data:api:impl-retrofit")
include(":data:api:model")
include(":data:api:config")

include(":ui")

include(":util")
