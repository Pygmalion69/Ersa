# Ersa
Dew point calculation library

[![Release](https://jitpack.io/v/Pygmalion69/Ersa.svg)](https://jitpack.io/#Pygmalion69/Ersa) [![Build Status](https://travis-ci.org/Pygmalion69/Ersa.svg?branch=master)](https://travis-ci.org/Pygmalion69/Ersa)

### Gradle

Add the JitPack repository in your root build.gradle at the end of repositories:

```
repositories {
    maven {
        url 'https://www.jitpack.io'
    }
}
```

Add the dependency:

```
dependencies {
    compile 'com.github.Pygmalion69:Ersa:0.4'
}
```

### Example

```java
Temperature temperature = new Temperature(20, Scale.CELSIUS);
Temperature dewPoint = new Temperature(Scale.CELSIUS);
double relativeHumidity = 60;

Dew dew = new Dew();

double kelvin = temperature.getKelvin();
dewPoint.setKelvin(dew.dewPoint(relativeHumidity, kelvin));
double dewPointC = dewPoint.getTemperature();
```

### Demo

A demo is included in the project.

![Dew Point Demo](screenshot_demo.png "Dew Point Demo")

