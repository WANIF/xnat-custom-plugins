# CTTPD Is Session Name Plugin

This plugin is to create a preprocessor that modifies the DICOM tag Clinical Trial Time Point Description (CTTPD) - (0012,0051), to be the session ID. This DICOM tag is defined in the following format: PatientID_StudyDescription, where
* PatientID is (0010,0020)
* StudyDescription is (0008,1030)

The reason this is necessary is that it is run BEFORE the usual Site Wide Anonymization Script (SWAS). The SWAS usually runs too late in the process to actually affect the setting of the session ID. This processor should be run AfterProjectSet (set using the archiveprocessor swagger page).

# Configuration, setup and install

Because I haven't bothered doing this properly, currently the DICOM modification rules are hard-coded into this plugin. At some point I would like to make a configuration setting so you can dynamically change it, but that is on my ever-expanding to-do list.

## Configure the rules for the plugin
You should set the session name rules using the DicomEdit scripting language (the same as the anonymization process in XNAT).
Edit `src/src/main/java/org/nrg/xnat/processors/CTTPDIsSessionNameProcessor.java`. The DicomEdit script is encoded in a Java string - unfortunately this version of Java doesn't support triple-quote strings, so you need to build it line by line, e.g.
`script = script + "Your DicomEdit line here\n"`

Once you have made this change, you should recompile the plugin and then copy it to your plugins folder on XNAT. You can actually do this on your xnat-web docker container if you wish (all of the dependencies are already installed). On your docker container (`docker exec -it xnat-xnat-web-1 bash`), run the following command:
`/gradlew xnatDataBuilder xnatPluginJar`
This will create the plugin file `build/libs/cttpd-is-session-name-plugin-1.0.0-SNAPSHOT-xpl.jar` (version names may change if you have configured this). Copy this to the XNAT plugin directory and restart XNAT.

# Enabling the plugin on XNAT
1. Go to the XNAT Swagger page (Administer -> Site Administration -> Miscellaneous).
2. Go to archive-processor-instance-api and /processes/site/create
3. Use the following details to create the processor.
```
{
    "label": "CTTPIDSessionNamer",
    "scope": "site",
    "scpWhitelist": [],
    "scpBlacklist": [],
    "location": "AfterProjectSet",
    "priority": 3,
    "parameters": {},
    "processorClass": "org.nrg.xnat.processors.CTTPDIsSessionNameProcessor",
    "projectIdsList": [],
    "enabled": true
  }
```
4. I don't know if you need to restart XNAT now or not, but it won't hurt.

