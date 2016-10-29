package robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.commands.DriveStraightToWall;
import robot.commands.TurnAngle;

public class DriveAroundFloor2 extends CommandGroup {

	public DriveAroundFloor2() {
		addSequential(new DriveStraightToWall());
		addSequential(new TurnAngle(90, .5));
		//addSequential(new DriveStraightToWall());
		//addSequential(new TurnAngle(90, .5));
	}

}
