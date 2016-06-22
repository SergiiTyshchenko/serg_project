package com.epam.training.dao;

/**
 * Created by Serega on 25.04.16.
 */
import com.epam.training.model.OrganizationModel;

import java.util.List;

public interface OrganizationDao {

    List<OrganizationModel> findAll();

    List<OrganizationModel> findOrganizationByCode(String code);
}