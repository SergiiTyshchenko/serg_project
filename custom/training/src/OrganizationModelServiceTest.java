/**
 * 
 */


import static org.junit.Assert.assertEquals;

import com.epam.training.hmc.model.OrganizationModel;
import com.epam.training.services.OrganizationModelService;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Sergii_Tyshchenko
 *
 */
public class OrganizationModelServiceTest
{

	private static final Integer NO_CUSTOMERS = 0;
	private static final Integer FEW_CUSTOMERS_EXIST = 5;

	private OrganizationModel organization;
	private OrganizationModelService organizationModelServicetest;

	@Before
	public void setUp()
	{
		organizationModelServicetest = new OrganizationModelService();
		organization = new OrganizationModel();
	}

	@Test
	public void requireReturnCorrectlyFormattedStringForNonEmptyCustomersCollection()
	{
		CustomerModel customer = new CustomerModel();
		List<CustomerModel> fiveCustomers = Arrays.asList(customer, customer, customer, customer, customer);
		organization.setCustomers(fiveCustomers);

		assertEquals("Resulting string is formatted incorrectly", FEW_CUSTOMERS_EXIST, organizationModelServicetest.getValueOfCustomersCount(organization));
	}

	@Test
	public void requireReturnNoCustomersFoundMessageWhenCustomersCollectionIsEmpty()
	{
		organization.setCustomers(Collections.EMPTY_LIST);

		assertEquals("No customers message is formatted incorrectly", NO_CUSTOMERS, organizationModelServicetest.getValueOfCustomersCount(organization));
	}
	
	@Test
	public void requireReturnNoCustomersFoundMessageWhenCustomersCollectionIsNull()
	{
		organization.setCustomers(null);

		assertEquals("No customers message is formatted incorrectly", NO_CUSTOMERS, organizationModelServicetest.getValueOfCustomersCount(organization));
	}

}