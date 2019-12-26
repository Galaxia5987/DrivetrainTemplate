package robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import robot.Robot;

public class ToggleCompressor extends InstantCommand {

    public ToggleCompressor(){

    }

    protected void initialize() {
        boolean isEnabled = Robot.compressor.enabled();
        if (isEnabled){
            Robot.compressor.stop();
        }else {
            Robot.compressor.start();

        }
    }

}
