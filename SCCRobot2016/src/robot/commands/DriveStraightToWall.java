package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.DriverStation;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.Drive;
import robot.utilities.ContinuousGyro;

public class DriveStraightToWall extends Command {

	private Drive drive = Robot.drive;
	private ContinuousGyro gyro = RobotMap.gyro;
	private double heading;
	private double yMotion = 1;

	public DriveStraightToWall() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		// getAngle returns a continuous angle as does pidGet in
		// ContinousGyro
		heading = gyro.getAngle();
		drive.driveOnHeadingInit(heading);
	}

	@Override
	protected void execute() {
		// yMotion is 0 for testing and 0 otherwise
		double power = .2;
		drive.driveOnHeading(power);
	}	

	@Override
	protected boolean isFinished() {

		// Goes on until interrupted
		return RobotMap.sonicAverage.getAverage() <= 300;
	}

	@Override
	protected void end() {
		drive.driveOnHeadingEnd();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
