package robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class sqrtJoystickDrive extends Command {

    public sqrtJoystickDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.m_drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double leftInput = -Robot.m_robotContainer.leftJoystick.getY();
        double rightInput = -Robot.m_robotContainer.rightJoystick.getY();
        leftInput = Math.sqrt(leftInput);
        rightInput = Math.sqrt(rightInput);
        Robot.m_drivetrain.setLeftSpeed(leftInput);
        Robot.m_drivetrain.setRightSpeed(rightInput);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
// subsystems is scheduled to run
    protected void interrupted() {
    }
}