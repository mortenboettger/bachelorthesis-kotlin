plugins {
    alias(libs.plugins.spring.boot)
    java
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":persistence-boundary"))
    implementation(project(":persistence:memory"))
    implementation(project(":web"))

    implementation(libs.spring.boot.starter)
}