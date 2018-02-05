package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


public class mechard
{
    //Motors
    public DcMotor rightWheelFront;
    public DcMotor rightWheelBack;
    public DcMotor leftWheelFront;
    public DcMotor leftWheelBack;

    /* Local OpMode members. */
    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public mechard() {}

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // save reference to HW Map
        hwMap = ahwMap;

        // Define and Initialize Motors

        //Wheels
        leftWheelFront  = hwMap.get(DcMotor.class, "leftWheelFront");
        leftWheelBack  = hwMap.get(DcMotor.class, "leftWheelBack");
        rightWheelFront = hwMap.get(DcMotor.class, "rightWheelFront");
        rightWheelBack = hwMap.get(DcMotor.class, "rightWheelBack");
        leftWheelFront.setDirection(DcMotor.Direction.REVERSE);
        leftWheelBack.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        leftWheelFront.setPower(0);
        leftWheelBack.setPower(0);
        rightWheelFront.setPower(0);
        rightWheelBack.setPower(0);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftWheelBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightWheelBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
