package robot.commands;

import robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ManualDriveStraight extends Command {

	@Override
	protected void initialize() {
		requires(Robot.drive);
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
