package au.edu.uwa.wanif.plugins.preimportdicomprocessor.preferences;


import lombok.extern.slf4j.Slf4j;
import org.nrg.framework.configuration.ConfigPaths;
import org.nrg.framework.utilities.OrderedProperties;
import org.nrg.prefs.annotations.NrgPreference;
import org.nrg.prefs.annotations.NrgPreferenceBean;
import org.nrg.prefs.beans.AbstractPreferenceBean;
import org.nrg.prefs.exceptions.InvalidPreferenceName;
import org.nrg.prefs.services.NrgPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@NrgPreferenceBean(toolId = PreimportDICOMProcessorPreferences.TOOL_ID,
                   toolName = "PreimportDICOMProcessor Preferences",
                   description = "Manages preferences for PreimportDICOMProcessor")
@Slf4j
public class PreimportDICOMProcessorPreferences extends AbstractPreferenceBean {

    public static final String TOOL_ID = "preimportidcomprocessor";
    public static final String PREIMPORTDICOMPROCESSOR_ENABLED = "PreimportDICOMProcessorEnabled";
    public static final String PREIMPORTDICOMPROCESSOR_SCRIPT = "PreimportDICOMProcessorScript";

    @Autowired
    protected PreimportDICOMProcessorPreferences(NrgPreferenceService preferenceService, ConfigPaths configFolderPaths, OrderedProperties initPrefs) {
	    super(preferenceService, configFolderPaths, initPrefs);
    }

    @NrgPreference(defaultValue="false")
    public boolean getPreimportDICOMProcessorEnabled() {
	    return getValue(PREIMPORTDICOMPROCESSOR_ENABLED);
    }

    public void setPreimportDICOMProcessorEnabled(final boolean preimportDICOMProcessorEnabled) {
	    try {
		    // Code to check and handle import processor status via REST
		    set(preimportDICOMProcessorEnabled, PREIMPORTDICOMPROCESSOR_ENABLED)
	    } catch (InvalidPreferenceName e) {
		    log.error("Invalid preference name " + PREIMPORTDICOMPROCESSOR_ENABLED + ": something is very wrong!", e);
	    }
    }


    @NrgPreference(defaultValue = "")
    public String getPreimportDICOMProcessorScript() {
            return getValue(PREIMPORTDICOMPROCESSOR_SCRIPT);
    }

     public void setPreimportDICOMProcessorScript(final String preimportDICOMProcessorScript) {
	    try {
                    set(preimportDICOMProcessorScript, PREIMPORTDICOMPROCESSOR_SCRIPT)
            } catch (InvalidPreferenceName e) {
                    log.error("Invalid preference name " + PREIMPORTDICOMPROCESSOR_SCRIPT + ": something is very wrong!", e);
            }
    }


}

