package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@Autonomous(name="Pushbot: Auto Drive By Time", group="Pushbot")
@Disabled
public class testBotBasicAuto extends LinearOpMode {

    HardwarePushbot robot   = new HardwarePushbot();    // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();    //runtime instance
    
    //Declaring the variables
    double drive = 0.5;
    double turn = 0.4;
    
    @Override
    public void runOpMode() {
        
        //Initializing the Hardwarek9bot
        robot.init(hardwareMap);
        
        //Ready message
        telemetry.addData("Say","Autonomous is ready");
        telemetry.update();
        
        //Wait for start
        waitForStart();
        
        //Let's get something for OpMode
        //Go forward for 3 second
        //Turn left 2 second
        //Go forward 2 second
        //go back 2 second
        //turn left 2 second
        //Go forward 3 second
        //Displacement should be 0 (For Physics MSDUKE <3)
        
        //Step 1
        robot.leftWheel.setPosition(drive);
        robot.rightWheel.setPosition(drive);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() < 3.0)){
            telemetry.addData("Path","Leg 1: %2.5f S Elapsed",runtime.seconds());
            telemetry.update();
        }
        
        //Step 2
        robot.leftWheel.setPosition(-drive);
        robot.rightWheel.setPosition(drive);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() < 1.5)){
            telemetry.addData("Path","Leg 2: %2.5f S Elapsed",runtime.seconds());
            telemetry.update();
        }
        
        //Step 3
        robot.leftWheel.setPosition(drive);
        robot.rightWheel.setPosition(drive);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() < 2.0)){
            telemetry.addData("Path","Leg 2: %2.5f S Elapsed",runtime.seconds());
            telemetry.update();
        }
        
        //Step 4
        robot.leftWheel.setPosition(-drive);
        robot.rightWheel.setPosition(-drive);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() < 2.0)){
            telemetry.addData("Path","Leg 2: %2.5f S Elapsed",runtime.seconds());
            telemetry.update();
        }
        
        //Step 5
        robot.leftWheel.setPosition(-drive);
        robot.rightWheel.setPosition(drive);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() < 1.5)){
            telemetry.addData("Path","Leg 2: %2.5f S Elapsed",runtime.seconds());
            telemetry.update();
        }
        
        //Step 6
        robot.leftWheel.setPosition(drive);
        robot.rightWheel.setPosition(drive);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() < 3.0)){
            telemetry.addData("Path","Leg 2: %2.5f S Elapsed",runtime.seconds());
            telemetry.update();
        }
        
        
        
    }

}