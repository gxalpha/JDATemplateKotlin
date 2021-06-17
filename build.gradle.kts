import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.github.gmazzo.buildconfig")
}
val group = "net.sebastianbeckmann"
val version = "1.0-SNAPSHOT"

/* execute "./gradlew generateBuildConfig" */
buildConfig {
    packageName("util")
    buildConfigField("String", "VERSION", "\"$version\"")
}

repositories {
    mavenCentral()
    maven {
        name = "m2-dv8tion"
        url = uri("https://m2.dv8tion.net/releases")
    }
    jcenter()
}
dependencies {
    testImplementation(kotlin("test-junit"))
    implementation("net.dv8tion:JDA:${findProperty("version.jda")}")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "core.MainKt"
    }
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
