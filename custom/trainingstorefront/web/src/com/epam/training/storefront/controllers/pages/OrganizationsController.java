package com.epam.training.storefront.controllers.pages;



        import com.epam.training.data.OrganizationData;
        import com.epam.training.facades.OrganizationFacade;

        import java.io.UnsupportedEncodingException;
        import java.net.URLDecoder;
        import java.util.List;

        import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrganizationsController extends AbstractPageController
{
    private OrganizationFacade organizationFacade;

    private static final String ORGANIZATION_DETAILS = "pages/organizations/organizationDetails";
    private static final String ORGANIZATION_LISTING = "pages/organizations/organizationListing";

    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    public String showOrganizations(final Model model)
    {
        final List<OrganizationData> organizations = organizationFacade.getOrganizations("organizationListFormat");
        model.addAttribute("organizations", organizations);
        return ORGANIZATION_LISTING;
    }

    @RequestMapping(value = "/organizations/{organizationName}", method = RequestMethod.GET)
    public String showOrganizationDetails(@PathVariable String organizationName, final Model model) throws UnsupportedEncodingException
    {
        organizationName = URLDecoder.decode(organizationName, "UTF-8");
        final OrganizationData organization = organizationFacade.getOrganization(organizationName, "organizationDetailsFormat");
        organization.setName(organization.getName());
        organization.setImageUrl(organization.getImageUrl());
        model.addAttribute("organization", organization);
        return ORGANIZATION_DETAILS;
    }

    @Autowired
    public void setFacade(final OrganizationFacade facade)
    {
        this.organizationFacade = facade;
    }

}