package com.epam.training.recource;

/**
 * Created by Serega on 26.04.16.
 */
import com.epam.training.dto.OrganizationDTO;
import com.epam.training.dto.OrganizationsDTO;
import com.epam.training.model.OrganizationModel;
import com.epam.training.resource.OrganizationResource;
import de.hybris.platform.webservices.AbstractCollectionResource;
import de.hybris.platform.webservices.AbstractYResource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Generated REST root resource for a collection of elements of  type Organization defined at extension training Allowed methods: GET, POST, PUT, DELETE, HEADER
 */
@SuppressWarnings("all")
@Path("/ooorganizations")
public class OrganizationsResource extends AbstractCollectionResource <Collection<OrganizationModel>>
{
    /**
     * <i>Generated constructor</i> - for generic creation.
     */
    public OrganizationsResource()
    {
        super("Organization");
    }


    /**
     * Generated HTTP method for covering GET requests.
     * @return {@link Response}
     */
    @GET
    public Response getAllOrganizations()
    {
        return createGetResponse().build(OrganizationsDTO.class);
    }

    /**
     *  Generated getter for sub resource of type {@link OrganizationResource} for current root resource
     */
    @Path("{organization}")
    public AbstractYResource getOrganizationResource(@PathParam("organization")  final String resourceKey)
    {
        final OrganizationResource resource  = resourceCtx.getResource(OrganizationResource.class);
        resource.setResourceId(resourceKey );
        resource.setParentResource(this);
        super.prepareJaloSession();
        return resource;
    }

    /**
     * Convenience method which just delegates to {@link #getResourceValue()}
     */
    public Collection<OrganizationModel> getOrganizationsCollection()
    {
        return super.getResourceValue();
    }

    /**
     * Generated HTTP method for covering POST requests.
     * @return {@link Response}
     */
    @POST
    public Response postOrganization(final OrganizationDTO dto)
    {
        return createPostResponse(dto).build();
    }

    /**
     * Convenience method which just delegates to {@link #setResourceValue(Collection)}
     */
    public void setOrganizationsCollection(final Collection<OrganizationModel> value)
    {
        super.setResourceValue(value);
    }

}