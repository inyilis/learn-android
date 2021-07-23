plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(AppConfig.compileSDK)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId("com.example.mychallenge")
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }
}

dependencies {
    implementation(AppDependencies.kotlinStdlib)
    implementation(AppDependencies.androidxCore)
    implementation(AppDependencies.androidxAppcompat)
    implementation(AppDependencies.androidMaterial)
    implementation(AppDependencies.constraintLayout)
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation(AppDependencies.junit)
    androidTestImplementation(AppDependencies.testExt)
    androidTestImplementation(AppDependencies.testEspresso)
}