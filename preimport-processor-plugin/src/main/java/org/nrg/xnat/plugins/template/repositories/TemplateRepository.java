/*
 * xnat-template-plugin: org.nrg.xnat.plugins.template.repositories.TemplateRepository
 * XNAT https://www.xnat.org
 * Copyright (c) 2005-2021, Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.nrg.xnat.plugins.template.repositories;

import lombok.extern.slf4j.Slf4j;
import org.nrg.framework.orm.hibernate.AbstractHibernateDAO;
import org.nrg.xnat.plugins.template.entities.Template;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class TemplateRepository extends AbstractHibernateDAO<Template> {
}
