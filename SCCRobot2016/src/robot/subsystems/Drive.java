package robot.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.RobotMap;

public class Drive extends Subsystem implements PIDOutput {

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void rawDrive(double leftPower, double rightPower) {
		RobotMap.motorBackLeft.set(leftPower);
		RobotMap.motorBackRight.set(rightPower);
		RobotMap.motorFrontLeft.set(leftPower);
		RobotMap.motorFrontRight.set(rightPower);
	}

}
