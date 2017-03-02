package com.app.email_validator_ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Theme("runo")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //initializing objects
    	final VerticalLayout layout = new VerticalLayout();
        final TextField email = new TextField();
        final Label require = new Label();
        final Label valid = new Label();
        final Label invalid = new Label();
        final Label empty = new Label();
        final Label rule1 = new Label();
        final Label rule2 = new Label();
        final Label rule3 = new Label();
        final Label rule4 = new Label();
        validate val = new validate();
        
        //setting captions and setting them as false to only appear when needed
        valid.setCaption("EMAIL IS VALID");
        invalid.setCaption("EMAIL IS INVALID!");
        valid.setVisible(false);
        invalid.setVisible(false);
        empty.setVisible(false);

        //requirements
        require.setCaption("REQUIREMENTS TO VALIDATE:");
        rule1.setCaption("1. MUST have one @ character.");
        rule2.setCaption("2. MUST have atleast one '.' character.");
        rule3.setCaption("3. MUST use gmail.com domain ONLY.");
        rule4.setCaption("4. MUST have between 6 and 12 characters(NOT INCLUDING @gmail.com)");
        email.setCaption("Enter your email address:");
        empty.setCaption("EMPTY FIELD");
        
        //validation happens when only validate button has been pressed
        Button button = new Button("Validate");
        button.addClickListener( e -> {
        	//checks if field is empty
			if(!email.getValue().isEmpty()){
				if(val.validate(email.getValue()) == 4){//4 because we want all 4 rules met
					valid.setVisible(true);
					invalid.setVisible(false);
					empty.setVisible(false);
					return;
				}else{
					invalid.setVisible(true);
					valid.setVisible(false);
					empty.setVisible(false);
					return;
				}
			}else{
				invalid.setVisible(false);
				valid.setVisible(false);
				empty.setVisible(true);
			}
			
		});
        	
        
        layout.addComponents(require, rule1, rule2, rule3, rule4, 
        		email, button, valid, invalid, empty);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
