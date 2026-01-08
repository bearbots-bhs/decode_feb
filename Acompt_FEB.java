package org.firstinspires.ftc.teamcode.feb2026_lynn;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "ACompt February 2026")

public class Acompt_FEB extends LinearOpMode {
    
    private DcMotor backleftmotor;
    private DcMotor frontrightmotor;
    private DcMotor frontleftmotor;
    private DcMotor backrightmotor;
    private DcMotor fRubberWheel; // front rubber wheel
    private DcMotor bRubberWheel; // Back rubber wheel
    private DcMotor launch; /launch motor

    @Override
    public void runOpMode() {
        backrightmotor = hardwareMap.get(DcMotor.class, "back right motor");
        backleftmotor = hardwareMap.get(DcMotor.class, "back left motor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "front right motor");
        frontleftmotor = hardwareMap.get(DcMotor.class, "front left motor");
        fRubberWheel = hardwareMap.get(DcMotor.class, "f rubber wheel");
        bRubberWheel = hardwareMap.get(DcMotor.class, "b rubber wheel");
        launch = hardwareMap.get(DcMotor.class, "launch");
        
        waitForStart();
        
        while (opModeIsActive()) {
            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            /* If gamepad 1 is not controlling drivetrain, we give gamepad 2 the capability to use the drivetrain. 
            This allows gamepad 1 to have primary override capability */
            if (x == 0.0 && y == 0.0) {
                x = gamepad2.left_stick_x;
                y = -gamepad2.left_stick_y;
            }
            
            double rx = getRot();
            frontleftmotor.setPower((y-x+rx)/2);
            frontrightmotor.setPower((y+x-rx)/2);
            backleftmotor.setPower((y+x+rx)/2);
            backrightmotor.setPower((y-x-rx)/2);
            fRubberWheel.setPower(getFront());
            bRubberWheel.setPower(getBack());
            launch.setPower(getLaunch());

            

        }
    }

    private double getRot()
    {
        double out = 0;
        if (gamepad1.left_trigger > 0.1)
        {
            out = -gamepad1.left_trigger; // value from [-1, 0)

        }
        else if (gamepad1.right_trigger > 0.1)
        {
            out = gamepad1.right_trigger; // value from [0, 1]
        }
        return out;
        
    }
    private double getFront()
    {
        double out = 1;
        double mode = 0;
        if (gamepad1.b)
        {
            mode = mode+1;
            mode = mode %2;
        }
        if (mode == 1)
        {
            out = 0;
        }    
        else
        {
            out = 1;
        }
        return out;
    }
    private double getBack()
    {
        double out = 1;
        double mode = 0;
        if (gamepad1.a)
        {
            mode = mode+1;
            mode = mode %2;
        }
        if (mode == 1)
        {
            out = 0;
        }    
        else
        {
            out = 1;
        }
        return out;
    }
    private double getLaunch()
    {
        double out = 1;
        double mode = 0;
        if (gamepad1.x)
        {
            mode = mode+1;
            mode = mode %2;
        }
        if (mode == 1)
        {
            out = 0;
        }    
        else
        {
            out = 1;
        }
        return out;
    }
}



