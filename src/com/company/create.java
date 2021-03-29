package com.company;

import java.util.Arrays;
import java.util.List;

import static com.company.Main.scanIn;


public class create {

    public static final String[] detailTitles = {"ID", "Name", "Desc", "Quantity"};
    public static final String[] userDetailTitles = {"ID","Username","Password","Name","User Level"};
    private static final String[] detailVerify={"admin","user"};
    private static String[] arrayChosen;
    private static String[] details;
    private static void arrayChooser(String fileName){
        /*We need to select which arrays we are working with, which is dependant on whether we are creating a game
          or a piece of music. As the filename is passed through these functions, we will check if it is the game
          or music file and then set our arrays to the necessary ones.
         */

        if(fileName.equals(Main.inventoryFileName)){
            arrayChosen=detailTitles;
            details =new String[detailTitles.length];
        }else{
            arrayChosen=userDetailTitles;
            details =new String[userDetailTitles.length];
        }


    }

    public static void addItem(String fileName) {
        arrayChooser(fileName);

        for (int i = 0; i < arrayChosen.length; i++) {
            System.out.println(arrayChosen[i]);             //Informs the user which detail they are being asked to enter

            String append = detailVerify(i);

            details[i] = append;                            //appending to our array before we call the writer function to append it to the csv.


        }
        historyLog.historyLogger(details,"creator",fileName);
        write.writer(details,fileName);
    }


    public static void editItem(int foundAt, List<List<String>> editList,String fileName,boolean stockChange,boolean personalChange){
        /*

        Used to edit any details within a record. The parameters passed through will be to inform the function whether just the stock is going to be changed
        or if they are just changing their own details.

        */


        String editDetail;
        String originator="editor"; //Sets our originator so the history log knows where the change came from.
        arrayChooser(fileName);  //Uses the filename to select whether we are editing a record or a user.
        int detailIgnorerLower=0;
        int detailIgnorerUpper=0;
        String[] editedRecord=new String[arrayChosen.length];

        for(int i = 0; i < editList.get(foundAt).size(); i++){
            editedRecord[i]= editList.get(foundAt).get(i);        //Creates an array with all the values of the record/user we want to edit
        }
        if(personalChange){
            detailIgnorerLower=2;   //If the user is going to edit their own data, we only want them to edit their name or their password
            detailIgnorerUpper=1;   //So a bound is set to ignore those other values in the array.
        }


        if(stockChange) {
            System.out.println("Would you like to edit the "+ arrayChosen[3]+" to?");
            editDetail = scanIn.nextLine().toUpperCase();//If only stock is to be edited, we need not loop through the array,
            //we just edit the quantity record and
            originator="stock changer";
            editedRecord[3]=editDetail;
            editList.get(foundAt).set(3, editDetail);

        }
        else {
            /*
            Here we loop through the array, allowing the user the ability to change all the values they have access to.
            They are prompted to say if they want to edit it, when editing we verify the details with the detailVerify
            function and if it is not valid, it is repeated until a valid input is entered
             */

            for (int i = detailIgnorerLower; i < editList.get(foundAt).size()-detailIgnorerUpper; i++) {
                System.out.println("Would you like to edit the " + arrayChosen[i] + "? Y/N");
                String editApprove = scanIn.nextLine().toUpperCase();
                boolean check = false;
                while (!check) {
                    if (editApprove.equals("Y")) {

                        System.out.println("What would you like to change the value of " + arrayChosen[i] + " to?");
                        editDetail = detailVerify(i);

                        editList.get(foundAt).set(i, editDetail);
                        editedRecord[i]=editDetail;
                        check = true;
                    } else if (editApprove.equals("N")) {
                        check = true;
                    } else {

                        System.out.println("Please enter a valid option. Y/N");
                        editApprove = scanIn.nextLine().toUpperCase();
                    }
                }
            }
        }

        historyLog.historyLogger(editedRecord,originator,fileName); //Calls the history logger with what was changed, what changed it, and what file was changed.
        write.rewrite(editList,fileName);

    }

    public static String detailVerify(int i){
        /*
        A verification function to make sure that what the user is entered is valid. In the case of user level, there should only ever be
        admin or user and ID and Quantity should always be a number. T
         */
        String detail = scanIn.nextLine();
        boolean verified=false;
        while(!verified) {
            if (arrayChosen[i].equals("User Level")){
                if(Arrays.asList(detailVerify).contains(detail)){
                    verified=true;
                }else {
                    System.out.println("Please enter a valid " + arrayChosen[i]);             //Informs the user which detail they are being asked to enter
                    detail = scanIn.nextLine();
                }
            }
            else if(arrayChosen[i].equals("ID") ||arrayChosen[i].equals("Quantity")) {
                int integerVerify = menuDecider.checkInt(detail);
                if(integerVerify>=0){
                    verified=true;
                }else{
                    System.out.println("Please enter a valid " + arrayChosen[i]);             //Informs the user which detail they are being asked to enter
                    detail = scanIn.nextLine();
                }

            }else{
                verified=true;
            }
        }
        return detail;
    }
}