plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

group = "com.otus.kfl.rcrs"
version = "0.0.1"

apply {
    from("repository.gradle")
}

subprojects {
    group = rootProject.group
    version = rootProject.version
}