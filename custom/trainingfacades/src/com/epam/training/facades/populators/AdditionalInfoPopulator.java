package com.epam.training.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.util.Assert;

import com.epam.training.core.data.AdditionalInfoData;
import com.epam.training.model.AdditionalInfoModel;

public class AdditionalInfoPopulator implements Populator<AdditionalInfoModel, AdditionalInfoData>
{
    @Override
    public void populate(final AdditionalInfoModel additionalInfoModel, final AdditionalInfoData additionalInfoData)
            throws ConversionException
    {
        Assert.notNull(additionalInfoModel, "Parameter additionalInfoModel cannot be null.");
        Assert.notNull(additionalInfoData, "Parameter additionalInfoData cannot be null.");

        additionalInfoData.setId(additionalInfoModel.getId());
        additionalInfoData.setFeature(additionalInfoModel.getFeature());
    }
}