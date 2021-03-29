package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class reader {

    public static List<List<String>> reader(String fileName){           //Carries through the parameter of which file we are reading from.
        List<List<String>> output = new ArrayList<>();                  //We will be storing the data in the files in a 2D Array List

        try{                                                            //Reading has to be done in a try-catch statement as there is a possibility that the
            FileReader file = new FileReader(fileName);                 //file cannot be traced, throwing an exception
            BufferedReader input = new BufferedReader(file);            //Here we open the file and set up our buffered reader
            String line;                                                //Initialising a variable which will allow us to read and append our data.

            while ((line = input.readLine()) != null) {                 //loops through every line in the csv until it finds an empty one
                String[] values = line.split(",");                //Splits up the line based on it's commas, allowing collection of each individual detail
                output.add(Arrays.asList(values));                     //To be appended into an array for that record, which is then added to our 2D list.
            }
            input.close();
        }

        catch(Exception e) {                                            //Checks for the getstacktrace exception
            e.getStackTrace();
        }
        return(output);                                                 //returns our 2d list.

    }

    public static void readPrinter(String fileName){
        List<List<String>> printList = reader.reader(fileName);
        System.out.println(Arrays.toString(create.detailTitles)+"\n");
        for (List<String> strings : printList) {
            System.out.println(strings);

        }
    }
}