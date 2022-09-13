package facades;

import entities.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeFacadeTest
{
    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;
    private Employee e1, e2, e3;


    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = EmployeeFacade.getInstance(emf);
    }

    @BeforeEach
    void setUp()
    {
        facade = EmployeeFacade.getInstance(emf);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Query query = em.createQuery("delete from Employee e");
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();

        e1 = facade.createEmployee("Anders And", 25000);
        e2 = facade.createEmployee("Fætter Højben", 28000);
        e3 = facade.createEmployee("Joachim Von And", 30000);
    }

    @Test
    void getEmployeeById()
    {
        Employee actual = facade.getEmployeeById(e1.getId());
        Employee expected = e1;
        assertEquals(expected, actual);
    }

    @Test
    void getEmployeesByName()
    {
        List<Employee> actual = facade.getEmployeesByName("Joachim Von And");
        assertEquals(1, actual.size());
        assertEquals(e3, actual.get(0));
    }

    @Test
    void getAllEmployees()
    {
        List<Employee> actual = facade.getAllEmployees();
        assertEquals(3, actual.size());
        assertThat(actual, containsInAnyOrder(e1, e2, e3));
    }

    @Test
    void getEmployeesWithHighestSalary()
    {
        List<Employee> actual = facade.getEmployeesWithHighestSalary();
        assertEquals(1, actual.size());
        assertEquals(e3, actual.get(0));
    }

    @Test
    void createEmployee()
    {
        Employee e4 = facade.createEmployee("Andersine", 45000);
        List<Employee> actual = facade.getAllEmployees();
        assertEquals(4, actual.size());
        assertThat(actual, containsInAnyOrder(e1, e2, e3, e4));
    }
}