package com.app.email_validator_ui;



import java.util.ArrayList;
import java.util.regex.Pattern;


public class validate 
{
	//to keep track of number of rules the email passed
    private static int num=0;

    public int getNum(){
    	return num;
    }
    
    public static void main(String[]args){
    	String test = "123456543201@gmail.com";
    	System.out.println(validate(test));
    }
    
    public static int validate(String mail){
    	//array to store characters
    	char[] words = new char[mail.length()];
    	char[] rule = {'@', 'g', 'm', 'a', 'i', 'l', '.', 'c', 'o', 'm'}; 
    	
    	//stores mail characters into array
    	for(int i=0; i< mail.length(); i++){
    		words[i] = mail.charAt(i);
    	}
    	
    	//RULE: MUST use @gmail.com
    	if(mail.matches(".*@gmail.com")){
    		num++;
    	}

    	/*
    	 * RULE: # of character must be greater or equal to 6
    	 * AND less or equal to 16
    	*/
    	if(mail.length() >= 16 && mail.length() <= 22){
    		num++;
    	}
    	
    	
    	//RULE: checker for @
    	for(int k=0; k < mail.length(); k++){
    		if(words[k] == '@'){
    			num++;
    		}
    	}
    	//RULE: checker for .
    	for(int k=0; k < mail.length(); k++){
    		if(words[k] == '.'){
    			num++;
    		}
    	}
    	return num;
   }
}
