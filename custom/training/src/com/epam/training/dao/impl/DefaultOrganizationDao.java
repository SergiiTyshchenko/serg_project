package com.epam.training.dao.impl;

/**
 * Created by Serega on 25.04.16.
 */
import com.epam.training.dao.OrganizationDao;
import com.epam.training.model.OrganizationModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "organizationDao")
public class DefaultOrganizationDao extends DefaultGenericDao<OrganizationModel> implements OrganizationDao {

    @Autowired
    private FlexibleSearchService flexibleSearchService;
    public DefaultOrganizationDao() {
        super(OrganizationModel._TYPECODE);
    }

    @Override
    public List<OrganizationModel> findAll() {
        return find();
    }

    @Override
    public List<OrganizationModel> findOrganizationByCode(final String code)
    {
        final String queryString = //
                "SELECT {p:" + OrganizationModel.PK + "}" //
                        + "FROM {" + OrganizationModel._TYPECODE + " AS p} "//
                        + "WHERE " + "{p:" + OrganizationModel.NAME + "}=?code ";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);

        return flexibleSearchService.<OrganizationModel> search(query).getResult();
    }

}