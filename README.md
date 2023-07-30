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

```kotlin
dependencies {
    implementation("com.github.CsakiTheOne:CsLib:Tag")
}
```

Tag can be: a release, a short commit hash or 'master-SNAPSHOT'

[![JitPack](https://jitpack.io/v/CsakiTheOne/CsLib.svg)](https://jitpack.io/#CsakiTheOne/CsLib)

## Usage

### CToast

Displays a toast message in the current composition. The toast will display every time one of the parameters change and the text is not blank.

```kotlin
@Composable
fun CToast(
    text: String,
    duration: Int = Toast.LENGTH_SHORT,
)
```

### Drag and drop

```kotlin
var dragTarget by remember { mutableStateOf(0) }
val dragDropState = rememberDragDropState {
    if (dragTarget == 1) {
        // do something
    }
}

Draggable(
    state = dragDropState,
) {
    Surface(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 8.dp,
    ) {}
}

DropTarget(
    state = dragDropState,
    onDragEnter = { dragTarget = 1 },
    onDragExit = { dragTarget = 0 },
) {
    Surface(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        color = if (dragTarget == 1) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp,
    ) {}
}
```

### PreferenceHolder

A composable function that can load and save a primitive or (some) complex value. Preference holders with the same id will stay in sync, no matter how many there are in the application.

```kotlin
@Composable
fun <T> PreferenceHolder(
    id: String,
    value: T,
    onValueChanged: (T) -> Unit,
    defaultValue: T,
)
```

### SystemBars

Composable function to manage the system bar appearance in an Android application.

* @param statusBarColor The color to be applied to the status bar. If null, the status bar color won't change.
* @param isStatusBarForegroundLight Determines whether the status bar icons and text should be light or dark. If null, the status bar color won't change.
* @param navigationBarColor The color to be applied to the navigation bar. If null, the navigation bar color won't change.
* @param isNavigationBarForegroundLight Determines whether the navigation bar icons and text should be light or dark. If null, the navigation bar color won't change.
* @param isImmersiveMode If true, status and navigation bars will be hidden. If null, no changes will apply.
* @param systemBarsBehavior Use WindowInsetsControllerCompat's behavior values.

```kotlin
@Composable
fun SystemBars(
    statusBarColor: Color? = null,
    isStatusBarForegroundLight: Boolean? = null,
    navigationBarColor: Color? = null,
    isNavigationBarForegroundLight: Boolean? = null,
    isImmersiveMode: Boolean? = null,
    systemBarsBehavior: Int? = null,
    onApplyWindowInsets: ((WindowInsetsCompat) -> WindowInsetsCompat)? = null,
)
```
