/*
 * xnat-template-plugin: org.apache.turbine.app.xnat.modules.screens.TemplateScreen
 * XNAT https://www.xnat.org
 * Copyright (c) 2005-2021, Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.apache.turbine.app.xnat.modules.screens;

import lombok.extern.slf4j.Slf4j;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.nrg.xdat.turbine.modules.screens.SecureScreen;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@SuppressWarnings("unused")
public class TemplateScreen extends SecureScreen {
    @Override
    protected void doBuildTemplate(final RunData data, final Context context) {
        context.put("dateTime", DATE_FORMAT.format(new Date()));
    }

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd_hhmmss");
}
