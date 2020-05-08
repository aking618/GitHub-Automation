package com.aking.app;

public class Application {

    public static void main(String[] args) {

        if (args.length == 0 || (args.length < 2) || (args.length > 2)) {
            System.out.println("Usage: java -jar target/gihub-automation [create or delete] [fileName]");
            System.exit(0);
        }

        String argument = args[0];
        String fileName = args[1];

        if (argument.equalsIgnoreCase("create")) {
            Create.create(fileName);
        } else if(argument.equalsIgnoreCase("delete")) {
            Delete.delete(fileName);
        } else {
            System.out.println("Usage: java -jar target/gihub-automation [create or delete] [fileName]");
        }
    }
}