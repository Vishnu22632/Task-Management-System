//
//package com.synergytech.tms.converter;
//
//import com.synergytech.tms.model.Task;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//
//
//public class TaskStatusConverter implements Converter<Task.TaskStatus>{
//
//    @Override
//    public Task.TaskStatus getAsObject(FacesContext context, UIComponent component, String value) {
//        
//        if(value==null || value.isEmpty()){
//            return null;
//        }
//
//        return Task.TaskStatus.valueOf(value);
//
//    }
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Task.TaskStatus value) {
//        if(value==null){
//            return "";
//        }
//        
//        return value.name();
//
//
//    }
//    
//}
