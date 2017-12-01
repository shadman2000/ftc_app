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
    private ElapsedTime     runtime = new ElapsedTime();

}