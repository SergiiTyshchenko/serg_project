package com.epam.training.services;

import java.util.List;

import com.epam.training.model.OrganizationModel;


public interface OrganizationService
{
    List<OrganizationModel> getOrganizations();

    String getImageUrlFromOrganization(OrganizationModel organization, String format);

    OrganizationModel getOrganizationForCode(String code);


}