package com.epam.training.services;


import com.epam.training.dao.OrganizationDao;
import org.apache.commons.collections.CollectionUtils;
import com.epam.training.model.OrganizationModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Sergii_Tyshchenko
 */
public class OrganizationModelService {

    @Autowired
    private OrganizationDao organizationDao;

    private static final Integer NO_CUSTOMERS = 0;
    private static final Integer FEW_CUSTOMERS_EXIST = 5;

    public Integer getValueOfCustomersCount(final OrganizationModel organizationModel) {
        if (CollectionUtils.isEmpty(organizationModel.getCustomers())) {
            return 0;
        }

        return organizationModel.getCustomers().size();
    }

    public List<OrganizationModel> getAllOrganizations() {
        List<OrganizationModel> organizationModels = organizationDao.findAll();
        if (CollectionUtils.isEmpty(organizationModels)) {
            return Collections.EMPTY_LIST;
        }
        return organizationModels;
    }
}







