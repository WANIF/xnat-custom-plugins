/*
 * xnat-template-plugin: org.nrg.xnat.plugins.template.entities.Template
 * XNAT https://www.xnat.org
 * Copyright (c) 2005-2021, Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.nrg.xnat.plugins.template.entities;

import lombok.extern.slf4j.Slf4j;
import org.nrg.framework.orm.hibernate.AbstractHibernateEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "templateId"))
@Slf4j
public class Template extends AbstractHibernateEntity {
    private String _templateId;

    public String getTemplateId() {
        return _templateId;
    }

    public void setTemplateId(final String templateId) {
        _templateId = templateId;
    }
}
