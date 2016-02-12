package hu.rozsenich.ideaplugin.spotifyplugin;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.win32.W32APIOptions;

/**
 * Created by rozsenichb on 10/02/2016.
 */
public interface MUser32 extends User32 {
	MUser32 INSTANCE = (MUser32) Native.loadLibrary("user32", MUser32.class, W32APIOptions.DEFAULT_OPTIONS);
	LRESULT SendMessage(HWND hWnd, int Msg, WPARAM wParam, LPARAM lParam);
}
