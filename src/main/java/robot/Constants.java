package robot;

/**
 * A class holding all of the constants of every mechanism on the robot.
 * Place global constants in this class, and mechanism-specific constants inside their respective mechanism subclass.
 * When accessing a mechanism-specific port, call Constants.[MECHANISM].[CONSTANT]
 */
public class Constants {

    public static final double TIME_STEP = 0.02;

    public static class Drivetrain {
        public static final int MAX_CURRENT = 35;

        public static final int DIFFERENTIAL_TOLERANCE = 1; //in m/s(not the correct number)

        public static final int MIN_SHIFT_TIME = 1; //the minimal time between shifts
        public static final double SHIFT_HIGH_POINT = 0.7;// the velocity that the robot can shift to a higher gear in m/s(not the correct number)
        public static final double SHIFT_HIGH_ACCELERATION = 2; // the minimal acceleration to shift to a higher gear in m/s^2 (not the correct number)
        public static final double SHIFT_LOW_POINT = 0.5;// the velocity that the robot should shift to a lower gear in m/s(not the correct number)
        public static final double SHIFT_LOW_CURRENT = 25;// the current which mean the robot attempts to drive but doesn't manage to
        public static final double SHIFT_LOW_ACCELERATION = 1; // the acceleration to shift to a lower gear in m/s^2 (not the correct number)

        //Stores all of the constants which change depending on the state of the shifter.
        // The numbers of the TICKS_PER_METER are equal because the encoder is connected to the wheel and not to the motor

        public enum Shifter {
            TICKS_PER_METER(256 / (4 * 0.0254 * Math.PI), 256 / (4 * 0.0254 * Math.PI)),
            MAX_VEL(3, 3),// not the final number
            MAX_ACCELERATION(5, 5),//not the final number
            ;

            private double lowratio, highratio;

            Shifter(double lowratio, double highratio) {
                this.lowratio = lowratio;
                this.highratio = highratio;
            }

            public double get() {
                return Robot.m_drivetrain.isOnHighGear() ? this.highratio : this.lowratio;
            }
        }
    }
}