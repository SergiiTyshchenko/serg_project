package com.epam.training.tests;

/**
 * Created by Serega on 25.04.16.
 */
import com.epam.training.jobs.SendEmailJob;
import com.epam.training.model.OrganizationModel;
import com.epam.training.services.MailService;
import com.epam.training.services.OrganizationModelService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class SendEmailJobTest {

    @Mock
    private MailService mailService;
    @Mock
    private OrganizationModelService modelService;
    @Mock
    private OrganizationModel organization;

    @InjectMocks
    private SendEmailJob sendEmailJob = new SendEmailJob();

    @Test
    public void requireReturnCronJobResultSuccessAndFinishedWhenOrganizationModelsIsEmpty() {
        PerformResult expectedResult = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        Mockito.when(modelService.getAllOrganizations()).thenReturn(Collections.EMPTY_LIST);

        PerformResult actualResult = sendEmailJob.perform(new CronJobModel());

        assertEquals(expectedResult.getResult(), actualResult.getResult());
        assertEquals(expectedResult.getStatus(), actualResult.getStatus());
    }

    @Test
    public void requireReturnCronJobResultSuccessAndFinishedWhenOrganizationModelsIsNotEmpty() {
        PerformResult expectedResult = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        Mockito.when(modelService.getAllOrganizations()).thenReturn(Arrays.asList(organization, organization, organization));

        PerformResult actualResult = sendEmailJob.perform(new CronJobModel());

        assertEquals(expectedResult.getResult(), actualResult.getResult());
        assertEquals(expectedResult.getStatus(), actualResult.getStatus());
    }

}