package com.company;


import java.util.Scanner;

public class Main {
    public static Scanner scanIn = new Scanner(System.in);
    public static String inventoryFileName="inventory.csv";
    private static final String accessCodeFilename="accessCodes.csv";
    public static String userLoggedInUserName;
    public static String userLoggedInName;
    public static void main(String[] args) {
        int menuChoice;
        boolean running;
        /*The userAccess function will return either a 1 or a 2 if the user has entered valid credentials
        if they have not, then the program will not end.
        */
        int userLevel = userAccess.login(0,reader.reader(accessCodeFilename));
        if(userLevel==1||userLevel==2){
            running = true;

        }else{
            running = false;
        }
        /*
        Two different switch case menus, one for an admin and the other for a regular user.
         */

        while(running){
            if(userLevel==1){
                System.out.println(
                        "**** Admin Menu ****\n" +
                                "1. View Inventory\n" +
                                "2. Search Inventory\n" +
                                "3. Change stock of an item\n"+
                                "4. Edit your data\n"+
                                "5. Add Item\n"+
                                "6. Add user\n"+
                                "7. Search users\n"+
                                "8. View history log\n"+
                                "9. Exit"
                );
                menuChoice = menuDecider.menuDecide(9);
                switch (menuChoice) {
                    case 1 -> reader.readPrinter(inventoryFileName);
                    case 2 -> searcher.search(inventoryFileName,userLevel,false,false);
                    case 3 -> searcher.search(inventoryFileName,userLevel,true,false);
                    case 4 -> searcher.search(accessCodeFilename,userLevel,false,true);
                    case 5 -> create.addItem(inventoryFileName);
                    case 6 -> create.addItem(accessCodeFilename);
                    case 7 -> searcher.search(accessCodeFilename,userLevel,false,false);
                    case 8 -> reader.readPrinter("HistoryLog");
                    case 9 -> {
                        System.out.println("Goodbye");
                        running = false;

                    }
                }
            }else{
                System.out.println(
                        "**** Menu ****\n" +
                                "1. View Inventory\n" +
                                "2. Search Inventory\n" +
                                "3. Change stock of an item.\n"+
                                "4. Edit your data\n"+
                                "5. Exit"

                );
                menuChoice = menuDecider.menuDecide(5);
                switch (menuChoice) {
                    case 1 -> {
                        reader.readPrinter(inventoryFileName);

                    }
                    case 2 -> searcher.search(inventoryFileName,userLevel,false,false);
                    case 3 -> searcher.search(inventoryFileName,userLevel,true,false);
                    case 4 -> searcher.search(accessCodeFilename,userLevel,false,true);
                    case 5 -> {
                        System.out.println("Goodbye");
                        running = false;

                    }
                }

            }



        }
    }
}