package automatedUI;

import java.awt.AWTException;
import java.awt.Robot;

public class KeyEvents {

	Robot robot;

	public KeyEvents() throws AWTException {
		robot = new Robot();
	}

	/**
	 * Raise key press / key release events for
	 *
	 * @param key
	 */
	public void raiseKeyEvent(int key) {
		robot.keyPress(key);
		robot.keyRelease(key);
	}
}
