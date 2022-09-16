/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfinaljavaproject;

/**
 *
 * @author nesma
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
    JPanel p = new JPanel();
    JFrame f = new JFrame();
    JLabel l;
    JLabel pass;
    JTextField t ;
    JPasswordField passf;
    JButton button1;
    JRadioButton checkbuttton1;
    JRadioButton checkbuttton2;
    String user="teacher";
    
     Connection connection;
     Statement  statement;
    public Login(){
     f.setSize(300 ,300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(p);
        p.setLayout(null);
        f.setLocation(500,200);
        
        l=new JLabel("user");
        l.setBounds(10, 20,120,25);
        p.add(l);
        
        t=new JTextField(25);
        t.setBounds(100,20,165,25);
        p.add(t);
        
        pass=new JLabel("password");
        pass.setBounds(10,50,80,25);
        p.add(pass);
        
        
        passf=new JPasswordField(25);
        passf.setBounds(100,50,165,25);
        p.add(passf);
        
         button1=new JButton("login");
        button1.setBounds(10, 80, 80, 25);
        p.add(button1);
        button1.addActionListener(this);
        
        
        checkbuttton1=new JRadioButton("Teacher");
       checkbuttton1.setBounds(10, 100, 80, 25);
       p.add(checkbuttton1);
       checkbuttton1.addActionListener(this);
          
          
          
         checkbuttton2 = new JRadioButton("Student");
        checkbuttton2.setBounds(10, 120, 80, 25);
        p.add(checkbuttton2);
        checkbuttton2.addActionListener(this);
          
          
        
         f.setVisible(true);

    }
    

    
    
    public static void main(String args[]){
        new Login();
       
    }
    
     private void db_Connection(){
    
          try {
              connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db","root","0000");
          } catch (SQLException ex) {
              System.out.println(" db_connection error ");
          }
       
    
    }
     

 
    @Override
    public void actionPerformed(ActionEvent e) {
        
         
           if (e.getSource() == checkbuttton1) {
              user="teacher";
           }
            if (e.getSource() == checkbuttton2) {
              user="student";
           }
        
        if (e.getSource() == button1) {
             
         String name=t.getText();
           
         String password=passf.getText();
        
          try {
              db_Connection();
               statement=connection.createStatement();
            
               ResultSet result=statement.executeQuery("SELECT * FROM my_db.login WHERE username='"+name+"' and password='"+password+"';");
               
               if(result.next()){
             if(user =="teacher")
             { 
              new ChooseSubjectTeacher();
                dispose();}
             else if(user =="student")
             {  
                  new ChooseSubjectstudent1();
                dispose();
             }
            
        
           }else {
                JOptionPane.showMessageDialog(this, "username or password is incorrect");
                t.setText("");
                passf.setText("");
               }
    
              
          } catch (SQLException ex) {
              System.out.println("ERROR IN TABLE DATA");
          }
                
        
        
                        }
   
    }
    
}
