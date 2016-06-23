package com.epam.training.dto;

/**
 * Created by Serega on 26.04.16.
 */
import com.epam.training.model.OrganizationModel;
import de.hybris.platform.webservices.dto.AbstractCollectionDTO;
import de.hybris.platform.webservices.util.objectgraphtransformer.GraphNode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Generated collection dto class for type Organization first defined at extension training
 */
@SuppressWarnings("all")
@GraphNode(target = OrganizationModel.class, uidProperties = "name")
@XmlRootElement(name = "organizations")
public class OrganizationsDTO extends AbstractCollectionDTO {
    /**
     * <i>Generated variable</i> - List of <code>OrganizationDTO
     */
    private List<OrganizationDTO> organizationsList;


    /**
     * <i>Generated constructor</i> - for generic creation.
     */
    public OrganizationsDTO() {
        super();
    }

    /**
     * <i>Generated constructor</i> - for generic creation.
     */
    public OrganizationsDTO(final List<OrganizationDTO> organizationsList) {
        super();
        this.organizationsList = organizationsList;
    }


    /**
     * @return the organizations
     */
    @XmlElement(name = "organization")
    public List<OrganizationDTO> getOrganizations() {
        return organizationsList;
    }

    /**
     * @param organizationsList the organizationsList to set
     */
    public void setOrganizations(final List<OrganizationDTO> organizationsList) {
        this.organizationsList = organizationsList;
    }

}