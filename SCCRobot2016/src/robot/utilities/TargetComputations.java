package robot.utilities;

import java.util.ArrayList;

import robot.RobotMap;
import vision.PixyCam.Block;

public class TargetComputations {

	private static double widthCalc = 0;
	private static double distanceCalc = 0;
	private static int width;
	private static double xCoord;
	private static final double TAN37_5 = Math.tan(37.5 / 180 * Math.PI);

	// meters per block at DISTANCE
	final private static double WIDTH = 4;
	// meters of distance when WIDTH is meters per block
	final private static int DISTANCE = 3;
	// width of target in blocks
	final private static double TARGET_WIDTH = 0.30;

	public static double getAngleFromPixy() {
//		int counter = 0;
//		do{
			ArrayList<Block> pixyData = RobotMap.pixy.getPixyFrameData();
			System.out.println("Pixy data size: " + pixyData.size());
			if (pixyData.size() > 0) {
				Block blockData = pixyData.get(0);
				System.out.println("Angle block data: " + blockData);
				xCoord = blockData.x;
				//width = blockData.width;

				return Math.atan((xCoord / 160 - 1) * TAN37_5) * 180 / Math.PI;
			}
			/*try{
				Thread.sleep(5);
			}
			catch(InterruptedException e){
				
			}
			counter++;
		} while(counter < 100);*/
		

		return 0;
	}
	
	public static double getDistanceFromPixy(){
		
		ArrayList<Block> pixyData = RobotMap.pixy.getPixyFrameData();
		if (pixyData.size() > 0) {
			Block blockData = pixyData.get(0);
			width = blockData.width;

			return 160 / TAN37_5 * TARGET_WIDTH / width;
		}
		
		return 0;
	}

	public static int getPixelWidth() {
		ArrayList<Block> pixyData = RobotMap.pixy.getPixyFrameData();
		if (pixyData.size() > 0) {
			return pixyData.get(0).width;

		}
		
		return 0;
	}
}
