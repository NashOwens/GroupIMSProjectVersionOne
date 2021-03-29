package com.company;

import java.util.List;

import static com.company.Main.scanIn;

public class userAccess {
    public static int definedAttempts=3;
    public static int login(int attempt, List<List<String>> accessCodes){
        /*
        A user login system, where they get 3 attempts to login with their details, if a login is successful, then their user
        level is returned(1 for admin, 2 for user). However, if it fails a null value is returned so that they cannot access the system.
        This function makes use of recursion in order to give the user multiple tries to login.
         */


        System.out.println(accessCodes);
        System.out.println("Please enter your username.");
        String userName=scanIn.nextLine();
        System.out.println("Please enter your password.");
        String userPassword=scanIn.nextLine();

        String accessLevel;
        for (List<String> accessCode : accessCodes) {
            if (accessCode.get(1).equals(userName)) {
                if (accessCode.get(2).equals(userPassword)) {
                    Main.userLoggedInName = accessCode.get(3);
                    System.out.println("Login Successful. Welcome " + Main.userLoggedInName);
                    Main.userLoggedInUserName = accessCode.get(1);
                    accessLevel = accessCode.get(4);
                    if (accessLevel.equals("admin")) {
                        return 1;
                    } else {                //Returns the corresponding user level
                        return 2;
                    }
                }
            }
        }

        attempt+=1;
        if(attempt==definedAttempts){           //Triggers when the user has ran out of attempts, returning the 0
            System.out.println("Login Failed");
            return 0;
        }


        else{ //If the user still has attempts left, the system will inform them how many and recall the function.
            System.out.println("Incorrect User Code\nYou have "+(3-attempt)+" more attempt(s)");
            int returnValue = login(attempt,accessCodes);
            if(returnValue==1){         //As the user may have entered the correct details on the second try, the stack would return here
                //Hence why we need another return statement based on that result.
                return 1;
            }else if(returnValue==2){

                return 2;
            }else{
                return 0;
            }
        }
    }
}

