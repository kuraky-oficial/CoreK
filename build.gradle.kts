plugins {
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1" // Necesario para meter Kotlin dentro del .jar
    id("java-library")
    id("maven-publish")
}

group = "com.kuraky.CoreK"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") // Repositorio de Paper
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    // API de Paper (Latest)
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")

    // Kotlin Standard Lib (se incluirá en el JAR final)
    implementation(kotlin("stdlib"))

    // Adventure (Para colores HEX y degradados de forma nativa)
    implementation("net.kyori:adventure-text-minimessage:4.17.0")

    // HikariCP (Para base de datos ultra rápida)
    implementation("com.zaxxer:HikariCP:5.1.0")
}

tasks {
    // Esto es vital: Une Kotlin y tus librerías dentro de un solo JAR "Shadow"
    shadowJar {
        archiveClassifier.set("") // Quita el sufijo -all del archivo final
        relocate("org.jetbrains.kotlin", "com.kuraky.CoreK.libs.kotlin") // Evita conflictos
    }

    build {
        dependsOn(shadowJar)
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "21"
    }
}