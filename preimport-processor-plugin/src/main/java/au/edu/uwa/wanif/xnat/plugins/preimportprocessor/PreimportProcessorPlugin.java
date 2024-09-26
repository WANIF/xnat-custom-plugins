/*
 * Preimport Processor Plugin
 * XNAT https://www.xnat.org
 *
 * Tim Rosenow
 * The University of Western Australia
 */

package au.edu.uwa.wanif.xnat.plugins.preimportprocessor;

//import lombok.extern.slf4j.Slf4j;
//import org.dcm4che2.data.Tag;
//import org.nrg.config.services.ConfigService;
//import org.nrg.framework.annotations.XnatDataModel;
import org.nrg.framework.annotations.XnatPlugin;
//import org.nrg.xdat.bean.TemplateSampleBean;
//import org.nrg.xdat.security.user.XnatUserProvider;
//import org.nrg.xnat.plugins.template.dcm.ConfigurableMappedAttributeExtractor;
//import org.nrg.xnat.plugins.template.dcm.ConfigurableMappedDicomObjectIdentifier;
//import org.nrg.xnat.plugins.template.dcm.ConfigurableMappedNumberExtractor;
//import org.nrg.xnat.plugins.template.dcm.ConfigurableMappedProjectIdentifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;

@XnatPlugin(value = "PreimportProcessorPlugin", name = "Preimport Processor Plugin")
@ComponentScan({"aau.edu.uwa.wanif.xnat.plugins.preimportprocessor.processors"})
// Below temporarily removed while testing
//@ComponentScan({"au.edu.uwa.wanif.xnat.plugins.preimportprocessor.preferences",
//"au.edu.uwa.wanif.xnat.plugins.preimportprocessor.processors"})
public class PreimportProcessorPlugin {
}
