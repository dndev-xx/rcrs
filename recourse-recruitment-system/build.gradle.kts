plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

group = "com.otus.kfl.rcrs"
version = "D-00.000.01"

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }
}

ext {
    val specDir = layout.projectDirectory.dir("../specification")
    set("spec-v1", specDir.file("rcrs-v1-spec.yaml").toString())
}

tasks {
    arrayOf("build", "clean", "check").forEach {tsk ->
        create(tsk) {
            group = "build"
            dependsOn(subprojects.map {  it.getTasksByName(tsk,false)})
        }
    }
}
