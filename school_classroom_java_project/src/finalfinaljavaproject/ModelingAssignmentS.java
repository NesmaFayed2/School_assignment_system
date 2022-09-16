/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfinaljavaproject;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author nesma
 */
public class ModelingAssignmentS extends JFrame implements ActionListener {
      JLabel l1,l2,l3,l4,l5;
      JTextField t1,t2,t3;
      JTextArea a1,a2;
      JButton b1,b2,b3,b4,b5;
      JPanel p1;
      JTable table;
       public static int  numofassignment ;
 
      Connection connection;
      Statement  statement;
        String s1=" ";

     
      
    public ModelingAssignmentS(){
         
         db_Connection();
         gui();
         filltable();
        
    
    }
    public void gui(){
        
         filltable();
        table.setBounds(10,20,560,400);
        table.setFont(new Font(null, Font.ROMAN_BASELINE, 15));
        add(table);
        
        l5=new JLabel("Enter assignment name to sumbit:-");
        l5.setBounds(10, 420,300,25);
        
        t3=new JTextField();
        t3.setBounds(10, 450,200,25);
        
        
        b5=new JButton("sumbit assignment");
        b5.setBounds(250, 430,200,50);
        b5.addActionListener(this);
      
        add(b5);
        add(l5);
        add(t3);
        
        
        setTitle("Asignment");
        setSize(600 ,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }
    
    public void filltable(){
       
           ArrayList<String> assignmentname_list =new ArrayList<>();
          ArrayList<String> deadline_list =new ArrayList<>();
          ArrayList<String> description_list =new ArrayList<>();
             ArrayList<String> studentanswer_list =new ArrayList<>();
  
          try {
              statement= connection.createStatement();
           ResultSet result=statement.executeQuery("SELECT * FROM my_db.assignment_data22;");
           
           while (result.next()){
              assignmentname_list.add(result.getString(2)) ;
              deadline_list.add(result.getString(3));
              description_list.add(result.getString(4));
              studentanswer_list.add(result.getString(6));
             
        
           }
           numofassignment=assignmentname_list.size();
             String [][]data=new String[assignmentname_list.size()][4];
               
          
             
             for(int i=0;i<assignmentname_list.size();i++){
                 data[i][0] = assignmentname_list.get(i);
                 data[i][1] = deadline_list.get(i);
                 data[i][2] = description_list.get(i);
                 data[i][3] = studentanswer_list.get(i);
       
             }
            
      
             
              String columns[] = {"assignmentname ", "deadline", "description","studentanswer"};
              table = new JTable(data, columns);
       
              
          } catch (SQLException ex) {
              System.out.println("error in table data");
          }
        
        
    }
    
    public static void main(String args[]){
           new ModelingAssignmentS();
          
       
        
    
    }
    
    private void db_Connection(){
    
          try {
              connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db","root","0000");
          } catch (SQLException ex) {
              System.out.println(" db_connection error ");
          }
 
    }
    
    
     public void insert(){
          try {
          
                
              statement.executeUpdate("UPDATE `my_db`.`assignment_data22` SET `studentanswer`= '"+s1+"' WHERE `name of assignment`='"+t3.getText()+"';");
              
                   
          } catch (SQLException ex) {
              System.out.println(" error in insert statement");
          }
    }
    
  

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b5){
         
     
        JFileChooser f2=new JFileChooser();
   
            int res2=f2.showSaveDialog(null);
            if(res2==JFileChooser.APPROVE_OPTION){
     
            File MyFile =new File(f2.getSelectedFile().getAbsolutePath());
            
                try {
                    FileInputStream file_input2 =new FileInputStream(MyFile);
                    int ch= file_input2.read();
                    while (ch!=-1){
                         s1+=(char)ch;         
                        ch=file_input2.read();
                      
                    }
                    System.out.println(s1);
                    insert();
                    dispose();
                    new ModelingAssignmentS();
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(OsAssignmentT.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(OsAssignmentT.class.getName()).log(Level.SEVERE, null, ex);
                }
   
        }
       
        }
        
      
    }
}
