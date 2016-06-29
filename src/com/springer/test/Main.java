package com.springer.test;

public class Main {
	public static void main(String[] args) {
		// write your code here
		Draw drawBoard = null;
		CommandReceiver receiver = new CommandReceiver();
		while(true) {
			Command command = receiver.getCommand();
			if(command == null) {
				System.exit(0);
			}

			System.out.println(command.getType().toString() + " command received");

			switch (command.getType()){
			case Q:
				System.exit(0);
				break;

			case C:
				CanvasData cd = new CanvasData(command);
				drawBoard = new Draw();
				drawBoard.drawCanvas(cd.getWidth(), cd.getHeight());
				break;

			case L:
				if(drawBoard == null) {
					System.out.println("No Canvas Drawn yet cannot draw a line");
					continue;
				}
				LineData ld = new LineData(command);
				drawBoard.drawLine(ld.getStartPoint(), ld.getEndPoint());
				break;

			case R:
				if(drawBoard == null) {
					System.out.println("No Canvas Drawn yet cannot draw a rectangle");
					continue;
				}

				LineData rd = new LineData(command);
				drawBoard.drawRect(rd.getStartPoint(), rd.getEndPoint());
				break;

			case B:
				if(drawBoard == null) {
					System.out.println("No Canvas Drawn yet cannot draw a rectangle");
					continue;
				}

				BucketData bd = new BucketData(command);
				drawBoard.bucketFill(bd.getPoint(), bd.getColor());
				break;

			default:
				// do nothing;
				break;
			}

			if(drawBoard != null) {
				drawBoard.printCanvas();
			}
		}

	}
}
