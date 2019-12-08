package robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.Constants;
import robot.Ports;
import robot.Robot;
import robot.subsystems.drivetrain.commands.DriveTypeChooser;

import static robot.Constants.Drivetrain.*;
import static robot.Ports.Drivetrain.*;

/**
 * Drivetrain subsystem for the gear-shifter drivetrain
 * In this drivetrain low gear and high gear is based on the torque , low gear = low torque and high gear = high torque
 */
public class Drivetrain extends Subsystem {


    private DoubleSolenoid shifter = new DoubleSolenoid(1, Ports.Drivetrain.SHIFTER_FORWARD_PORT, Ports.Drivetrain.SHIFTER_REVERSE_PORT);
    private Timer shiftCounter = new Timer();
    private boolean isShiftingEnabled;
    private TalonSRX leftMaster = new TalonSRX(LEFT_MASTER_PORT);
    private TalonSRX rightMaster = new TalonSRX(RIGHT_MASTER_PORT);
    private VictorSPX right1 = new VictorSPX(RIGHT_SLAVE_1_PORT);
    private VictorSPX left1 = new VictorSPX(LEFT_SLAVE_1_PORT);
    private VictorSPX right2 = new VictorSPX(RIGHT_SLAVE_2_PORT);
    private VictorSPX left2 = new VictorSPX(LEFT_SLAVE_2_PORT);

    public Drivetrain() {
        leftMaster.setInverted(LEFT_MASTER_REVERSED);
        left1.setInverted(LEFT_SLAVE_1_REVERSED);
        left2.setInverted(LEFT_SLAVE_2_REVERSED);
        rightMaster.setInverted(RIGHT_MASTER_REVERSED);
        right1.setInverted(RIGHT_SLAVE_1_REVERSED);
        right2.setInverted(RIGHT_SLAVE_2_REVERSED);

        right1.follow(rightMaster);
        right2.follow(rightMaster);
        left1.follow(leftMaster);
        left2.follow(leftMaster);

        leftMaster.configPeakCurrentLimit(MAX_CURRENT);
        rightMaster.configPeakCurrentLimit(MAX_CURRENT);
    }

    public void setLeftSpeed(double speed) {
        leftMaster.set(ControlMode.PercentOutput, speed);
    }

    public void setRightSpeed(double speed) {
        rightMaster.set(ControlMode.PercentOutput, speed);
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

    public void gearShiftEnabled(boolean enable) {
        isShiftingEnabled = enable;
    }

    /**
     * This method checks if the robot can shift gear , and if so he shifts the gear
     * to the desired gear
     *
     * @param shiftUp the desired gear if true the high gear and if false to the lower gear.
     */
    public void shift(boolean shiftUp) {
        if (canShift()) {
            shiftCounter.reset();
            if (shiftUp) {
                shifter.set(shiftUp());
            } else {
                shifter.set(shiftDown());
            }
        }
    }

    private DoubleSolenoid.Value shiftUp() {
        shiftCounter.start();
        return DoubleSolenoid.Value.kForward;
    }

    private DoubleSolenoid.Value shiftDown() {
        shiftCounter.start();
        return DoubleSolenoid.Value.kReverse;
    }

    /**
     * This method is meant for automatic shifting
     * first of all it checks if the drivetrain can shift gear
     * than it check if it should shift the gear up or down
     */
    public void autoShift() {
        if (canShift()) {
            if (canShiftHigh()) {
                shifter.set(shiftUp());
            } else if (canShiftLow()) {
                shifter.set(shiftDown());
            }
        }
    }

    public int convertDistanceToTicks(double distance) {
        return (int) (distance * Shifter.TICKS_PER_METER.get());
    }

    /**
     * because the max input from the joystick is 1 , the joystick input * max velocity is
     * function which represent the relation
     *
     * @param joystickInput the y value from the joystick
     * @return joystick value in m/s
     */
    public double convertJoystickInputToVelocity(double joystickInput) {
        return joystickInput * Shifter.MAX_VEL.get();
    }


    /**
     * limit the drivetrain's right side acceleration to a certain acceleration
     *
     * @param desiredVelocity the desired velocity
     * @return the desired velocity if possible, if not the current velocity plus the max acceleration
     */
    public double limitRightAcceleration(double desiredVelocity) {

        //Take the attempted acceleration and see if it is too high.
        if (Math.abs(desiredVelocity - getRightVelocity()) / Constants.TIME_STEP >= Shifter.MAX_ACCELERATION.get()) {
            return getRightVelocity() + Shifter.MAX_ACCELERATION.get();
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
        if (Math.abs((desiredVelocity - getLeftVelocity()) / Constants.TIME_STEP) >= Shifter.MAX_ACCELERATION.get()) {
            return getLeftVelocity() + Shifter.MAX_ACCELERATION.get();
        }

        return desiredVelocity;
    }

    public double convertTicksToDistance(int tick) {
        return tick / Shifter.TICKS_PER_METER.get();
    }

    /**
     * Check if the gear is on high gear which mean more torque
     * or if on low gear which mean lower torque and more speed
     *
     * @return if the robot is on high gear
     */
    public boolean isOnHighGear() {
        return shifter.get() == DoubleSolenoid.Value.kForward;
    }

    /**
     * This method return if the robot hasn't shifted recently or turn
     * to check if the robot can shift gear
     *
     * @return if the drivetrain can shift gear
     */
    public boolean canShift() {
        return (Math.abs(getLeftVelocity() - getRightVelocity()) < DIFFERENTIAL_TOLERANCE) && (shiftCounter.get() > MIN_SHIFT_TIME) && isShiftingEnabled;
    }

    /**
     * This method check if the acceleration is higher than the minimal acceleration for gear shifting
     * and if the velocity is higher than the minimal velocity for shifting
     *
     * @return if the robot should shift the gear up
     */
    public boolean canShiftHigh() {
        return (Robot.navx.getRawAccelX() > SHIFT_HIGH_ACCELERATION) && ((getRightVelocity() + getLeftVelocity()) / 2 > SHIFT_HIGH_POINT && !isOnHighGear());
    }

    /**
     * This method check if the acceleration is lower than the minimal acceleration for gear shifting
     * and if the velocity is lower than the minimal velocity for shifting
     *
     * @return if the robot should shift the gear down
     */
    public boolean canShiftLow() {
        return (Robot.navx.getRawAccelX() < SHIFT_LOW_ACCELERATION) && ((getRightVelocity() + getLeftVelocity()) / 2 < SHIFT_LOW_POINT && isOnHighGear());
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveTypeChooser());
    }
}
