package robot.commands;

import robot.DriverStation;
import robot.Robot;
import robot.utilities.TargetComputations;

public class DriveToTarget extends DriveStraight{
	
	private int countRaw;
	private int countValues;
	private int[] values = new int[10];
	private int index;
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		Robot.cameraLight.turnOn();
	}
	
	@Override
	protected void execute() {
		// 
		double power = -1 * DriverStation.joystick.getZ();
		System.out.println("DriveToTarget() Power: +" + power);
		Robot.drive.driveOnHeading(power);
	}
	
	@Override
	protected boolean isFinished() {
		int currentWidth = TargetComputations.getPixelWidth();
		int width;
		
		if (currentWidth != 0){
			values[index] = currentWidth;
			countValues++;
		}
		
		countRaw++;
		
		if (countRaw - countValues >= 8){
			width = 0;
		} else {
			int sum = 0;
			if (countValues < values.length){
				for ( int i = 0; i <= index; i++){
					sum += values[i];
				}
				width = sum / (index + 1);
			} else {
				for ( int i = 0; i < values.length; i++){
					sum += values[i];
				}
				width = sum / values.length;
			}
		}
		index = (index + 1)%values.length;
		
		return width > 110;
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		super.end();
		Robot.cameraLight.turnOff();
	}
}
