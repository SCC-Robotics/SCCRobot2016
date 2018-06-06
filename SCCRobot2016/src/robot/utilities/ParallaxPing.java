package robot.utilities;

import edu.wpi.first.wpilibj.AnalogInput;
public class ParallaxPing extends UltrasonicRangeFinder {
	
	private static final double VOLTAGE = 5/250;
	
	public AnalogInput input;
	
	public ParallaxPing(AnalogInput device) {
		super(device, VOLTAGE);
		input = device;
	}
	
	public double getVoltage() {
		
		return input.getVoltage();
		
	}
}
