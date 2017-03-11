package robot.utilities;

import robot.RobotMap;

public class AnalogUltrasonic {
	private int lastValue;

	public int getValue() {
		// Ignore values that are too high.
		int value = RobotMap.sonicSensor.getValue();
		if (value > 1000) {
			return lastValue;
		} else {
			lastValue = value;
			return value;
		}
	}
}
