package junit;

/*
* Adapted from: https://www.tutorialspoint.com/junit/junit_ignore_test.htm
* T. Hoshino 8 Feb 2017
* This class prints the given message on console.
*/

public class MessageUtil {

    private String message;

    //Constructor
    //@param message to be printed
    public MessageUtil(String message){
        this.message = message;
    }

    // prints the message
    public String printMessage(){
        System.out.println(message);
        return message;
    }

    // add "Hi!" to the message
    public String salutationMessage(){
        message = "Hi!" + message;
        System.out.println(message);
        return message;
    }

}