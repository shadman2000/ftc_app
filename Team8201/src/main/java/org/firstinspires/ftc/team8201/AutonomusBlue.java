package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Blue", group = "Autonomous")
public class AutonomusBlue extends LinearOpMode {
    HardwareK9bot robot = new HardwareK9bot();
    private ElapsedTime runtime = new ElapsedTime();

    //Start declaring the variables
    static final double COUNTS_PER_MOTOR_REV = 280;     //The Motor we have Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    @Override
    public void runOpMode() {
        encoderDrive(DRIVE_SPEED, 10.0, 10.0, 5.0);     //Just to test if it goes straight
        encoderTurn(90);                                                     //Calling the turn function
    }

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS){

        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.leftWheelFront.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightWheelBack.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            robot.leftWheelFront.setTargetPosition(newLeftTarget);
            robot.rightWheelFront.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.leftWheelFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightWheelFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftWheelFront.setPower(Math.abs(speed));
            robot.rightWheelFront.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftWheelFront.isBusy() && robot.rightWheelFront.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        robot.leftWheelFront.getCurrentPosition(),
                        robot.rightWheelFront.getCurrentPosition());
                telemetry.update();
            }

        }
    }

    public void encoderTurn(double degrees) {
        double circumference = 54.0;
        double arc = circumference * (degrees / 360);           //Formula that get's the turn inches
        encoderDrive(TURN_SPEED, arc, -arc, 5.0);
    }
}