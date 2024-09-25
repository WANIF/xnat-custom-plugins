/*
 * xnat-template-plugin: org.nrg.xnat.plugins.template.rest.TemplateApi
 * XNAT https://www.xnat.org
 * Copyright (c) 2005-2021, Washington University School of Medicine
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.nrg.xnat.plugins.template.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.nrg.framework.annotations.XapiRestController;
import org.nrg.xapi.exceptions.NotFoundException;
import org.nrg.xapi.rest.AbstractXapiRestController;
import org.nrg.xapi.rest.XapiRequestMapping;
import org.nrg.xdat.security.services.RoleHolder;
import org.nrg.xdat.security.services.UserManagementServiceI;
import org.nrg.xnat.plugins.template.entities.Template;
import org.nrg.xnat.plugins.template.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api("Template API")
@XapiRestController
@RequestMapping(value = "/template/entities")
@Slf4j
public class TemplateApi extends AbstractXapiRestController {
    @Autowired
    protected TemplateApi(final UserManagementServiceI userManagementService, final RoleHolder roleHolder, final TemplateService templateService) {
        super(userManagementService, roleHolder);
        _templateService = templateService;
    }

    @ApiOperation(value = "Returns a list of all templates.", response = Template.class, responseContainer = "List")
    @ApiResponses({@ApiResponse(code = 200, message = "Templates successfully retrieved."),
                   @ApiResponse(code = 401, message = "Must be authenticated to access the XNAT REST API."),
                   @ApiResponse(code = 500, message = "Unexpected error")})
    @XapiRequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Template> getEntities() {
        return _templateService.getAll();
    }

    @ApiOperation(value = "Creates a new template.", response = Template.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Template successfully created."),
                   @ApiResponse(code = 401, message = "Must be authenticated to access the XNAT REST API."),
                   @ApiResponse(code = 500, message = "Unexpected error")})
    @XapiRequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Template createEntity(@RequestBody final Template entity) {
        return _templateService.create(entity);
    }

    @ApiOperation(value = "Retrieves the indicated template.",
                  notes = "Based on the template ID, not the primary key ID.",
                  response = Template.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Template successfully retrieved."),
                   @ApiResponse(code = 401, message = "Must be authenticated to access the XNAT REST API."),
                   @ApiResponse(code = 500, message = "Unexpected error")})
    @XapiRequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Template getEntity(@PathVariable final String id) throws NotFoundException {
        if (!_templateService.exists("templateId", id)) {
            throw new NotFoundException("No template with the ID \"" + id + "\" was found.");
        }
        return _templateService.findByTemplateId(id);
    }

    @ApiOperation(value = "Updates the indicated template.",
                  notes = "Based on primary key ID, not subject or record ID.",
                  response = Long.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Template successfully updated."),
                   @ApiResponse(code = 401, message = "Must be authenticated to access the XNAT REST API."),
                   @ApiResponse(code = 500, message = "Unexpected error")})
    @XapiRequestMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public long updateEntity(@PathVariable final Long id, @RequestBody final Template entity) throws NotFoundException {
        if (!_templateService.exists("templateId", id)) {
            throw new NotFoundException("No template with the ID \"" + id + "\" was found.");
        }
        final Template existing = _templateService.retrieve(id);
        existing.setTemplateId(entity.getTemplateId());
        _templateService.update(existing);
        return id;
    }

    @ApiOperation(value = "Deletes the indicated template.",
                  notes = "Based on primary key ID, not subject or record ID.",
                  response = Long.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Template successfully deleted."),
                   @ApiResponse(code = 401, message = "Must be authenticated to access the XNAT REST API."),
                   @ApiResponse(code = 500, message = "Unexpected error")})
    @XapiRequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public long deleteEntity(@PathVariable final Long id) {
        final Template existing = _templateService.retrieve(id);
        _templateService.delete(existing);
        return id;
    }

    private final TemplateService _templateService;
}
