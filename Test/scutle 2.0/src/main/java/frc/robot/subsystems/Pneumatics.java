// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import edu.wpi.first.wpilibj.PneumaticHub;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;



public class Pneumatics extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private DoubleSolenoid lifter;
  //private Timer time;
  // private Relay cannon;
  private Spark cannon2;
  private final PneumaticHub hub = new PneumaticHub();
  private final Compressor m_compressor = new Compressor(PneumaticsModuleType.REVPH);
  private GenericEntry chonkyDragon ;

  public Pneumatics() {
   lifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.Pneumatics.PNEUMATICS_LIFTER_UP, Constants.Pneumatics.PNEUMATICS_LIFTER_DN);
    //time = new Timer();
    // cannon = new Relay(7);
    cannon2 = new Spark(2);
    addShuffleBoard();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void fire(){
    // cannon.set(Relay.Value.kOn);
    cannon2.set(1);;
    }
  
  public void stop(){
    // cannon.set(Relay.Value.kOff);
    cannon2.set(0);;
  }
  public void liftUp(){
    lifter.set(Value.kForward);
  }

  public void liftDn(){
    lifter.set(Value.kReverse);
  }
  public void disable(){
    lifter.set(Value.kOff);
  }
  public void enableCompressor(){
    // m_compressor.enableAnalog(70, 120);
    // m_compressor.enableHybrid(70, 120);
    m_compressor.enableDigital();
  }
  public void resetCompressor(){
    // m_compressor.enableAnalog(70, 120);
    // m_compressor.enableHybrid(70, 120);
    //m_compressor.disable();
    //m_compressor.close();
  }
  public boolean getCompressor(){
    return m_compressor.isEnabled();
  }

  public void toggleCompressor(){
    if (getCompressor() == true )
    {
     //set the pressure hopefully!!
     m_compressor.enableHybrid(0, chonkyDragon.getDouble(Constants.Pneumatics.PNEUMATICS_PRESSURE_LIMIT));

     // disableCompressor();
    }
    else
    {
      //enableCompressor();
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  public void addShuffleBoard(){


    ShuffleboardTab tab = Shuffleboard.getTab("Pneumatics");
    tab.add("Lifter", lifter);
    tab.add("Compressor", m_compressor);
    //tab.addDouble("PH Pressure [PSI]", this.hub.getPressure(0));

   // tab.addDouble("PH Pressure [PSI]2", this.hub.getPressure(1));
    // Get compressor current draw.
    tab.addDouble("Compressor Current", m_compressor::getCurrent);
    // Get whether the compressor is active.
    tab.addBoolean("Compressor Active", m_compressor::isEnabled);
    // Get the digital pressure switch connected to the PCM/PH.
    // The switch is open when the pressure is over ~120 PSI.
    tab.addBoolean("Pressure Switch", m_compressor::getPressureSwitchValue);

    chonkyDragon = Shuffleboard.getTab("Pneumatics")
        .add("Pressure Limit", Constants.Pneumatics.PNEUMATICS_PRESSURE_LIMIT)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 130))
        .getEntry();
  }
}
