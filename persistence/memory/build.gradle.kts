dependencies {
    implementation(project(":domain"))
    implementation(project(":persistence-boundary"))

    implementation(libs.persistence.migration)
    implementation(libs.persistence.hikari)
    implementation(libs.persistence.hibernate.core)
    implementation(libs.persistence.h2)
}
