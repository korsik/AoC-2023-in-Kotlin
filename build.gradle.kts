plugins {
    kotlin("jvm") version "1.9.20"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.10")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")

    implementation("io.ksmt:ksmt-core:0.5.6")
    implementation("io.ksmt:ksmt-z3:0.5.6")

    implementation("de.fabmax.kool:kool-core:0.14.0")
    implementation("de.fabmax.kool:kool-physics:0.14.0")

    listOf("natives-windows", "natives-linux", "natives-macos", "natives-macos-arm64").forEach { platform ->
        val lwjglVersion = "3.3.3"
        val physxJniVersion = "2.3.1"

        // lwjgl runtime libs
        runtimeOnly("org.lwjgl:lwjgl:$lwjglVersion:$platform")
        listOf("glfw", "opengl", "jemalloc", "nfd", "stb", "vma", "shaderc").forEach { lib ->
            runtimeOnly("org.lwjgl:lwjgl-$lib:$lwjglVersion:$platform")
        }

        // physx-jni runtime libs
        runtimeOnly("de.fabmax:physx-jni:$physxJniVersion:$platform")
    }
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
