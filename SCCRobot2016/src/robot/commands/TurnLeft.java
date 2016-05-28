package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.DriverStation;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.Drive;

public class TurnLeft extends Command {

	private double heading; // heading after turning left
	private Drive drive = Robot.drive;
	private static final double TOLERANCE = 2;

	public TurnLeft() {
		requires(drive);
	}

	@Override
	protected void initialize() {
		heading = RobotMap.ahrs.getYaw() - 90;
		if (heading < -180) {
			heading += 360;
		} else if (heading > 180) {
			heading -= 360;
		}
		drive.driveOnHeadingInit(heading, 1);
	}

	@Override
	protected void execute() {
		double power = -DriverStation.joystick.getY();
		drive.driveOnHeading(power);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(RobotMap.ahrs.getYaw() - heading) <= TOLERANCE;
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
