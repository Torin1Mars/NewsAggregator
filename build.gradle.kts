// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    val kotlinVersion = "2.0.0"
    id("com.android.application") version "8.8.0-alpha05" apply false
    id("org.jetbrains.kotlin.android") version "$kotlinVersion" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "$kotlinVersion" apply false

    id("com.google.devtools.ksp") version  "2.0.0-1.0.22" apply false
    id("com.google.dagger.hilt.android") version "2.57.1" apply false
}