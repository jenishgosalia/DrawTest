package com.springer.test;

import java.awt.Point;

public class LineData extends ExtractedData {

	private Point startPoint = new Point(0,0);
	private Point endPoint = new Point(0,0);

	public LineData(Command c) {
		// TODO Auto-generated constructor stub
		super(c);

		int x,y;

		try {
			x = Integer.valueOf(c.getNextData());
		}
		catch (NumberFormatException e) {
            System.out.println("Invalid Data in Line command");
			x = 0;
		}

		try {
			y = Integer.valueOf(c.getNextData());
		}
		catch (NumberFormatException e) {
            System.out.println("Invalid Data in Line command");
			y = 0;
		}

		startPoint.setLocation(x, y);

		try {
			x = Integer.valueOf(c.getNextData());
		}
		catch (NumberFormatException e) {
			x = 0;
		}

		try {
			y = Integer.valueOf(c.getNextData());
		}
		catch (NumberFormatException e) {
			y = 0;
		}

		endPoint.setLocation(x, y);

	}

	public Point getStartPoint(){
		return startPoint;
	}

	public Point getEndPoint(){
		return endPoint;
	}

}
