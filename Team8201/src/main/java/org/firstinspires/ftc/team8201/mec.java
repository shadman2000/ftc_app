package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "mecTest", group = "Testing")
public class mec extends LinearOpMode {

    //Creating an instance of HardwareK9bot as "robot"
    mechard robot = new mechard();

    @Override
    public void runOpMode() {

        //Declare the variables
        //Motor(s)
        double leftWheelPowerFront = 0.0;
        double leftWheelPowerBack = 0.0;
        double rightWheelPowerFront = 0.0;
        double rightWheelPowerBack = 0.0;

        //Initializing the hardwareK9bot file
        robot.init(hardwareMap);

        //Ready message
        telemetry.addData("Say ", "MecTest robot is ready");
        telemetry.update();

        //Wait for play
        waitForStart();

        //opMode code that will run in a loop while we are in opMode
        while (opModeIsActive()) {
            //Mecanum Wheel
            //Forward and turn
            if(gamepad1.right_trigger > 0) {
                //Redefining the power of both wheel according to the position of the trigger
                leftWheelPowerFront = gamepad1.right_trigger;
                leftWheelPowerBack = gamepad1.right_trigger;
                rightWheelPowerFront = gamepad1.right_trigger;
                rightWheelPowerBack = gamepad1.right_trigger;
                //Setting wheelPowers for turning
                if (gamepad1.left_stick_x > 0) {
                    rightWheelPowerFront = -gamepad1.right_trigger;
                    rightWheelPowerBack = -gamepad1.right_trigger;
                }
                if (gamepad1.left_stick_x < 0) {
                    leftWheelPowerFront = -gamepad1.right_trigger;
                    leftWheelPowerBack = -gamepad1.right_trigger;
                }

            }
            //Backward and turn
            if(gamepad1.left_trigger > 0){
                //Redefining the power of both wheel according to the position of the trigger
                rightWheelPowerBack = -gamepad1.left_trigger;
                rightWheelPowerFront = -gamepad1.left_trigger;
                leftWheelPowerBack = -gamepad1.left_trigger;
                leftWheelPowerFront = -gamepad1.left_trigger;
                //Setting wheelPowers for turning
                if(gamepad1.left_stick_x > 0){
                    rightWheelPowerFront = gamepad1.left_trigger;
                    rightWheelPowerBack = gamepad1.left_trigger;
                }
                if(gamepad1.left_stick_x < 0){
                    leftWheelPowerFront = gamepad1.left_trigger;
                    leftWheelPowerBack = gamepad1.left_trigger;
                }
            }
            //Going right
            if(gamepad1.right_stick_x > 0){
                rightWheelPowerFront = gamepad1.right_stick_x;
                rightWheelPowerBack = -gamepad1.right_stick_x;
                leftWheelPowerFront = gamepad1.right_stick_x;
                leftWheelPowerBack = -gamepad1.right_stick_x;
            }
            //Going Left
            if(gamepad1.right_stick_x < 0){
                leftWheelPowerBack = -gamepad1.right_stick_x;
                leftWheelPowerFront = gamepad1.right_stick_x;
                rightWheelPowerFront = gamepad1.right_stick_x;
                rightWheelPowerBack = -gamepad1.right_stick_x;
            }
            if(gamepad2.dpad_up == true){
                rightWheelPowerFront = 1.0;
                leftWheelPowerFront = 1.0;
            }
            if(gamepad2.dpad_down == true){
                leftWheelPowerBack = 1.0;
                rightWheelPowerBack = 1.0;
            }
            //setting all motor (driving wheel) power to 0 if nothing is pressed
            if(gamepad1.right_trigger <= 0 && gamepad1.left_trigger <= 0 && gamepad1.right_stick_x == 0 && gamepad2.dpad_up == false && gamepad2.dpad_down == false) {         //DO NOT USE AN ELSE!!!!!!!!!!!!!!!!!
                rightWheelPowerFront = 0;
                rightWheelPowerBack = 0;
                leftWheelPowerFront = 0;
                leftWheelPowerBack = 0;
            }
            //Sending the powers as motor Power
            robot.leftWheelFront.setPower(leftWheelPowerFront);
            robot.leftWheelBack.setPower(leftWheelPowerBack);
            robot.rightWheelBack.setPower(rightWheelPowerFront);
            robot.rightWheelFront.setPower(rightWheelPowerBack);

            //Testing messages
            telemetry.addData("leftWheelPowerFront" , leftWheelPowerFront);
            telemetry.addData("leftWheelPowerBack" , leftWheelPowerBack);
            telemetry.addData("rightWheelPowerFront" , rightWheelPowerFront);
            telemetry.addData("rightWheelPowerBack" , rightWheelPowerBack);
            telemetry.update();
        }
    }
}