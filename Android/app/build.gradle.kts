plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        applicationId = AppConfig.APPLICATION_ID
        minSdk = AppConfig.MIN_SDK
        targetSdk = AppConfig.TARGET_SDK
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packagingOptions {
        resources.excludes.addAll(listOf(
            "META-INF/DEPENDENCIES",
            "META-INF/LICENSE",
            "META-INF/LICENSE.txt",
            "META-INF/license.txt",
            "META-INF/NOTICE",
            "META-INF/NOTICE.txt",
            "META-INF/INDEX.LIST",
            "META-INF/notice.txt",
            "META-INF/ASL2.0"
        ))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = "1.8"
        }

        viewBinding {
            android.buildFeatures.viewBinding = true
        }

        dataBinding {
            android.buildFeatures.dataBinding = true
        }
    }
}

dependencies {
    // todo di module로 분리
    // todo 의존성 그래프 정리
    implementation(project(path = Modules.CARD_STACK))
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.DATA))
    implementation(project(Modules.REMOTE))
    implementation(project(Modules.LOCAL))
    implementation(project(Modules.SHARED_DOMAIN))
    implementation(project(Modules.SHARED_ANDROID))

    // Architecture Components
    implementation(Libs.LIFECYCLE_VIEW_MODEL_KTX)
    implementation(Libs.LIFECYCLE_SERVICE)

    // UI
    implementation(Libs.ACTIVITY_KTX)
    implementation(Libs.FRAGMENT_KTX)
    implementation(Libs.MATERIAL)
    implementation(Libs.CORE_KTX)
    implementation(Libs.WORK_RUNTIME_KTX)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.LOTTIE)

    // Navigation
    implementation(Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(Libs.NAVIGATION_UI_KTX)

    // Koin
    implementation(Libs.KOIN_ANDROID)
    implementation(Libs.KOIN_ANDROID_COMPAT)
    testImplementation(Libs.KOIN_TEST)

    // coroutines
    implementation(Libs.COROUTINES)
    testImplementation(Libs.COROUTINES_TEST)

    // google cloud
    implementation(platform(Libs.GOOGLE_LIBRARIES_BOM))
    implementation(Libs.GOOGLE_CLOUD_STORAGE)

    // junit
    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.JUNIT_ANDROID)
    androidTestImplementation(Libs.ESPRESSO_CORE)

//    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
//    implementation 'io.grpc:grpc-okhttp:1.45.1'
}