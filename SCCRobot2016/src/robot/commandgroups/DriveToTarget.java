package robot.commandgroups;

import java.util.Timer;
import java.util.TimerTask;

import robot.RobotMap;
import robot.commands.*;
import robot.utilities.AngleRotateFace;
import vision.PixyCam.Block;

public class DriveToTarget {

	boolean demo = false;

	public DriveToTarget() {

		// Starting timer for demo mode
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				exit();
				System.out.println("Exiting Demo Mode.");
			}

		}, 60000);

		while (!demo) {

			// Search for target
			boolean targetLocked = false;

			while (!targetLocked) {
				if (RobotMap.pixy.getPixyFrameData().size() == 0) {
					// rotate robot 50 degrees
					System.out.println("Searching for target.");
					// robot.commands.TurnAngle(50/180 * Math.PI);
				}
			}

			// Align with target
			for (Block b : RobotMap.pixy.getPixyFrameData()) {
				double angle = AngleRotateFace.calculateAngle(b.x, b.width);
				if (angle != 0) {
					// Access TurnAngle command
					String response = "Turn ";
					response += (angle > 0) ? "Left" : "Right";
					System.out.println(response);
					// robot.commands.TurnAngle(angle);
				}
				double distance = AngleRotateFace.distanceToDrive();
				// Access DriveStraightDistane
				System.out.println("Drive Forward");
				// robot.commands.DriveStraightDistance(distance);
			}
		}
	}

	private void exit() {
		demo = true;
	}
}
