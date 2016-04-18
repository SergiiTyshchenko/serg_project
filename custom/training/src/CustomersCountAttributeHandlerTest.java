/**
 * 
 */


import static org.junit.Assert.assertEquals;

import com.epam.training.attributehandlers.CustomersCountAttributeHandler;
import com.epam.training.model.OrganizationModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.epam.training.services.OrganizationModelService;


/**
 * @author Sergii_Tyshchenko
 *
 */
public class CustomersCountAttributeHandlerTest
{

	private static final Integer NO_CUSTOMERS = 0;
	private static final Integer FEW_CUSTOMERS_EXIST = 5;

	private OrganizationModel organization;
	private CustomersCountAttributeHandler handler;
	private OrganizationModelService organizationServiceMock = Mockito.mock(OrganizationModelService.class);

	@Before
	public void setUp()
	{
		handler = new CustomersCountAttributeHandler();
		handler.setOrganizationModelService(organizationServiceMock);

		organization = new OrganizationModel();
	}

	@Test
	public void requireReturnCorrectlyFormattedStringWhenOrganizationsCollectionIsNotEmpty()
	{
		CustomerModel customer = new CustomerModel();
		List<CustomerModel> fiveCustomers = Arrays.asList(customer, customer, customer, customer, customer);
		organization.setCustomers(fiveCustomers);
		Mockito.when(organizationServiceMock.getValueOfCustomersCount(organization)).thenReturn(5);

		assertEquals(FEW_CUSTOMERS_EXIST, handler.get(organization));
	}

	@Test
	public void requireReturnCorrectlyFormattedStringWhenOrganizationsCollectionIsEmpty()
	{
		organization.setCustomers(Collections.EMPTY_LIST);
		Mockito.when(organizationServiceMock.getValueOfCustomersCount(organization)).thenReturn(0);

		assertEquals(NO_CUSTOMERS, handler.get(organization));
	}

}