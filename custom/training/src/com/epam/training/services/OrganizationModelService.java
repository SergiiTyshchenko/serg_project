/**
 *
 */
package com.epam.training.services;


import com.epam.training.model.OrganizationModel;
import org.apache.commons.collections.CollectionUtils;

/**
 * @author Sergii_Tyshchenko
 */
public class OrganizationModelService {

    public Integer getValueOfCustomersCount(final OrganizationModel model) {
        if (CollectionUtils.isEmpty(model.getCustomers())) {
            return 0;
        }

        return model.getCustomers().size();
    }

}