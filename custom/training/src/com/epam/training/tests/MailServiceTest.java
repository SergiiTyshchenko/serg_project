package com.epam.training.tests;

/**
 * Created by Serega on 25.04.16.
 */
import com.epam.training.model.OrganizationModel;
import com.epam.training.services.MailService;
import com.epam.training.services.OrganizationModelService;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class MailServiceTest {

    @Mock
    private OrganizationModelService modelService;
    @Mock
    private EmailService emailService;
    @Mock
    EmailMessageModel emailMessageModel;
    @Mock
    CustomerModel customerModel;
    @Mock
    EmailAddressModel emailAddressModel;
    @Mock
    private OrganizationModel organizationModel;
    @Mock
    private Properties properties;

    @InjectMocks
    private MailService mailService = new MailService();

    public static final String EMAIL = "tratata@example.com";
    public static final String NAME = "displayName";
    public static final String SUBJECT = "subject";
    public static final String BODY_MESSAGE = "In our organization working: displayName displayName displayName";

    @Test(expected = IllegalArgumentException.class)
    public void requireReturnIllegalArgumentExceptionWhenEachOfOrganizationModelsIsEmpty() {
        mailService.sendEmail(Collections.EMPTY_LIST,"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void requireReturnIllegalArgumentExceptionWhenEachOfOrganizationModelsIsNull() {
        mailService.sendEmail(null,"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void requireReturnIllegalArgumentExceptionWhenCustomersInOrganizationModelIsNull() {
        List<OrganizationModel> organizationModels = Arrays.asList(organizationModel, organizationModel, organizationModel);

        when(organizationModel.getCustomers()).thenReturn(null);

        mailService.sendEmail(organizationModels,"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void requireReturnIllegalArgumentExceptionWhenNameInOrganizationModelIsNull() {
        List<OrganizationModel> organizationModels = Arrays.asList(organizationModel, organizationModel, organizationModel);

        when(organizationModel.getName()).thenReturn(null);

        mailService.sendEmail(organizationModels,"");
    }

    @Test
    public void requireEmailSendedWhenEachOfOrganizationModelHasNecessaryData() {
        List<CustomerModel> customerModels = Arrays.asList(customerModel, customerModel, customerModel, customerModel, customerModel);
        List<OrganizationModel> organizationModels = Arrays.asList(organizationModel, organizationModel, organizationModel, organizationModel, organizationModel);

        when(emailService.getOrCreateEmailAddressForEmail(EMAIL, NAME)).thenReturn(emailAddressModel);
        when(properties.getProperty("mailService.sendersEmail")).thenReturn(EMAIL);
        when(properties.getProperty("mailService.sendersName")).thenReturn(NAME);
        when(properties.getProperty("mailService.subject")).thenReturn(SUBJECT);
        when(customerModel.getName()).thenReturn(NAME);
        when(organizationModel.getCustomers()).thenReturn(customerModels);
        when(organizationModel.getEmail()).thenReturn(EMAIL);
        when(organizationModel.getName()).thenReturn(NAME);

        when(emailService.createEmailMessage(
                Collections.singletonList(emailAddressModel),
                Collections.EMPTY_LIST,
                Collections.EMPTY_LIST,
                emailAddressModel, "", SUBJECT, BODY_MESSAGE, Collections.EMPTY_LIST)).thenReturn(emailMessageModel);

        when(emailService.send(emailMessageModel)).thenReturn(true);

        mailService.sendEmail(organizationModels, BODY_MESSAGE);

        verify(emailService, times(customerModels.size())).send(emailMessageModel);
    }

    @Test
    public void requireEmailSendedWhenOneOfOrganizationModelDoNotHasEmail() {
        List<CustomerModel> customerModels = Arrays.asList(customerModel, customerModel, customerModel);
        OrganizationModel organizationModelNotMock = new OrganizationModel();
        organizationModelNotMock.setCustomers(customerModels);

        List<OrganizationModel> organizationModels = Arrays.asList(organizationModel, organizationModel, organizationModelNotMock);

        when(emailService.getOrCreateEmailAddressForEmail(EMAIL, NAME)).thenReturn(emailAddressModel);
        when(properties.getProperty("mailService.sendersEmail")).thenReturn(EMAIL);
        when(properties.getProperty("mailService.sendersName")).thenReturn(NAME);
        when(properties.getProperty("mailService.subject")).thenReturn(SUBJECT);
        when(customerModel.getName()).thenReturn(NAME);
        when(organizationModel.getCustomers()).thenReturn(customerModels);
        when(organizationModel.getEmail()).thenReturn(EMAIL);
        when(organizationModel.getName()).thenReturn(NAME);

        when(emailService.createEmailMessage(
                Collections.singletonList(emailAddressModel),
                Collections.EMPTY_LIST,
                Collections.EMPTY_LIST,
                emailAddressModel, "", SUBJECT, BODY_MESSAGE, Collections.EMPTY_LIST)).thenReturn(emailMessageModel);

        when(emailService.send(emailMessageModel)).thenReturn(true);

        mailService.sendEmail(organizationModels, BODY_MESSAGE);

        verify(emailService, times(customerModels.size() - 1)).send(emailMessageModel);
    }

}