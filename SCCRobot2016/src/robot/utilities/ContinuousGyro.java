package robot.utilities;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI.Port;

public class ContinuousGyro extends AHRS {

	public ContinuousGyro(Port serial_port_id) {
		super(serial_port_id);
	}

	@Override
	public double pidGet() {
		// use the continuous yaw (and not the angle limited to [-180, 180])
		return getAngle();
	}

}
