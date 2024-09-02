
package com.synergytech.tms.repository;

import com.synergytech.tms.model.Task;
import javax.ejb.Stateless;


@Stateless
public class TaskRepository extends BaseRepository<Task, Long>{
    
    
    public TaskRepository(){
        super(Task.class);
    }

    public void delete(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}
