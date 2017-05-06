package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.DriverStation;
import robot.Robot;

public class Shoot extends Command {
	public Shoot(){
		requires(Robot.launcher);
	}
	@Override
	protected void initialize() {
		super.initialize();
	}
	
	@Override
	protected void execute() {
		double power = DriverStation.joystick.getZ();
		Robot.launcher.rawShoot(power);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
