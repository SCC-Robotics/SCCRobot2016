package robot.utilities;

import edu.wpi.first.wpilibj.AnalogInput;

public class AverageSonicSensor {

	private AnalogInput sonicSensor;
	private int nValues;
	private int count;
	private int start;
	private double sum;
	private double[] sensorList;

	public AverageSonicSensor(AnalogInput sonicSensor, int nValues) {
		this.sonicSensor = sonicSensor;
		this.nValues = nValues;
		sensorList = new double[nValues];
	}

	public double getAverage() {
		return sum / count;
	}

	public int getCount() {
		return count;
	}

	public double getSum() {
		return sum;
	}

	public void addValue() {
		int sValue = sonicSensor.getValue();
		if (Math.abs(sValue - getAverage()) <= 500|| count==0) {
			sum -= sensorList[start];
			sensorList[start] = sonicSensor.getValue();
			sum += sensorList[start];
			start = (start + 1) % nValues;
			count = Math.min(count + 1, nValues);
		}
	}
}
