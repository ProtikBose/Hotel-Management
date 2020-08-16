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
public class pending {
    private String name;
    private String gid;
    private String app;
    private String pay;
    private String amount;
    private String invid;

    public pending(String name, String gid, String app, String pay, String amount, String invid) {
        this.name = name;
        this.gid = gid;
        this.app = app;
        this.pay = pay;
        this.amount = amount;
        this.invid = invid;
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
     * @return the gid
     */
    public String getGid() {
        return gid;
    }

    /**
     * @param gid the gid to set
     */
    public void setGid(String gid) {
        this.gid = gid;
    }

    /**
     * @return the app
     */
    public String getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(String app) {
        this.app = app;
    }

    /**
     * @return the pay
     */
    public String getPay() {
        return pay;
    }

    /**
     * @param pay the pay to set
     */
    public void setPay(String pay) {
        this.pay = pay;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
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
    
}
