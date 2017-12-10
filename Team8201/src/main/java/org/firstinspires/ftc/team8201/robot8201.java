package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "robot8201", group = "Testing")
public class robot8201 extends LinearOpMode {

    //Creating an instance of HardwareK9bot as "robot"
    HardwareK9bot robot = new HardwareK9bot();

    @Override
    public void runOpMode() {

        //Declare the variables
        //Motor(s)
        double leftWheelPowerFront = 0.0;
        double leftWheelPowerBack = 0.0;
        double rightWheelPowerFront = 0.0;
        double rightWheelPowerBack = 0.0;
        double suckInWheelRight = 0.0;
        double suckInWheelLeft = 0.0;
        double elevatorPower = 0.0;
        //servo(s)
        double rightCollectorPower = 0.0;
        double leftCollectorPower = 0.0;
        double gemArm;

        //Initializing the hardwareK9bot file
        robot.init(hardwareMap);

        //Sensor(s)
        DigitalChannel digitalTouch;
        digitalTouch = hardwareMap.get(DigitalChannel.class, "groundTouch");
        // set the digital channel to input.
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        //Ready message
        telemetry.addData("Say ", "8201 robot is ready");
        telemetry.update();

        //Wait for play
        waitForStart();

        //opMode code that will run in a loop while we are in opMode
        while (opModeIsActive()) {
            //Acceleration of both wheels on right trigger
            if(gamepad1.right_trigger > 0) {
                //Redefining the power of both wheel according to the position of the trigger
                leftWheelPowerFront = -gamepad1.right_trigger;
                leftWheelPowerBack = -gamepad1.right_trigger;
                rightWheelPowerFront = -gamepad1.right_trigger;
                rightWheelPowerBack = -gamepad1.right_trigger;
                //Setting wheelPowers for turning
                if (gamepad1.left_stick_x > 0) {
                    rightWheelPowerFront = gamepad1.right_trigger;
                    rightWheelPowerBack = gamepad1.right_trigger;
                } else if (gamepad1.left_stick_x < 0) {
                    leftWheelPowerFront = gamepad1.right_trigger;
                    leftWheelPowerBack = gamepad1.right_trigger;
                }
            }

            //Deceleration of both wheels on left trigger
            if(gamepad1.left_trigger > 0) {
                //Redefining the power of both wheel according to the position of the trigger
                leftWheelPowerFront = gamepad1.left_trigger;
                leftWheelPowerBack = gamepad1.left_trigger;
                rightWheelPowerFront = gamepad1.left_trigger;
                rightWheelPowerBack = gamepad1.left_trigger;
                //Setting wheelPowers for turning
                if (gamepad1.left_stick_x > 0) {
                    rightWheelPowerFront = -gamepad1.left_trigger;
                    rightWheelPowerBack = -gamepad1.left_trigger;
                } else if (gamepad1.left_stick_x < 0) {
                    leftWheelPowerFront = -gamepad1.left_trigger;
                    leftWheelPowerBack = -gamepad1.left_trigger;
                }
            }
            //setting all motor (driving wheel) power to 0 if nothing is pressed
            if(gamepad1.right_trigger <= 0 && gamepad1.left_trigger <= 0) {         //DO NOT USE AN ELSE!!!!!!!!!!!!!!!!!
                rightWheelPowerFront = 0;
                rightWheelPowerBack = 0;
                leftWheelPowerFront = 0;
                leftWheelPowerBack = 0;
            }

            //The suck-in wheels
            //Need to be tested which way the wheel rotates
            if(gamepad2.right_trigger > 0){
                //Redefining the power of both wheel (suck-in) according to the position of the trigger
                suckInWheelLeft = gamepad2.right_trigger;
                suckInWheelRight = gamepad2.right_trigger;

            }
            //The wheels of the suck-in will both rotate outwards with left_trigger
            if (gamepad2.left_trigger > 0) {
                //Redefining the power of both wheel (suck-in) according to the position of the trigger
                suckInWheelLeft = -gamepad2.left_trigger;
                suckInWheelRight = -gamepad2.left_trigger;
            }
            //Stopping the wheel when none is pressed
            if(gamepad2.left_trigger <= 0 && gamepad2.right_trigger <= 0){
                suckInWheelLeft = 0;
                suckInWheelRight = 0;
            }

            //The elevator
            //Need to be tested which way the wheel rotates
            //The motors will go both up and down according to the y_axis of gamepad2 rightstick
            if (gamepad2.right_stick_y > 0) {
                //Motor power goes up so as the elevator
                elevatorPower = gamepad2.right_stick_y;
            }
            //Down goes elevator
            if (gamepad2.right_stick_y < 0) {
                //Motor power goes up so as the elevator
                elevatorPower = gamepad2.right_stick_y;

            }
            //stop it when it's 0
            if(gamepad2.right_stick_y == 0) {
                elevatorPower = 0;
            }
            //Stop also when ground touch sensor is activated
            if (digitalTouch.getState() == true && gamepad2.right_stick_y < 0) {
                elevatorPower = 0;
            }


//            //The gem arm
//            //drop
//            if(gamepad2.dpad_down){
//                robot.gemArm.setPosition(1); //Need to test accoring to the initial
//            }
//
//            //up
//            if(gamepad2.dpad_up){
//                robot.gemArm.setPosition(0); //Need to test according to the initial
//            }
//

            //The cube collector
            //Limiting the power(s) of servo
            //TESTED
            if(rightCollectorPower >= 0.75){
                rightCollectorPower = 0.75;
            }
            if(rightCollectorPower <= 0.598){
                rightCollectorPower = 0.598;
            }
            if(leftCollectorPower >= 0.38){
                leftCollectorPower = 0.38;
            }
            if(leftCollectorPower <= 0.165){
                leftCollectorPower = 0.165;
            }
            //Gamepad 2 right bumper holder movement
            if(gamepad2.right_bumper){
                //Increasing Servo power
                rightCollectorPower-=0.1;
                leftCollectorPower+=0.1;
                //Sending the powers to the servo
                robot.cubeHolderLeft.setPosition(leftCollectorPower);
                robot.cubeHolderRight.setPosition(rightCollectorPower);
            }
            //Gamepad 2 left bumper holder movement
            if(gamepad2.left_bumper){
                //Increasing Servo power
                rightCollectorPower+=0.1;
                leftCollectorPower-=0.1;
                //Sending the powers to the servo
                robot.cubeHolderLeft.setPosition(leftCollectorPower);
                robot.cubeHolderRight.setPosition(rightCollectorPower);
            }

            //Sending the powers as motor Power
            robot.leftWheelFront.setPower(leftWheelPowerFront);
            robot.leftWheelBack.setPower(leftWheelPowerBack);
            robot.rightWheelBack.setPower(rightWheelPowerFront);
            robot.rightWheelFront.setPower(rightWheelPowerBack);
            robot.suckInWheelleft.setPower(suckInWheelLeft);
            robot.suckInWheelright.setPower(suckInWheelRight);
            robot.elevator.setPower(elevatorPower);

            //Testing messages
            telemetry.addData("leftServo" , leftCollectorPower);
            telemetry.addData("rightServo" , rightCollectorPower);
            telemetry.addData("Left Collector Wheel" , leftCollectorPower);
            telemetry.addData("right Collector Wheel" , rightCollectorPower);
            telemetry.update();
        }
    }
}