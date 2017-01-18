package client;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.*;

/**
 *
 * @author Armin
 */
public class LoginGUI extends JPanel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Textfelder und Dropdowns
    private JTextField userID;
    private JComboBox abteilung;
    //Labels
    private JLabel userIDL;
    private JLabel abteilungL;
    private JLabel invisLabel;
        
    //Buttons
    private JButton anmelden;
    
    //Style panels
    private JPanel start;
    private JPanel mid;
    private JPanel end;
    
    private BorderLayout bLayout1;
    private BorderLayout bLayout2;
    
    
    public void initialize(String[] abteilungen){
    	
    	bLayout1 = new BorderLayout();
    	bLayout1.setHgap(2);
    	bLayout1.setVgap(2);
    	bLayout2 = new BorderLayout();
    	bLayout2.setHgap(2);
    	bLayout2.setVgap(2);
        //Baue Panel zusammen
    	
    	start = new JPanel();
    	mid = new JPanel();
    	end = new JPanel();
    	
    	start.setLayout(bLayout1);
    	mid.setLayout(bLayout2);
    	

    	userID = new JTextField(6);
    	abteilung = new JComboBox(abteilungen);
    	userIDL = new JLabel("Benutzer ID");
    	abteilungL = new JLabel("Abteilung");
    	anmelden = new JButton("anmelden");
    	invisLabel = new JLabel("   ");
    	
    	start.add(userIDL, BorderLayout.PAGE_START);
    	start.add(userID, BorderLayout.PAGE_END);
    	
    	mid.add(abteilungL, BorderLayout.PAGE_START);;
    	mid.add(abteilung, BorderLayout.PAGE_END);
    	
    	end.add(invisLabel);
    	end.add(anmelden);
    	
    	this.add(start);
    	this.add(mid);
    	this.add(end);
    }    
}

