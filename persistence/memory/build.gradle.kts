dependencies {
    implementation(project(":domain"))
    implementation(project(":persistence:gateway"))

    implementation(libs.persistence.migration)
    implementation(libs.persistence.hikari)
    implementation(libs.persistence.hibernate.core)
    implementation(libs.persistence.h2)
}
