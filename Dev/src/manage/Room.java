/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PRANTO
 */
public class Room {
    private final SimpleStringProperty roomNum;
    
    private final SimpleStringProperty empNum;
    private final SimpleStringProperty roomType;
    private final SimpleStringProperty price;
    
    public Room(String rmnum,String emid,String rtype,String pr){
        this.roomNum=new SimpleStringProperty(rmnum);
        
        this.empNum=new SimpleStringProperty(emid);
        this.roomType=new SimpleStringProperty(rtype);
        this.price=new SimpleStringProperty(pr);
    }
    
    public String getRoomNum(){
        return roomNum.get();
    }
    
    
    
    public String getEmpNum(){
        return empNum.get();
    }
    
    public String getRoomType(){
        return roomType.get();
    }
    
    public String getPrice(){
        return price.get();
    }
    
    public void setRoomNum(String rmnum){
        roomNum.set(rmnum);
    }
    
    
    
    public void setEmpNum(String emid){
        empNum.set(emid);
    }
    
    public void setRoomType(String rtype){
        roomType.set(rtype);
    }
    
    public void setPrice(String pr){
        price.set(pr);
    }
}
