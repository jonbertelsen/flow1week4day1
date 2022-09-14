package dtos;

import entities.Customer;

import java.util.Objects;

public class CustomerDTO
{
    private int id;
    private String name;

    public CustomerDTO(Customer customer)
    {
        this.id = customer.getId();
        this.name = customer.getName();
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        CustomerDTO that = (CustomerDTO) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getName());
    }
}
