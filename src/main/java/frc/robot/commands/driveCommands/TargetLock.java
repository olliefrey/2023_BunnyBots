// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivebase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TargetLock extends SequentialCommandGroup {

  private Drivebase drivebase;

  /** Creates a new TargetLock. */
  public TargetLock(Drivebase drivebase, DoubleSupplier speedX, DoubleSupplier speedY, DoubleSupplier rot) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new FindTarget(drivebase, speedX, speedY, rot),
      new TargetDrive(drivebase, speedX, speedY)
    );
  }
}