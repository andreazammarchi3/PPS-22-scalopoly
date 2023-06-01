plugins {
    id("org.danilopianini.gradle-kotlin-qa") version "0.34.1"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.21"
}

repositories { mavenCentral() }

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0-RC")
    implementation("com.github.uharaqo.kotlin-hocon-mapper:kotlin-hocon-mapper:1.4.10")
    implementation("com.typesafe:config:1.4.2")
}
