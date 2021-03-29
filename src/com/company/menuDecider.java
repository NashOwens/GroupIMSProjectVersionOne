package com.company;

import static com.company.Main.scanIn;





public class menuDecider {


    public static int menuDecide(int max){
        boolean intCheck = false;
        String menuOption = scanIn.nextLine();                          //Ask the user which option they would like
        int menuInt=checkInt(menuOption);                               //Here we call the function to check if what they entered was an int

        while(!intCheck){
            if(menuInt>max || menuInt<=0){                              //If the entered option is higher than the max number of options or
                System.out.println("Please enter a valid option.");     //lower than 1, it will inform the user that their
                menuOption = scanIn.nextLine();                         //option was invalid and ask for another input.
                menuInt=checkInt(menuOption);                           //As we have asked for another input, we must check if we can parse an
                // int again.
            }else{
                intCheck=true;                                          //If it is in the correct range, we set out escape boolean to true before returning.
            }
        }
        return(menuInt);                                                //We return, taking the user's option with us as a return value now that it has been validated.
    }




    public static Integer checkInt(String stringConvert) {
        try {
            return Integer.parseInt(stringConvert);                     //If it can parse an integer from the entered string, it will
        } catch (NumberFormatException e) {                             //return that in an int variable, if it cannot, it will return
            return(-9);                                                 //my error code of 999, as there are no menus with 999 options,
            //it will always be an invalid option.
        }
    }




}
