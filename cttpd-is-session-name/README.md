# CTTPD Is Session Name Plugin

This plugin is to create a preprocessor that modifies the DICOM tag Clinical Trial Time Point Description (CTTPD) - (0012,0051), to be the session ID. This DICOM tag is defined in the following format: PatientID_StudyDescription, where
* PatientID is (0010,0020)
* StudyDescription is (0008,1030)

The reason this is necessary is that it is run BEFORE the usual Site Wide Anonymization Script (SWAS). The SWAS usually runs too late in the process to actually affect the setting of the session ID. This processor should be run AfterProjectSet (set using the archiveprocessor swagger page).

# How to set up and install

Because I haven't bothered doing this properly, currently the DICOM modification rules are hard-coded into this plugin. At some point I would like to make a configuration setting so you can dynamically change it, but that is on my ever-expanding to-do list.

## Configure the rules
You should set the session name rules using the DicomEdit scripting language (the same as the anonymization process in XNAT).
Edit src/src/main/java/org/nrg/xnat/processors/CTTPDIsSessionNameProcessor.java
