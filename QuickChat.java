
package userregistration;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;


public class QuickChat 
{
    String messageID;
    int messageNumber;
    String recipient;
    String message;
    String messageHash;
    String messageStatus;
    
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();
    
    //Where the messages are stored
    static ArrayList<QuickChat> sentMessages = new ArrayList<>();
    
    //Keeps track of the amount of messages that are sent
    static int totalMessages = 0;
    
    //Keeps track of the message number
    static int nextMessageNumber = 1;
    
    //QuickChat menu
    public void startQuickChat()
    {
      int quickChoice = 0;
      while(quickChoice != 3)
       {  
        System.out.println(" ");
        System.out.println("==== QUICKCHAT MENU ====");
        System.out.println("1. Send Message");
        System.out.println("2. Coming Soon");
        System.out.println("3. Quit");
        System.out.println(" ");
        
        System.out.print("Please select an option: ");
        
        //Make sure the user enters a number
        while(!input.hasNextInt())
        {
            System.out.println(" ");
            System.out.print("Please enter an option: ");
            input.nextLine();
            System.out.println("Please select an option");
        }
        
        quickChoice = input.nextInt();
        input.nextLine();
        
        switch(quickChoice)
        {
            case 1:
                sendMessage();
                break;
                
            case 2:
                System.out.println("Coming Soon!");
                break;
                
            case 3:
                System.out.println("Thank you for using QuickChat!");
                break;
                
            default:
                System.out.println("Invalid option.");
                break;
        }
      }
    }
    
