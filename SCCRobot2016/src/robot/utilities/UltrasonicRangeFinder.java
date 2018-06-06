package robot.utilities;
import edu.wpi.first.wpilibj.AnalogInput;

public abstract class UltrasonicRangeFinder {
	/**
	 * The analog input that the sensor is in.
	 */
	private AnalogInput sensor;
	
	/**
	 * The voltage drop per inch that the target moves
	 */
	private double voltsPerInch;
	
	
	/**
	 * The voltage at the set zero position
	 */
	private double zeroPos;
	
	/**
	 * Creates a new UltrasonicRangeFinder 
	 * @param device - the analog input the range finder is in
	 * @param voltsPerInch - the volts the analog reading changes per inch
	 */
	public UltrasonicRangeFinder(AnalogInput device, double voltsPerInch){
		sensor = device;
		this.voltsPerInch = voltsPerInch;
		zeroPos = 0;
	}
	
	/**
	 * Sets the zero point for the sensor at the current distance
	 */
	public void zero(){
		zeroPos = sensor.getVoltage();
	}
	
	/**
	 * Resets the zero point to 0
	 */
	public void resetZero(){
		zeroPos = 0;
	}
	
	/**
	 * Gets the range in inches to the nearest object
	 * @return The range in inches
	 */
	public double getRange(){
		return (sensor.getVoltage() - zeroPos) / voltsPerInch;
	}
	
	
	public abstract double getVoltage();
	
}
