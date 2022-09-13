package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import facades.EmployeeFacade;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("employee")
public class EmployeeResource
{

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final EmployeeFacade facade = EmployeeFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo()
    {
        return "{\"msg\":\"Hello Employee\"}";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll()
    {
        List<EmployeeDTO> employeeDTOList = facade.getAllEmployees();
        return GSON.toJson(employeeDTOList);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("id") int id)
    {
        return GSON.toJson(facade.getEmployeeById(id));
    }

    @GET
    @Path("highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaid()
    {
        List<EmployeeDTO> employeeDTOList = facade.getEmployeesWithHighestSalary();
        return GSON.toJson(employeeDTOList);
    }

    @GET
    @Path("name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("name") String name)
    {
        return GSON.toJson(facade.getEmployeesByName(name));
    }








}
