package com.epam.training.facades;



import com.epam.training.data.OrganizationData;
import java.util.List;

public interface OrganizationFacade
{

    OrganizationData getOrganization(String name, String format);
    List<OrganizationData> getOrganizations(String format);

}