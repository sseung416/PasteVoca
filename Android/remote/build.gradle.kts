plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
        targetSdk = AppConfig.TARGET_SDK
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(project(Modules.DATA))
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.SHARED_DOMAIN))
    implementation(project(Modules.SHARED_ANDROID))

    implementation(Libs.RETROFIT)
    implementation(Libs.CONVERTER_GSON)
    implementation(Libs.OKHTTP)
    implementation(Libs.LOGGING_INTERCEPTOR)
}