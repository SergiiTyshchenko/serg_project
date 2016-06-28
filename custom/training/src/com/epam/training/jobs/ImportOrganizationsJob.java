package com.epam.training.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.impex.jalo.ImpExManager;
import de.hybris.platform.impex.jalo.ImpExMedia;
import de.hybris.platform.impex.jalo.exp.ExportConfiguration;
import de.hybris.platform.impex.jalo.exp.ExportUtils;
import de.hybris.platform.impex.jalo.exp.Exporter;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;


public class ImportOrganizationsJob extends AbstractJobPerformable<CronJobModel>
{

    private static final Logger LOG = Logger.getLogger(ImportOrganizationsJob.class);

    @Override
    public PerformResult perform(final CronJobModel cronJob)
    {
        LOG.info("Start organization job");
        try
        {
            final File script = File.createTempFile("tmporgexp", ".temp");
            final byte buffer[] = new byte[0xffff];
            int nbytes;
            final FileOutputStream fos = new FileOutputStream(script, true);
            final InputStream fis = ImpExManager.class.getResourceAsStream("/impex/OrganizationsExport.impex");
            while ((nbytes = fis.read(buffer)) != -1)
            {
                fos.write(buffer, 0, nbytes);
            }
            fos.close();
            fis.close();

            final ImpExMedia scriptmedia = ImpExManager.getInstance().createImpExMedia("exportorganizationsscript");
            scriptmedia.setFieldSeparator(';');
            scriptmedia.setFile(script);

            final ExportConfiguration config = new ExportConfiguration(scriptmedia, ImpExManager.getExportOnlyMode());
            config.setFieldSeparator(";");
            config.setDataExportTarget(ExportUtils.createDataExportTarget("exportorganizations"));

            final Exporter exporter = new Exporter(config);
            exporter.export();
            LOG.info("Finish organization job");
        }
        catch (final Exception e)
        {
            LOG.error("Exception while performing job");
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

}