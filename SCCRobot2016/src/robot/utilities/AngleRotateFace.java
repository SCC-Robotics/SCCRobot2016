package robot.utilities;

import java.util.ArrayList;

import robot.RobotMap;
import vision.PixyCam.Block;

public class AngleRotateFace {

	private static double widthCalc = 0;
	private static double distanceCalc = 0;
	private static int width;
	private static int xCoord;
	private static final double TAN37_5 = Math.tan(37.5 / 180 * Math.PI);

	// meters per block at DISTANCE
	final private static double WIDTH = 4;
	// meters of distance when WIDTH is meters per block
	final private static int DISTANCE = 3;
	// width of target in blocks
	final private static int TARGET_WIDTH = 3;

	/**
	 * Static method will take in a value for the x-coordinate of a pixy cam
	 * block data and calculate and return the angle from the optical
	 * equilibrium in radians.
	 * 
	 * @param x
	 * @return angle
	 */
	public static double calculateAngle(int x, int boxWidth) {

		double angle = 0.0;
		int target;
		width = boxWidth;
		// finding the object in the pixy cam view

		if (x > 161 || x < 160) {
			// object is on the right side of the pixy cam view if target > 0
			// object is on the left side of the pixy cam view if target < 0
			target = x - 160;
		} else /*
				 * if(x < 160) { // object is on the left side of the pixy cam
				 * view target = 160 - x; } else
				 */ {
			return angle;
		}

		// calculate the size ratio of the target width and the distance
		widthRatio(target);
		distanceRatio();

		// evaluate the angle
		angle = Math.atan2(widthCalc, distanceCalc);

		return angle;
	}

	public static double getAngleFromPixy() {
		int counter = 0;
		do{
			ArrayList<Block> pixyData = RobotMap.pixy.getPixyFrameData();
			System.out.println("Pixy data size: " + pixyData.size());
			if (pixyData.size() > 0) {
				Block blockData = pixyData.get(0);
				System.out.println("Angle block data: " + blockData);
				xCoord = blockData.x;
				width = blockData.width;

				return Math.atan((1 - xCoord / 160) * TAN37_5) * 180 / Math.PI;
			}
			try{
				Thread.sleep(5);
			}
			catch(InterruptedException e){
				
			}
			counter++;
		} while(counter < 100);
		

		return 0;
	}

	private static void widthRatio(int targetXcoord) {
		widthCalc = targetXcoord * WIDTH;
	}

	private static void distanceRatio() {
		distanceCalc = DISTANCE * (TARGET_WIDTH / width);
	}

	public static double distanceToDrive() {
		return distanceCalc - DISTANCE;
	}
}
