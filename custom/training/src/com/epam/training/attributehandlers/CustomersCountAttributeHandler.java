/**
 * 
 */
package com.epam.training.attributehandlers;



import com.epam.training.model.OrganizationModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.training.services.OrganizationModelService;


/**
 * @author Sergii_Tyshchenko
 *
 */
@Component
public class CustomersCountAttributeHandler extends AbstractDynamicAttributeHandler<Integer, OrganizationModel>
{

	@Autowired
	private OrganizationModelService organizationModelService;

	@Override
	public Integer get(final OrganizationModel model)
	{
		return organizationModelService.getValueOfCustomersCount(model);
	}

	public void setOrganizationModelService(final OrganizationModelService organizationModelService)
	{
		this.organizationModelService = organizationModelService;
	}

}