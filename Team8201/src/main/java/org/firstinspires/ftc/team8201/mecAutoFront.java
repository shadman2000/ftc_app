package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "FrontEncoder", group = "Autonomous")
public class mecAutoFront extends LinearOpMode {
    mechard robot = new mechard();
    private ElapsedTime runtime = new ElapsedTime();

    //Start declaring the variables
    static final double COUNTS_PER_MOTOR_REV = 280;     //The Motor we have Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;    // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        // Reset encoders
        stopAndResetEncoders();

        // Wait for "PLAY"
        waitForStart();

        //Testing
        encoderDrive(DRIVE_SPEED, 50.0, 50.0, 50.0, 50.0, 3);
        robot.rightWheelBack.setPower(0.0);
        // sleep(2000);
        // encoderTurn(90);
        // sleep(2000);
        // encoderMoveLeft(10.0);
        // sleep(2000);
        // encoderMoveRight(10.0);
    }

    // Reset encoders and kill motors
    public void stopAndResetEncoders() {
        robot.leftWheelFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightWheelFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(50); // Wait 50ms to make sure it fully processes

        robot.leftWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void encoderDrive(double speed, double leftFrontInches, double rightFrontInches, double leftBackInches, double rightBackInches , double timeoutS){

        int newLeftFront;
        int newRightFront;
        int newLeftBack;
        int newRightBack;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftFront = robot.leftWheelFront.getCurrentPosition() + (int) (leftFrontInches * COUNTS_PER_INCH);
            newRightFront = robot.rightWheelFront.getCurrentPosition() + (int) (rightFrontInches * COUNTS_PER_INCH);
            newLeftBack = robot.leftWheelBack.getCurrentPosition() + (int) (leftBackInches * COUNTS_PER_INCH);
            newRightBack = robot.rightWheelBack.getCurrentPosition() + (int) (rightBackInches * COUNTS_PER_INCH);
            robot.leftWheelFront.setTargetPosition(newLeftFront);
            robot.rightWheelFront.setTargetPosition(newRightFront);
            robot.leftWheelBack.setTargetPosition(newLeftBack);
            robot.rightWheelBack.setTargetPosition(newRightBack);


            // Turn On RUN_TO_POSITION
            robot.leftWheelFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightWheelFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftWheelBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightWheelBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftWheelFront.setPower(Math.abs(speed));
            robot.rightWheelFront.setPower(Math.abs(speed));
            robot.leftWheelBack.setPower(Math.abs(speed));
            robot.rightWheelBack.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftWheelFront.isBusy() && robot.rightWheelFront.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftFront, newRightFront, newLeftBack, newRightBack);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        robot.leftWheelFront.getCurrentPosition(),
                        robot.rightWheelFront.getCurrentPosition());
                telemetry.update();
            }

        }
    }

    public void encoderTurn(double degrees) {
        double circumference = 54.0;
        double arc = circumference * (degrees / 360);
        encoderDrive(TURN_SPEED, arc, -arc, arc, -arc, 5.0);
    }

    public void encoderMoveLeft(double inches){                 //Test it out
        encoderDrive(DRIVE_SPEED, -inches, inches, inches, -inches, 5.0);
    }

    public void encoderMoveRight(double inches){                 //Test it out
        encoderDrive(DRIVE_SPEED, inches, -inches, -inches, inches, 5.0);
    }
}