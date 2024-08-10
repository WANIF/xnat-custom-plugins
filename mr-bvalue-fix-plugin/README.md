This plugin is to fix the XNAT issue with large numbers of b-values encoded in DICOM tags.

XNAT concatenates all b-values and stores them in a database field, however this is limited to 128 (?) characters. If this is exceeded, then XNAT has a big sad and you can't archive or delete scans. 

This plugin removes XNAT's ability to store b-values in its own database. It does not affect the DICOM files themselves, so no data is affected.
