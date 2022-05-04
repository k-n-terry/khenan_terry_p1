package dev.terry.utilities;

import java.util.Date;

public class Logger {
    public static void log(String message, LogLevel level){
        // LOG LEVEL + message + TimeStamp
        String logTitle = level.name() +" at "+ new Date();
        String logMessage = "\t"+message+"\n";

        System.out.println(logTitle);
        System.out.println(logMessage);
    }
    public static void log(String message01, String message02, LogLevel level){
        // LOG LEVEL + message + TimeStamp
        String logTitle = level.name() +" at "+ new Date();
        String logMessage01 = "\t"+message01;
        String logMessage02 = "\t"+message02+"\n";

        System.out.println(logTitle);
        System.out.println(logMessage01);
        System.out.println(logMessage02);
    }
}
