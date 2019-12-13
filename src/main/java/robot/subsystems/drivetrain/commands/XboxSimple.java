package robot.subsystems.drivetrain.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

import static robot.Constants.Drivetrain.*;

public class XboxSimple extends Command {
    private double forward;
    private double turn;
    private NetworkTable xboxTable = NetworkTableInstance.getDefault().getTable("xbox");
    private NetworkTableEntry speedEntry = xboxTable.getEntry("speed");

    public XboxSimple(){
        requires(Robot.m_drivetrain);
    }

    @Override
    protected void initialize(){
    }

    @Override
    protected void execute(){
        forward = Robot.m_robotContainer.getRightXboxY();
        turn = -Robot.m_robotContainer.getLeftXboxX();
        double leftSpeed = forward*RIGHT_VELOCITY + turn*LEFT_VELOCITY;
        double rightSpeed = forward*RIGHT_VELOCITY - turn*LEFT_VELOCITY;

        Robot.m_drivetrain.setLeftSpeed(leftSpeed);
        Robot.m_drivetrain.setRightSpeed(rightSpeed);
        speedEntry.setDouble(leftSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
