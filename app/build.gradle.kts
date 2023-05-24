/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Scala application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.2/userguide/building_java_projects.html
 */

plugins {
    // Apply the scala Plugin to add support for Scala.
    scala

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala3-library_3:3.2.2")
    implementation("commons-collections:commons-collections:3.2.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

application {
    // Define the main class for the application.
    mainClass.set("PPS.scalopoly.App")
}
