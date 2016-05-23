package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.Drive;

public class TurnAngle extends Command {
	private Drive drive = Robot.drive;
	private double power, tolerance, angle;
	private double finalHeading;

	public TurnAngle(double angle, double power, double tolerance) {
		requires(drive);
		this.power = power;
		this.tolerance = tolerance;
		this.angle = angle;
	}

	@Override
	protected void initialize() {
		finalHeading = RobotMap.ahrs.getAngle() + angle;
		// drive.turnToHeadingInit(finalHeading, 1, tolerance);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
