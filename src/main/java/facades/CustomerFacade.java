package facades;

import dtos.CustomerDTO;
import dtos.EmployeeDTO;
import entities.Customer;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class CustomerFacade
{
    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {}

    public static CustomerFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<CustomerDTO> getCustomerByName(String name)
    {
        EntityManager em = getEntityManager();
        try
        {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.name = :name", Customer.class);
            query.setParameter("name", name);
            return  CustomerDTO.getDTOs(query.getResultList());
        }
        finally
        {
            em.close();
        }
    }

    public CustomerDTO createCustomer(String name)
    {
        EntityManager em = getEntityManager();
        try
        {
            if (getCustomerByName(name).size() > 0)
            {
                throw new WebApplicationException("Customer already exists with name: " + name);
            }
           Customer newCustomer = new Customer(name);
            em.getTransaction().begin();
            em.persist(newCustomer);
            em.getTransaction().commit();
            return new CustomerDTO(newCustomer);
        }
        finally
        {
            em.close();
        }
    }


}
