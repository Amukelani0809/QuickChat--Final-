
package userregistration;

import java.util.Scanner;
import java.util.ArrayList;

public class UserRegistration 
{

    static Scanner input = new Scanner(System.in);
    static String surname, name;
    static String cellphoneNumber = " ";
    static String password = " ";
   
    public static void main(String[] args) 
    {
     String username;
     int choice;
     boolean loginSuccessful = false;
     
    Login Object = new Login();
    
    System.out.println("==== WELCOME TO QUICKCHAT ====");
    System.out.println(" ");
    
//    System.out.println("1. Sign Up");
//    System.out.println("2. Login");
//    System.out.println(" ");    
//    System.out.print("Which service would you like to access: ");
//    choice = input.nextInt();
//    input.nextLine();
//    
//    switch(choice)
//    {
//    case 1:

    System.out.println(" ");
    System.out.println("==== Sign Up ====");
    System.out.println(" ");
    System.out.print("Name: ");
    name = input.nextLine();
  
    System.out.print("Surname: ");
    surname = input.nextLine();
    System.out.println(" ");
    System.out.println("Please enter a username: ");
    System.out.println("It must include:");
    System.out.println("\t - A underscore");
    System.out.println("\t - No more that 5 characters");
    username = input.nextLine();
   
    
         if (Object.checkUserName(username))
         {
             System.out.println(" ");
             System.out.println("Username successfully captured");  
         }

         else
         {
            System.out.println(" ");
             while(!Object.checkUserName(username))
               {
                   System.out.println(" ");
                   System.out.println("Username is correctly formatted");
                   System.out.println("Please insure that your username contains: ");
                   System.out.println("\t - A underscore");
                   System.out.println("\t - No more that 5 characters");
                   System.out.println(" ");
                   System.out.print("Please enter your username again: ");
                   username = input.nextLine();   
               } 
         }
    
    if (Login.checkPasswordComplexity(password))
         {
             System.out.println(" ");
             System.out.println("Password successfully captured");  
         }

         else
         {
             System.out.println(" ");
             while(!Login.checkPasswordComplexity(password))
               {
                   //\t = tab
                   System.out.println(" ");
                   System.out.println("Please enter your password");
                   System.out.println("It must include:");
                   System.out.println("\t - At least eight characters"); 
                   System.out.println("\t - A capital letter");
                   System.out.println("\t - A numeric value"); 
                   System.out.println("\t - A special character");
                   password = input.nextLine();
                   System.out.println(" ");
                   System.out.print("Please enter your password again: ");
                   password = input.nextLine();   
               } 
         }
    
    
    if (Login.checkCellPhoneNumber(cellphoneNumber))
         {
             System.out.println(" ");
             System.out.println("Cellphone number was successfully added");  
         }

         else
         {   
             while(!Login.checkCellPhoneNumber(cellphoneNumber))
               {
                   System.out.println(" ");
                   System.out.println("Cellphone number");
                   System.out.println("It must include:");
                   System.out.println("\t - The international code +27");
                   cellphoneNumber = input.nextLine();
                   System.out.println(" ");
                   System.out.print("Please enter your cellphone number again: ");
                   cellphoneNumber = input.nextLine();   
               } 
             
             System.out.println(" ");
             System.out.println(Object.registerUser(username, password, name, surname));
         }
         
         System.out.println(" ");
        System.out.println("Would you like to do anything else?");
        int loginChoice;
        
        System.out.println(" ");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println(" ");
        System.out.print("What would you like to do? ");
        loginChoice = input.nextInt();
        input.nextLine();
        
            switch(loginChoice)
            {
                case 1:
                    System.out.println("==== Login in ====");
                    System.out.println(" ");
                    
                    System.out.print("Username: ");
                    username = input.nextLine();
                  
                    System.out.print("Password: ");
                    password = input.nextLine();
                    System.out.println(" ");
                    

                    loginSuccessful = Object.loginUser(username, password);
                    System.out.println(Object.returnLoginStatus(loginSuccessful, surname, name));             
                    break;
                    
                case 2:
                    System.out.println("==== Goodbye ====");
                    System.out.println("Thank you for Signing up! We hope to see you next time.");
                    break;
                    
                default:
                System.out.println("A invalid character was entered");
            }
        
//    break;
//    
//    case 2:
//    
//    System.out.println(" ");
//    System.out.println("==== Login in ====");
//    
//    System.out.println(" ");
//    System.out.print("Username: ");
//    username = input.nextLine();
//   
//    
//    System.out.print("Password: ");
//    password = input.nextLine();
//    System.out.println(" ");
//    
//    loginSuccessful = Object.loginUser(username, password);
//    System.out.println(Object.returnLoginStatus(loginSuccessful, surname, name)); 
//
//    System.out.println("==== Goodbye ====");
//    System.out.println("Thank you for Signing up! We hope to see you next time.");
//    break;
//    
//    default:
//    System.out.println("A invalid character was entered");Amukel
  }  
}
