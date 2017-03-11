package robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;

public class ToggleCameraLight extends Command {
	
	public ToggleCameraLight() {
		requires(Robot.cameraLight);
	}
	
	@Override
	protected void execute() {
		if (RobotMap.lightSol.get()) {
			Robot.cameraLight.turnOff();
		} else {
			Robot.cameraLight.turnOn();
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
