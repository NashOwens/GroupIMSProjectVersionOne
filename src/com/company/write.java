package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.List;


public class write{
    public static void writer( String[] itemDetails,String fileName){

        try {

            FileWriter file = new FileWriter(fileName,true); //Opens our file writer
            BufferedWriter output = new BufferedWriter(file);


            for(int i = 0; i< itemDetails.length; i++) {            //For each value in the array, we write it to the file, separated by a comma.
                output.write((itemDetails[i] + ","));

            }
            output.newLine();                                       //Adds a new line for the next record before closing our writer
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }


    }

    public static void rewrite(List<List<String>> rewriteList,String fileName){
        try {
            FileWriter file = new FileWriter(fileName);                                     //Opening our filewriter.
            BufferedWriter output = new BufferedWriter(file);
            for(int i = 0; i< rewriteList.size(); i++) {                                    //We loop through our 2D list to get each record and then we
                for(int i2 = 0; i2< rewriteList.get(i).size(); i2++) {                      //Loop through each record in order to
                    output.write((rewriteList.get(i).get(i2)+ ","));                        //write that to the file
                    if((i2== rewriteList.get(i).size()-1)){
                        output.newLine();                                                   //When a record is finished, we start a new line, for the next record.
                    }
                }
            }
            output.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }



}