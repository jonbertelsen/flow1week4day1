/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
  /*      FacadeExample fe = FacadeExample.getFacadeExample(emf);
        fe.create(new RenameMeDTO(new RenameMe("First 1", "Last 1")));
        fe.create(new RenameMeDTO(new RenameMe("First 2", "Last 2")));
        fe.create(new RenameMeDTO(new RenameMe("First 3", "Last 3")));
*/
        EmployeeFacade employeeFacade = EmployeeFacade.getInstance(emf);
  /*
        employeeFacade.createEmployee("Anders And", 25000);
        employeeFacade.createEmployee("Fætter Højben", 28000);
        employeeFacade.createEmployee("Joachim Von And", 28000);
    */
        employeeFacade.addCustomer(5, "Bent Ole");
        employeeFacade.addCustomer(5, "Svend Bent");


    }
    
    public static void main(String[] args) {
        populate();
    }
}
