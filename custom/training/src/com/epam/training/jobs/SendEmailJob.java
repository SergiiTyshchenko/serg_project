package com.epam.training.jobs;

/**
 * Created by Serega on 25.04.16.
 */
import com.epam.training.model.OrganizationModel;
import com.epam.training.services.MailService;
import com.epam.training.services.OrganizationModelService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class SendEmailJob extends AbstractJobPerformable<CronJobModel> {
    private static final Logger LOGGER = Logger.getLogger(SendEmailJob.class);
    public static final String MESSAGE_PREFIX = "In our organization working: ";

    @Autowired
    private MailService mailService;
    @Autowired
    private OrganizationModelService modelService;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        LOGGER.info("Sending emails");
        List<OrganizationModel> organizationModels = modelService.getAllOrganizations();

        if (CollectionUtils.isEmpty(organizationModels)) {
            LOGGER.info("No competitions have changed, skipping send emails");
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }

        mailService.sendEmail(organizationModels, MESSAGE_PREFIX);
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

}