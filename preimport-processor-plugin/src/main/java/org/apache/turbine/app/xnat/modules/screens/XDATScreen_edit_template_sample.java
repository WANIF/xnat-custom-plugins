/*
 * xnat-template-plugin: org.apache.turbine.app.xnat.modules.screens.XDATScreen_edit_template_sample
 * XNAT http://www.xnat.org
 * Copyright (c) 2005-2021, Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.apache.turbine.app.xnat.modules.screens;

import lombok.extern.slf4j.Slf4j;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.nrg.xdat.om.TemplateSample;
import org.nrg.xft.ItemI;
import org.nrg.xnat.turbine.modules.screens.EditSubjectAssessorScreen;

@SuppressWarnings("unused")
@Slf4j
public class XDATScreen_edit_template_sample extends EditSubjectAssessorScreen {
    @Override
    public String getElementName() {
        return TemplateSample.SCHEMA_ELEMENT_NAME;
    }

    @Override
    public ItemI getEmptyItem(final RunData data) throws Exception {
        return super.getEmptyItem(data);
    }

    @Override
    public void finalProcessing(final RunData data, final Context context) {
        super.finalProcessing(data, context);
        if (data.getParameters().containsKey("subjectId")) {
            context.put("subjectId", data.getParameters().get("subjectId"));
        }
    }
}
