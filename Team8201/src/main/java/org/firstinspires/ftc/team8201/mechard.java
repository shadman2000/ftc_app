package org.firstinspires.ftc.team8201;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


public class mechard
{
    //Motors
    public DcMotor rightWheelFront;
    public DcMotor rightWheelBack;
    public DcMotor leftWheelFront;
    public DcMotor leftWheelBack;
    public DcMotor collectorLeft;
    public DcMotor collectorRight;

    public Servo liftLeft;
    public Servo liftRight;
    public Servo flip;
    public Servo cop;


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
        // leftWheelFront  = hwMap.get(DcMotor.class, "leftWheelFront");
        // leftWheelBack  = hwMap.get(DcMotor.class, "leftWheelBack");
        // rightWheelFront = hwMap.get(DcMotor.class, "rightWheelFront");
        // rightWheelBack = hwMap.get(DcMotor.class, "rightWheelBack");

        collectorLeft = hwMap.get(DcMotor.class, "cleft");
        collectorRight = hwMap.get(DcMotor.class, "cright");

        liftLeft = hwMap.get(Servo.class, "lleft");
        liftRight = hwMap.get(Servo.class, "lright");
        flip = hwMap.get(Servo.class, "flipper");
        cop = hwMap.get(Servo.class, "cp");

        // leftWheelFront.setDirection(DcMotor.Direction.REVERSE);
        // leftWheelBack.setDirection(DcMotor.Direction.REVERSE);
        collectorRight.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        // leftWheelFront.setPower(0);
        // leftWheelBack.setPower(0);
        // rightWheelFront.setPower(0);
        // rightWheelBack.setPower(0);
        collectorLeft.setPower(0);
        collectorRight.setPower(0);
        liftLeft.setPosition(0.34);
        liftRight.setPosition(0.66);
        cop.setPosition(0.0);



        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        // leftWheelFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // leftWheelBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // rightWheelFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // rightWheelBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collectorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collectorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
