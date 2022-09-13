package dtos;

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
    }

    public static List<EmployeeDTO> getDTOs(List<Employee> employeeList)
    {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeList.forEach( e -> employeeDTOList.add(new EmployeeDTO((e))) );
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