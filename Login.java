 
package userregistration;
import java.util.Scanner;

class Login       
{
    QuickChat Object = new QuickChat();
    Scanner input = new Scanner(System.in);
    
    String[] usernames = new String[10];
    String[] passcodes = new String[10];
    String[] names = new String[10];
    String[] surnames = new String[10];
    
    int userCount = 0;
    int loggedInUser = -1;
            
    public boolean checkUserName(String username)
    {
        return username.contains("_") && username.length() <= 5;
    }  
    
     public static boolean checkPasswordComplexity(String password)
    {
        if (password.length() < 8){
          return false;  
        }
        
        boolean hasCapitalLetter = false;
        boolean specialCharacter = false;
        boolean hasNumber = false;
        
        for(int i = 0; i < password.length(); i++)
        {
            char ch = password.charAt(i);
            
            if(Character.isUpperCase(ch)){
                hasCapitalLetter = true;
            }
            
            if(Character.isDigit(ch)){
                hasNumber = true;
            }
            
            if(!Character.isLetterOrDigit(ch)){
                specialCharacter = true;
            }
        }   
        return password.length() >= 8 && hasCapitalLetter && hasNumber && specialCharacter;
    }
    
    public static boolean checkCellPhoneNumber(String cellphoneNumber)
        {
           if(cellphoneNumber.startsWith("+27") && cellphoneNumber.length() == 12)
           {
               return true;
           }
           
           else
           {
               return false;
           }
        }
        
        public String registerUser(String username, String password, String name, String surname)
        {
           
               while(!checkUserName(username))
               {
               //\n for next line
               //! = not 
               //no sout because it returns void and we do not want to return void
                   System.out.println("Username is correctly formatted");
                   System.out.println("Please insure that your username contains: ");
                   System.out.println("\t - A underscore");
                   System.out.println("\t - No more that 5 characters");
                   
                   System.out.println(" ");
                   System.out.println("Please enter your username again: ");
                   username = input.nextLine();
                      
               }
         
           
           if(!checkPasswordComplexity(password))
           {
               //\n for next line
               //! = not 
               //no sout because it returns void and we do not want to return void
                   System.out.println("Password is incorrectly formatted");
                   System.out.println("Please insure that your password contains: ");
                   System.out.println("\t - A capital letter");
                   System.out.println("\t - A numeric value");
                   System.out.println("\t - A special Character");
           }
            
           usernames[userCount] = username;
           passcodes[userCount] = password;
           names[userCount] = name;
           surnames[userCount] = surname;
           
           userCount++;
           
            return " " + "User has been registered successfully";
        }
        
        public boolean loginUser(String username, String password)
        {
            for (int i = 0; i < userCount; i++)
            {
                //equal() = checks that things are the same
                if (usernames[i].equals(username) && passcodes[i].equals(password))   
                {
                 loggedInUser = i;
                 return true;   
                }
                else if(!usernames[i].equals(username) && passcodes[i].equals(password))
                {
                    System.out.println("Your username is incorrect");
                    System.out.print("Please try again: ");
                    username = input.nextLine();
                    return false;
                }  
                else if(usernames[i].equals(username) && !passcodes[i].equals(password))
                {
                    System.out.println("Your password is incorrect");
                    System.out.print("Please try again: ");
                    password = input.nextLine();
                    return false;
                }    
                else if(!usernames[i].equals(username) && !passcodes[i].equals(password))
                {
                    System.out.println("Your password and username are incorrect");
                    System.out.println("Please try again!");
                    System.out.println("Username: ");
                    username = input.nextLine();
                    System.out.println("Password: ");
                    password = input.nextLine();
                    return false;
                }    
            }
        return false;   
        }
        
        public String returnLoginStatus(boolean loginSuccessful, String surname, String name)
        {String message;
           // for (int i = 0; i < userCount; i++)
           // {    
                if (loginSuccessful)
                {
                        System.out.println(" ");
                        message="Login Sucessful!\nWelcome " +
                                names[loggedInUser] + " " + surnames[loggedInUser] +
                                "\nIt is great to see you again!";
                
                        System.out.println(" ");
                        System.out.println("Opening QuickChat...");
                        
                        Object.startQuickChat();
                }    
                else
                {
                     System.out.println(" ");
                     message= "Login Unsucessful!";
                     }
         return message;
       }
} 


