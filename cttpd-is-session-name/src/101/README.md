# XNAT Plugin 101 Example

Welcome to the XNAT Plugin 101 Example.
This briefest of examples demonstrates:
* A small build.gradle file and corresponding settings.gradle file
* One java file that creates a plugin that logs the existence of the plugin and nothing else

## Review the Source Code
### build.gradle
These are items to notice
* This plugin is built on XNAT version 1.8.2.2
* The version of this plugin is 0.1.0
* Building the plugin relies on Maven Central as well as a Maven repository hosted by the XNAT team
* A small number of values are computed and go into the jar manifest. Other examples will fill this out further.

### settings.gradle
This file contains the name of the root project. In this case: xnat-plugin-101

### src/main/java/org/unicorn/xnatx/plugin/Plugin101.java
This is the java file with the plugin code. Please note:
* The XnatPlugin annotation
    - Declares this is a plugin with an identifier (value) and name (text)
* The constructor logs some simple messages at ERROR level including a stack trace.

## Build The Example
In this the 101 folder, clean and build the plugin jar
    ./gradlew clean xnatPluginJar
Examine jar that is created; look at the text files in the jar.
    cp build/libs/xnat-plugin-101-0.1.0.jar /tmp
    mkdir /tmp/x
    pushd /tmp/x
    jar xf ../xnat-plugin-101-0.1.0.jar
    find . -type f

## Install the Plugin
1. Copy build/libs/xnat-plugin-101.0.1.0.jar to the XNAT plugins folder
2. Stop and start tomcat
3. Examine the log output in the XNAT logs folder (logs/plugin-101.log). You should see one log message announcing that XNAT is creating the Plugin101 configuration class
4. Log in to your XNAT as an administrator and look for the plugin
4.1. Select Administer --> Site Administration
4.2 Look in the left flap and select Installed Plugins
4.3 View the information concerning the 101 plugin
