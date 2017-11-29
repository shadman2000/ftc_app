package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwareK9botTest;

@TeleOp(name = "Test TeleOp", group = "Testing")
public class robot8201 extends LinearOpMode {

    //Creating an instance of HardwareK9bot as "robot"
    HardwareK9bot robot = new HardwareK9bot();

    @Override
    public void runOpMode() {

        //Declare the variables
        double leftWheelsPower = 0.0;
        double rightWheelsPower = 0.0;
        double gemArmPower = 0.0;           //Not sure is it a servo or motor
        double rightCollectorPower = 0.0;
        double leftCollectorPower = 0.0;
        double elevatorPower = 0.0;

        double leftServoPosition = 0.0;      //Needs to be tested
        double rightServoPosition = 0.0;     //Needs to be tested
        double servoIncrease = 0.25;         //Needs to be tested

        //Initializing the hardwareK9bot file
        robot.init(hardwareMap);

        //Ready message
        telemetry.addData("Say ", "8201 robot is ready");
        telemetry.update();

        //Wait for play
        waitForStart();

        //opMode code that will run in a loop while we are in opMode
        while (opModeIsActive()) {

            //Acceleration of both wheels on right trigger
            if (gamepad1.right_trigger > 0) {

                //Redefining the power of both wheel according to the position of the trigger
                leftWheelsPower = gamepad1.right_trigger;
                rightWheelsPower = gamepad1.right_trigger;

                //ranging the power of wheelPowers
                rightWheelsPower = Range.clip(rightWheelsPower, 0.0, 1.0);
                leftWheelsPower = Range.clip(leftWheelsPower, 0.0, 1.0);

                //Setting wheelPowers for turning
                if (gamepad1.left_stick_x > 0) {
                    rightWheelsPower *= -1;
                } else if (gamepad1.left_stick_x < 1) {
                    leftWheelsPower *= -1;
                }
            }

            //Deceleration of both wheels on left trigger
            if (gamepad1.left_trigger > 0) {

                //Redefining the power of both wheel according to the position of the trigger
                leftWheelsPower = gamepad1.left_trigger;
                rightWheelsPower = gamepad1.left_trigger;

                //ranging the power of wheelPowers
                rightWheelsPower = Range.clip(rightWheelsPower, 0.0, -1.0);
                leftWheelsPower = Range.clip(leftWheelsPower, 0.0, -1.0);

                //Setting wheelPowers for turning
                if (gamepad1.left_stick_x > 0) {
                    rightWheelsPower *= -1;
                } else if (gamepad1.left_stick_x < 1) {
                    leftWheelsPower *= -1;
                }
            }

            //The suck-in wheels
            //Need to be tested which way the wheel rotates

            //The wheels of the suck-in will both rotate inwards with right_trigger
            if (gamepad2.right_trigger > 0) {

                //Redefining the power of both wheel (suck-in) according to the position of the trigger
                leftCollectorPower = gamepad2.right_trigger;
                rightCollectorPower = gamepad2.right_trigger;

                //Setting the range of the motor power
                leftCollectorPower = Range.clip(leftCollectorPower, 0.0, 1.0);
                rightCollectorPower = Range.clip(rightCollectorPower, 0.0, 1.0);
            }

            //The wheels of the suck-in will both rotate outwards with left_trigger
            if (gamepad2.left_trigger > 0) {

                //Redefining the power of both wheel (suck-in) according to the position of the trigger
                leftCollectorPower = gamepad2.right_trigger;
                rightCollectorPower = gamepad2.right_trigger;

                //Setting the range of the motor power
                leftCollectorPower = Range.clip(leftCollectorPower, 0.0, -1.0);
                rightCollectorPower = Range.clip(rightCollectorPower, 0.0, -1.0);
            }

            //The elevator
            //Need to be tested which way the wheel rotates

            //The motors will go both up and down according to the y_axis of gamepad2 rightstick
            if (gamepad2.right_stick_y > 0) {

                //Motor power goes up so as the elevator
                elevatorPower = gamepad2.right_stick_y;

                //Ranging the right stick power
                elevatorPower = Range.clip(elevatorPower, 0.0, 1.0);
            }
            if (gamepad1.right_stick_y < 0) {

                //Motor power goes up so as the elevator
                elevatorPower = gamepad2.right_stick_y;

                //Ranging the right stick power
                elevatorPower = Range.clip(elevatorPower, 0.0, -1.0);
            }

            //setting all motor power to 0 if nothing is pressed
            else {
                rightWheelsPower = 0;
                leftWheelsPower = 0;
                leftCollectorPower = 0;
                rightCollectorPower = 0;
                //Need to test if elevator needed to be set to zero
            }

            //Sending the powers as motor Power
            robot.leftWheel.setPower(leftWheelsPower);
            robot.rightWheel.setPower(rightWheelsPower);
            robot.suckInWheeleft.setPower(leftCollectorPower);
            robot.suckInWheelright.setPower(rightCollectorPower);
            robot.elevator.setPower(elevatorPower);
        }
    }
}