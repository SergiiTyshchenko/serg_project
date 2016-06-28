package com.epam.training.facades.impl;


import com.epam.training.data.OrganizationData;
import com.epam.training.facades.OrganizationFacade;
import com.epam.training.model.OrganizationModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.lang.UnsupportedOperationException;

import com.epam.training.services.OrganizationService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

@Component(value = "organizationFacade")
public class DefaultOrganizationFacade implements OrganizationFacade
{

    private OrganizationService organizationService;

    @Override
    public List<OrganizationData> getOrganizations(final String format)
    {
        final List<OrganizationModel> organizationModels = organizationService.getOrganizations();
        final List<OrganizationData> organizationFacadeData = new ArrayList<OrganizationData>();
        String urlImg;
        for (final OrganizationModel sm : organizationModels)
        {
            try
            {
                urlImg = organizationService.getImageUrlFromOrganization(sm, format);
            }
            catch (final Exception e)
            {
                urlImg = "";
            }

            final OrganizationData sfd = new OrganizationData();
            sfd.setName(sm.getName());
            sfd.setImageUrl(urlImg);
            organizationFacadeData.add(sfd);
        }
        return organizationFacadeData;
    }

    @Override
    public OrganizationData getOrganization(final String name, final String format)
    {
       //throw new UnsupportedOperationException("Not supported yet.");
        final OrganizationModel organizationModel = organizationService.getOrganizationForCode(name);
        if (organizationModel == null)
        {
            return null;
        }

        String urlImg;
        try
        {
            urlImg = organizationService.getImageUrlFromOrganization(organizationModel, format);
        }
        catch (final Exception e)
        {
            urlImg = "";
        }

        // Now we can create the StadiumData transfer object
        final OrganizationData organizationData = new OrganizationData();
        organizationData.setName(name);
        organizationData.setImageUrl(urlImg);
        return organizationData;
    }

    @Required
    public void setOrganizationService(final OrganizationService organizationService)
    {
        this.organizationService = organizationService;
    }
    
}