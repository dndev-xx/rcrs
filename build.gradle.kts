plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

group = "com.otus.kfl.rcrs"
version = "D-00.000.001"

apply {
    from("repository.gradle")
}

subprojects {
    repositories {
        mavenCentral()
    }
    group = rootProject.group
    version = rootProject.version
}

tasks {
    create("check") {
        group = "verification"
        dependsOn(gradle.includedBuild("recourse-recruitment-system").task(":check"))
    }
}