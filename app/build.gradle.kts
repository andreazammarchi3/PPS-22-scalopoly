plugins {
    // Apply the scala Plugin to add support for Scala.
    scala

    // Apply the application plugin to add support for building a CLI application in Java.
    application

    id("cz.alenkacz.gradle.scalafmt") version "1.16.2"
    id("jacoco")
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.danilopianini.gradle-kotlin-qa") version "0.34.1"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala3-library_3:3.2.2")
    implementation("org.scalafx:scalafx_3:20.0.0-R31")
    implementation("org.scalafx:scalafxml-core_2.11:0.2.1")
    scalaCompilerPlugins("org.wartremover:wartremover_3.2.2:3.0.11")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

javafx {
    version = "17"
    modules = listOf("javafx.controls", "javafx.media", "javafx.fxml", "javafx.base", "javafx.graphics")
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

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.jacocoTestReport {
    // Configurazione dei report di copertura
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.7".toBigDecimal()
            }
        }
    }
}

scalafmt {
    // .scalafmt.conf in the project root is default value, provide only if other location is needed
    // config file has to be relative path from current project or root project in case of multimodule projects
    // example usage:
    configFilePath = "config/.scalafmt.conf"
}

// Code Linting (error prevention...)
val wartRemoverCompileOptions = Wartremover.configFile(file("../config/.wartremover.conf")).toCompilerOptions()

// Scala Compiler Options
tasks.withType(ScalaCompile::class.java) {
    scalaCompileOptions.additionalParameters =
        listOf(
            "-Xtarget:17",
            "-indent",
            "-rewrite",
            "-feature",
            "-language:implicitConversions"
        ) + wartRemoverCompileOptions
}
