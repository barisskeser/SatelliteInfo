pluginManagement {
    repositories {
        google()
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

rootProject.name = "SatelliteInfo"
include(":app")
include(":feature:satellites")
include(":feature:satelliteDetail")
include(":navigation")
include(":common")
include(":data")
include(":domain")
include(":core")
