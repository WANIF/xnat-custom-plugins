# Preimport DICOM processor plugin

This plugin creates a preprocessor for custom dicom modification that runs BEFORE the SiteWideAnonymizer, with the aim to create custom changes to the DICOM data before the study is routed (e.g. for using composite routing tags).

The reason this is necessary is that it is run BEFORE the usual Site Wide Anonymization Script (SWAS). The SWAS usually runs too late in the process to actually affect the setting of the session ID. This processor should be run AfterProjectSet (set using the archiveprocessor swagger page).

# Configuration, setup and install
This plugin creates a configuration page where you can put in your pre-routing DicomEdit script. You will, however, still need to enable it on XNAT using the swagger plugin (this is not automatic, but may be changed in the future).

## Configure the rules for the plugin
You should set the session name rules using the DicomEdit scripting language (the same as the anonymization process in XNAT) in the XNAT configuration page.

## Building the plugin
`/gradlew xnatDataBuilder xnatPluginJar`
This will create the plugin file `build/libs/preimport-dicom-processor-[version]-xpl.jar` (version names may change if you have configured this). Copy this to the XNAT plugin directory and restart XNAT.

## Enabling the plugin on XNAT (may be unnecessary in future versions)
0. Configure the plugin on the plugin administration page
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
    "processorClass": "au.edu.uwa.wanif.plugins.preimportdicomprocessor.processors.PreimportDICOMProcessor",
    "projectIdsList": [],
    "enabled": true
  }
```
4. I don't know if you need to restart XNAT now or not, but it won't hurt.


