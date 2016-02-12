package hu.rozsenich.ideaplugin.spotifyplugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.sun.jna.platform.win32.WinDef;

/**
 * Created by rozsenichb on 10/02/2016.
 */
public abstract class AbstractSpotifyCommandAction extends AnAction {

	protected boolean active = false;

	@Override
	public void actionPerformed(AnActionEvent event) {
		WinDef.HWND hwnd = MUser32.INSTANCE.FindWindow("SpotifyMainWindow", null);
		MUser32.INSTANCE.SendMessage(hwnd, COMMANDS.APPCOMMAND, COMMANDS.CMD_ZERO, command());
	}

	abstract WinDef.LPARAM command();

	public void setActive(boolean active) {
		this.active = active;
	}
}
