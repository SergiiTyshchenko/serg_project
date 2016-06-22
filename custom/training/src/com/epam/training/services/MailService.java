package com.epam.training.services;

/**
 * Created by Serega on 25.04.16.
 */
import com.epam.training.model.OrganizationModel;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.email.impl.DefaultEmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Properties;


public class MailService {

    private static final Logger LOGGER = Logger.getLogger(MailService.class);

    @Autowired
    private Properties properties;
    @Autowired
    private EmailService emailService;

    public void sendEmail(List<OrganizationModel> organizationModels, String message) throws IllegalArgumentException {
        if (CollectionUtils.isEmpty(organizationModels)) {
            throw new IllegalArgumentException("organizationModels must not be null or empty");
        }
        LOGGER.info("In method sendEmail com.epam.training.services.MailService");
        EmailAddressModel fromAddress = emailService.getOrCreateEmailAddressForEmail(properties.getProperty("mailService.sendersEmail"), properties.getProperty("mailService.sendersName"));

        for (OrganizationModel organizationModel : organizationModels) {
            LOGGER.info("Creating bodyMessage...");
            String bodyMessage = getBodyMessage(organizationModel, message);
            LOGGER.info("BodyMessage created. " + bodyMessage.toString());

            LOGGER.info("Creating EmailMessageModel...");
            if (null == organizationModel.getEmail()) {
                continue;
            }
            EmailMessageModel emailMessageModel = emailService.createEmailMessage(
                    getDestinationEmailAddressModel(organizationModel),
                    Collections.EMPTY_LIST,
                    Collections.EMPTY_LIST, fromAddress, "", properties.getProperty("mailService.subject"), bodyMessage, Collections.EMPTY_LIST);
            LOGGER.info("EmailMessageModel created.");

            LOGGER.info("Sending email...");
            emailService.send(emailMessageModel);
            LOGGER.info("Email sended.");
        }
    }

    private String getBodyMessage(OrganizationModel organizationModel, String message) {
        if (CollectionUtils.isEmpty(organizationModel.getCustomers())) {
            throw new IllegalArgumentException("customers must not be null or empty");
        }
        List<CustomerModel> customers = (List<CustomerModel>) organizationModel.getCustomers();
        StringBuilder bodyMessage = new StringBuilder(message);
        customers = (List<CustomerModel>) organizationModel.getCustomers();
        for (CustomerModel customer : customers) {
            bodyMessage.append(" " + customer.getName());
        }
        return bodyMessage.toString();
    }

    private List<EmailAddressModel> getDestinationEmailAddressModel(OrganizationModel organizationModel) {

        return Collections.singletonList(getEmailAddressModelsFromOrganization(organizationModel));
    }

    private EmailAddressModel getEmailAddressModelsFromOrganization(OrganizationModel organizationModel) {
        LOGGER.info("Organization's name is: " + organizationModel.getName());
        if (null == organizationModel.getName()) {
            throw new IllegalArgumentException("name must not be null");
        }
        EmailAddressModel emailAddressModel = emailService.getOrCreateEmailAddressForEmail(organizationModel.getEmail(), organizationModel.getName());
        LOGGER.info("DisplayName is: " + emailAddressModel.getDisplayName());
        return emailAddressModel;
    }

    public void setDefaultEmailService(final DefaultEmailService emailService) {
        this.emailService = emailService;
    }

}