    public void sendMessage()
    {
        int numberOfMessages;
        int count = 0;
        int num = 1; 
        
        ArrayList<QuickChat> processedMessages = new ArrayList<>();
        int sentCount = 0;
        int storedCount = 0;
        int disregardedCount = 0;
        
        System.out.print("How many messages would you like to send? ");
                
        while(!input.hasNextInt())
        {
            input.nextLine();
            System.out.println(" ");
            System.out.println("Please enter a number");
            System.out.println("How many messages would you like to send?");

        }
        
            numberOfMessages = input.nextInt();
            input.nextLine();
        
            
            //Number must be greater than 0
            while(numberOfMessages <= 0)
            {
                System.out.println(" ");
                System.out.println("Please enter a number greater than 0");
                System.out.println("How many messages would you like to send?");
            
                while(!input.hasNextInt())
                {
                    input.nextLine();
                    System.out.println("Please enter a number");
                    System.out.println("How many messages would you like to send? ");
                } 
                
            numberOfMessages = input.nextInt();
            input.nextLine();
            
            }
        
        //Create each message    
        while(count < numberOfMessages)
        {
           QuickChat newMessage = new QuickChat();
            
            System.out.println(" ");
            System.out.println("==== MESSAGE " + (num + count) + " ====");
           
            //Give the message a number
            newMessage.messageNumber = nextMessageNumber;
            nextMessageNumber++;
            
            //Generate unique message ID
            newMessage.checkMessageID();
            
            System.out.println(" ");
            System.out.println("Message ID: " + newMessage.messageID);
            
            
        
        //Recipient cellphone number
            System.out.println(" ");
            System.out.println("Enter recipient cellphone number: ");
            System.out.println("- It must include the international code +27");
            System.out.println(" ");
            
            //.trim() removes extra spaces at the beginning and at the end of a String
            newMessage.recipient = input.nextLine().trim();
            
            while(!newMessage.checkRecipientCell().equals("Cellphone number is successfully captured"))
            {
                System.out.println(" ");
                System.out.println("Recipient cellphone number is incorrectly formatted.");
                System.out.println("It must start with +27 and contain the correct number of digits");
                
                System.out.println(" ");
                System.out.println("Please enter the recipient cellphone number again: ");
                
                newMessage.recipient = input.nextLine().trim();
            
            }
            
            System.out.println(" ");
            System.out.println("The recipient's number has been successfully captured");

            //Ask the user to enter the message
            System.out.println(" ");
            System.out.println("Enter your message: ");
            newMessage.message = input.nextLine();
            
            //Makes count increment by one
            count ++;
            
            //Make sure th emessage is no longer than 250 characters
            while(newMessage.message.length() > 250)
            {
                System.out.println(" ");
                System.out.println(
                                    "Your message exceeds the 250 character limit by " 
                                    + (newMessage.message.length() - 250)
                                    + " characters."
                                   );
                System.out.println("Please enter your message again: ");
                newMessage.message = input.nextLine();
            }
            
                System.out.println(" ");
                System.out.println("Message ready to send.");
                
                //Create message hash
                newMessage.createMessageHash();
                
                System.out.println(" ");
                System.out.println("Message Hash: " + newMessage.messageHash);
                
                boolean processed = false;
                
                //Sent/Disregard/Store
                while(!processed)
                {
                System.out.println("==== LET'S COMMUNICATE ====");
                System.out.println(" ");
                System.out.println("What would you like to do?");

                System.out.println("1. Send Message");
                System.out.println("2. Disregard Message");
                System.out.println("3. Store Message");
                System.out.println(" ");
                
                System.out.print("Please select an option: ");
                
                while(!input.hasNextInt())
                {
                    System.out.println("Please enter a number");
                    input.nextLine();
                    System.out.println("Please select an option: ");
                }
                
                int messageChoice = input.nextInt();
                input.nextLine();
                               
                switch(messageChoice)
                {
                    case 1:
                        System.out.println(" ");
                        System.out.println("==== SEND MESSAGE ====");
                        System.out.println(" ");
                        
                        newMessage.messageStatus = "Sent";
                        sentMessages.add(newMessage);
                        totalMessages++;
                        System.out.println(newMessage.SentMessage(input));
                        count++;
                        sentCount++;
                        processed = true;
                        break;


                    case 2:
                        System.out.println(" ");
                        System.out.println("==== DISREGARD MESSAGE ====");
                        System.out.println(" ");
                        
                        newMessage.messageStatus = "Disregarded";
                        
                        System.out.println(" ");
                        System.out.println("Message disregarded");
                        count++;
                        disregardedCount++;
                        processed = true;
                        break;

                    case 3:    
                        System.out.println(" ");
                        System.out.println("==== STORE MESSAGE ====");
                        System.out.println(" ");                 
                        System.out.println(newMessage.storeMessage());
                        count++;
                        storedCount++;
                        processed = true;
                        break;
                        
                    default:
                        System.out.println(" ");
                        System.out.println("Invalid option"); 
                        System.out.println("Message was not processed");
                        //Since it wasn't processed it wasn't counted
                        count--;
                        break;
                }
              //  count++;
              }
    
            //After all the messages have been processed
            System.out.println("==== SUMMARY ====");
            System.out.println(" ");
            
            for(int i = 0; i < processedMessages.size(); i++)
            {
                QuickChat m = processedMessages.get(i);
            System.out.println(" ");
            System.out.println("---- Message " + (i + 1) + " ----");
            System.out.println("Message ID : " + m.messageID);
            System.out.println("Message Hash: " + m.messageHash);
            System.out.println("Recipient   : " + m.recipient);
            System.out.println("Message     : " + m.message);
            System.out.println("Status      : " + m.messageStatus);
            }
            
        System.out.println(" ");
        System.out.println("==============================");
        System.out.println("Messages processed : " + count);
        System.out.println("Messages sent      : " + sentCount);
        System.out.println("Messages stored    : " + storedCount);
        System.out.println("Messages disregarded: " + disregardedCount);
        System.out.println("Total messages sent (all time): " + returnTotalMessage());
        System.out.println("==============================");
        }
    }

                  
    public boolean checkMessageID()
    {
       Random random = new Random();
       boolean unique;
       
       do
       {
       messageID = "";
       
       for(int i = 0; i < 10; i++)
       {
           messageID = messageID + random.nextInt(10);
       }
       
       unique = true;
       
       //Check whether ID already exists
       for(int i = 0; i < sentMessages.size(); i++)
       {
           if(sentMessages.get(i).messageID.equals(messageID))
           {
               unique = false;
               break;
           }
          }
       }while(!unique);
       return messageID.length() == 10;
         
       }  

    
    public String checkRecipientCell()
    {
        String text;
        
        if(recipient != null
                && recipient.startsWith("+27") 
                && recipient.length() == 12 
                && recipient.substring(1).matches("[0-9]+"))
        {
           text = "Cellphone number is successfully captured";
           checkMessageID();
        }
        else 
        {
           text = "Cellphone number is incorrectly formatted";
        }  
        
        return text;
    }
    
