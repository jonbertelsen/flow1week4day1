package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("employee")
public class EmployeeResource
{
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final EmployeeFacade FACADE =  EmployeeFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String hello()
    {
        return "{\"message\": \"Hello, Employee!\"}";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll()
    {
        List<EmployeeDTO> employeeList = FACADE.getAllEmployees();
        return GSON.toJson(employeeList);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") int id)
    {
        EmployeeDTO employeeDTO = FACADE.getEmployeeById(id);
        return GSON.toJson(employeeDTO);
    }

    @GET
    @Path("highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHigestPaid()
    {
        List<EmployeeDTO> employeeList = FACADE.getEmployeesWithHighestSalary();
        return GSON.toJson(employeeList);
    }

    @GET
    @Path("name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("name") String name)
    {
        List<EmployeeDTO> employeeDTO = FACADE.getEmployeesByName(name);
        return GSON.toJson(employeeDTO);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String createEmployee(String input)
    {
        EmployeeDTO employeeDTO = GSON.fromJson(input, EmployeeDTO.class);
        EmployeeDTO newEmployee = FACADE.createEmployee(employeeDTO.getName(), employeeDTO.getSalary());
        return GSON.toJson(newEmployee);
    }

    /*
{
  "name": "Fætter Højben",
  "salary": 28000
}
*/


}



