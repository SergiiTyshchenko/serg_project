package com.epam.training.tests;

/**
 * Created by Serega on 18.05.16.
 */

        import com.epam.training.core.interceptors.OrganizationEmailInterceptor;
        import com.epam.training.model.OrganizationModel;
        import com.epam.training.services.MailService;
        import de.hybris.platform.core.model.user.CustomerModel;
        import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
        import de.hybris.platform.servicelayer.interceptor.InterceptorException;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;
        import org.mockito.runners.MockitoJUnitRunner;

        import java.util.Arrays;
        import java.util.Collections;
        import java.util.List;

        import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationEmailInterceptorTest {

    public static final String MAX_CUSTOMERS_COUNT_ACHIEVED_MESSAGE = "The Organization %s has %d customers now. If you would like to add new one at first you need to fire one.";
    public static final String ORGANIZATION_NAME = "OrganizationName";

    @Mock
    private MailService mailService;
    @Mock
    private OrganizationModel organization;
    @Mock
    private InterceptorContext interceptorContext;

    @InjectMocks
    private OrganizationEmailInterceptor emailInterceptor = new OrganizationEmailInterceptor();


    @Test
    public void requireEmailSendedWhenCustomersAmountEqualsFive() throws InterceptorException {
        CustomerModel customer = new CustomerModel();
        List<CustomerModel> customers = Arrays.asList(customer, customer, customer, customer, customer);
        organization.setCustomers(customers);
        organization.setName(ORGANIZATION_NAME);
        List<OrganizationModel> organizationModelList = Collections.singletonList(organization);
        when(organization.getCustomers()).thenReturn(customers);

        emailInterceptor.onValidate(organization, interceptorContext);
        verify(mailService, times(1)).sendEmail(organizationModelList, String.format(MAX_CUSTOMERS_COUNT_ACHIEVED_MESSAGE, organization.getName(), customers.size()));

}

}