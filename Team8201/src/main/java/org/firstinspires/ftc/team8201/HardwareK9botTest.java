package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareK9botTest
{
    /* Public OpMode members. */
    public DcMotor leftWheel;
    public DcMotor rightWheel;
    
    /* Local OpMode members. */
    HardwareMap hwMap;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareK9botTest() {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap; //Get this line clear****

        // Define and Initialize Motors
        leftWheel  = hwMap.get(DcMotor.class, "leftWheel");
        rightWheel = hwMap.get(DcMotor.class, "rightWheel");

        // Set all motors to zero power
        leftWheel.setPower(0);
        rightWheel.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
