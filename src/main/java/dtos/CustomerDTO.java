package dtos;

import entities.Customer;
import entities.Employee;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Customer} entity
 */
public class CustomerDTO implements Serializable
{
    private final Integer id;
    @Size(max = 45)
    private final String name;
    private List<EmployeeDTO> employees;


    public CustomerDTO(String name)
    {
        this.id = 0;
        this.name = name;
    }

    public CustomerDTO(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public CustomerDTO(Customer customer)
    {
        this.id = customer.getId();
        this.name = customer.getName();
        List<Employee> employeeList = new ArrayList<>(customer.getEmployees());
        this.employees = EmployeeDTO.getDTOs(employeeList);
    }

    public static List<CustomerDTO> getDTOs(List<Customer> customerList)
    {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerList.forEach( c -> customerDTOList.add(new CustomerDTO((c))) );
        return customerDTOList;
    }

    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public List<EmployeeDTO> getEmployees()
    {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees)
    {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO entity = (CustomerDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }
}