package robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A sequence of commands that automatically drives the robot in a path.
 */
public class DrivePath extends CommandGroup {

	/**
	 * Creates a new command that drives in a path.
	 * <p>
	 * The path is a comma-separated list of commands, where each command has
	 * one argument. For example, a path could look like:
	 * 
	 * <pre>
	 * d 1.2, t 90, d 2.3, t -90
	 * </pre>
	 * <p>
	 * The supported commands are:
	 * <p>
	 * <code>d [distance]</code><br>
	 * Drives in a straight line for the given distance (in meters).
	 * <p>
	 * <code>t [angle]</code><br>
	 * Turns by the given angle (in degrees).
	 * </pre>
	 * 
	 * @param path
	 *            the path to drive
	 */
	public DrivePath(String path) {
		parsePath(path);
	}

	/**
	 * Parses a string of commands and adds each one to the command group.
	 * 
	 * @param path
	 *            the path to drive
	 */
	private void parsePath(String path) {
		for (String command : path.trim().split(",\\s+")) {
			String[] parts = command.split("\\s+", 2);
			addPathCommand(parts[0], Double.parseDouble(parts[1]));
		}
	}

	/**
	 * Adds a path command to the command group.
	 * 
	 * @param name
	 *            the name of the command
	 * @param arg
	 *            the argument to the command
	 */
	private void addPathCommand(String name, double arg) {
		switch (name.toLowerCase()) {
		case "d":
			addSequential(new DriveStraightDistance(arg));
			break;
		case "t":
			addSequential(new TurnAngle(arg));
			break;
		default:
			throw new IllegalArgumentException("Command not found: " + name);
		}
	}

}
