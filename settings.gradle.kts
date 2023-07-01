rootProject.name = "bachelorthesis"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {

            version("kotlin", "1.8.22")
            version("spring-boot", "2.7.4")
            version("hikari", "6.1.3.Final")
            version("flyway", "9.3.1")
            version("hibernate", "6.1.3.Final")
            version("h2", "2.1.214")
            version("sonarqube", "4.2.1.3168")

            plugin("kotlin-jvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
            plugin("kotlin-noarg", "org.jetbrains.kotlin.plugin.noarg").versionRef("kotlin")
            plugin("spring-boot", "org.springframework.boot").versionRef("spring-boot")
            plugin("sonarqube", "org.sonarqube").versionRef("sonarqube")

            // bom
            library("bom-spring-boot", "org.springframework.boot", "spring-boot-dependencies").versionRef("spring-boot")

            // kotlin
            library("kotlin-stdlib", "org.jetbrains.kotlin", "kotlin-stdlib-jdk8").withoutVersion()
            library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").withoutVersion()

            // spring boot
            library("spring-boot-starter", "org.springframework.boot", "spring-boot-starter").withoutVersion()
            library("spring-boot-web", "org.springframework.boot","spring-boot-starter-web").withoutVersion()

            // persistence mysql
            library("persistence-hikari", "org.hibernate.orm", "hibernate-hikaricp").versionRef("hikari")
            library("persistence-migration", "org.flywaydb", "flyway-mysql").versionRef("flyway")
            library("persistence-hibernate-core", "org.hibernate", "hibernate-core").versionRef("hibernate")

            // persistence memory
            library("persistence-h2", "com.h2database", "h2").versionRef("h2")

            // jackson
            library("jackson-jsr310", "com.fasterxml.jackson.datatype", "jackson-datatype-jsr310").withoutVersion()
            library("jackson-kotlin", "com.fasterxml.jackson.module", "jackson-module-kotlin").withoutVersion()
            library("jackson-databind", "com.fasterxml.jackson.core", "jackson-databind").withoutVersion()
            library("jackson-core", "com.fasterxml.jackson.core", "jackson-core").withoutVersion()

            // #################
            // bundles
            // #################

            bundle("kotlin", listOf("kotlin-stdlib", "kotlin-reflect"))
        }
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

include(":starter", ":domain", ":usecase:impl", ":usecase:boundary", ":persistence:gateway", ":persistence:memory", ":web")
