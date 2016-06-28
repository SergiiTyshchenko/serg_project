package com.epam.training.tests;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.epam.training.services.OrganizationService;
import de.hybris.bootstrap.annotations.IntegrationTest;
import com.epam.training.model.OrganizationModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

@IntegrationTest
public class OrganizationServiceIntegrationTest  extends ServicelayerTransactionalTest
{
    @Resource
    private OrganizationService organizationService;
    @Resource
    private ModelService modelService;

    private OrganizationModel organizationModel;
    private final static String ORGANIZATION_DETAILS = "wembley";
    private final static String ORGANIZATION_LISTING = "8908098";

    @Before
    public void setUp()
    {
        // This instance of a OrganizationModel will be used by the tests
        organizationModel = new OrganizationModel();
        organizationModel.setName(ORGANIZATION_DETAILS);
        organizationModel.setPhone(ORGANIZATION_LISTING);
    }

    @Test(expected = UnknownIdentifierException.class)
    public void testFailBehavior()
    {
        organizationService.getOrganizationForCode(ORGANIZATION_DETAILS);
    }

    @Test
    public void testOrganizationService()
    {
        List<OrganizationModel> organizationModels = organizationService.getOrganizations();
        final int size = organizationModels.size();

        modelService.save(organizationModel);

        organizationModels = organizationService.getOrganizations();
        assertEquals(size + 1, organizationModels.size());
        assertEquals("Unexpected organization found", organizationModel, organizationModels.get(organizationModels.size() - 1));

        final OrganizationModel persistedOrganizationModel = organizationService.getOrganizationForCode(ORGANIZATION_DETAILS);
        assertNotNull("No organization found", persistedOrganizationModel);
        assertEquals("Different organization found", organizationModel, persistedOrganizationModel);
    }
}