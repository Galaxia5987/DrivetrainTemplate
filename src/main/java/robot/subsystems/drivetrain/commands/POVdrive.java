package robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

/**
 *
 */
public class POVdrive extends Command {

    public POVdrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.m_drivetrain);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double leftInput;
        double rightInput;
        double drive = Robot.m_robotContainer.getRightXboxY();
        double turn = Robot.m_robotContainer.getLeftXboxY();

        double max = Math.max(Math.abs(drive), Math.abs(turn));

        leftInput = drive-turn;
        rightInput = drive + turn;

        if (max >1){

            leftInput /= max;
            rightInput /= max;

        }
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