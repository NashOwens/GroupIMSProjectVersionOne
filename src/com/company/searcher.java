package com.company;

import java.util.List;


import static com.company.Main.scanIn;
import static com.company.Main.userLoggedInUserName;


public class searcher {
    public static void search(String fileName,int userLevel,boolean stockChange,boolean personalChange){
        String titleCheck;
        List<List<String>> searchList = reader.reader(fileName);                            //To search through our records, we must first read them.
        if(!personalChange) {
            System.out.println("Search for what record ?");                                 //Asking the user which title they want to find.
            titleCheck = scanIn.nextLine();
        }else{
            titleCheck=userLoggedInUserName;
        }

        boolean found=false;

        int foundAt = 0;

        for(int i=0;i<searchList.size();i++){                                               //We loop through each record in our list
            if(searchList.get(i).get(1).equals(titleCheck)){                                //Checking if the title is equal any title in the list
                System.out.println("Here are the details for " + titleCheck +":");          //If found, it then prints out each
                for(int i2=0;i2<searchList.get(0).size();i2++){ //detail
                    if(fileName.equals(Main.inventoryFileName)){
                        System.out.println(create.detailTitles[i2] + ": " + searchList.get(i).get(i2));
                    }else{
                        System.out.println(create.userDetailTitles[i2] + ": " + searchList.get(i).get(i2));
                    }

                }

                foundAt=i;                                                                  //Before storing the index of where that record was found and breaking out of the loop.
                found=true;
                break;
            }
        }

        if(!found){                                                                         //If found was never set to true, the record was never found so we inform them here.
            System.out.println("Out of the " + searchList.size() +
                    " records we have, we couldn't find it! Returning to the main menu!\n");
        }else{
            if(stockChange){
                create.editItem(foundAt,searchList,fileName, true,personalChange);
            }else if(personalChange){
                create.editItem(foundAt,searchList,fileName, false, true);
            }
            else if (userLevel == 1) {
                foundMenu(foundAt, searchList, fileName);                                   //If we did find it, we then call the function that displays the edit/delete menu
            }


        }


    }



    private static void foundMenu(int foundAt, List<List<String>> searchList,String fileName){
        int menuChoice;

        System.out.println(
                """
                        **** Menu ****
                        1. Edit record
                        2. Delete record
                        3. Exit to Main Menu
                        """
        );
        menuChoice = menuDecider.menuDecide(3);                                          //Call the menu function with parameter of maximum options, in this case 3

        switch (menuChoice) {
            case 1 -> {
                System.out.println("Editing");                                               //If  they wish to edit, we call the edit function, passing through which file
                create.editItem(foundAt,searchList,fileName,false,false);               //and at what index the record was found
            }                                                                               //The same applies for the delete function.
            case 2 -> {
                String[] deletedItem= searchList.get(foundAt).toArray(new String[0]);
                historyLog.historyLogger(deletedItem,"deleter",fileName);
                searchList.remove(foundAt);                                                 //We use the list remove function to remove that record from the list
                write.rewrite(searchList,fileName);                                         //Then we call our rewrite function, passing through the filename and the new list so we can overwrite the file
                System.out.println("Deleted!");
            }
            case 3 -> System.out.println("Returning to main menu.");
        }

    }
}
