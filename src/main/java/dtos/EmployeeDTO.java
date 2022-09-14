package dtos;

import entities.Customer;
import entities.Employee;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Employee} entity
 */
public class EmployeeDTO implements Serializable
{
    private final Integer id;
    @Size(max = 45)
    private final String name;
    private final Integer salary;
    private List<CustomerDTO> customers;

    public EmployeeDTO(Integer id, String name, Integer salary)
    {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public EmployeeDTO(Employee employee)
    {
        this.id = employee.getId();
        this.name = employee.getName();
        this.salary = employee.getSalary();
        List<Customer> customerList = new ArrayList<>(employee.getCustomers());
        customers = CustomerDTO.getDTOs(customerList);

    }

    public static List<EmployeeDTO> getDTOs(List<Employee> employeeList)
    {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeList.forEach( e -> employeeDTOList.add(new EmployeeDTO((e))) );
        employeeDTOList.forEach( e -> e.getCustomers().forEach( c -> c.setEmployees(null) ));
        return employeeDTOList;
    }

    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Integer getSalary()
    {
        return salary;
    }

    public List<CustomerDTO> getCustomers()
    {
        return customers;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO entity = (EmployeeDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.salary, entity.salary);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, salary);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "salary = " + salary + ")";
    }
}