package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Erobot8201", group = "Testing")
public class Erobot8201 extends LinearOpMode {

    //Creating an instance of HardwareK9bot as "robot"
    HardwareK9bot robot = new HardwareK9bot();

    @Override
    public void runOpMode() {

        //Declare the variables
        //Motor(s)
        double leftWheelPower = 0.0;
        double rightWheelPower = 0.0;
        double suckInWheelright = 0.0;
        double suckInWheelLeft = 0.0;

        //servo(s)
        double leftRoller = 0.0;
        double rightRoller = 0.0;

        //Initializing the hardwareK9bot file
        robot.init(hardwareMap);

        //Ready message
        telemetry.addData("Say ", "Eugene robot is ready");
        telemetry.update();

        //Wait for play
        waitForStart();

        //opMode code that will run in a loop while we are in opMode
        while (opModeIsActive()) {
            //Acceleration of both wheels on right trigger
            if(gamepad1.right_trigger > 0) {
                //Redefining the power of both wheel according to the position of the trigger
                leftWheelPower = -gamepad1.right_trigger;
                rightWheelPower = -gamepad1.right_trigger;
                //Setting wheelPowers for turning
                if (gamepad1.left_stick_x > 0) {
                    rightWheelPower = gamepad1.right_trigger;
                } else if (gamepad1.left_stick_x < 0) {
                    leftWheelPower = gamepad1.right_trigger;
                }
            }

            //Deceleration of both wheels on left trigger
            if(gamepad1.left_trigger < 0) {
                //Redefining the power of both wheel according to the position of the trigger
                leftWheelPower = gamepad1.left_trigger;
                rightWheelPower = gamepad1.left_trigger;
                //Setting wheelPowers for turning
                if (gamepad1.left_stick_x > 0) {
                    rightWheelPower = -gamepad1.left_trigger;
                } else if (gamepad1.left_stick_x < 0) {
                    leftWheelPower = -gamepad1.left_trigger;
                }
            }

            //setting all motor (driving wheel) power to 0 if nothing is pressed
            if(gamepad1.right_trigger <= 0 && gamepad1.left_trigger <= 0) {         //DO NOT USE AN ELSE!!!!!!!!!!!!!!!!!
                leftWheelPower = 0.0;
                rightWheelPower = 0.0;
            }

            //The suck-in wheels
            //Need to be tested which way the wheel rotates
            if(gamepad2.right_trigger > 0){
                //Redefining the power of both wheel (suck-in) according to the position of the trigger
                suckInWheelright = gamepad2.right_trigger;
                suckInWheelLeft = gamepad2.right_trigger;

            }
            //The wheels of the suck-in will both rotate outwards with left_trigger
            if (gamepad2.left_trigger > 0) {
                //Redefining the power of both wheel (suck-in) according to the position of the trigger
                suckInWheelLeft = -gamepad2.left_trigger;
                suckInWheelright = -gamepad2.left_trigger;
            }


            //Stopping the wheel when none is pressed
            if(gamepad2.left_trigger <= 0 && gamepad2.right_trigger <= 0 && gamepad2.a == false){
                suckInWheelLeft = 0.0;
                suckInWheelright = 0.0;
            }

            //The gem arm
            //drop
            if(gamepad2.dpad_down){
                robot.gemArm.setPosition(1.0); //Need to test accoring to the initial
            }
            //up
            if(gamepad2.dpad_up){
                robot.gemArm.setPosition(0.0); //Need to test according to the initial
            }


            //Sending the powers as motor Power
            robot.leftWheelFront.setPower(leftWheelPower * 0.6);
            robot.rightWheelFront.setPower(rightWheelPower * 0.6);


        }
    }
}