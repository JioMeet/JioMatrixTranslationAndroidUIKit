## Configure JioMatrixTranslationAndroidUIKit inside your app

i. **Step 1** : Generate a Personal Access Token for GitHub

- Settings -> Developer Settings -> Personal Access Tokens -> Generate new token
- Make sure you select the following scopes (“ read:packages”) and Generate a token
- After Generating make sure to copy your new personal access token. You cannot see it again! The only option is to generate a new key.

ii. Update build.gradle inside the application module

```kotlin
    repositories {
    maven {
        credentials {
            <!--github user name-->
                username = ""
            <!--github user token-->
                password = ""
        }
        url = uri("https://maven.pkg.github.com/JioMeet/JioMatrixTranslationAndroidUIKit")
    }
    google()
    mavenCentral()
}
```

iii. In Gradle Scripts/build.gradle (Module: <projectname>) add the CORE dependency. The dependencies
section should look like the following:

```gradle
dependencies {
    ...
    implementation "com.jiomeet.platform:jiomatrixtranslationandroiduikit:<version>"
    ...
}
```

Find the [Latest version](https://github.com/JioMeet/JioMatrixTranslationAndroidUIKit/releases) of the Core
SDK and replace <version> with the one you want to use. For example: 0.1.0-alpha

### Add permissions for network and device access.

In /app/Manifests/AndroidManifest.xml, add the following permissions after </application>:

```gradle
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_CONTACTS" />
```

---

### Initialisation

```kotlin
        val speechKey = ""
        val speechRegion = ""
        val textTranslationKey = ""
        
        val jioTranslationUiKit = JioTranslationUiKit()
        jioTranslationUiKit.initializeModules(
            uiContext = applicationContext,
            speechKey = speechKey,
            speechRegion = speechRegion,
            textTranslationKey = textTranslationKey,
            onJioTranslationTokenExpire = object : JioTranslationTokenExpireInterface {
                override fun onTokenExpire() {
                    jioTranslationUiKit.refreshToken("")
                }
            })
        val myJioToken = ""
        setContent {
            MainScreen(myJioToken)
        }
```