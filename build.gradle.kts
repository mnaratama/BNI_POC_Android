import org.json.CDL

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.5.0.2730")
    }

}
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.kotlinKapt) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.sonarQube) apply true
}

tasks.register("clean", Delete::class) {
    delete (rootProject.buildDir)
}

sonarqube {
    val sonarProperties = rootProject.file("sonarqube.properties")
    val props = java.util.Properties()
    props.load(java.io.FileInputStream(sonarProperties))
    properties {
        property("sonar.projectName", props["projectName"].toString())
        property("sonar.projectKey", props["projectKey"].toString())
        property("sonar.host.url", props["url"].toString())
        property("sonar.tests",  props["tests"].toString())
        property("sonar.test.inclusions", props["inclusions"].toString())
        property("sonar.sourceEncoding", props["sourceEncoding"].toString())
        property("sonar.sources", props["sources"].toString())
        property("sonar.login", props["login"].toString())
        property("sonar.password", props["password"].toString())
        property("sonar.exclusions", props["exclusions"].toString())

    }
}

