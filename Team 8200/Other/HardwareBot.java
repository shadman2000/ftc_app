package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwareBot {
    HardwareMap hwMap  = null;

    //Drive
    public DcMotor leftWheelFront;
    public DcMotor rightWheelFront;
    public DcMotor leftWheelBack;
    public DcMotor rightWheelBack;

    //SuperStructure
    public DcMotor hangElevator;
    public DcMotor leftFlipElevator;
    public DcMotor rightFlipElevator;
    public DcMotor slider;
    public DcMotor collector;

    public HardwareBot() {}

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        leftWheelFront = hwMap.get(DcMotor.class, "lf");
        rightWheelFront = hwMap.get(DcMotor.class, "rf");
        leftWheelBack = hwMap.get(DcMotor.class, "lb");
        rightWheelBack = hwMap.get(DcMotor.class, "rb");
        //hangElevator = hwMap.get(DcMotor.class, "he");
        // leftFlipElevator = hwMap.get(DcMotor.class, "lfe");
        // rightFlipElevator = hwMap.get(DcMotor.class, "rfe");
        //slider = hwMap.get(DcMotor.class, "sli");
        //collector = hwMap.get(DcMotor.class, "col");

        leftWheelFront.setDirection(DcMotor.Direction.REVERSE);
        leftWheelBack.setDirection(DcMotor.Direction.REVERSE);
        // leftFlipElevator.setDirection(DcMotor.Direction.REVERSE);

        leftWheelFront.setPower(0);
        rightWheelFront.setPower(0);
        leftWheelBack.setPower(0);
        rightWheelBack.setPower(0);
        //hangElevator.setPower(0);
        // leftFlipElevator.setPower(0);
        // rightFlipElevator.setPower(0);
        //slider.setPower(0);
        //collector.setPower(0);

        // leftWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // leftWheelBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // rightWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // rightWheelBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
