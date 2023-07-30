# CsLib

## Getting started

Step 1. Add it in your root build.gradle at the end of repositories:

Groovy:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency:

Groovy:

```groovy
dependencies {
    implementation 'com.github.CsakiTheOne:CsLib:Tag'
}
```

Kotlin:

```kt
dependencies {
    implementation("com.github.CsakiTheOne:CsLib:Tag")
}
```

Tag can be: a release, a short commit hash or 'master-SNAPSHOT'

[![JitPack](https://jitpack.io/v/CsakiTheOne/CsLib.svg)](https://jitpack.io/#CsakiTheOne/CsLib)

## Usage

### CToast

Displays a toast message in the current composition. The toast will display every time one of the parameters change and the text is not blank.

```kt
@Composable
fun CToast(
    text: String,
    duration: Int = Toast.LENGTH_SHORT,
)
```

### SystemBars

Composable function to manage the system bar appearance in an Android application.

* @param statusBarColor The color to be applied to the status bar. If null, the status bar color won't change.
* @param isStatusBarForegroundLight Determines whether the status bar icons and text should be light or dark. If null, the status bar color won't change.
* @param navigationBarColor The color to be applied to the navigation bar. If null, the navigation bar color won't change.
* @param isNavigationBarForegroundLight Determines whether the navigation bar icons and text should be light or dark. If null, the navigation bar color won't change.

```kt
@Composable
fun SystemBars(
    statusBarColor: Color? = null,
    isStatusBarForegroundLight: Boolean? = null,
    navigationBarColor: Color? = null,
    isNavigationBarForegroundLight: Boolean? = null,
)
```
