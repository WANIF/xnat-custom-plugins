package au.edu.uwa.wanif.plugins.preimportdicomprocessor;

import au.edu.uwa.wanif.plugins.preimportdicomprocessor.preferences.PreimportDICOMProcessorPreferences;
import org.nrg.framework.annotations.XnatPlugin;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

@XnatPlugin(value = "PreimportDICOMProcessor", name = "Preimport DICOM processor plugin", description = "Modifies DICOM tags before the DICOM import processor, to enable custom routing")
@ComponentScan({"au.edu.uwa.wanif.plugins.preimportdicomprocessor.preferences"})


public class PreimportDICOMProcessorPlugin {
	
	private final PreimportDICOMProcessorPreferences preimportDICOMProcessorPreferences;

	@Autowired
	public PreimportDICOMProcessorPlugin(final PreimportDICOMProcessorPreferences preimportDICOMProcessorPreferences) {
		this.preimportDICOMProcessorPreferences = preimportDICOMProcessorPreferences;
	}
}

