/*
 * xnat-template-plugin: org.nrg.xnat.plugins.template.tasks.TemplateTask
 * XNAT https://www.xnat.org
 * Copyright (c) 2005-2021, Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.nrg.xnat.plugins.template.tasks;

import lombok.extern.slf4j.Slf4j;
import org.nrg.framework.task.XnatTask;
import org.nrg.framework.task.services.XnatTaskService;
import org.nrg.xnat.services.XnatAppInfo;
import org.nrg.xnat.task.AbstractXnatTask;
import org.springframework.jdbc.core.JdbcTemplate;

@XnatTask(taskId = "TemplateTask", description = "Template Task", defaultExecutionResolver = "SingleNodeExecutionResolver", executionResolverConfigurable = true)
@Slf4j
public class TemplateTask extends AbstractXnatTask {
    public TemplateTask(final XnatTaskService taskService, final XnatAppInfo appInfo, final JdbcTemplate jdbcTemplate) {
        super(taskService, true, appInfo, jdbcTemplate);
    }

    @Override
    protected void runTask() {
        log.info("Now running the template task");
    }
}
