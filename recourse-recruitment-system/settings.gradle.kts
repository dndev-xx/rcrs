rootProject.name = "recourse-recruitment-system"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

pluginManagement {
    includeBuild("../build-plugin")
    plugins {
        id("build-jvm") apply false
        id("build-kmp") apply false
    }
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":rcrs-v1-common")
include(":rcrs-v1-spec-compile")
include(":rcrs-v1-sql-builder")


