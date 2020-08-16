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
public class Complain {
    private String Name;
    private String complaint;
    

    public Complain(String name, String complaint) {
        this.Name = name;
        this.complaint = complaint;
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * @return the complaint
     */
    public String getComplaint() {
        return complaint;
    }

    /**
     * @param complaint the complaint to set
     */
    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    /**
     * @return the gid
     */
   
}
