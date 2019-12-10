package robot;

/**
 * A class holding all of the ports on the robot.
 * Place mechanism-specific ports inside their own sub-class.
 * When accessing a mechanism-specific port, call Ports.[MECHANISM].[PORT_NAME]
 */
public class Ports {
    public static class Drivetrain {
        public static final boolean leftMasterReversed = true;
        public static final int LEFT_MASTER = 16;
        public static final int LEFT_SLAVE_1 = 15;
        public static final int LEFT_SLAVE_2 = 14;
        public static final boolean leftSlave1Reversed = true;
        public static final boolean leftSlave2Reversed = true;
        public static final boolean rightMasterReversed = false;
        public static final boolean rightSlave1Reversed = false;
        public static final boolean rightSlave2Reversed = false;

        public static final int RIGHT_MASTER = 11;
        public static final int RIGHT_SLAVE_1 = 12;
        public static final int RIGHT_SLAVE_2 = 13;
    }
}
