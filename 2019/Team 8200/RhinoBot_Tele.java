package org.firstinspires.ftc.team8200;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



@TeleOp(name = "MasterBot", group = "Testing")
public class RhinoBot_Tele extends LinearOpMode {
    HardwareBot rhino = new HardwareBot();

    @Override
    public void runOpMode() {

        //Declare the variables
        //Motor(s)
        double leftWheelPowerFront = 0.0;
        double leftWheelPowerBack = 0.0;
        double rightWheelPowerFront = 0.0;
        double rightWheelPowerBack = 0.0;
        double hangElevatorPower = 0.0;
        double sliderPower = 0.0;
        double collectorPower = 0.0;
        
        boolean rightBumperPreviously = false;
        boolean collectorOn = false;

        //Initializing the hardware file
        rhino.init(hardwareMap);

        // //Ready message
        telemetry.addData("Say ", "MecTest robot is ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.right_bumper && !rightBumperPreviously) {
                collectorOn = !collectorOn;
            }
            rightBumperPreviously = gamepad1.right_bumper;
            
            
            
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
            
            sliderPower = gamepad2.right_trigger - gamepad2.left_trigger;
            

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

            //setting all motor (driving wheel) power to 0 if nothing is pressed
            if(gamepad1.right_trigger <= 0 && gamepad1.left_trigger <= 0 && gamepad1.right_stick_x == 0) {         //DO NOT USE AN ELSE!!!!!!!!!!!!!!!!!
                rightWheelPowerFront = 0.0;
                rightWheelPowerBack = 0.0;
                leftWheelPowerFront = 0.0;
                leftWheelPowerBack = 0.0;
            }
            
            //Diag: movement
            if (gamepad1.dpad_up == true) {
                rightWheelPowerFront = 1.0;
                leftWheelPowerFront = 1.0;
            }
            if (gamepad1.dpad_down == true) {
                leftWheelPowerBack = 1.0;
                rightWheelPowerBack = 1.0;
            }
            if (gamepad1.dpad_left == true) {
                rightWheelPowerFront = -1.0;
                leftWheelPowerFront = -1.0;
            }
            if (gamepad1.dpad_right == true) {
                leftWheelPowerBack = -1.0;
                rightWheelPowerBack = -1.0;
            }

            //Hanging Mechanism
            hangElevatorPower = gamepad2.right_stick_y;
            
            //Slider
            sliderPower = gamepad2.left_stick_y;
            
            //Collector
            if(gamepad2.right_trigger > 0){
                collectorPower = gamepad2.right_trigger;
            }
            
            if(gamepad2.left_trigger > 0){
                collectorPower = -gamepad2.left_trigger;
            }
            
            if(gamepad2.right_trigger <= 0 && gamepad2.left_trigger <= 0){
                collectorPower = 0.0;
            }


            //Sending the powers as motor Power
            rhino.leftWheelFront.setPower(leftWheelPowerFront);
            rhino.leftWheelBack.setPower(leftWheelPowerBack);
            rhino.rightWheelBack.setPower(rightWheelPowerFront);
            rhino.rightWheelFront.setPower(rightWheelPowerBack);
            rhino.hangElevator.setPower(hangElevatorPower);
            rhino.slider.setPower(sliderPower / 6);
            rhino.collector.setPower(collectorPower / 4.5);
            
            
            
            telemetry.addData("collector", collectorOn);
            telemetry.addData("hangPower", hangElevatorPower);
            telemetry.update();
        }
    }
}