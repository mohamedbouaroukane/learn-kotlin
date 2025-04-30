rootProject.name = "chartsgenerator"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()

        // مستودعات Jzy3d
        maven { url = uri("https://maven.jzy3d.org/releases") }
        maven { url = uri("https://maven.jzy3d.org/snapshots/") }
        maven { url = uri("https://jogamp.org/deployment/maven/") }
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()

        // ✅ أضف مستودعات jzy3d هنا أيضًا:
        maven { url = uri("https://maven.jzy3d.org/releases") }
        maven { url = uri("https://maven.jzy3d.org/snapshots/") }
        maven { url = uri("https://jogamp.org/deployment/maven/") }

    }
}

include(":composeApp")
