package robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

public class CameraLight extends Subsystem {

	@Override
	protected void initDefaultCommand() {
	}

	public void turnOn() {
		RobotMap.lightSol.set(true);
	}

	public void turnOff() {
		RobotMap.lightSol.set(false);
	}

}
