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
public class ModelingAssignmentT extends JFrame implements ActionListener {
      JLabel l1,l2,l3,l4,l5;
      JTextField t1,t2,t3;
      JTextArea a1,a2;
      JButton b1,b2,b3,b4,b5;
      JPanel p1;
      JTable table;
       public static int  numofassignment ;
 
      Connection connection;
      Statement  statement;
      static String m2=" ";
      
    public ModelingAssignmentT(){
         
         db_Connection();
         gui();
         filltable();
        
    
    }
    public void gui(){
        
         filltable();
        table.setBounds(10,250,560,200);
        table.setFont(new Font(null, Font.ROMAN_BASELINE, 15));
        add(table);
        
        l5=new JLabel("Enter assignmentname to view student answer:-");
        l5.setBounds(10, 470,300,25);
        
        t3=new JTextField();
        t3.setBounds(10, 495,200,25);
        
        
        b5=new JButton("VIEW STUDENT ANSWERS");
        b5.setBounds(300, 470,200,50);
        b5.addActionListener(this);
      
     
          
        l1=new JLabel("Assignment Name");
        l1.setBounds(10, 20,120,25);
        t1=new JTextField(20);
        t1.setBounds(120,20,165,25);

        l2=new JLabel("Deadline");
        l2.setBounds(10,50,80,25);
        t2=new JTextField(20);
        t2.setBounds(120,50,165,25);
          
        l3=new JLabel("Description");
        l3.setBounds(10,80,80,25);
        a1=new JTextArea();
        a1.setBounds(120,80,165,25);
        
      
        l4=new JLabel("Model Answer");
        l4.setBounds(10,110,120,25);
        b3=new JButton("UPLOAD FILE");
        b3.setBounds(120,110,165,25);
        b3.addActionListener(this);
                
                
        b1=new JButton("ADD");
        b1.setBounds(10,200,120,25);
        b1.addActionListener(this);
        
        
        b2=new JButton("DELETE");
        b2.setBounds(220,200,120,25);
        b2.addActionListener(this);
        
        b4=new JButton("UPDATE");
        b4.setBounds(430,200,120,25);
        b4.addActionListener(this);
  
        setBounds(100,110,165,25);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(a1);
        add(l4);
        add(b3);
        add(b1);
        add(b2);
        add(b4);
        add(b5);
        
        
        
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
          ArrayList<String> modelanswer =new ArrayList<>();
        
          
          try {
              
             
              statement= connection.createStatement();
          
           ResultSet result=statement.executeQuery("SELECT * FROM my_db.assignment_data22;");
           
           while (result.next()){
              assignmentname_list.add(result.getString(2)) ;
              deadline_list.add(result.getString(3));
              description_list.add(result.getString(4));
              modelanswer.add(result.getString(5));
              
           }
            
           numofassignment=assignmentname_list.size();
             String [][]data=new String[assignmentname_list.size()][4];
               
          
             
             for(int i=0;i<assignmentname_list.size();i++){
                 data[i][0] = assignmentname_list.get(i);
                 data[i][1] = deadline_list.get(i);
                 data[i][2] = description_list.get(i);
                 data[i][3]=modelanswer.get(i);

                 
             }
            
      
             
              String columns[] = {"assignmentname ", "deadline", "description","modelanswer"};
              table = new JTable(data, columns);
           
             
              
              
          } catch (SQLException ex) {
              System.out.println("error in table data");
          }
        
        
    }
    
    public static void main(String args[]){
           new ModelingAssignmentT();
           System.out.println(numofassignment);
       
        
    
    }
    
    private void db_Connection(){
    
          try {
              connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db","root","0000");
          } catch (SQLException ex) {
              System.out.println(" db_connection error ");
          }
       
    
    }
    
   private void insert(){
          try {
          
              statement.executeUpdate("INSERT INTO `my_db`.`assignment_data22` ( `name of assignment`, `deadline`, `description`, `modelanswer`, `studentanswer`) VALUES ('"+t1.getText()+"', '"+t2.getText()+"', '"+a1.getText()+"', '"+m2+"', ' ');");
            
          } catch (SQLException ex) {
              System.out.println(" error in insert statement");
          }
    }
   
   private void delete(){
          try {
              statement.executeUpdate("DELETE FROM `my_db`.`assignment_data22` WHERE `name of assignment`='"+t1.getText()+"';");
      
          } catch (SQLException ex) {
              System.out.println("error in delete");
          }
   
   }
   
   private void update(){
        try {
              statement.executeUpdate("UPDATE `my_db`.`assignment_data22` SET `name of assignment`='"+t1.getText()+"', `deadline`='"+t2.getText()+"', `description`='"+a1.getText()+"', `modelanswer`='"+m2+"' WHERE `name of assignment`='"+t1.getText()+"';");
          } catch (SQLException ex) {
              System.out.println("error in update");
          }
       
       
   }
   
    /* public  void search(){
       try {
                   statement= connection.createStatement();
                 ResultSet r = statement.executeQuery("SELECT * FROM my_db.login WHERE `username`='"+t3.getText()+"';");
                   while (r.next()){
                    
                 
           }
                   
                    
            } catch (SQLException ex) {
               System.out.println("error in search");
            }
    
    
    }*/
 @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b3){
            JFileChooser f=new JFileChooser();
   
            int res2=f.showSaveDialog(null);
            if(res2==JFileChooser.APPROVE_OPTION){
     
            File MFile =new File(f.getSelectedFile().getAbsolutePath());
            try {
                    FileInputStream file_input =new FileInputStream(MFile);
                    int ch= file_input.read();
                    while (ch!=-1){
                         m2+=(char)ch;         
                        ch=file_input.read();
                      
                    }
                    
                }  catch (IOException ex) {
                   System.out.println("error in reading 2");
                }
     
            }
     
        }else if (e.getSource()==b1){
            insert();
            dispose();
            new ModelingAssignmentT();
        }else if (e.getSource()==b2){
            delete();
             dispose();
            new ModelingAssignmentT();
            
        }else if(e.getSource()==b4){
           update();
           dispose();
          new ModelingAssignmentT();
        
        }else if(e.getSource()==b5){
        
                dispose();
            new ModelingAnswerView();
          
          
            
        }
        
      
    }
}
