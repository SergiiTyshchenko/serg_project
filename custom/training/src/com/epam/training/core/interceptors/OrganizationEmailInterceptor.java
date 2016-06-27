package com.epam.training.core.interceptors;

/**
 * Created by Serega on 18.05.16.
 */
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.training.model.OrganizationModel;
import org.springframework.beans.factory.annotation.Qualifier;


public class OrganizationEmailInterceptor implements ValidateInterceptor
{
    static final private Logger LOG = Logger.getLogger(OrganizationEmailInterceptor.class);
    public static final int CUSTOMERS_AMOUNT = 5;
    public static final String CUSTOMERS_AMOUNT_MESSAGE = "Customers amount equals to 5";
    public static final String CUSTOMERS_AMOUNT_SUBJECT = "Customers amount";
    public static final String ADDRESS = "darwinepam@gmail.com";
    public static final String FROM = "darwinepam@gmail.com";

    //@Autowired
    //private ModelService modelService;


    @Autowired
    private EmailService emailService;


    @Override
    public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException
    {
        final OrganizationModel organization = (OrganizationModel) model;
        LOG.info("Organization email interceptor checking");
        if (CUSTOMERS_AMOUNT == organization.getCustomers().size())
        {
            LOG.info("Organization email interceptor working");
            LOG.info("0");
            final EmailAddressModel from = emailService.getOrCreateEmailAddressForEmail(ADDRESS, FROM);
            LOG.info("1");
            final EmailAddressModel to = emailService.getOrCreateEmailAddressForEmail(organization.getEmail(),
                    organization.getName());
            LOG.info("2");
            final EmailMessageModel emailMessageModel = emailService.createEmailMessage(Collections.singletonList(to),
                    Collections.EMPTY_LIST, Collections.EMPTY_LIST, from, "", CUSTOMERS_AMOUNT_SUBJECT, CUSTOMERS_AMOUNT_MESSAGE,
                    Collections.EMPTY_LIST);
            LOG.info("Sending email to " + organization.getEmail() + " due to " + CUSTOMERS_AMOUNT_MESSAGE);
            emailService.send(emailMessageModel);
        }
    }

}