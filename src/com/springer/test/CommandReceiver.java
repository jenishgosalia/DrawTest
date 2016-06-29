package com.springer.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jenish on 21/06/2016.
 */
public class CommandReceiver {
	BufferedReader mBufferedReader;

	public CommandReceiver() {
		mBufferedReader = new BufferedReader(new InputStreamReader(System.in));
	}

	/*Blocking Call*/
	private String readNewCommand()  {
		System.out.print("\nEnter Command: ");
		String command = null;
		try {
			command = mBufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("\nCommand entered: "+command);
		return  command;
	}

	private Command parseCommand(String command) {

		Command newCommand = null;

		if (command == null)
			return newCommand;

		if (command.isEmpty())
			return  newCommand;

		List<String> list = Arrays.asList(command.split(" "));

		newCommand = new Command();
		try {
			newCommand.setType(list.get(0));
		}
		catch (IllegalArgumentException e) {
			System.out.print("Cannot set Command Type for command:" + command);
			newCommand = null;
			return newCommand;
		}
		//remove the type and set rest as data
		newCommand.addData(list.subList(1,list.size()));

		return  newCommand;
	}

	public Command getCommand() {
		String commandString = readNewCommand();
		Command command = parseCommand(commandString);
		return command;
	}
}
