package robot;

/**
 * A class holding all of the ports on the robot.
 * Place mechanism-specific ports inside their own sub-class.
 * When accessing a mechanism-specific port, call Ports.[MECHANISM].[PORT_NAME]
 */
public class Ports {

    public static class Drivetrain {
        public static final int LEFT_MASTER_PORT = 16;
        public static final int RIGHT_MASTER_PORT = 11;
        public static final int LEFT_SLAVE_1_PORT = 15;
        public static final int RIGHT_SLAVE_1_PORT = 12;
        public static final int LEFT_SLAVE_2_PORT = 14;
        public static final int RIGHT_SLAVE_2_PORT = 13;
      
        public static final int SHIFTER_FORWARD_PORT = 1;
        public static final int SHIFTER_REVERSE_PORT = 0;

        public static final boolean LEFT_MASTER_REVERSED = true;
        public static final boolean LEFT_SLAVE_1_REVERSED = true;
        public static final boolean LEFT_SLAVE_2_REVERSED = true;
        public static final boolean RIGHT_MASTER_REVERSED = false;
        public static final boolean RIGHT_SLAVE_1_REVERSED = false;
        public static final boolean RIGHT_SLAVE_2_REVERSED = false;
    }

}
