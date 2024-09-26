package au.edu.uwa.wanif.xnat.plugins.preimportprocessor.preferences;

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


@NrgPreferenceBean(toolId = PreimportProcessorPreferences.TOOL_ID,
	toolName = "Preimport Processor Preferences",
	description = "Manages preferences for Preimport Processor")
@Slf4j
public class PreimportProcessorPreferences extends AbstractPreferenceBean {

    public static final String TOOL_ID = "preimportprocessor"



} 
