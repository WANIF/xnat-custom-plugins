/*
 * xnat-template-plugin: org.nrg.xnat.plugins.template.services.impl.HibernateTemplateService
 * XNAT https://www.xnat.org
 * Copyright (c) 2005-2021, Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.nrg.xnat.plugins.template.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.nrg.framework.orm.hibernate.AbstractHibernateEntityService;
import org.nrg.xnat.plugins.template.entities.Template;
import org.nrg.xnat.plugins.template.repositories.TemplateRepository;
import org.nrg.xnat.plugins.template.services.TemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manages {@link Template} data objects in Hibernate.
 */
@Service
@Slf4j
public class HibernateTemplateService extends AbstractHibernateEntityService<Template, TemplateRepository> implements TemplateService {
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Template findByTemplateId(final String templateId) {
        log.trace("Requested template with ID {}", templateId);
        return getDao().findByUniqueProperty("templateId", templateId);
    }
}
