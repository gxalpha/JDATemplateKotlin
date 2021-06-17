pluginManagement {
    plugins {
        kotlin("jvm") version settings.extra["version.kotlin"].toString()
        id("com.github.gmazzo.buildconfig") version settings.extra["version.buildconfig"].toString()
    }
}
