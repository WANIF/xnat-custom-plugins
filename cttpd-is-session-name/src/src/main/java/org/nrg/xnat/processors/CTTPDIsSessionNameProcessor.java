package org.nrg.xnat.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.nrg.action.ServerException;
import org.nrg.dicom.mizer.objects.AnonymizationResult;
import org.nrg.dicom.mizer.objects.AnonymizationResultError;
import org.nrg.dicom.mizer.objects.AnonymizationResultNoOp;
import org.nrg.dicom.mizer.objects.AnonymizationResultReject;
import org.nrg.dicom.mizer.service.MizerService;
import org.nrg.xnat.entities.ArchiveProcessorInstance;
import org.nrg.xnat.helpers.merge.anonymize.DefaultAnonUtils;
import org.nrg.xnat.helpers.prearchive.SessionData;
import org.restlet.data.Status;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class CTTPDIsSessionNameProcessor extends AbstractArchiveProcessor {

// edits up to here
    @Override
    public boolean process(final DicomObject dicomData, final SessionData sessionData, final MizerService mizer, ArchiveProcessorInstance instance, Map<String, Object> aeParameters) throws ServerException{
        try {
            final String studyInstanceUID = dicomData.getString(Tag.StudyInstanceUID);

            // Default Study script, replaced with this hard-coded script
	    // String script = DefaultAnonUtils.getService().getStudyScript(studyInstanceUID);
	    String script = "version \"6.4\"\n";
	    // Create a new session ID, because XNAT needs session to be unique.
	    // XNAT obtains it from Study Description (or StudyID prior to Paravision 360.3.4)
	    // We save this session ID to (0012,0051) - Clinical Trial Time
	    // Point Description (CTTPD) - and use this as the import filter.
	    //
	    // If StudyDesc exists, set CTTPD to PatientID:StudyDesc
	    // If not, set it to PatientID:StudyID
	    // Finally all else fails, PatientID:StudyDate
	    // StudyDesc is (0008,1030)
	    // StudyID is (0020,0010)
	    script = script + "PatientID := (0010,0020)\n";
	    script = script + "StudyDate := (0008,0020)\n";

	    // If StudyDesc doesn't exist, use StudyID instead
	    script = script + "(0008,1030) !~ \"[a-zA-Z_0-9]*\" ? StudyDesc := (0020,0010) : StudyDesc := (0008,1030)\n";

	    // Fix Adam's staff's dumb typos: "Agrenica"
	    script = script + "PatientID := replace[ PatientID, \"Agrenica\", \"Argenica\" ]\n";
	    script = script + "StudyDesc := replace[ StudyDesc, \"Agrenica\", \"Argenica\" ]\n";
	    // If StudyDesc does not contain PatientID, then we include StudyID at the start.
	    script = script + "TestString := format[ \".*{0}.*\", PatientID ]\n";
	    script = script + "StudyDesc !~ TestString ? StudyDesc := format[ \"{0}_{1}\", PatientID, StudyDesc ] : StudyDesc := StudyDesc\n";
	    script = script + "(0012,0051) := StudyDesc\n";

            if(StringUtils.isNotBlank(script)){
                String proj = "";
                String subj = "";
                String folder = "";
                if(sessionData!=null){
                    proj = sessionData.getProject();
                    subj = sessionData.getSubject();
                    folder = sessionData.getFolderName();
                }
                AnonymizationResult result = mizer.anonymize(dicomData, proj, subj, folder, script, true);
                if (result instanceof AnonymizationResultError) {
                    String msg = result.getMessage();
                    log.debug("Dicom anonymization failed: {}: {}", dicomData, msg);
                    throw new ServerException(Status.SERVER_ERROR_INTERNAL,msg);
                }
                if ( result instanceof AnonymizationResultNoOp) {
                    return false;
                }
            }
        } catch (Throwable e) {
            log.debug("Dicom anonymization failed: " + dicomData, e);
            throw new ServerException(Status.SERVER_ERROR_INTERNAL,e);
        }
        return true;
    }
}
