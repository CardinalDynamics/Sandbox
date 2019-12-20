/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  //pineapple

  private Spark BunnyLift, intake;
  private XboxController control;
  private Talon motor1L, motor2L, motor3L,motor4L, motor1R, motor2R, motor3R, motor4R;
  private SpeedControllerGroup Left, Right;
  // private Solenoid exampleSolenoid;
  private final Timer m_timer = new Timer();  

  // private Compresor c;
  @Override
  public void robotInit() {
    // Solenoid.exampleSolenoid = Solenoid(1);
    control = new XboxController(1);

    motor1L = new Talon(0);
    motor2L = new Talon(1);
    motor3L = new Talon(2);
    motor1R = new Talon(7);
    motor2R = new Talon(8);
    motor3R = new Talon(9);

    BunnyLift = new Spark(5);
    intake = new Spark(4);
    // c = new Compresor(0);
    Left = new SpeedControllerGroup(motor1L, motor2L, motor3L);
    Right = new SpeedControllerGroup(motor1R, motor2R, motor3R);

    m_myRobot = new DifferentialDrive(Left, Right);
  }

   /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      m_myRobot.arcadeDrive(-0.5, 0.0); // drive forwards half speed
      intake.set(0.8);
    } else {
      m_myRobot.stopMotor(); // stop robot
      intake.stopMotor();
    }
  }

  @Override
  public void teleopPeriodic() {
    
    m_myRobot.arcadeDrive(control.getY(Hand.kLeft), control.getX(Hand.kRight));

    
     if (control.getAButton())
    {
      BunnyLift.set(0.5);
    }

    else if (control.getBButton())
    {
      BunnyLift.set(-0.5);
    }

    else
    {
      BunnyLift.stopMotor();
    }

    if(control.getXButton()){
      intake.set(0.8);
    }
    else if(control.getYButton()){
      intake.set(-0.8);
    }
    else {
      intake.stopMotor();
    }

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    // SmartDashboard.putNumber("LimelightArea", area);
  }
}
