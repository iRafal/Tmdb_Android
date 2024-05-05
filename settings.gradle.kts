pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Tmdb"
include(":app")
include(":util-logging")
include(":util")
include(":ui-core")
include(":data:model")
include(":data:api:config")
include(":data:api:model")
include(":data:api:impl-ktor")
include(":data:api:impl-retrofit")
include(":data:db:room")
include(":data:source:local:contract")
include(":data:source:local:impl-room")
include(":data:source:remote:contract")
include(":data:source:remote:impl-ktor")
include(":data:source:remote:impl-retrofit")
include(":data:db:object-box")
include(":data:db:realm")
include(":data:source:local:impl-objectBox")
include(":data:source:local:impl-realm")
include(":store:action")
include(":store:feature")
include(":store:state")
include(":store:env")
include(":store:base")
include(":store:app-store")
include(":feature:home:reducer")
include(":feature:movie:details:reducer")
include(":feature:home:ui")
include(":feature:movie:details:ui")
include(":ui-gallery")
include(":feature:common")
include(":feature:common-test")
