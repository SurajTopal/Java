/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_application;

/**
 *
 * @author Suraj Topal
 */
class User {
    private String transactionid,title,date,time,username;
    private int amount;   
      User(String transactionid,String title,String date,String time, String username,int amount)
     {
        this.transactionid=transactionid;
        this.title=title;
        this.date=date;
        this.time=time;
        this.username=username;
        this.amount=amount;
     }

     public String gettransactioonid()
     {
        return transactionid;
     }
      
     public String gettitle()
     {
        return title;
     }
       
     public String getdate()
     {
        return date;
     }
        
     public String gettime()
     {
        return time;
     }
     
     public String getusername()
     {
        return username;
     }
          
     public int getamount()
     {
        return amount;
     }
          
      
      
}
