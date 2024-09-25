/*
 * xnat-template-plugin: org.nrg.xnat.plugins.template.preferences.TemplatePreferencesBean
 * XNAT https://www.xnat.org
 * Copyright (c) 2005-2021, Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.nrg.xnat.plugins.template.preferences;

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

@NrgPreferenceBean(toolId = "template", toolName = "XNAT Template Plugin")
@Slf4j
public class TemplatePreferencesBean extends AbstractPreferenceBean {
    @Autowired
    public TemplatePreferencesBean(final NrgPreferenceService preferenceService, final ConfigPaths configFolderPaths, final OrderedProperties orderedProperties) {
        super(preferenceService, configFolderPaths, orderedProperties);
    }

    @NrgPreference(defaultValue = "['Standard']")
    public List<String> getTemplateNames() {
        return getListValue("templateNames");
    }

    @SuppressWarnings("unused")
    public void setTemplateNames(final List<String> templateNames) {
        try {
            setListValue("templateNames", templateNames);
        } catch (InvalidPreferenceName invalidPreferenceName) {
            //
        }
    }

    @NrgPreference(defaultValue = "['standard']")
    public List<String> getTemplateTypes() {
        return getListValue("templateTypes");
    }

    @SuppressWarnings("unused")
    public void setTemplateTypes(final List<String> templateTypes) {
        try {
            setListValue("templateTypes", templateTypes);
        } catch (InvalidPreferenceName invalidPreferenceName) {
            //
        }
    }
}
