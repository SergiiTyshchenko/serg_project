package com.epam.training.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.epam.training.core.data.AdditionalInfoData;
import com.epam.training.model.AdditionalInfoModel;

public class ProductAdditionalInfoPopulator implements Populator<ProductModel, ProductData>
{

    private Converter<AdditionalInfoModel, AdditionalInfoData> productAdditionalInfoConverter;

    public Converter<AdditionalInfoModel, AdditionalInfoData> getProductAdditionalInfoConverter()
    {
        return productAdditionalInfoConverter;
    }

    public void setProductAdditionalInfoConverter(final Converter<AdditionalInfoModel, AdditionalInfoData> productAdditionalInfoConverter)
    {
        this.productAdditionalInfoConverter = productAdditionalInfoConverter;
    }

    @Override
    public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException
    {
        final List<AdditionalInfoData> productAdditionalInfos = new ArrayList<AdditionalInfoData>();
        for (final AdditionalInfoModel model : productModel.getAdditionalInfos())
        {
            productAdditionalInfos.add(productAdditionalInfoConverter.convert(model));
        }
        productData.setAdditionalInfos(productAdditionalInfos);
    }

}