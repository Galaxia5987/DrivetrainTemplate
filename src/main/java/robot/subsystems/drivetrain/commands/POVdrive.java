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
        double difference;


        leftInput = drive-turn;
        rightInput = drive + turn;
        difference = Math.abs(leftInput - rightInput);

        if (leftInput > 1 && rightInput > -1) {
            leftInput = 1;
            rightInput -= difference;
        } else if (leftInput > -1 && rightInput > 1) {
            leftInput = 1;
            rightInput -= difference;
        } else if (leftInput < 1 && rightInput < -1) {
            rightInput = -1;
            leftInput += difference;
        } else if (leftInput < -1 && rightInput < 1) {
            leftInput = -1;
            rightInput += difference;
        }

        Robot.m_drivetrain.setLeftSpeed(normalizeInput(leftInput));
        Robot.m_drivetrain.setRightSpeed(normalizeInput(rightInput));
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

    /**
     * this function make sure that the input is between 1 to -1
     *
     * @param input the input in percent output
     * @return the normalized value if necessary and if not the input
     */
    public double normalizeInput(double input) {
        if (input > 1) {
            return 1;
        } else if (input < -1) {
            return -1;
        } else {
            return input;
        }
    }
}