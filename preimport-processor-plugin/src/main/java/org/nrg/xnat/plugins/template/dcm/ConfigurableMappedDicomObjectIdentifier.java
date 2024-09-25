package org.nrg.xnat.plugins.template.dcm;

import org.dcm4che2.data.Tag;
import org.nrg.dcm.ChainExtractor;
import org.nrg.dcm.ContainedAssignmentExtractor;
import org.nrg.dcm.id.CompositeDicomObjectIdentifier;

import java.util.Arrays;
import java.util.regex.Pattern;

public final class ConfigurableMappedDicomObjectIdentifier extends CompositeDicomObjectIdentifier {
    public ConfigurableMappedDicomObjectIdentifier(final ConfigurableMappedProjectIdentifier projectIdentifier, final ConfigurableMappedAttributeExtractor subjectIdentifier, final ConfigurableMappedAttributeExtractor sessionIdentifier) {
        super(projectIdentifier, subjectIdentifier, sessionIdentifier, new ChainExtractor(Arrays.asList(new ContainedAssignmentExtractor(Tag.PatientComments, "AA", Pattern.CASE_INSENSITIVE), new ContainedAssignmentExtractor(Tag.StudyComments, "AA", Pattern.CASE_INSENSITIVE))));
    }
}
