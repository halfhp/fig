# Fig
A utility for Android apps to easily configure any object from XML.

![image](docs/images/fig.png)
 
## Why Use It?

* Incredibly simple to use.
* Configure any class, not just your own.
* Supports `sp`, `dp`, `pix`, `pt`, `in`, `mm` and `color` params.

# Usage

### Gradle Dependency

```groovy
dependencies {
    compile "com.halfhp.fig:library:1.0"
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
Fig

# Fig & Obfuscation
Fig uses runtime reflection to configure object instances and relies on  class method names remaining 
consistent at runtime, therefore obfuscation must be disabled for any classes you wish to configure.
This is typically as simple as adding a rule to your proguard config to preserve these class' members:

```
# preserve a single class:
-keepclassmembers class com.halfhp.fig.Fig { *; }

# preserve an entire package:
-keepclassmembers com.halfhp.fig.** { *; }
```