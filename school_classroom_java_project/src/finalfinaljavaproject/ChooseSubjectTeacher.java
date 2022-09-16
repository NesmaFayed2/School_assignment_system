/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalfinaljavaproject;

/**
 *
 * @author nesma
 */
import javax.swing.*;    
import java.awt.event.*;
public class ChooseSubjectTeacher extends JFrame implements ActionListener{

    JFrame f;    
     ChooseSubjectTeacher(){    
    f=new JFrame("Choosing subject for teacher ");   
    final JLabel label = new JLabel();          
    label.setHorizontalAlignment(JLabel.CENTER);  
    label.setSize(400,100);  

   
    String subject[]={"os","modeling"};        
    final JComboBox cb=new JComboBox(subject);   
    cb.setBounds(50, 100,90,20);  
    
    
      
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cb.getSelectedIndex() ==0){
                  new OsAssignmentT();
                }else if(cb.getSelectedIndex() ==1){
                    
                 new  ModelingAssignmentT();
                }
            }
        });


    f.add(cb); f.add(label);  
    f.setLayout(null);    
    f.setSize(350,350);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    f.setVisible(true);

  
}         
        public static void main(String[] args){
            
            new ChooseSubjectTeacher();     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
    }

    
}

