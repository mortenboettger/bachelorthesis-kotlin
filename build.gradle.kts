plugins {
    alias(libs.plugins.kotlin.jvm) apply true
    alias(libs.plugins.sonarqube) apply true
    id("io.gitlab.arturbosch.detekt").version("1.23.0") apply true
}

sonarqube {
    properties {
        property("sonar.projectKey", "mortenboettger_bachelorthesis-kotlin")
        property("sonar.organization", "mboettger")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

detekt {
    source.setFrom("persistence/memory/src/main/kotlin")
}

allprojects {
    group = "io.mboettger"
    version = "1.0.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = rootProject.libs.plugins.kotlin.jvm.get().pluginId)

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {

        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xcontext-receivers")
            jvmTarget = "17"
        }
    }

    dependencies {
        implementation(platform(rootProject.libs.bom.spring.boot))

        implementation(rootProject.libs.bundles.kotlin)
    }
}