
package frc.robot.subsystems;

//import com.revrobotics.CANSparkLowLevel;
//import com.revrobotics.CANSparkMax;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.util.Map;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

//import frc.robot.Constants;
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkFlex;

//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkLowLevel;

public class Intake extends SubsystemBase {

    // private PWMSparkMax intakeMotor;
    private PWMVictorSPX intakeMotor1;
    private PWMVictorSPX intakeMotor2;
    private GenericEntry maxSpeed;
    private double intakeDefault = .4;

    public Intake() {
        // intakeMotor = new PWMSparkMax(9);
        intakeMotor1 = new PWMVictorSPX(Constants.motors.intakemotor1);
        intakeMotor2 = new PWMVictorSPX(Constants.motors.intakemotor2);

        intakeMotor1.setInverted(true);
        intakeMotor2.setInverted(false);
        intakeMotor1.addFollower(intakeMotor2);

        maxSpeed = Shuffleboard.getTab("Motors")
                .add("Intake Speed", intakeDefault)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();

    }

    public void suck()

    {
        // .5 was what we got it to shoot well with.
        double speed = maxSpeed.getDouble(intakeDefault);
        intakeMotor1.set(speed);
    }

    public void suck(boolean doIt) {
        if (doIt) {
            suck();
        } else {
            stop();
        }
    }

    public void reversesuck() {
        intakeMotor1.set(-.4);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void stop() {
        intakeMotor1.set(0);
        intakeMotor1.stopMotor();
        intakeMotor2.set(0);
        intakeMotor2.stopMotor();
    }

    public void feed() {
        intakeMotor1.set(.4);
    }

}
