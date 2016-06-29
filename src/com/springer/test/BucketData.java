package com.springer.test;

import java.awt.Point;

public class BucketData extends ExtractedData{

	private Point point = new Point(0,0);
	private String color = "o";

	public BucketData(Command c) {
		// TODO Auto-generated constructor stub
		super(c);

		int y;
		int x;

		try {
			x = Integer.valueOf(c.getNextData());
		}
		catch (NumberFormatException e) {
            System.out.println("Invalid Data in Bucket Fill command");
			x = 0;
		}

		try {
			y = Integer.valueOf(c.getNextData());
		}
		catch (NumberFormatException e) {
			y = 0;
		}

		point.setLocation(x, y);

		color = c.getNextData();

		if (color == null) {
			System.out.println("No color specified using default");
		}else if(color.length() > 1) {
			System.out.println("Color can only be a character using default");
			color = null;
		}

	}

	public Point getPoint() {
		return point;
	}

	public String getColor() {
		return color;
	}

}