    public String createMessageHash() //JSON file
    {
        String trimmedMessage = message.trim();
        
        if(trimmedMessage.isEmpty())
        {
            messageHash = 
                    messageID.substring(0,2) 
                    + ":" 
                    + messageNumber 
                    + ":";
            
            return messageHash;
        }
        
        String[] words = trimmedMessage.split("\\s+");
        
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
       messageHash = 
               messageID.substring(0,2) 
               + ":" 
               + messageNumber 
               + ":" 
               + firstWord 
               + lastWord;
       
       messageHash = messageHash.toUpperCase();
       
       return messageHash;
    }
    
    public String SentMessage(Scanner input)
    {
        
        String messageSent = "Message sent succesfully";
        
        return messageSent;
    }
    
    public String printMessages()
    {
        
        StringBuilder output = new StringBuilder();
        
        if(sentMessages.isEmpty())
        {
            String noMessage;
            
            noMessage = "No messages have been sent";
            
            return noMessage;
        }
        for (int i = 0; i < sentMessages.size(); i++)
        {
            if(sentMessages.get(i).messageStatus.equals("Sent"))
            {
            output.append("Message ID: ")
                  .append(sentMessages.get(i).messageID);
                   
            output.append("\nMessage Hash: ")
                  .append(sentMessages.get(i).messageHash);
            
            output.append("\nRecipient: ")
                  .append(sentMessages.get(i).recipient);
            
            output.append("\nMessage: ")
                  .append(sentMessages.get(i).message)
                  .append("\n\n");
            }
        }
        
        return output.toString();
    }
    
    public int returnTotalMessage()
    {
        return totalMessages;
    }
    
    public String storeMessage()
    {
        messageStatus = "Stored";
        
        //Add message to the ArrayList
        sentMessages.add(this);
        
        //Store messages in JSON file
        String fileName = "messages.json";
        
        try{
            FileWriter writer = new FileWriter(fileName, true);
            
            //Simple 
        
            writer.write("{\n");
            
            writer.write(" \"messageID\": \""
            + escapeJson(messageID)
            + "\",\n"
            );
            
            writer.write(
            " \"messageHash\": \""
            + escapeJson(messageHash)
            + "\",\n"
            ); 
            
            writer.write(
            " \"recipient\": \""
            + escapeJson(recipient)
            + "\",\n"
            );
                 
            writer.write(
            " \"message\": \""
            + escapeJson(message)
            + "\",\n"
            );
            
            writer.write("}\n");
            writer.close();
            
           return "Message stored successfully";
        
        }catch(IOException e)
        {
            return "Error storing message: " + e.getMessage();
        }            
    }

    private String escapeJson(String text)
    {
       if(text == null)
       {
           return "";
       } 
       
       return text
               .replace("\\", "\\\\")
               .replace("\"", "\\\"")
               .replace("\n", "\\n")
               .replace("\r", "\\r")
               .replace("\t", "\\t");
    }
    
}


//When user selects:
//- option number one - Program must ask the user how many messages they want to send (In numeric values. Must be prompted again if anything else is entered again)
//                    - Each message must be awarded a unique ID for tracking (Must be prompted till the number they asked for is finished)
//                    - While with a IF statement for the incorrect character limit
//- option number two -


//You have to utilise an arraylist not an array

//
