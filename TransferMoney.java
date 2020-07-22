/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_application;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Suraj Topal
 */
public class TransferMoney extends javax.swing.JFrame {
    public void bankBalanceUpdate(String accountno,String email,int senderbalance,int receiverbalance)
    {
        String url="jdbc:mysql://localhost:3306/demo";
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection(url,"root","");
           String update_Receiverbalance="update demo.bankform set bankbalance='"+receiverbalance+"' where accountno='"+accountno+"'";
           String update_Senderbalance="update demo.bankform set bankbalance='"+senderbalance+"' where email='"+email+"'";
           Statement st=con.createStatement();
           st.executeUpdate(update_Receiverbalance);
           st.executeUpdate(update_Senderbalance);
          // int c=st.executeUpdate(update_login);
          // int c1=st.executeUpdate(update_register);
          // System.out.println(c+" updated in login , "+c1+" updated in register  ...");
           st.close();
           con.close();
        }
        catch(SQLException | NullPointerException|ClassNotFoundException e)
        {
          System.out.println(e);
        }
    }
    public String transactiondate()
    {       
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	
        Date date = new Date();
	String transactiondate=(formatter.format(date)); // It will give date of the transaction..
        return transactiondate;
    }
    
    //This fuction give current time..
    public String time()
     {
    ZoneId zoneId = ZoneId.of("Asia/Kolkata");
    LocalTime localTime=LocalTime.now(zoneId);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    String Time=localTime.format(formatter);
      return Time;
     }
    
    /**
     * Creates new form TransferMoney
     */
    public TransferMoney() {
        initComponents();
        warning.setVisible(false);
        name.setVisible(false);
        
    }
    void sendMoney(int sendmoney,String accOfPay,String sendername,String accOfReceiver,String receivername)
    {
        Random random=new Random();
       String url="jdbc:mysql://localhost:3306/demo";
       String ReceiverTable="user"+accOfReceiver;
       String PayTable="user"+accOfPay;
       String transactionid="TRANS"+(random.nextInt(8888)+1111);
       String time=time();     // current Time.. 
       String date=transactiondate();
       int receivedpayment=sendmoney;
       System.out.println("table name : "+ReceiverTable+"\n transactionid : "+transactionid+"\n time : "+time +" date : "+date +"\n Received money : "+receivedpayment);
       System.out.println("Receivername : "+receivername);
       String Receiver="insert into "+ReceiverTable+" (transactionid,title,date,time,username,amount) values (?,?,?,?,?,?)";
       String Pay="insert into "+PayTable+" (transactionid,title,date,time,username,amount) values (?,?,?,?,?,?)";
         
       try
       {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection(url,"root","");
        // Receiver data insert..    
            PreparedStatement ps=con.prepareStatement(Receiver);
            ps.setString(1, transactionid);
            ps.setString(2,"Received");
            ps.setString(3, date);
            ps.setString(4,time);
            ps.setString(5,sendername);
            ps.setInt(6,receivedpayment);
            ps.executeUpdate();
            System.out.println("Insert : "+Receiver);
            ps.close();
         //Receiver data inserted..   
         
         //Paid data inserted..
            PreparedStatement p=con.prepareStatement(Pay);
            p.setString(1, transactionid);
            p.setString(2,"Paid");
            p.setString(3, date);
            p.setString(4,time);
            p.setString(5,receivername);
            p.setInt(6,receivedpayment);
            p.executeUpdate();
            p.close();
            con.close();
       }
       catch(Exception e )
       {
          System.out.println("Exception  : "+e);
       }
    }
     void database()
    {
       String  accountno=accountnotxt.getText();
       String ifsccode=ifsccodetxt.getText();
       String fetch_bankform="select * from demo.bankform where accountno ='"+accountno+"' and ifsccode ='"+ifsccode+"'";  // who is receiving..
       String fetch_bankformforbankbalance="select * from demo.bankform where email='"+senderemail+"'";    // who is paying..check balance..
       System.out.println("Query : "+ fetch_bankform);
       System.out.println("Query 2: "+fetch_bankformforbankbalance);      
       String url="jdbc:mysql://localhost:3306/demo";
       String receivername="";   // user name show to whom we are sending money
       String sendername="";
       String accOfReceiver="";
       int senderbalance=0,receiverbalance=0; 
       String accOfPay="";
      
       //int receivedbankbalance=0;   //bankbalance who is receiving the money...
       int sendmoney=0;  
       String receivedemail=""; // Receivedemail is showing the the email to whom we are sending money..
       int row;
       try
       {
          Class.forName("com.mysql.jdbc.Driver");
          Connection con=DriverManager.getConnection(url,"root","");
          Statement stmt=con.createStatement();
          ResultSet rt=stmt.executeQuery(fetch_bankform);
     
          rt.last();
          row=rt.getRow();
          System.out.println("row : "+row);
          if(row==1)
          {  
            ResultSet r=stmt.executeQuery(fetch_bankform);
            r.next();
            receivedemail=r.getString("email");
            accOfReceiver=r.getString("accountno");
            receiverbalance=r.getInt("bankbalance");
            System.out.println("Email : "+r.getString("email"));
            warning.setText("Account Checked");
            warning.setForeground(Color.green);
            warning.setVisible(true);
            
          }
          else
          {
            warning.setText("Invalid Account Details..");
            warning.setForeground(Color.red);
            warning.setVisible(true);
          }
          rt.close();
          ResultSet resultset=stmt.executeQuery(fetch_bankformforbankbalance);
          resultset.next();
          senderbalance=resultset.getInt("bankbalance");
          accOfPay=resultset.getString("accountno");
          //Fetch Receiver name....
          String fetch_receivername="select * from demo.register where email ='"+receivedemail+"'"; 
          ResultSet rs=stmt.executeQuery(fetch_receivername);
          rs.next();
          receivername=rs.getString("firstname")+" "+rs.getString("lastname");
          name.setText(receivername);
          name.setVisible(true);
          System.out.println("Received email : "+receivedemail+"\n Paidbankbalance : "+senderbalance +" \n Receiver money : "+receiverbalance+"\n Receiver name : "+receivername);
          
          //Fetch Sender name...
          String fetch_sendername="select * from demo.register where email ='"+senderemail+"'";
          ResultSet r=stmt.executeQuery(fetch_sendername);
          r.next();
          sendername=r.getString("firstname")+" "+r.getString("lastname");
        if(amounttxt.getText().equals(""))
        {
           JOptionPane.showMessageDialog(TransferMoney.this,"Enter the amount..");
        }  
        else
        {   
            sendmoney=Integer.parseInt(amounttxt.getText());
                if(sendmoney<=senderbalance)
                 {
                     System.out.println("money sent");
                     senderbalance=senderbalance-sendmoney;
                     receiverbalance+=sendmoney;
                     accountnotxt.setEditable(false);
                     ifsccodetxt.setEditable(false);
                      JOptionPane.showMessageDialog(TransferMoney.this,"sent money..");
                         // String accountno,String email,int receiverbalance,int senderbalance 
                      sendMoney(sendmoney,accOfPay,sendername,accOfReceiver,receivername);
                       
                      bankBalanceUpdate(accOfReceiver,senderemail,senderbalance,receiverbalance);
                     dispose();
                 }
                 else
                 {
                     JOptionPane.showMessageDialog(TransferMoney.this,"insufficient balance..");
                 }
        }        
         con.close();
       }
       catch(HeadlessException | ClassNotFoundException | NumberFormatException | SQLException e)
       {
          System.out.println("Exception : "+e);
       }

    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        warning = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        ifsccodetxt = new javax.swing.JTextField();
        accountnotxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        send = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        amounttxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(540, 462));
        setMinimumSize(new java.awt.Dimension(540, 462));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(540, 462));
        jPanel1.setMinimumSize(new java.awt.Dimension(540, 462));

        warning.setBackground(new java.awt.Color(255, 255, 255));
        warning.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        warning.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warning.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));
        warning.setOpaque(true);

        name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        name.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));

        ifsccodetxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ifsccodetxt.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));

        accountnotxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        accountnotxt.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("IFSC Code");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Account No.");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));

        send.setBackground(new java.awt.Color(51, 153, 255));
        send.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        send.setText("send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Amount");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));

        amounttxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        amounttxt.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Transfer payment");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountnotxt, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(ifsccodetxt, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(amounttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(send)))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {accountnotxt, ifsccodetxt});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel5});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(warning, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(accountnotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ifsccodetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amounttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {accountnotxt, ifsccodetxt, jLabel3, jLabel4, jLabel5});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
      // this function check the account no , ifsc code... and show user name then check bank balance..
      database();
    }//GEN-LAST:event_sendActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransferMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransferMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransferMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransferMoney.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TransferMoney().setVisible(true);
        });
    }

    public String senderemail=Login.email.getText();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountnotxt;
    private javax.swing.JTextField amounttxt;
    private javax.swing.JTextField ifsccodetxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel name;
    private javax.swing.JButton send;
    private javax.swing.JLabel warning;
    // End of variables declaration//GEN-END:variables
}
