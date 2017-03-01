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

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
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
        valid.setCaption("EMAIL IS VALID");
        invalid.setCaption("EMAIL IS INVALID!");
        valid.setVisible(false);
        invalid.setVisible(false);
        empty.setVisible(false);

        require.setCaption("REQUIREMENTS TO VALIDATE:");
        rule1.setCaption("1. MUST have one @ character.");
        rule2.setCaption("2. MUST have atleast one '.' character.");
        rule3.setCaption("3. MUST use gmail domain ONLY.");
        rule4.setCaption("4. MUST have between 6 and 12 characters(NOT INCLUDING @gmail.com)");
        email.setCaption("Enter your email address:");
        empty.setCaption("EMPTY FIELD");
        

        Button button = new Button("Validate");
        button.addClickListener( e -> {
			if(!email.getValue().isEmpty()){
				System.out.println(val.validate(email.getValue()));
				if(val.validate(email.getValue()) == 4){
					valid.setVisible(true);
					invalid.setVisible(false);
					empty.setVisible(false);
					//val.setNum(0);
					return;
				}else{
					invalid.setVisible(true);
					valid.setVisible(false);
					empty.setVisible(false);
					//val.setNum(0);
					return;
				}
			}else{
				invalid.setVisible(false);
				valid.setVisible(false);
				empty.setVisible(true);
				//val.setNum(0);
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
