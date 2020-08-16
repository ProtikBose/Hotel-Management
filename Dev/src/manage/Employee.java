/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;



/**
 *
 * @author PRANTO
 */
public class Employee {
    
    private String name;
    private String emp_id;
    private String email;
    private String manager_id;
    private String phone;
    private String address;
    private String emp_job;
    private String join_date;

    public Employee(String name, String emp_id, String email, String manager_id, String phone, String address, String emp_job, String join_date) {
        this.name = name;
        this.emp_id = emp_id;
        this.email = email;
        this.manager_id = manager_id;
        this.phone = phone;
        this.address = address;
        this.emp_job = emp_job;
        this.join_date = join_date;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the emp_id
     */
    public String getEmp_id() {
        return emp_id;
    }

    /**
     * @param emp_id the emp_id to set
     */
    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the manager_id
     */
    public String getManager_id() {
        return manager_id;
    }

    /**
     * @param manager_id the manager_id to set
     */
    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the emp_job
     */
    public String getEmp_job() {
        return emp_job;
    }

    /**
     * @param emp_job the emp_job to set
     */
    public void setEmp_job(String emp_job) {
        this.emp_job = emp_job;
    }

    /**
     * @return the join_date
     */
    public String getJoin_date() {
        return join_date;
    }

    /**
     * @param join_date the join_date to set
     */
    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }
    
    
}
