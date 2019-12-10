package robot;

/**
 * A class holding all of the ports on the robot.
 * Place mechanism-specific ports inside their own sub-class.
 * When accessing a mechanism-specific port, call Ports.[MECHANISM].[PORT_NAME]
 */
public class Ports {
    public static class Drivetrain {
        public static final int LEFT_MASTER = 16;
        public static final int LEFT_SLAVE_1 = 15;
        public static final int LEFT_SLAVE_2 = 14;

        public static final int RIGHT_MASTER = 11;
        public static final int RIGHT_SLAVE_1 = 12;
        public static final int RIGHT_SLAVE_2 = 13;
    }
}
