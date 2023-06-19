dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/libraries.versions.toml"))
        }
    }
}

rootProject.name = "Tmdb"
rootProject.buildFileName = "build.gradle.kts"
include(":app")

include(":ui")
include(":ui-core")

include(":store:base")
include(":store:feature")
include(":store:action")
include(":store:env")

include(":store:reducer:app")
include(":store:reducer:movie:details")

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

include(":data:source:remote:impl-ktor")
include(":data:db:room")
include(":data:db:realm")
include(":data:source:local:impl-realm")
include(":data:db:object-box")
include(":data:source:local:impl-objectBox")

include(":util")

include(":feature:home:ui")
include(":feature:home:reducer")

include(":feature:movie:details:ui")
