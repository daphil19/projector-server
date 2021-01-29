import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
  kotlin("jvm") apply false
  java
  `maven-publish`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))    
    }
}

val compiler by extra(javaToolchains.compilerFor {
    languageVersion.set(JavaLanguageVersion.of(11))
})


val kotlinVersion: String by project
val targetJvm: String by project

subprojects {
  group = "org.jetbrains"
  version = "1.0-SNAPSHOT"

  repositories {
    jcenter()
    maven("https://jitpack.io")
  }

//   tasks.withType<JavaCompile> {
//     sourceCompatibility = targetJvm
//   }

  tasks.withType<KotlinCompile<*>> {
    kotlinOptions {
      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
      //allWarningsAsErrors = true  // todo: resolve "different Kotlin JARs in runtime classpath" and "bundled Kotlin runtime"
    }
  }

  tasks.withType<KotlinJvmCompile> {
    kotlinOptions {
      jvmTarget = targetJvm
      jdkHome = compiler.get().metadata.installationPath.asFile.absolutePath
    }
  }
}
