## Android Latest Best Practices MVVM Architecture
Integrating https://dummyjson.com/ 
GET	/products 

### Adding dependencies:
#### [KSP](https://github.com/google/ksp/releases) (Kotlin Symbol Processing):
First, declare the KSP plugin in your top level build.gradle.kts file.
```
plugins {
  alias(libs.plugins.ksp) apply false
}
```
Then inside the `libs.versions.toml` add versions and plugins reference:
```
[versions]
kspVersion = "1.9.23-1.0.20"

[plugins]
ksp = { id = "com.google.devtools.ksp", version.ref = "kspVersion" }
```
Then, enable KSP in your module-level build.gradle.kts file:
```
plugins {
  alias(libs.plugins.ksp)
}
```
#### [Hilt](https://dagger.dev/hilt/quick-start) for dependency injection:
First inside the `libs.versions.toml` add versions and plugins reference:
```
[versions]
hiltAndroid = "2.51.1"

[libraries]
hilt-android = { module = "com.google.dagger:hilt-android", version.ref = "hiltAndroid" }
hilt-android-compiler = { module = "com.google.dagger:hilt-android-compiler", version.ref = "hiltAndroid" }

[plugins]
hilt = { id = "com.google.dagger.hilt.android", version.ref = "hiltAndroid" }
```
Then, add the `hilt-android-gradle-plugin` plugin to your project's root `build.gradle` file:
```
plugins {
  alias(libs.plugins.hilt) apply false
}
```
Then, apply the Gradle plugin and add these dependencies in your app/build.gradle file:
```
plugins {
  alias(libs.plugins.hilt)
}

android {
  ...
}

dependencies {
  implementation(libs.hilt.android)
  ksp(libs.hilt.android.compiler)
}
```
### [coil](https://coil-kt.github.io/coil/) for image loading:
First inside the `libs.versions.toml` add versions and plugins reference:
```
[versions]
coil = "2.6.0"

[libraries]
coil = { module = "io.coil-kt:coil", version.ref = "coil" }
```
Then, apply the Gradle plugin and add these dependencies in your app/build.gradle file:
```
dependencies {
  implementation(libs.coil)
}
```

### [retrofit](https://square.github.io/retrofit/) for HTTP network requests and Gson for JSON parsing:
First inside the `libs.versions.toml` add versions and plugins reference:
```
[versions]
retrofit = "2.11.0"
converterGson = "2.11.0"


[libraries]
retrofit = { module = "com.squareup.retrofit2:retrofit", version.ref = "retrofit" }
converter-gson = { module = "com.squareup.retrofit2:converter-gson", version.ref = "converterGson" }

```
Then, apply the Gradle plugin and add these dependencies in your app/build.gradle file:
```
dependencies {
  implementation(libs.retrofit)
  implementation(libs.converter.gson)
}
```

### [Compose navigation and hilt](https://developer.android.com/develop/ui/compose/libraries#hilt-navigation) for navigation:
First inside the `libs.versions.toml` add versions and plugins reference:
```
[versions]
navigationCompose = "2.7.7"
hiltNavigationCompose = "1.2.0"


[libraries]
androidx-navigation-compose = { module = "androidx.navigation:navigation-compose", version.ref = "navigationCompose" }
androidx-hilt-navigation-compose = { module = "androidx.hilt:hilt-navigation-compose", version.ref = "hiltNavigationCompose" }

```
Then, apply the Gradle plugin and add these dependencies in your app/build.gradle file:
```
dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
}
```

### [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) for concurrency management:
First inside the `libs.versions.toml` add versions and plugins reference:
```
[versions]
kotlinxCoroutinesAndroid = "1.8.0"

[libraries]
kotlinx-coroutines-android = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-android", version.ref = "kotlinxCoroutinesAndroid" }

```
Then, apply the Gradle plugin and add these dependencies in your app/build.gradle file:
```
dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
```

### [Sandwich](https://skydoves.github.io/sandwich/) handling API responses and exceptions:
First inside the `libs.versions.toml` add versions and plugins reference:
```
[versions]
sandwichRetrofit = "2.0.7"

[libraries]
sandwich-retrofit = { module = "com.github.skydoves:sandwich-retrofit", version.ref = "sandwichRetrofit" }

```
Then, apply the Gradle plugin and add these dependencies in your app/build.gradle file:
```
dependencies {
    implementation(libs.sandwich.retrofit)
}
```
### [Okhttp Interceptors](https://square.github.io/okhttp/features/interceptors/) for API Interceptors:
First inside the `libs.versions.toml` add versions and plugins reference:
```
[versions]
loggingInterceptor = "4.12.0"

[libraries]
logging-interceptor = { module = "com.squareup.okhttp3:logging-interceptor", version.ref = "loggingInterceptor" }

```
Then, apply the Gradle plugin and add these dependencies in your app/build.gradle file:
```
dependencies {
    implementation(libs.logging.interceptor)
}
```