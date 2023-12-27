pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            credentials {
                username = ""
                //Enter you github token with repo write permission access
                password = ""
            }
            url = uri("https://maven.pkg.github.com/JioMeet/JioMatrixTranslationAndroidUIKit")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "JioAudioTranslationSampleApp"
include(":app")
