package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

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

        //Initializing the hardwareK9bot file
        robot.init(hardwareMap);

        //Sensor(s)
        TouchSensor groundTouch;
        TouchSensor cubeTouch;
        cubeTouch = hardwareMap.get(TouchSensor.class,"cubeTouch");
        groundTouch = hardwareMap.get(TouchSensor.class, "groundTouch");



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
            // //Mecanum Wheel
            // //Forward and turn
            // if(gamepad1.right_trigger > 0) {
            //     //Redefining the power of both wheel according to the position of the trigger
            //     leftWheelPowerFront = -gamepad1.right_trigger;
            //     leftWheelPowerBack = -gamepad1.right_trigger;
            //     rightWheelPowerFront = -gamepad1.right_trigger;
            //     rightWheelPowerBack = -gamepad1.right_trigger;
            //     //Setting wheelPowers for turning
            //     if (gamepad1.left_stick_x > 0) {
            //         rightWheelPowerFront = gamepad1.right_trigger;
            //         rightWheelPowerBack = gamepad1.right_trigger;
            //     }
            //     if (gamepad1.left_stick_x < 0) {
            //         leftWheelPowerFront = gamepad1.right_trigger;
            //         rightWheelPowerBack = gamepad1.right_trigger;
            //     }

            // }
            // //Backward and turn
            // if(gamepad1.left_trigger > 0){
            //     //Redefining the power of both wheel according to the position of the trigger
            //     rightWheelPowerBack = gamepad1.left_trigger;
            //     rightWheelPowerFront = gamepad1.left_trigger;
            //     leftWheelPowerBack = gamepad1.left_trigger;
            //     leftWheelPowerFront = gamepad1.left_trigger;
            //     //Setting wheelPowers for turning
            //     if(gamepad1.left_stick_x > 0){
            //         rightWheelPowerFront = -gamepad1.left_trigger;
            //         rightWheelPowerBack = -gamepad1.right_trigger;
            //     }
            //     if(gamepad1.right_stick_x < 0){
            //         leftWheelPowerBack = -gamepad1.left_trigger;
            //         rightWheelPowerFront = -gamepad1.left_trigger;
            //     }
            // }
            // //Going right
            // if(gamepad1.right_stick_x > 0){
            //     rightWheelPowerFront = -gamepad1.right_stick_x;
            //     rightWheelPowerBack = gamepad1.right_stick_x;
            //     leftWheelPowerFront = gamepad1.right_stick_x;
            //     leftWheelPowerBack = -gamepad1.right_stick_x;
            // }
            // //Going Left
            // if(gamepad1.right_stick_x < 0){
            //     leftWheelPowerBack = gamepad1.right_stick_x;
            //     leftWheelPowerFront = -gamepad1.right_stick_x;
            //     rightWheelPowerFront = gamepad1.right_stick_x;
            //     rightWheelPowerBack = -gamepad1.right_stick_x;
            // }
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
            //The motors will go both up and down according to the y_axis of gamepad2 rightstick
            //Motor power goes up so as the elevator
            elevatorPower = -gamepad2.right_stick_y;
            //Stoping the elevator when touches the ground
            if (groundTouch.isPressed() == true && elevatorPower < 0) {
                elevatorPower = 0;
            }
            //Stopping the elevator when a cube is touched
            if(cubeTouch.isPressed() == true && elevatorPower < 0){
                elevatorPower = 0;
            }

            //The gem arm
            //drop
            if(gamepad2.dpad_down){
                robot.gemArm.setPosition(0.8); //Need to test accoring to the initial
            }
            //up
            if(gamepad2.dpad_up){
                robot.gemArm.setPosition(0.3); //Need to test according to the initial
            }


            //The cube collector
            //Limiting the power(s) of servo
            //TESTED
            if(rightCollectorPower >= 0.46){
                rightCollectorPower = 0.46;
            }
            if(rightCollectorPower <= 0.29){
                rightCollectorPower = 0.29;
            }
            if(leftCollectorPower >= 0.79){
                 leftCollectorPower = 0.79;
            }
            if(leftCollectorPower <= 0.57){
                leftCollectorPower = 0.57;
            }
            //Gamepad 2 right bumper holder movement
            if(gamepad2.right_bumper){
                //Increasing Servo power
                rightCollectorPower-=0.01;
                leftCollectorPower+=0.01;
                //Sending the powers to the servo
                robot.cubeHolderLeft.setPosition(leftCollectorPower);
                robot.cubeHolderRight.setPosition(rightCollectorPower);
            }
            //Gamepad 2 left bumper holder movement
            if(gamepad2.left_bumper){
                //Increasing Servo power
                rightCollectorPower+=0.01;
                leftCollectorPower-=0.01;
                //Sending the powers to the servo
                robot.cubeHolderLeft.setPosition(leftCollectorPower);
                robot.cubeHolderRight.setPosition(rightCollectorPower);
            }

            //Sending the powers as motor Power
            robot.leftWheelFront.setPower(leftWheelPowerFront * 0.6);
            robot.leftWheelBack.setPower(leftWheelPowerBack * 0.6);
            robot.rightWheelBack.setPower(rightWheelPowerFront * 0.6);
            robot.rightWheelFront.setPower(rightWheelPowerBack * 0.6);
            robot.suckInWheelleft.setPower(suckInWheelLeft);
            robot.suckInWheelright.setPower(suckInWheelRight);
            robot.elevator.setPower(elevatorPower * 0.5);

            //Testing messages
            telemetry.addData("leftServo" , leftCollectorPower);
            telemetry.addData("rightServo" , rightCollectorPower);
            telemetry.addData("Left Collector Wheel" , leftCollectorPower);
            telemetry.addData("right Collector Wheel" , rightCollectorPower);
            telemetry.addData("elevator" , elevatorPower);
            telemetry.addData("groundTouch" , groundTouch.isPressed());
            telemetry.update();
        }
    }
}