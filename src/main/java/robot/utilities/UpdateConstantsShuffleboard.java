package robot.utilities;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.security.Key;

public class UpdateConstantsShuffleboard {

    UpdateConstantsShuffleboard(String[] names ,double... constants){
        for (int i = 0; i < constants.length; i++ ){
            constants[i] = getConstant(names[i], constants[i]);
        }
    }

    private double getConstant(String key, double value){
        SmartDashboard.putNumber(key,SmartDashboard.getNumber(key,value));
        return SmartDashboard.getNumber(key,value);
    }
}
