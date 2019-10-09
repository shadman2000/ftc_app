package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoDepot", group="Pushbot")
public class AutoDepot extends LinearOpMode{

    HardwareBot robot = new HardwareBot();
    ElapsedTime runtime = new ElapsedTime();
    
    Vuforia vuf = new Vuforia();

    //Start declaring the variables
    static final double COUNTS_PER_MOTOR_REV = 280;     //The Motor we have Encoder
    static final double COUNTS_PER_INCH = COUNTS_PER_MOTOR_REV/Math.PI;
    static final double DRIVE_SPEED = 0.7;
    static final double TURN_SPEED = 0.7;
    static final double FORWARD_SPEED = 1.0;
    
    private String positionOfRobot = "";

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        vuf.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        //Wait for player to hit start
        waitForStart();
        stopAndResetEncoders();
        
        // descentOntoGround();
        locateGoldMaterial();
        pushGoldMaterial();
        placeTeamMarker();
        parkInCrater();
        

        //END POWERS
        robot.leftWheelFront.setPower(0);
        robot.leftWheelBack.setPower(0);
        robot.rightWheelFront.setPower(0);
        robot.rightWheelBack.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

    }
    
    public void descentOntoGround() {
        robot.hangElevator.setPower(1);
        sleep(4200);
        robot.hangElevator.setPower(0);
        sleep(1000);
        drive(-4);
        strafe(-2);
        drive(4);
    }
    
    public void locateGoldMaterial() {
        double location = -1;
        
        // rotate and drive towards materials
        strafe(-16);
        
        // detect if gold mineral is at center
        positionOfRobot = "center";
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2 && location < 0) {
            vuf.update();
            location = vuf.getGoldMineralLeft();
        }
        if (location != -1) return;
        
        // go left and detect if gold mineral is at the left
        drive(16);
        positionOfRobot = "left";
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2 && location < 0) {
            vuf.update();
            location = vuf.getGoldMineralLeft();
        }
        if (location != -1) return;
        
        // if the code goes this far, the gold mineral must be on the right
        drive(-32);
        sleep(100);
        positionOfRobot = "right";
    }
    
    public void pushGoldMaterial() {
        rotate(90);
        sleep(100);
        drive(18); // forward to push gold material
        sleep(100);
        drive(-18); // back to original position
        sleep(100);
    }
    
    public void placeTeamMarker() {
        if (positionOfRobot.equals("left"))
            strafe(5);
        else if (positionOfRobot.equals("center"))
            strafe(20);
        else if (positionOfRobot.equals("right"))
            strafe(35);
            
        rotate(-40);
        sleep(100);
        drive(32);
        sleep(100);
        
        // go to depot
        rotate(90);
        sleep(100);
        drive(40);
        sleep(100);
    }
    
    public void parkInCrater() {
        drive(-110);
        sleep(100);
    }
    

    
    public void rotate(double degrees) {
        double circumference = 91;
        double arc = circumference * (degrees / 360);
        encoderDrive(TURN_SPEED, arc, -arc, arc, -arc, 10.0);
    }

    public void strafe(double inches) {
        encoderDrive(DRIVE_SPEED, -inches, inches, inches, -inches, 5.0);
    }

    public void drive(double inches, double speed){
        encoderDrive(speed, inches,inches,inches,inches,10.0);
    }

    public void drive(double inches){
        encoderDrive(DRIVE_SPEED, inches,inches,inches,inches,10.0);
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
    public void pull(double speed, double inches){

        int target;


        // Ensure that the opmode is still active
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 10 &&
            robot.hangElevator.isBusy()) {

            // Determine new target position, and pass to motor controller
            target = robot.hangElevator.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
            robot.hangElevator.setTargetPosition(target);



            // Turn On RUN_TO_POSITION
            robot.hangElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            robot.hangElevator.setPower(Math.abs(speed));


        }
    }
}
