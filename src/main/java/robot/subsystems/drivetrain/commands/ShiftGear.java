package robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import robot.Robot;

/**
 *
 */
public class ShiftGear extends InstantCommand {

    public gearMode desiredMode;

    public ShiftGear() {
        requires(Robot.m_drivetrain);
        desiredMode= gearMode.toggle;
    }

    public ShiftGear(boolean shiftUp) {
        requires(Robot.m_drivetrain);
        if (shiftUp){
            desiredMode = gearMode.high;
        }else{
            desiredMode = gearMode.low;
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        switch (desiredMode){
            case toggle:
                Robot.m_drivetrain.shift(!Robot.m_drivetrain.isOnHighGear());
            case high:
                Robot.m_drivetrain.shift(true);
            case low:
                Robot.m_drivetrain.shift(false);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
// subsystems is scheduled to run
    protected void interrupted() {
    }

    public enum gearMode{
        toggle,
        high,
        low
    }
}