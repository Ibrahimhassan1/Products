## Android Latest Best Practices MVVM Architecture
Integrating https://dummyjson.com/ 
GET	/products 

### Adding dependencies:
#### KSP (Kotlin Symbol Processing):
First, declare the [KSP plugin](https://github.com/google/ksp/releases) in your top level build.gradle.kts file.
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
#### Hilt for dependency injection:
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
