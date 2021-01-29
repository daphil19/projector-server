/*
 * GNU General Public License version 2
 *
 * Copyright (C) 2019-2021 JetBrains s.r.o.
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
plugins {
  kotlin("jvm")
  `maven-publish`
  java
}

publishing {
  publications {
    create<MavenPublication>("maven") {
      from(components["java"])
    }
  }
}

val kotlinVersion: String by project
val projectorClientVersion: String by project

dependencies {
  implementation("com.github.JetBrains.projector-client:projector-util-logging:$projectorClientVersion")
  testImplementation(kotlin("test", kotlinVersion))
  testImplementation(kotlin("test-junit", kotlinVersion))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))    
    }
}
