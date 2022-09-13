package facades;

import dtos.EmployeeDTO;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class EmployeeFacade
{
    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}

    public static EmployeeFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EmployeeDTO getEmployeeById(int id)
    {
        EntityManager em = getEntityManager();
        try
        {
            Employee e = em.find(Employee.class, id);
            if (e != null)
            {
                return new EmployeeDTO(e);
            }
            else
            {
                return null;
            }
        }
        finally
        {
            em.close();
        }
    }

    public List<EmployeeDTO> getEmployeesByName(String name)
    {
        EntityManager em = getEntityManager();
        try
        {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
            query.setParameter("name", name);
            return  EmployeeDTO.getDTOs(query.getResultList());
        }
        finally
        {
            em.close();
        }
    }

    public List<EmployeeDTO> getAllEmployees()
    {
        EntityManager em = getEntityManager();
        try
        {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return EmployeeDTO.getDTOs(query.getResultList());
        }
        finally
        {
            em.close();
        }
    }

    //
    public List<EmployeeDTO> getEmployeesWithHighestSalary()
    {
        EntityManager em = getEntityManager();
        try
        {
            TypedQuery<Employee> query = em.createQuery(
                    "SELECT e FROM Employee e where e.salary = (select max(e.salary) FROM Employee e)", Employee.class);
            return EmployeeDTO.getDTOs(query.getResultList());
        }
        finally
        {
            em.close();
        }
    }


    public EmployeeDTO createEmployee(String name, int salary_per_month)
    {
        EntityManager em = getEntityManager();
        try
        {
            if (getEmployeesByName(name).size() > 0)
            {
                throw new WebApplicationException("Employee already exists with name: " + name);
            }
            Employee newEmployee = new Employee(name, salary_per_month);
            em.getTransaction().begin();
                em.persist(newEmployee);
            em.getTransaction().commit();
            return new EmployeeDTO(newEmployee);
        }
        finally
        {
            em.close();
        }

    }
}
