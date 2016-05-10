package robot.commands;

import robot.DriverStation;
import robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ManualDriveStraight extends Command {

	@Override
	protected void initialize() {
		requires(Robot.drive);
	}

	@Override
	protected void execute() {
		double leftPower = DriverStation.joystick.getX();
		double rightPower = DriverStation.joystick.getY();
		Robot.drive.rawDrive(leftPower, rightPower);
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
