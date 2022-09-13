package dtos;

import entities.Employee;

import javax.ws.rs.WebApplicationException;
import java.util.Objects;

public class EmployeeDTO
{
    private int id;
    private String name;
    private int salary;

    public EmployeeDTO(int id, String name, int salary)
    {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public EmployeeDTO(Employee employee)
    {
        if (employee != null)
        {
            this.id = employee.getId();
            this.name = employee.getName();
            this.salary = employee.getSalary();
        } else
        {
           throw new WebApplicationException("Trying to convert null");
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof EmployeeDTO)) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
