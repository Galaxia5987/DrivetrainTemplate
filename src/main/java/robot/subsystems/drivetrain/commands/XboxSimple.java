package robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

import static robot.Constants.Drivetrain.*;

public class XboxSimple extends Command {
    private double forward = -Robot.m_robotContainer.getRightXboxY();
    private double turn = -Robot.m_robotContainer.getLeftXboxX();
    public XboxSimple(){
        requires(Robot.m_drivetrain);
    }

    @Override
    protected void initialize(){

    }

    @Override
    protected void execute(){
        Robot.m_drivetrain.setLeftSpeed(forward*RIGHT_VELOCITY + turn*LEFT_VELOCITY);
        Robot.m_drivetrain.setRightSpeed(forward*RIGHT_VELOCITY - turn*LEFT_VELOCITY);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
