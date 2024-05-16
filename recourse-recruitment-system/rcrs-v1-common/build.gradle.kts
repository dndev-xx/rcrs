plugins {
    id("build-jvm")
    kotlin("kapt")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(libs.jackson.kotlin)
    implementation(libs.jackson.datatype)
    compileOnly(libs.kotlin.mapstruct)
    kapt(libs.kotlin.mapstruct.processor)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib)
    testImplementation(kotlin("test-junit"))
    api(libs.kotlinx.datetime)
    implementation(project(":rcrs-v1-spec-compile"))
}
