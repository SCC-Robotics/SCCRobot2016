package robot.utilities;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * An ultrasonic rangefinder that uses an analog channel. This class filters out
 * values from the sensor that are too high.
 */
public class AnalogUltrasonic {

	/**
	 * The highest value the sensor can return.
	 */
	private static final int MAX_VALUE = 1000;

	/**
	 * The input from the sensor.
	 */
	private AnalogInput sensor;

	/**
	 * The last good value from the sensor.
	 */
	private int lastValue;

	/**
	 * Creates an ultrasonic sensor.
	 * 
	 * @param channel
	 *            the analog channel number of the sensor
	 */
	public AnalogUltrasonic(int channel) {
		sensor = new AnalogInput(channel);
	}

	/**
	 * Gets the value from the sensor.
	 * 
	 * @return the value from the sensor
	 */
	public int getValue() {
		// Ignore values that are too high and return the last good value
		// instead.
		int value = sensor.getValue();
		if (value > MAX_VALUE) {
			return lastValue;
		} else {
			lastValue = value;
			return value;
		}
	}
	
	public double getVoltage() {
		return sensor.getAverageVoltage();
	}

}
