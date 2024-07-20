plugins {
    id("java")
}

group = "me.vrganj"
version = "0.9.6"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.13.2-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

tasks.withType<ProcessResources> {
    filesMatching("**/plugin.yml") {
        expand("version" to project.version)
    }
}
