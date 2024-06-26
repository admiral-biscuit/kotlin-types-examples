import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins { kotlin("jvm") version "1.9.10" }

group = "de.admiralbiscuit.akquinet.kotlin-types-examples"

version = "1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
  implementation("io.arrow-kt:arrow-core:1.2.4")
  testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
  testImplementation("io.kotest.extensions:kotest-assertions-arrow-jvm:1.4.0")
}

tasks.test { useJUnitPlatform() }

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }
