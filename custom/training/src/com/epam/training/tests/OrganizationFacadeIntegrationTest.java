package com.epam.training.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import com.epam.training.model.OrganizationModel;
import com.epam.training.data.OrganizationData;
import com.epam.training.facades.OrganizationFacade;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;


/**
 * This test file tests and demonstrates the behavior of the OrganizationFAcade's methods getAllStadiums and getOrganization.
 */
public class OrganizationFacadeIntegrationTest extends ServicelayerTransactionalTest
{
    @Resource
    private OrganizationFacade organizationFacade;

    @Resource
    private ModelService modelService;

    private OrganizationModel organizationModel;
    private final String ORGANIZATION_DETAILS = "my_org";
    private final String ORGANIZATION_LISTING = "890809";

    @Before
    public void setUp()
    {
        // This instance of a OrganizationModel will be used by the tests
        organizationModel = new OrganizationModel();
        organizationModel.setName(ORGANIZATION_DETAILS);
        organizationModel.setPhone(ORGANIZATION_LISTING);
    }


//    *
//     * Tests exception behavior by getting a org which doesn't exist

    @Test(expected = UnknownIdentifierException.class)
    public void testInvalidParameter()
    {
        organizationFacade.getOrganization(ORGANIZATION_DETAILS,"");
    }

//    *
//     * Tests exception behavior by passing in a null parameter

    @Test(expected = IllegalArgumentException.class)
    public void testNullParameter()
    {
        organizationFacade.getOrganization(null, null);
    }


//    *
//     * Tests and demonstrates the Facade's methods

    @Test
    public void testOrganizationFacade()
    {
        List<OrganizationData> organizationListData = organizationFacade.getOrganizations("");
        assertNotNull(organizationListData);
        final int size = organizationListData.size();
        modelService.save(organizationModel);

        organizationListData = organizationFacade.getOrganizations("");
        assertNotNull(organizationListData);
        assertEquals(size + 1, organizationListData.size());
        assertEquals(ORGANIZATION_DETAILS, organizationListData.get(size).getName());
        assertEquals(ORGANIZATION_LISTING.toString(), organizationListData.get(size).getName());

        final OrganizationData persistedOrganizationData = organizationFacade.getOrganization(ORGANIZATION_DETAILS,"");
        assertNotNull(persistedOrganizationData);
        assertEquals(ORGANIZATION_DETAILS, persistedOrganizationData.getName());
        assertEquals(ORGANIZATION_LISTING.toString(), persistedOrganizationData.getName());
    }
}