package com.met622.utility;

public class PrintUtility {
    /**
     * function to handle printing of search results in the console
     * @param data
     * @param searchKey
     */
    public void printSearchResults(String[][] data, String searchKey){
        String textFormat = "| %-40s | %-25s | %-15s | %-15s |%n";
        int[] lengthArray = new int[]{40,25,15,15};
        System.out.println("\nSearch Key is "+searchKey);
        System.out.format("+------------------------------------------+---------------------------+-----------------+-----------------+%n");
        System.out.format("| project_name                             | category                  | funding_amount  | goal_amount     |%n");
        System.out.format("+------------------------------------------+---------------------------+-----------------+-----------------+%n");

        for (int i = 0; i < data.length; i++) {
            String[] temp = new String[data[i].length];
            for(int j=0;j<temp.length;j++){
                temp[j] = data[i][j].substring(0,Math.min(lengthArray[j],data[i][j].length()));
            }
            System.out.format(textFormat, temp);
        }
        System.out.format("+------------------------------------------+---------------------------+-----------------+-----------------+%n");
    }

    /**
     * prints the history commands.
     * @param data
     */
    public void printHistoryCommands(String[][] data){
        System.out.println("\nCommand History");
        for (int i = 0; i < data.length; i++) {
           System.out.println(data[i][0] + ", "+data[i][1]);
        }
    }
}
