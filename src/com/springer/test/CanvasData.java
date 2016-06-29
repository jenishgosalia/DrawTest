package com.springer.test;

public class CanvasData extends ExtractedData {

	private int width;
	private int height;

	public CanvasData(Command c) {
		super(c);

		try {
			width = Integer.valueOf(c.getNextData());
		}
		catch (NumberFormatException e) {
            System.out.println("Invalid Data in Canvas command");
			width = 0;
		}

		try {
			height = Integer.valueOf(c.getNextData());
		}
		catch (NumberFormatException e) {
			height = 0;
		}

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
