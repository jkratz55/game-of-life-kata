import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
}

group = "io.jkratz.katas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(group = "junit", name = "junit", version = "4.12")
    compile(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-jdk8", version = "1.2.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}