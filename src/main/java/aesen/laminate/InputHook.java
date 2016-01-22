package aesen.laminate;

/**
 * Accepts input events such as key presses, mouse movements,
 * and mouse clicks.
 *
 */
public interface InputHook {
	void onKeyDown(char keyChar, int keyCode);
	void onKeyUp(char keyChar, int keyCode);
	
	void onMouseMove(int x, int y);
	
	void onMouseDown(int x, int y, int button);
	void onMouseUp(int x, int y, int button);
	void onMouseScroll(int x, int y, int scrollAmount);
}
