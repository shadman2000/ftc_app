package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RhinoAuto extends LinearOpMode {
    
    static final double COUNTS_PER_MOTOR_REV = 280;
    static final double COUNTS_PER_INCH      = COUNTS_PER_MOTOR_REV / Math.PI;
    static final double DRIVE_SPEED          = 0.7;
    static final double TURN_SPEED           = 0.7;
    static final double FORWARD_SPEED        = 1.0;
    
    public HardwareBot robot    = new HardwareBot();
    public Vuforia vuf          = new Vuforia();
    public ElapsedTime runtime  = new ElapsedTime();
    
    public String positionOfRobot = "";

    
    @Override
    public void runOpMode() {}
    
    public void initAutonomous() {
        robot.init(hardwareMap);
        vuf.init(hardwareMap);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        
        stopAndResetEncoders();
    }
    
    public void finish() {
        // robot.stopAllMotors();

        telemetry.addData("Status", "Complete");
        telemetry.update();
    }
    
    public void descentOntoGround() {
        robot.hangElevator.setPower(0.5); // Positive => Down
        sleep(5000);
        robot.hangElevator.setPower(0);
        sleep(500);
        drive(-4);
        strafe(-2);
        drive(4);
    }
    
    public void driveTowardsMinerals() {
        strafe(-16);
    }
    
    public void locateGoldMineral() {
        double location = -1;
        
        // Check if gold mineral is at the center
        positionOfRobot = "center";
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2 && location < 0) {
            vuf.update();
            location = vuf.getGoldMineralLeft();
        }
        if (location != -1) return; // If found, exit the method
        
        // Check if gold mineral is on the right
        drive(-16);
        positionOfRobot = "right";
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2 && location < 0) {
            vuf.update();
            location = vuf.getGoldMineralLeft();
        }
        if (location != -1) return; // If found, exit the method
        
        // If the code goes this far, the gold mineral must be on the left
        drive(32);
        positionOfRobot = "left";
    }
    
    public void pushGoldMineral() {
        rotate(-90);
        drive(-15); // forward to push gold mineral
        // drive(15); // back to original position
    }
    
    public void placeTeamMarker() {
        // place team marker in depot
        // robot.leftFlipElevator.setPower(1);
        // robot.rightFlipElevator.setPower(1);
        // sleep(250);
        // robot.leftFlipElevator.setPower(-0.3);
        // robot.rightFlipElevator.setPower(-0.3);
        // sleep(750);
        // robot.leftFlipElevator.setPower(0);
        // robot.rightFlipElevator.setPower(0);
    }
    
    public void extendSlider() {
        robot.slider.setPower(0.2);
        sleep(1200);
        robot.slider.setPower(0);
    }
    
    
    public void putCollectorDown() {
        robot.collector.setPower(-0.2);
        sleep(500);
        robot.collector.setPower(0);
    }
    
    public void putCollectorUp() {
        robot.collector.setPower(0.2);
        sleep(500);
        robot.collector.setPower(0);
    }
    
    

    public void rotate(double degrees) {
        double circumference = 91;
        double arc = circumference * (degrees / 360);
        encoderDrive(TURN_SPEED, arc, -arc, arc, -arc, 10.0);
        sleep(200);
    }

    public void strafe(double inches){                 //Test it out
        encoderDrive(DRIVE_SPEED, -inches, inches, inches, -inches, 5.0);
        sleep(200);
    }

    public void drive(double inches, double speed){
        encoderDrive(speed, inches,inches,inches,inches,10.0);
        sleep(200);
    }

    public void drive(double inches){
        encoderDrive(DRIVE_SPEED, inches,inches,inches,inches,10.0);
        sleep(200);
    }

    public void stopAndResetEncoders() {
        robot.leftWheelFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightWheelFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sleep(50); // Wait 50ms to make sure it fully processes

        robot.leftWheelBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightWheelBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
            robot.leftWheelBack.setTargetPosition(newLeftBack);
            robot.rightWheelBack.setTargetPosition(newRightBack);
            robot.rightWheelFront.setTargetPosition(newRightFront);


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
                telemetry.addData("lf" , newLeftFront);
                telemetry.addData("rb" , newRightBack);
                telemetry.update();
            }

        }
    }

    
}
