package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.DriverStation;
import robot.Robot;
import robot.RobotMap;
import robot.subsystems.Drive;
import robot.utilities.TargetComputations;
import robot.utilities.ContinuousGyro;

public class TurnToTarget extends Command {
	private Drive drive = Robot.drive;
	private ContinuousGyro gyro = RobotMap.gyro;
	private double tolerance, angle;
	private double finalHeading;
	private int cycles;

	public TurnToTarget(double tolerance) {
		requires(drive);
		this.tolerance = tolerance;
	}

	public TurnToTarget() {
		this(0.5);
		//System.out.println("TurnToTarget() called");
	}

	@Override
	protected void initialize() {
		// get the angle from the pixy cam
		angle = TargetComputations.getAngleFromPixy();
		System.out.println("Gyro angle: " + gyro.getAngle());
		System.out.println("Pixy angle: " + angle);
		finalHeading = gyro.getAngle() + angle;
		SmartDashboard.putNumber("Final Heading", finalHeading);
		//System.out.println("Final Heading: " + finalHeading);
//		String result = "Turn ";
//		if(angle != 0){
//			result += (angle < 0)? "Left" : "Right";
//		} else{
//			result = "No Target";
//		}
//		System.out.println(result);
		cycles = 0;
		drive.driveOnHeadingInit(finalHeading);
	}

	@Override
	protected void execute() {
		double power = DriverStation.joystick.getThrottle();
		System.out.println("TurnToTarget.execute()");
		System.out.println("Power: " + power);
		drive.driveOnHeading(power);
	}

	@Override
	protected boolean isFinished() {
		// stop if the angle stays close to the final heading for several cycles
		SmartDashboard.putNumber("Cycles", cycles);
		if (Math.abs(gyro.getAngle() - finalHeading) <= tolerance) {
			cycles++;
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
