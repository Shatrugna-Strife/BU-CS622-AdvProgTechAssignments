package com.met622;

import com.met622.Entity.MongoEntity;
import com.met622.Entity.MysqlEntity;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        MongoEntity mongoEntity = new MongoEntity();
        MysqlEntity mysqlEntity = new MysqlEntity();
        mysqlEntity.insertData();
        System.out.println("DBLP data inserted successfully in MySQL Database\n");
        mongoEntity.insertData();
        System.out.println("DBLP data inserted successfully in MongoDB Database\n");

        System.out.println("Keywords search in MySQL and MongoDB\n" +
                "Keyword - wearable\n\n");
        System.out.println("MySQL search count - "+mysqlEntity.textSearch("wearable") +
                "\n" +
                "MongoDB search count - "+mongoEntity.textSearch("wearable")+"\n\n");
        System.out.println("Keyword - gradient descent\n\n" +
                "MySQL search count - "+mysqlEntity.textSearch("gradient descent") +
                "\n" +
                "MongoDB search count - "+mongoEntity.textSearch("gradient descent")+"\n\n");


        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        while(true) {
            System.out.println("Enter a keyword:");
            String keyword = myObj.nextLine();
            System.out.println("Enter start date in format yyyy-MM. Ex:- 2022-03");
            String from = myObj.nextLine();
            System.out.println("Enter end date in format yyyy-MM. Ex:- 2022-03");
            String to = myObj.nextLine();

            System.out.println("Keyword - " + keyword +
                    "MySQL search count - " + mysqlEntity.rangeTextSearch(keyword,from+"-01", to+"-01") +
                    "\n" +
                    "MongoDB search count - " + mongoEntity.rangeTextSearch(keyword,from+"-01", to+"-01") + "\n\n");
        }
    }

}