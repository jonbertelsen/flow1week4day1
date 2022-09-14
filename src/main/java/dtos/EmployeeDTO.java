package dtos;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeDTO
{
    private int id;
    private String name;
    private int salary;
//    private String customers;


    public EmployeeDTO(Employee employee)
    {
        this.id = employee.getId();
        this.name = employee.getName();
        this.salary = employee.getSalary();
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getSalary()
    {
        return salary;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof EmployeeDTO)) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return id == that.id && salary == that.salary && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, salary);
    }

    public static List<EmployeeDTO> getDTOList(List<Employee> employeeList)
    {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeList.forEach( e -> employeeDTOList.add(new EmployeeDTO(e)) );
        return employeeDTOList;
    }


}
