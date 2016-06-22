package com.epam.training.tests;

/**
 * Created by Serega on 25.04.16.
 */
import com.epam.training.dao.OrganizationDao;
import com.epam.training.model.OrganizationModel;
import com.epam.training.services.OrganizationModelService;
import de.hybris.platform.core.model.user.CustomerModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;



@RunWith(MockitoJUnitRunner.class)
public class OrganizationModelServiceTest {

    private static final Integer NO_CUSTOMERS = 0;
    private static final Integer FEW_CUSTOMERS_EXIST = 5;

    @Mock
    private OrganizationModel organization;
    @Mock
    private OrganizationDao organizationDaoMock;

    @InjectMocks
    private OrganizationModelService organizationModelService = new OrganizationModelService();

    @Test
    public void requireReturnCorrectlyFormattedStringForNonEmptyCustomersCollection() {
        CustomerModel customer = new CustomerModel();
        List<CustomerModel> fiveCustomers = Arrays.asList(customer, customer, customer, customer,customer);
        organization.setCustomers(fiveCustomers);
        Mockito.when(organization.getCustomers()).thenReturn(fiveCustomers);

        assertEquals("Resulting string is formatted incorrectly",
                FEW_CUSTOMERS_EXIST, fiveCustomers.size(),
                organizationModelService.getValueOfCustomersCount(organization));
    }

    @Test
    public void requireReturnNoCustomersFoundMessageWhenCustomersCollectionIsEmpty() {
        organization.setCustomers(Collections.EMPTY_LIST);

        assertEquals("No customers message is formatted incorrectly", NO_CUSTOMERS, organizationModelService.getValueOfCustomersCount(organization));
    }

    @Test
    public void requireReturnNoCustomersFoundMessageWhenCustomersCollectionIsNull() {
        organization.setCustomers(null);

        assertEquals("No customers message is formatted incorrectly", NO_CUSTOMERS, organizationModelService.getValueOfCustomersCount(organization));
    }

    @Test
    public void requireReturnListOfAllOrganizationsWhenOrganizationsExist() throws Exception {
        List<OrganizationModel> expected = Arrays.asList(organization, organization, organization, organization, organization);
        Mockito.when(organizationDaoMock.findAll()).thenReturn(expected);

        List<OrganizationModel> actual = organizationModelService.getAllOrganizations();

        assertEquals(expected, actual);
    }

    @Test
    public void requireReturnEmptyListOfAllOrganizationsWhenOrganizationsListIsEmpty() throws Exception {
        List<OrganizationModel> expected = Collections.EMPTY_LIST;
        Mockito.when(organizationDaoMock.findAll()).thenReturn(expected);

        List<OrganizationModel> actual = organizationModelService.getAllOrganizations();

        assertEquals(expected, actual);
    }

    @Test
    public void requireReturnEmptyListOfAllOrganizationsWhenOrganizationsListIsNull() throws Exception {
        List<OrganizationModel> expected = Collections.EMPTY_LIST;
        Mockito.when(organizationDaoMock.findAll()).thenReturn(null);

        List<OrganizationModel> actual = organizationModelService.getAllOrganizations();

        assertEquals(expected, actual);
    }

}