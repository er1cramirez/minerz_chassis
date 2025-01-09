// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private final WPI_VictorSPX frontLeftM = new WPI_VictorSPX(0);
  private final WPI_VictorSPX rearLeftM = new WPI_VictorSPX(1);
  private final WPI_VictorSPX frontRightM = new WPI_VictorSPX(2);
  private final WPI_VictorSPX rearRightM = new WPI_VictorSPX(3);

  private final CANSparkMax motor_1 = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax motor_2 = new CANSparkMax(2, MotorType.kBrushless);

  private final XboxController controller = new XboxController(0);
  private final XboxController controller2 = new XboxController(1);

  @Override
  public void robotInit() {

    motor_1.restoreFactoryDefaults();
    motor_2.restoreFactoryDefaults();


    motor_2.setInverted(true);
    motor_2.follow(motor_1);
  }

  @Override
  public void teleopPeriodic() {
    DifferentialDrive(controller.getLeftY(), controller.getRightY());
    Double sparkVel = MathUtil.applyDeadband(controller2.getRightY(), 0.1) * 0.5;
    motor_1.set(sparkVel);
  }

  public void DifferentialDrive(double leftSpeed, double rightSpeed) {
    frontLeftM.set(leftSpeed);
    rearLeftM.set(leftSpeed);
    frontRightM.set(rightSpeed);
    rearRightM.set(rightSpeed);
  }
}
