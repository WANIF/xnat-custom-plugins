# XNAT Plugin 102 Example

Plugin 102 extends Plugin 101:
* Adds a class that can accept EventService events and log them

## Review the Source Code
### build.gradle
These are items to notice
* No substantive changes from 101

### settings.gradle
* No substantive changes for 102

### src/main/java/org/unicorn/xnatx/plugin/Plugin102.java
 * Adds a logConfiguration file
 * Adds @ComponentScan annotation that will tell Spring to pick up the classes in org.unicorn.xnatx.actions (where we have an Action Provider

### src/main/resources/META-INF/resources/plugin-102-logback.xml
This is the configuration file used by logback. Please note:
* This file is located in src/main/resources/META-INF/resources in the code repository. That will translate to  META-INF/resources in the jar file.
* There is an &lt;appender&gt; element which defines properties for the log file itself.
* There is a &lt;logger&gt; element that links the plugin class to the log file.
* Logs messages for the package org.unicorn.xnatx

### src/main/java/org/unicorn/xnatx/actions/UnicornAction.java
* Extends the SingleActionProvider class
* The processEvent method is invoked when an event is detected.
 * This example logs some items at ERROR level to force things into log files.

## Build The Example
In this the 102 folder, clean and build the plugin jar
    ./gradlew clean xnatPluginJar

## Install the Plugin
1. Copy build/libs/xnat-plugin-102-0.1.0.jar to the XNAT plugins folder
2. Stop / start tomcat
3. Examine two log files in the XNAT logs folder.
3.1 logs/application.log: Will log the constructor for org.unicorn.xnatx.plugin.Plugin102
3.2 logs/plugin102.log: Will log the constructor for org.unicorn.xnatx.actions.UnicornAction
4. Link the ActionListener that was created to a Session Created event (details below)
5. Create a new XNAT Session (details below)
6. Examine the log output in logs/plugin102.log. You will see information indicating an event was recognized.

## Detailed Notes / Appendix Material Referenced Above

### Linking the ActionListener
* Using the admin account, select Administer -> Event Service
* Make sure the Event Service is enabled (top of the page)
* Add a new subscription by selecting the Add New Event Subscription button. On the form that appears
  * Enter a label (random text is OK)
  * Select Event: Session -- Created
  * Select Project: Let this apply to all projects
  * Select Action: Select Unicorn Logging Action
  * Status: Enable the subscription (bottom of the form)
  * OK

### Creating a New XNAT Session
You can upload a new session into a project or do this through the Web UI
* Select New -> Experiment
* On the form that appears
  * Select an existing project
  * Select an existing subject
  * Select MR Session
* On the MR Session form
  * Enter a session label (text)
  * Delete all rows for scans (keep it simple)
  * Submit
