package com.epam.training.services.impl;

import java.util.List;

import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.media.MediaService;
import com.epam.training.dao.OrganizationDao;
import com.epam.training.model.OrganizationModel;
import com.epam.training.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;


public class DefaultOrganizationService implements OrganizationService
{

    private OrganizationDao organizationDao;

    @Autowired
    private MediaService mediaService;

    public void setOrganizationDao(final OrganizationDao organizationDao)
    {
        this.organizationDao = organizationDao;
    }

    @Override
    public List<OrganizationModel> getOrganizations()
    {
        return organizationDao.findAll();
    }

    @Override
    public OrganizationModel getOrganizationForCode(final String code) throws AmbiguousIdentifierException, UnknownIdentifierException
    {
        final List<OrganizationModel> result = organizationDao.findOrganizationByCode(code);
        if (result.isEmpty())
        {
            throw new UnknownIdentifierException("Organization with code '" + code + "' not found!");
        }
        else if (result.size() > 1)
        {
            throw new AmbiguousIdentifierException("Organization code '" + code + "' is not unique, " + result.size()
                    + " organizations found!");
//            final List<OrganizationModel> result_new = result.subList(0,1);
//            return result_new.get(0);
        }
            return result.get(0);
    }

    @Override
    public String getImageUrlFromOrganization(final OrganizationModel organization, final String format)
    {
        final MediaFormatModel mediaFormat = mediaService.getFormat(format);
        MediaModel media = null;
        if (organization.getOrganizationImage() != null && mediaFormat != null)
        {
            media = mediaService.getMediaByFormat(organization.getOrganizationImage(), mediaFormat);
        }
        if (media != null)
        {
            return media.getURL();
        }
        else
        {
            return null;
        }
    }

}