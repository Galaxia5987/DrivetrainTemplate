package robot.subsystems.drivetrain;

import static robot.Constants.Drivetrain.*;
import static robot.Ports.Drivetrain.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.Ports;
import robot.subsystems.drivetrain.commands.DriveTypeChooser;
import robot.subsystems.drivetrain.commands.JoystickDrive;
import robot.subsystems.drivetrain.commands.POVdrive;
import robot.subsystems.drivetrain.commands.XboxSimple;

/**
 * This is a temporary subsystem from last year.
 */
public class Drivetrain extends Subsystem {

    private TalonSRX leftMaster = new TalonSRX(LEFT_MASTER_PORT);
    private VictorSP left1 = new VictorSP(LEFT_SLAVE_1_PORT);
    private VictorSP left2 = new VictorSP(LEFT_SLAVE_2_PORT);

    private TalonSRX rightMaster = new TalonSRX(RIGHT_MASTER_PORT);
    private VictorSP right1 = new VictorSP(RIGHT_SLAVE_1_PORT);
    private VictorSP right2 = new VictorSP(RIGHT_SLAVE_2_PORT);

    public Drivetrain() {
        leftMaster.setInverted(LEFT_MASTER_REVERSED);
        left1.setInverted(LEFT_SLAVE_1_REVERSED);
        left2.setInverted(LEFT_SLAVE_2_REVERSED);
        rightMaster.setInverted(RIGHT_MASTER_REVERSED);
        right1.setInverted(RIGHT_SLAVE_1_REVERSED);
        right2.setInverted(RIGHT_SLAVE_2_REVERSED);

//        right1.follow(rightMaster);
//        right2.follow(rightMaster);
//        left1.follow(leftMaster);
//        left2.follow(leftMaster);

        leftMaster.configPeakCurrentLimit(MAX_CURRENT);
        rightMaster.configPeakCurrentLimit(MAX_CURRENT);

//        leftMaster.configMotionSCurveStrength(4);
//        rightMaster.configMotionSCurveStrength(4);
    }

    public void setLeftSpeed(double speed) {
        leftMaster.set(ControlMode.PercentOutput, speed);
        left1.set(speed);
        left2.set(speed);
    }

    public void setRightSpeed(double speed) {
        rightMaster.set(ControlMode.PercentOutput, speed);
        right1.set(speed);
        right2.set(speed);
    }

    public double getLeftDistance() {
        return convertTicksToDistance(leftMaster.getSelectedSensorPosition());
    }

    public double getRightDistance() {
        return convertTicksToDistance(rightMaster.getSelectedSensorPosition());
    }

    public double getRightVelocity() {
        return convertTicksToDistance(rightMaster.getSelectedSensorVelocity()) * 10;
    }

    public double getLeftVelocity() {
        return convertTicksToDistance(leftMaster.getSelectedSensorVelocity()) * 10;
    }

    public int convertDistanceToTicks(double distance) {
        return (int) (distance * TICKS_PER_METER);
    }

    /**
     * because the max input from the joystick is 1 , the joystick input * max velocity is
     * function which represent the relation
     *
     * @param joystickInput the y value from the joystick
     * @return joystick value in m/s
     */
    public double convertJoystickInputToVelocity(double joystickInput) {
        return joystickInput * MAX_VEL;
    }


    /**
     * limit the drivetrain's right side acceleration to a certain acceleration
     *
     * @param desiredVelocity the desired velocity
     * @return the desired velocity if possible, if not the current velocity plus the max acceleration
     */
    public double limitRightAcceleration(double desiredVelocity) {

        //Take the attempted acceleration and see if it is too high.
        if (Math.abs(desiredVelocity - getRightVelocity()) / TIME_STEP >= MAX_ACCELERATION) {
            return getRightVelocity() + MAX_ACCELERATION;
        }

        return desiredVelocity;
    }

    /**
     * limit the drivetrain's left side acceleration to a certain acceleration
     *
     * @param desiredVelocity the desired velocity
     * @return the desired velocity if possible, if not the current velocity plus the max acceleration
     */
    public double limitLeftAcceleration(double desiredVelocity) {

        //Take the attempted acceleration and see if it is too high.
        if (Math.abs((desiredVelocity - getLeftVelocity()) / TIME_STEP) >= MAX_ACCELERATION) {
            return getLeftVelocity() + MAX_ACCELERATION;
        }

        return desiredVelocity;
    }

    public double convertTicksToDistance(int tick) {
        return tick / TICKS_PER_METER;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }
}
