package com.springer.test;

public class Main {
    public static void main(String[] args) {
	// write your code here

        CommandHandler handler = new CommandHandler();
        while(true) {
            Command command = handler.getCommand();
            if(command == null) {
                System.exit(0);
            }

            System.out.print(command.getType().toString() + " command received \n ");

            switch (command.getType()){
                case Q:
                    System.exit(0);
                    break;

                default:
                    // do nothing;
                    break;
            }
        }
    }
}
