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
            version("sonarqube", "3.4.0.2513")

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

include(":starter", ":domain", ":usecase", ":usecase-boundary", ":persistence-boundary", ":persistence:memory", ":web")
