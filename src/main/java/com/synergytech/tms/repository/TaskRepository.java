/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.synergytech.tms.repository;

import com.synergytech.tms.model.Task;
import javax.ejb.Stateless;


@Stateless
public class TaskRepository extends BaseRepository<Task, Long>{
    
    
    public TaskRepository(){
        super(Task.class);
    }
    
    
    
    
}
