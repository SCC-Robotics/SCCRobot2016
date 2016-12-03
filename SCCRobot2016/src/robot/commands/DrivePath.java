package robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivePath extends CommandGroup {

	/**
	 * 
	 * @param path
	 *            a comma-separated string listing distances to drive and angles
	 *            to turn, such as: "d1.2, a90, d2.3"
	 */
	public DrivePath(String path) {
		for (String command : path.split(",\\s+")) {
			command = command.toLowerCase().trim();
			double value = Double.parseDouble(command.substring(1));
			if (command.startsWith("d")) {
				addSequential(new DriveStraightDistance(value));
			} else if (command.startsWith("a")) {
				addSequential(new TurnAngle(value));
			}
		}
	}

}
