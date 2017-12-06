package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.team8201.testBot.HardwareK9botTest;

@TeleOp(name = "Test TeleOp", group = "Testing")
public class testBotBasicOp extends LinearOpMode {
    HardwareK9botTest robot = new HardwareK9botTest();

    @Override
    public void runOpMode() {

        //Declaring the variables

        //motor left
        double leftMotorPower = 0.0;

        //motor right
        double rightMotorPower =0.0;

        //Initializing the hardware
        robot.init(hardwareMap);



        //Wait for play
        waitForStart();

        //Running everything in the while loop
        while(opModeIsActive()) {

            //right_triggr
            if(gamepad1.right_trigger > 0){

                //Defining te the powers of both mototrs according the right_trigger positions
                rightMotorPower = gamepad1.right_trigger;
                leftMotorPower = gamepad1.right_trigger;

                //Setting the range of the motor power
                rightMotorPower = Range.clip(rightMotorPower, 0.0, 1.0);
                leftMotorPower = Range.clip(leftMotorPower, 0.0, 1.0);

                if(gamepad1.left_stick_x > 0 ){
                    rightMotorPower *= -1;
                }

                else if(gamepad1.left_stick_x < 0){
                    leftMotorPower *= -1;
                }

            }



            //left_trigger
            else if(gamepad1.left_trigger > 0){

                //Defining te the powers of both mototrs according the left_trigger positions
                rightMotorPower = gamepad1.left_trigger;
                leftMotorPower = gamepad1.left_trigger;

                //Setting the range of the motor power
                rightMotorPower = Range.clip(rightMotorPower, 0.0, -1.0);
                leftMotorPower = Range.clip(leftMotorPower, 0.0, -1.0);

                if(gamepad1.left_stick_x < 0 ){
                    leftMotorPower *= -1;
                }

                else if(gamepad1.left_stick_x > 0) {
                    rightMotorPower *= -1;
                }
            }




            //Setting mototrPower to 0 if none trigger is in action
            else {
                rightMotorPower = 0;
                leftMotorPower = 0;
            }

            //Sending the powers for wheel to the motors
            robot.leftWheel.setPower(leftMotorPower);
            robot.rightWheel.setPower(rightMotorPower);

            telemetry.addData("RWP ", rightMotorPower);
            telemetry.addData("LWP ", leftMotorPower);
            telemetry.update();
        }
    }
}