package com.company;

import java.util.Arrays;

public class historyLog {
    public static void historyLogger(String[] historyDetails,String origin,String affectedFile){
        /*
        Creates an array of data to be stored in a CSV detailing what has happened in the program. It stores what happened,
        where it happened, what caused it to happen and who made the change.
        */
        String fileName = "HistoryLog";
        historyDetails= Arrays.copyOf(historyDetails,historyDetails.length+4);
        historyDetails[historyDetails.length-4]=origin;
        historyDetails[historyDetails.length-3]=affectedFile;
        historyDetails[historyDetails.length-2]=Main.userLoggedInUserName;
        historyDetails[historyDetails.length-1]=Main.userLoggedInName;
        write.writer(historyDetails,fileName);
    }

}
