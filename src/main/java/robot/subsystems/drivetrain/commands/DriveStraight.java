package robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

public class DriveStraight extends Command {

    private double speed;

    public DriveStraight(double speed) {
        requires(Robot.m_drivetrain);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        Robot.m_drivetrain.setLeftSpeed(speed);
        Robot.m_drivetrain.setRightSpeed(speed);
    }

    @Override
    protected void execute() {
        System.out.println(speed);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {

    }

    @Override
    protected void end() {
        Robot.m_drivetrain.setRightSpeed(0);
        Robot.m_drivetrain.setLeftSpeed(0);
    }
}
