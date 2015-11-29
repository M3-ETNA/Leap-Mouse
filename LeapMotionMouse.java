import com.leapmotion.*;
import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.State;
import com.leapmotion.leap.Gesture.Type;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.InteractionBox;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;
//import com.leapmotion.*;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

class LeapListener extends Listener
{
	public Robot robot;
	
	public void onInit(Controller cont)
	{
		System.out.println("Initialized");
	}
	
	public void onConnect(Controller cont)
	{
		System.out.println("Connected to motion Sensor");
		cont.enableGesture(Gesture.Type.TYPE_SWIPE);
		cont.enableGesture(Gesture.Type.TYPE_CIRCLE);
		cont.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		cont.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}
	
	public void onDisconnect(Controller cont)
	{
		System.out.println("Motion Sensor Disconnected");
	}
	
	public void onExit(Controller cont)
	{
		System.out.println("Exited");
	}
	
	
	public void onFrame(Controller cont)
	{
		try	{robot = new Robot(); } catch (Exception e)	{}
		Frame frame = cont.frame();
		InteractionBox box = frame.interactionBox();

		for (Hand han : frame.hands())
		{
			
			Vector handPos = han.palmPosition();
			Vector boxHandPalmPos = box.normalizePoint(handPos);
			
			float pinchStrength = han.pinchStrength();
			Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			
								/* ********** */
								/* Profondeur */			
								/* ********** */
/*			
			robot.mouseMove((int)(screen.width * boxHandPalmPos.getX()) ,
					(int)(screen.height - boxHandPalmPos.getY() * screen.height));
*/			
			
								/* ********* */
								/*  Hauteur  */
								/* ********* */
			robot.mouseMove((int)(screen.width * boxHandPalmPos.getX()) ,
							(int)(screen.height * boxHandPalmPos.getZ()));
	
			System.out.println(" PinchLevel: " + pinchStrength);
			if (pinchStrength >= 0.05)
			{
				robot.mousePress(InputEvent.BUTTON1_MASK);
				try {Thread.sleep(500);} catch (Exception e) {}
				robot.mouseRelease(InputEvent.BUTTON1_MASK);				
			}
			else
			{
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}
		}
	}
}

public class LeapMotionMouse {

	public static void main(String[] args)
	{
		LeapListener ll = new LeapListener();
		Controller cont = new Controller();
		cont.addListener(ll);
		Calculatrice calc = new Calculatrice();
		try
		{
			System.in.read();
		}
		catch (Exception e)
		{
			cont.removeListener(ll);
		}

	}

}