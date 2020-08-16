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
public class payment {
    private String name;
    private String invid;
    private String bill;

    public payment(String name, String invid, String bill) {
        this.name = name;
        this.invid = invid;
        this.bill = bill;
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
     * @return the invid
     */
    public String getInvid() {
        return invid;
    }

    /**
     * @param invid the invid to set
     */
    public void setInvid(String invid) {
        this.invid = invid;
    }

    /**
     * @return the bill
     */
    public String getBill() {
        return bill;
    }

    /**
     * @param bill the bill to set
     */
    public void setBill(String bill) {
        this.bill = bill;
    }
    
}
