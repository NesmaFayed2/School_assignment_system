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
public class OsAnswerView extends JFrame implements ActionListener {
      JLabel l1,l2,l3,l4,l5;
      JTextField t1,t2,t3;
      JTextArea a1,a2;
     static JTextArea a3;
      JButton b1,b2,b3,b4,b5;
      JPanel p1;
      JTable table;
       public static int  numofassignment ;
 
      Connection connection;
      Statement  statement;
       String s3="";
      
    public OsAnswerView(){
         
         db_Connection();
         gui();
         filltable();
        
    
    }
    public void gui(){
        
         filltable();
        table.setBounds(10,20,200,400);
        table.setFont(new Font(null, Font.ROMAN_BASELINE, 15));
        add(table);
        
        l5=new JLabel("Enter assignment name to view  file:-");
        l5.setBounds(10, 420,400,25);
        
        t3=new JTextField();
        t3.setBounds(10,450,200,25);
        
        a3=new JTextArea();
        a3.setBounds(220, 20, 350,400);
        
        
        
        
        b5=new JButton("view file");
        b5.setBounds(250, 430,200,50);
        b5.addActionListener(this);
      
        add(b5);
        add(l5);
        add(t3);
        add(a3);
        
        
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
           ResultSet result=statement.executeQuery("SELECT * FROM my_db.assignment_data ;");
           
           while (result.next()){
              assignmentname_list.add(result.getString(2)) ;
         
              studentanswer_list.add(result.getString(6));
              
              
           
           }
           
           numofassignment=assignmentname_list.size();
             String [][]data=new String[assignmentname_list.size()][2];
               
          
             
             for(int i=0;i<assignmentname_list.size();i++){
                 data[i][0] = assignmentname_list.get(i);
                 data[i][1] = studentanswer_list.get(i);
       
             }
            
      
             
              String columns[] = {"assignmentname ","studentanswer"};
              table = new JTable(data, columns);
       
              
          } catch (SQLException ex) {
              System.out.println("error in table data");
          }
        
        
    }
    
    public static void main(String args[]){
           new OsAnswerView();
          
           
       
        
    
    }
    
    private void db_Connection(){
    
          try {
              connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db","root","0000");
          } catch (SQLException ex) {
              System.out.println(" db_connection error ");
          }
       
    
    }
    
    
     /*private void insert(){
          try {
              statement.executeUpdate("UPDATE `my_db`.`assignment_data` SET `studentanswer`='"+s2+"' WHERE `name of assignment`='"+t3.getText()+"';");
                   
          } catch (SQLException ex) {
              System.out.println(" error in insert statement");
          }
    }*/
    
           public  void search(){
       try {
                   statement= connection.createStatement();
                 ResultSet r = statement.executeQuery("SELECT * FROM my_db.assignment_data WHERE `name of assignment`='"+t3.getText()+"';");
                   while (r.next()){
                  
                  s3=r.getString(6);
                  a3.setText(s3);
              
                 
           }
                   
                    
            } catch (SQLException ex) {
               System.out.println("error in search");
            }
    
    
    }
    
    
  

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b5){
            System.out.println("nesma");  
         search();
      
   
      
            
        }
          
           
          
            
        }
        
      
    }

