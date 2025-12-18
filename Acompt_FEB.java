// Teleop controls



package org.firstinspires.ftc.teamcode.feb2026_lynn;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "ACompt February 2026")

public class Acompt_FEB extends LinearOpMode {
    
    private DcMotor backleftmotor;
    private DcMotor frontrightmotor;
    private DcMotor frontleftmotor;
    private DcMotor backrightmotor;

    // todo: write your code here
    private double getrot()
    {
        double out = 0;
        if (gamepad1.left_trigger > 0.1)
        {
            out = -0.5;

        }
        else if (gamepad1.right_trigger > 0.1)
        {
            out = 0.5;
        }
        return out;
        
    }
    
    public void runOpMode() {
        backrightmotor = hardwareMap.get(DcMotor.class, "back right motor");
        backleftmotor = hardwareMap.get(DcMotor.class, "back left motor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "front right motor");
        frontleftmotor = hardwareMap.get(DcMotor.class, "front left motor");
        
        waitForStart();
        
        while (opModeIsActive()) {
            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double rx = getrot();
            frontleftmotor.setPower((y-x+rx)/2);
            frontrightmotor.setPower((y+x-rx)/2);
            backleftmotor.setPower((y+x+rx)/2);
            backrightmotor.setPower((y-x-rx)/2);

            
            
            
            
            
        }
    }
    
}

