## Android Latest Best Practices MVVM Architecture
Integrating https://dummyjson.com/ 
GET	/products 

### Adding dependencies:
- Hilt for dependency injection:
  - ```kotlin
plugins {
...
id("com.google.dagger.hilt.android") version "2.44" apply false
}
```