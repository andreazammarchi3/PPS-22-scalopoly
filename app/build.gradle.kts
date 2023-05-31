plugins {
    // Apply the scala Plugin to add support for Scala.
    scala

    // Apply the application plugin to add support for building a CLI application in Java.
    application

    id("cz.alenkacz.gradle.scalafmt") version "1.16.2"
    id("jacoco")
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala3-library_3:3.2.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

application {
    // Define the main class for the application.
    mainClass.set("PPS.scalopoly.App")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<ScalaCompile> {
    targetCompatibility = ""
}

tasks.jacocoTestReport {
    // Configurazione dei report di copertura
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}
