/*
 * xnat-template-plugin: org.nrg.xnat.plugins.template.plugin.XnatTemplatePlugin
 * XNAT https://www.xnat.org
 * Copyright (c) 2005-2021, Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.unicorn.xnatx.plugin;

import lombok.extern.slf4j.Slf4j;
import org.nrg.config.services.ConfigService;
import org.nrg.framework.annotations.XnatPlugin;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;

@XnatPlugin(value = "Plugin102", name = "XNAT Plugin 102",
	logConfigurationFile = "META-INF/resources/plugin-102-logback.xml")
@ComponentScan(value = "org.unicorn.xnatx.actions",
        excludeFilters = @Filter(type = FilterType.REGEX, pattern = ".*TestConfig.*", value = {}))
@Slf4j
public class Plugin102 {

    public Plugin102() {
        log.error("Creating the Plugin102 configuration class (logging as ERROR)");
        log.warn("Creating the Plugin102 configuration class (logging as WARN)");
        log.info("Creating the Plugin102 configuration class (logging as INFO)");

    }

    @Bean
    public String templatePluginMessage() {
        return "This comes from deep within the Plugin 102 example.";
    }
}
