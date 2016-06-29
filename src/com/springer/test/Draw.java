package com.springer.test;

import java.awt.Point;

/**
 * Created by jenish on 25/06/2016.
 */
public class Draw {

	private int mWidth;
	private int mHeight;

	private int mMinX=1, mMinY=1, mMaxX, mMaxY;
	private String[][] mCanvas;

	// x or width is columns
	// y or height is rows
	public void drawCanvas(int width, int height) {
		if(width ==0 || height ==0 ) {
			System.out.print("invalid parameters for canvas");
			return;
		}

		// plus 2 for integrating canvas border
		mWidth = width + 2;
		mHeight = height + 2;

		mMaxX = mWidth-1 ;
		mMaxY = mHeight-1;

		mCanvas = new String[mHeight][mWidth];

		//clear all
		for (int i=0; i<mHeight; i++) {
			for (int j=0; j<mWidth; j++) {
				mCanvas[i][j] = Element.DEFAULT_COLOR.EMPTY.getColor();
			}
		}

		// Draw the canvas
		int first_row = 0;
		int last_row = mMaxY;
		for(int i=0; i<mWidth; i++) {
			mCanvas[first_row][i] = Element.DEFAULT_COLOR.CANVAS_COLUMN.getColor();
			mCanvas[last_row][i]  = Element.DEFAULT_COLOR.CANVAS_COLUMN.getColor();
		}

		int first_column = 0;
		int last_column = mMaxX;
		for(int i=0; i<mHeight; i++) {
			mCanvas[i][first_column] = Element.DEFAULT_COLOR.CANVAS_ROW.getColor();
			mCanvas[i][last_column]  = Element.DEFAULT_COLOR.CANVAS_ROW.getColor();
		}
	}

	private String mBucketColor = Element.DEFAULT_COLOR.BUCKET_FILL.getColor();

	public void bucketFill(Point point, String color) {

		if(!boundCheck(point)) {
			return;
		}

		if(color != null)
            mBucketColor = color;

		String point_color = mCanvas[point.y][point.x];

		floodFillScanline(point.x,point.y,mBucketColor,point_color);

	}

	public void drawRect(Point topLeft, Point bottomRight) {
		Point p1 = new Point(), p2 = new Point();

		if(!boundCheck(topLeft)) return;
		if(!boundCheck(bottomRight)) return;

		//left side
		p1.setLocation(topLeft.x,topLeft.y);
		p2.setLocation(topLeft.x,bottomRight.y);
		drawLine(p1,p2);

		//top side
		p2.setLocation(bottomRight.x,topLeft.y);
		drawLine(p1,p2);

		//right side
		p1.setLocation(bottomRight);
		drawLine(p1,p2);

		//bottom side
		p2.setLocation(topLeft.x,bottomRight.y);
		drawLine(p1,p2);

	}

	public void drawLine(Point startPoint, Point endPoint) {
		int startX = startPoint.x;
		int stopX = endPoint.x;
		int startY = startPoint.y;
		int stopY = endPoint.y;

		if(!boundCheck(startPoint)) return;
		if(!boundCheck(endPoint)) return;

		int deltax = Math.abs(startX - stopX);
		int deltay = Math.abs(startY - stopY);

		if(deltax != 0 && deltay != 0) {
			// seems like a diagonal line not supported yet
			System.out.print("Not supported line format for "+ startPoint + " to " + endPoint);
		}

		if(deltax == 0) {
			// line along height / rows
			if(startY > stopY) {
				int temp = startY;
				startY  = stopY;
				stopY = temp;
			}
			for(int i = startY; i<=stopY; i++) {
				setColor(i,startX, Element.DEFAULT_COLOR.LINE.getColor());
			}
		}else {
			// line along widht
			if(startX > stopX) {
				int temp = startX;
				startX  = stopX;
				stopX = temp;
			}
			for(int i = startX; i<=stopX;i++){
				setColor(startY,i, Element.DEFAULT_COLOR.LINE.getColor());
			}
		}
	}

	private boolean boundCheck(Point point) {
		int x = point.x;
		int y = point.y;

		if(x < mMinX || x > mMaxX) {
			System.out.print("x point is outside the canvas for point:" + point);
			return false;
		}

		if(y < mMinY || y > mMaxY) {
			System.out.print("y point is outside the canvas for point:" + point);
			return false;
		}

		return  true;
	}

	private String getColor(int x, int y) {
		return mCanvas[x][y];
	}

	private void setColor(int x, int y, String color){
		mCanvas[x][y] = color;
	}

	// x is width and columns, y is height and rows
	private void floodFillScanline(int x, int y, String newColor, String oldColor)
	{
		if(oldColor.equals(newColor)) return;

		if(!getColor(y,x).equals(oldColor)) return;

		int x1;

		//draw current scanline from start position to the right
		x1 = x;
		while(x1 <= mMaxX && getColor(y, x1).equals(oldColor))
		{
			setColor(y, x1, newColor);
			x1++;
		}

		//draw current scanline from start position to the left
		x1 = x - 1;
		while(x1 >= mMinX && getColor(y, x1).equals(oldColor))
		{
			setColor(y, x1, newColor);
			x1--;
		}

		//test for new scanlines above
		x1 = x;
		while(x1 <= mMaxX && getColor(y, x1).equals(newColor))
		{
			if(y >= mMinX && getColor(y-1, x1).equals(oldColor))
			{
				floodFillScanline(x1, y - 1, newColor, oldColor);
			}
			x1++;
		}
		x1 = x - 1;
		while(x1 >= mMinX && getColor(y, x1).equals(newColor))
		{
			if(y >= mMinX && getColor(y-1, x1).equals(oldColor))
			{
				floodFillScanline(x1, y - 1, newColor, oldColor);
			}
			x1--;
		}

		//test for new scanlines below
		x1 = x;
		while(x1 <= mMaxX && getColor(y, x1).equals(newColor))
		{
			if(y <= mMaxY && getColor(y+1, x1).equals(oldColor))
			{
				floodFillScanline(x1, y + 1, newColor, oldColor);
			}
			x1++;
		}
		x1 = x - 1;
		while(x1 >= mMinX && getColor(y, x1).equals(newColor))
		{
			if(y <= mMaxY && getColor(y+1, x1).equals(oldColor))
			{
				floodFillScanline(x1, y + 1, newColor, oldColor);
			}
			x1--;
		}
	}


	public void printCanvas() {
		if(mCanvas == null) return;

		for (int i =0; i<mHeight; i++) {
			StringBuilder builder = new StringBuilder();
			for (int j=0; j<mWidth; j++) {
				builder.append(mCanvas[i][j]);
			}
			System.out.println(builder.toString());
		}
	}
}
