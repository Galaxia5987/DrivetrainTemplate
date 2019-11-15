package robot;

/**
 * A class holding all of the constants of every mechanism on the robot.
 * Place global constants in this class, and mechanism-specific constants inside their respective mechanism subclass.
 * When accessing a mechanism-specific port, call Constants.[MECHANISM].[CONSTANT]
 */
public class Constants {
    //All general constants go here
    //public static final double TIME_STEP = 0.02;


    public static class Drivetrain {
        public static final double TICKS_PER_METER = 256 / (4 * 0.0254 * Math.PI);
        public static final double MAX_VEL = 3;// in m/s
        public static final double TIME_STEP = 0.02;
        public static final double MAX_ACCELERATION = 0.4;// in m/s^2 (currently not the correct number)
        public static final int MAX_CURRENT = 35;
        public static final int DIFFERENTIAL_TOLERANCE = 0; //in m/s(not the correct number)
        public static final int MIN_SHIFT_TIME = 1; //the minimal time between shifts
        public static final int SHIFT_UP_POINT = 2;// the velocity that the robot can shift to a higher gear in m/s(not the correct number)
        public static final int SHIFT_UP_ACCELERATION = 2; // the minimal acceleration to shift to a higher gear in m/s^2 (not the correct number)
        public static final int SHIFT_DOWN_POINT = 1;// the velocity that the robot should shift to a lower gear in m/s(not the correct number)
        public static final int SHIFT_DOWN_ACCELERATION = 2; // the acceleration to shift to a lower gear in m/s^2 (not the correct number)
    }


    public static class ExampleSubsystem1 {
        //All of the Subsystem specific constants go here,and need to be static.

        //public static final double TICKS_PER_METER = 256 / (4*0.0254*Math.PI);
        //public static final double MAX_VELOCITY = 5;
    }
}