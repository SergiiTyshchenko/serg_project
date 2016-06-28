package com.epam.training.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.training.facades.impl.DefaultOrganizationFacade;
import com.epam.training.data.OrganizationData;
import com.epam.training.model.OrganizationModel;
import com.epam.training.services.OrganizationService;

import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;


public class OrganizationFacadeUnitTest
{
    private DefaultOrganizationFacade organizationFacade;

    private OrganizationService organizationService;

    private final static String ORGANIZATION_DETAILS = "my_org";
    private final static String ORGANIZATION_LISTING = "";

    // Convenience method for returning a list of Organization
    private List<OrganizationModel> dummyDataOrganizationList()
    {
        final OrganizationModel my_org = new OrganizationModel();
        my_org.setName(ORGANIZATION_DETAILS);
        my_org.setPhone(ORGANIZATION_LISTING);
        final List<OrganizationModel> organizations = new ArrayList<OrganizationModel>();
        organizations.add(my_org);
        return organizations;
    }

    // Convenience method for returning a Organization with code and capacity set for my_org
    private OrganizationModel dummyDataOrganization()
    {
        final OrganizationModel my_org = new OrganizationModel();
        my_org.setName(ORGANIZATION_DETAILS);
        my_org.setPhone(ORGANIZATION_LISTING);
        return my_org;
    }

    @Before
    public void setUp()
    {
        // We will be testing the POJO DefaultOrganizationFacade - the implementation of the OrganizationFacade interface.
        organizationFacade = new DefaultOrganizationFacade();

        /**
         * The facade is expected to make calls to an implementation of OrganizationService but in this test we want to verify
         * the correct behaviour of DefaultOrganizationFacade itself and not also implicitly test the behaviour of a
         * OrganizationService. In fact as of writing this class, we do only have the interface OrganizationService and no
         * implementation. This requires that we mock out the OrganizationService interface. There are several strong arguments
         * for following this practice:
         *
         * If we were to include a real implementation of OrganizationService rather than mocking it out..
         *
         * 1) we will not get "false failures" in DefaultOrganizationFacade due to errors in the OrganizationService implementation.
         * Such errors should be caught in tests that are focusing on OrganizationService instead.
         *
         * 2) The condition could arise where an error in the facade gets hidden by a complimentary error in the
         * OrganizationService implementation - resulting in a "false positive".
         *
         * By mocking out the interface OrganizationService..
         *
         * 3) we do not actually need an implementation of it. This therefore helps us to focus our tests on this POJO
         * before having to implement other POJOs on which it depends - allowing us to write tests early.
         *
         * 4) by focusing on the behaviour of the facade and the interfaces it uses, we are forced to focus also on the
         * those interface, improving them before writing their implementation.
         *
         *
         * Therefore we create a mock of the OrganizationService in the next line.
         */
        organizationService = mock(OrganizationService.class);
        // We then wire this service into the OrganizationFacade implementation.
        organizationFacade.setOrganizationService(organizationService);
    }

    /**
     * The aim of this test is to test that:
     *
     * 1) The facade's method getOrganizations makes a call to the OrganizationService's method getOrganizations
     *
     * 2) The facade then correctly wraps OrganizationModels that are returned to it from the OrganizationService's getOrganizations
     * into Data Transfer Objects of type OrganizationData.
     */
    @Test
    public void testGetAllOrganizations()
    {
        /**
         * We instantiate an object that we would like to be returned to OrganizationFacade when the mocked out
         * OrganizationService's method getOrganizations is called. This will be a list of two OrganizationModels.
         */
        final List<OrganizationModel> organizations = dummyDataOrganizationList();
        // create my_org organization for the assert comparison
        final OrganizationModel my_org = dummyDataOrganization();
        // We tell Mockito we expect OrganizationService's method getOrganizations to be called, and that when it is, organizations should be returned
        when(organizationService.getOrganizations()).thenReturn(organizations);

        /**
         * We now make the call to OrganizationFacade's getOrganizations. If within this method a call is made to OrganizationService's
         * getOrganizations, Mockito will return the organizations instance to it. Mockito will also remember that the call was
         * made.
         */
        final List<OrganizationData> dto = organizationFacade.getOrganizations("");
        // We now check that dto is a DTO version of organizations..
        assertNotNull(dto);
        assertEquals(organizations.size(), dto.size());
        assertEquals(my_org.getName(), dto.get(0).getName());
        assertEquals(my_org.getPhone().toString(), dto.get(0).getName());
    }

/*    @Test
    public void testGetOrganization()
    {
        *//**
 * We instantiate an object that we would like to be returned to OrganizationFacade when the mocked out
 * OrganizationService's method getOrganization is called. This will be the OrganizationModel for my_org organization.
 *//*
        // create my_org organization
        final OrganizationModel my_org = dummyDataOrganization();

        // We tell Mockito we expect OrganizationService's method getOrganizationForCode to be called, and that when it is, my_org should be returned
        when(organizationService.getOrganizations()).thenReturn();

        *//**
 * We now make the call to OrganizationFacade's getOrganization. If within this method a call is made to OrganizationService's
 * getOrganization, Mockito will return the my_org instance to it. Mockito will also remember that the call was made.
 *//*
        final OrganizationData organization = organizationFacade.getOrganization("my_org","");
        // We now check that organization is a correct DTO representation of the ServiceModel my_org
        assertEquals(my_org.getName(), organization.getName());
        assertEquals(my_org.getPhone().toString(), organization.getName());
    }*/

}