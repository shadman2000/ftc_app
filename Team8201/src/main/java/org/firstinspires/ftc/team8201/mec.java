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

        double cl = 0.0;
        double cr = 0.0;
        double gemA = 0.849;  //0.29
        double hr = 1.0;
        double hl = 0.0;

        // //Initializing the hardwareK9bot file
        robot.init(hardwareMap);

        // //Ready message
        telemetry.addData("Say ", "MecTest robot is ready");
        telemetry.update();

        // //Wait for play
        waitForStart();

        // //opMode code that will run in a loop while we are in opMode
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
            if(gamepad1.dpad_up == true){
                rightWheelPowerFront = 1.0;
                leftWheelPowerFront = 1.0;
            }
            if(gamepad1.dpad_down == true){
                leftWheelPowerBack = 1.0;
                rightWheelPowerBack = 1.0;
            }
            //setting all motor (driving wheel) power to 0 if nothing is pressed
            if(gamepad1.right_trigger <= 0 && gamepad1.left_trigger <= 0 && gamepad1.right_stick_x == 0 && gamepad2.dpad_up == false && gamepad2.dpad_down == false) {         //DO NOT USE AN ELSE!!!!!!!!!!!!!!!!!
                rightWheelPowerFront = 0.0;
                rightWheelPowerBack = 0.0;
                leftWheelPowerFront = 0.0;
                leftWheelPowerBack = 0.0;
            }

            //      //test
            //      if(gamepad1.dpad_down == true){
            //         gemA -= 0.025;
            //         robot.gem.setPosition(gemA);
            //      }

            //      if(gamepad1.dpad_up == true){
            //          gemA += 0.025;
            //          robot.gem.setPosition(gemA);
            //      }

            //Gamepad 2
            //Collector wheels
            if(gamepad2.right_trigger > 0){
                cl = -gamepad2.right_trigger;

            }

            if(gamepad2.left_trigger > 0){
                cl = gamepad2.left_trigger;
            }

            if(gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0){
                cl = 0.0;
                cr = 0.0;
            }

            if(gamepad2.dpad_up){
                robot.liftLeft.setPosition(0);
                robot.liftRight.setPosition(1);
            }

            if(gamepad2.dpad_down){
                robot.liftLeft.setPosition(1);
                robot.liftRight.setPosition(0);
            }

            //Sending the powers as motor Power
            robot.leftWheelFront.setPower(leftWheelPowerFront);
            robot.leftWheelBack.setPower(leftWheelPowerBack);
            robot.rightWheelBack.setPower(rightWheelPowerFront);
            robot.rightWheelFront.setPower(rightWheelPowerBack);
            robot.collectorLeft.setPower(cl);
            robot.collectorRight.setPower(cl);

            //Testing messages
            telemetry.addData("leftWheelPowerFront" , leftWheelPowerFront);
            telemetry.addData("leftWheelPowerBack" , leftWheelPowerBack);
            telemetry.addData("rightWheelPowerFront" , rightWheelPowerFront);
            telemetry.addData("rightWheelPowerBack" , rightWheelPowerBack);
            // telemetry.addData("gemArm" , gemA);
            // telemetry.addData("rightCollector" , cl);
            // telemetry.addData("leftCollector" , cr);
            // telemetry.addData("hl" , hl);
            // telemetry.addData("hr" , hr);
            telemetry.update();

            sleep(40);
        }
    }
}