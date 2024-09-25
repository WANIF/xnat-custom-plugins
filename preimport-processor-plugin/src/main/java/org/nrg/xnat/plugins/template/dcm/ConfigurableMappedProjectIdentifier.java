package org.nrg.xnat.plugins.template.dcm;

import java.util.*;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dcm4che2.data.DicomObject;

import org.nrg.xdat.om.XnatProjectdata;
import org.nrg.config.services.ConfigService;
import org.nrg.dcm.id.DicomProjectIdentifier;
import org.nrg.xft.security.UserI;

@Slf4j
public final class ConfigurableMappedProjectIdentifier implements DicomProjectIdentifier {
    private static final String CONFIG_ID   = "MappedIdentifiers";
    private static final String CONFIG_FILE = "projectMapConfig";

    private final ConfigService _configService;
    private final int           _tag;

    /**
     * Constructor that sets the key tag for this identifier.
     *
     * @param tag The tag to inspect to get the destination project.
     */
    public ConfigurableMappedProjectIdentifier(final ConfigService configService, final int tag) {
        _configService = configService;
        _tag = tag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedSet<Integer> getTags() {
        return Collections.emptySortedSet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XnatProjectdata apply(final UserI user, final DicomObject dicom) {
        return XnatProjectdata.getProjectByIDorAlias(getProjectId(dicom), user, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        log.debug("Someone reset me but there's nothing to do");
    }

    private String getProjectId(final DicomObject object) {
        final String project = object.getString(_tag);
        try {
            final Map<String, String> projectMap = getProjectMap();
            if (projectMap.containsKey(project)) {
                return projectMap.get(project);
            }
        } catch (Exception e) { /* couldn't get project map */ }
        return StringUtils.isNotBlank(project) ? project : null;
    }

    private Map<String, String> getProjectMap() throws Exception {
        final String configuration = StringUtils.defaultIfBlank(_configService.getConfigContents(CONFIG_ID, CONFIG_FILE), "");
        final Map<String, String> projectMap = Arrays.stream(configuration.split("\n"))
                                                     .map(line -> line.split("=", 2))
                                                     .filter(keyValue -> StringUtils.isNoneBlank(keyValue[0], keyValue[1]))
                                                     .collect(Collectors.toMap(keyValue -> keyValue[0], keyValue -> keyValue[1]));
        if (projectMap.isEmpty()) {
            throw new Exception("Couldn't retrieve project map from config file");
        }
        return projectMap;
    }
}