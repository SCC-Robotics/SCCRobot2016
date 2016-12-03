package robot.commands;

import robot.DriverStation;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.Drive;
import robot.utilities.ContinuousGyro;
import edu.wpi.first.wpilibj.command.Command;

public class TurnAngle extends Command {
	private Drive drive = Robot.drive;
	private ContinuousGyro gyro = RobotMap.gyro;
	private double tolerance, angle;
	private double finalHeading;
	private int cycles;

	public TurnAngle(double angle, double tolerance) {
		requires(drive);
		this.tolerance = tolerance;
		this.angle = angle;
	}
	
	public TurnAngle(double angle) {
		this(angle, 0.5);
	}

	@Override
	protected void initialize() {
		finalHeading = gyro.getAngle() + angle;
		cycles = 0;
		drive.driveOnHeadingInit(finalHeading);
	}

	@Override
	protected void execute() {
		double power = -DriverStation.joystick.getY();
		drive.driveOnHeading(power);
	}

	@Override
	protected boolean isFinished() {
		// stop if the angle stays close to the final heading for several cycles
		if (Math.abs(gyro.getAngle() - finalHeading) <= tolerance) {
			cycles ++;
			return cycles >= 10;
		} else {
			cycles = 0;
			return false;
		}
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
