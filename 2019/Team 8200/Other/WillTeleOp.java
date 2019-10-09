package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



@TeleOp(name = "WillBot", group = "Testing")
public class WillTeleOp extends LinearOpMode {
    HardwareBot rhino = new HardwareBot();

    @Override
    public void runOpMode() {

        //Declare the variables
        //Motor(s)
        double leftWheelPowerFront = 0.0;
        double leftWheelPowerBack = 0.0;
        double rightWheelPowerFront = 0.0;
        double rightWheelPowerBack = 0.0;
        //double hangElevatorPower = 0.0;
        //double flipElevatorLeftPower = 0.0;
        //double flipElevatorRightPower = 0.0;
        //double sliderPower = 0.0;
        
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
                rightWheelPowerFront =  gamepad1.right_trigger;
                rightWheelPowerBack = gamepad1.right_trigger;
              
                
            }
            else if(gamepad1.right_bumper){
                rightWheelPowerBack = -0.6;
                rightWheelPowerFront = -0.6;
            }
            else{
                
                rightWheelPowerBack = 0;
                rightWheelPowerFront = 0;
            }

            //Backward and turn
            if(gamepad1.left_trigger > 0){
                //Redefining the power of both wheel according to the position of the trigger                
                leftWheelPowerBack = gamepad1.left_trigger;
                leftWheelPowerFront = gamepad1.left_trigger;
            
                
            }
            
            else if(gamepad1.left_bumper){
                leftWheelPowerBack = -0.6;
                leftWheelPowerFront = -0.6;
            }
            
            else{
                leftWheelPowerBack = 0;
                leftWheelPowerFront = 0;
            }
            
            //Sending the powers as motor Power
            rhino.leftWheelFront.setPower(leftWheelPowerFront);
            rhino.leftWheelBack.setPower(leftWheelPowerBack);
            rhino.rightWheelBack.setPower(rightWheelPowerFront);
            rhino.rightWheelFront.setPower(rightWheelPowerBack);
            
          
            
        }
    }
}