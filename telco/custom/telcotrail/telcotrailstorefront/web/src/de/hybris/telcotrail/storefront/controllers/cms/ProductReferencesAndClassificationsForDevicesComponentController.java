/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package de.hybris.telcotrail.storefront.controllers.cms;

import de.hybris.platform.acceleratorcms.model.components.ProductReferencesComponentModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.telcotrail.storefront.controllers.util.ProductDataHelper;
import de.hybris.telcotrail.core.model.ProductReferencesAndClassificationsForDevicesComponentModel;
import de.hybris.telcotrail.facades.product.TelcoProductFacade;
import de.hybris.telcotrail.storefront.controllers.TelcoControllerConstants;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * Component controller that handles product references, feature compatible and vendor compatible products
 * 
 */
@Controller("ProductReferencesAndClassificationsForDevicesComponentController")
@RequestMapping(value = TelcoControllerConstants.Actions.Cms.ProductReferencesAndClassificationsForDevicesComponent)
public class ProductReferencesAndClassificationsForDevicesComponentController extends
		ProductReferencesAndClassificationsComponentController
{

	@Resource(name = "telcoProductFacade")
	private TelcoProductFacade telcoProductFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final ProductReferencesComponentModel component)
	{
		final ProductReferencesAndClassificationsForDevicesComponentModel prComponent = (ProductReferencesAndClassificationsForDevicesComponentModel) component;

		final List<ProductData> referenceAndClassificationsProducts = telcoProductFacade
				.getProductReferencesAndFeatureCompatibleAndVendorCompatibleProductsForCode(
						ProductDataHelper.getCurrentProduct(request), prComponent.getProductReferenceTypes(),
						ProductReferencesAndClassificationsComponentController.PRODUCT_OPTIONS, prComponent.getMaximumNumberProducts(),
						prComponent.getClassAttributeAssignment(), prComponent.getTargetItemType());

		model.addAttribute("title", prComponent.getTitle());

		model.addAttribute("productAccessories", referenceAndClassificationsProducts);
	}

}
