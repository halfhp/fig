# Fig
A utility for Android apps to easily configure any object from XML.

![image](docs/images/fig.png)
 
## Why Use It?

* Configure anything; instances of Paint, View, your own classes, third party libs, etc.
* Supports `sp`, `dp`, `pix`, `pt`, `in`, `mm` and `color` params.
* Keep UI code where it belongs: store configurations by screen size and density using standard 
Android resource directory conventions.
* Easy to use.

# Usage
Fig relies on JavaBean naming conventions to map XML parameters to objects:

```java
// given:
Button button = new Button();
```

then:
```java
button.getPaint().setColor(Color.RED);
```

becomes:

```xml
button.paint.color="@color/red"
```

### Gradle Dependency

```groovy
repositories {
    ...
    maven {
        url "https://maven.pkg.github.com/halfhp/fig"
        credentials {
            username "anon"
            password "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"
        }
    }
}
...
dependencies {
    compile "com.halfhp.fig:figlib:1.0.10"
}
```

###  A Simple Example
Given a class:

```java
// A thingy that has a name and a fill color.
public class ColorfulThingy {    
    private String name;
    private Paint fillPaint;
    
    // JavaBean style setters/getters:
    public String getName() { return name; }    
    public void setName(String name) { this.name = name; }
    public Paint getFillPaint() { return fillPaint; }
    public void setFillPaint(Paint fillPaint) { this.fillPaint = fillPaint; }
}
```

We can configure any instances:

```java
ColorfulBoxyThingy thingy = new ColorfulBoxyThingy();
Fig.configure(context, thingy, R.xml.thingy_config.xml);
```

From thingyConfig.xml, for any property that has a standard Java Bean setter:

**thingy_config.xml:**
```xml
<?xml version="1.0" encoding="utf-8"?>
<config name="My Thingy"/>
```

We can also configure properties of nested objects as long as the chain of nested objects
is accessible via Standard Java Bean getter and the target property also has a standard Java Bean setter:

```xml
<?xml version="1.0" encoding="utf-8"?>
<config name="My Thingy"
        fillPaint.color="#FF0000"/>
```

### Standard JavaBean Setters/Getters
If you use IntelliJ or Android Studio to auto generate your setters and getters, you can safely skip 
this section.  If you're using another IDE or are just curious, read on.

When using Fig, there are two conventions to follow:

* Setters begin with lower-case "set" followed by the name of the property being set, eg. `setFoo(...)`.
* Getters begin with lower-case "get" followed by the name of the property being retrieved, `eg. getFoo()`.

The JavaBean spec has more to say on casing conventions etc, but Fig's matching is case insensitive so
feel free to use whatever casing convention you prefer.

# Fig & Obfuscation
Fig uses runtime reflection to configure object instances and relies on  class method names remaining 
consistent at runtime. Obfuscation must be disabled for any classes you wish to configure.
This is typically a matter of adding a rule to your proguard config to preserve these class' members:

```
# preserve a single class:
-keepclassmembers class com.somedomain.somepackage.Foo { *; }

# preserve an entire package:
-keepclassmembers com.somedomain.somepackage.** { *; }
```