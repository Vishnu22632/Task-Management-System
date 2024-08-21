package com.synergytech.tms.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String userRole;
    
    @Column(nullable = false)
    private String address;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fullName=" + fullName + ", email=" + email + ", password=" + password + ", userRole=" + userRole + ", address=" + address + '}';
    }

    
    
    
    
}



//<datasource jta="true" jndi-name="java:/jboss/datasources/tmsDS" pool-name="tmsDS" enabled="true" use-ccm="true">
//                    <connection-url>jdbc:mysql://localhost:3306/tmsdb</connection-url>
//                    <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
//                    <driver>mysql</driver>
//                    <security>
//                        <user-name>vishnu</user-name>
//                        <password>mahi07</password>
//                    </security>
//                    <validation>
//                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
//                        <background-validation>true</background-validation>
//                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
//                    </validation>
//                </datasource>











