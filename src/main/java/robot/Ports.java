package robot;

/**
 * A class holding all of the ports on the robot.
 * Place mechanism-specific ports inside their own sub-class.
 * When accessing a mechanism-specific port, call Ports.[MECHANISM].[PORT_NAME]
 */
public class Ports {

    public static class Drivetrain {
        public static final int leftMaster = 16;
        public static final int rightMaster = 11;
        public static final int leftSlave1 = 15;
        public static final int rightSlave1 = 12;
        public static final int leftSlave2 = 14;
        public static final int rightSlave2 = 13;
        public static final int shifterUp = 0;
        public static final int shifterDown = 0;

        public static final boolean leftMasterReversed = true;
        public static final boolean leftSlave1Reversed = true;
        public static final boolean leftSlave2Reversed = true;
        public static final boolean rightMasterReversed = false;
        public static final boolean rightSlave1Reversed = false;
        public static final boolean rightSlave2Reversed = false;
    }

}
