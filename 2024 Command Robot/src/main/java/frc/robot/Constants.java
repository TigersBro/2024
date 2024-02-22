// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class OIConstants {
        public static final int kDriverController = 0;
        public static final double kDriveDeadband = 0.05;
        public static final double kArmManualDeadband = 0.05;
        public static final double kArmManualScale = 0.5;
    }
    
    public static final class Drivetrain {
        public static final int kFrontLeftCanId = 1;
        public static final int kFrontRightCanId = 2;
        public static final int kRearLeftCanId = 3;
        public static final int kRearRightCanId = 4;

        public static final boolean kFrontLeftInverted = true;
        public static final boolean kFrontRightInverted = false;
        public static final boolean kRearLeftInverted = true;
        public static final boolean kRearRightInverted = false;

        public static final int kCurrentLimit = 55;

        public static final double kTurningScale = 0.5;
    }

    

        public static final double kSoftLimitReverse = 0.0;
        public static final double kSoftLimitForward = 4.6;

       

       }