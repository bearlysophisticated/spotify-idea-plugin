package hu.rozsenich.ideaplugin.spotifyplugin;

import com.sun.jna.platform.win32.WinDef;

/**
 * Created by rozsenichb on 10/02/2016.
 */
public class COMMANDS {
	public static int APPCOMMAND = 0x0319;
	public static WinDef.WPARAM CMD_ZERO = new WinDef.WPARAM(0L);
	public static WinDef.LPARAM CMD_PLAYPAUSE = new WinDef.LPARAM(917504L);
	public static WinDef.LPARAM  CMD_MUTE = new WinDef.LPARAM(524288L);
	public static WinDef.LPARAM  CMD_VOLUMEDOWN = new WinDef.LPARAM(589824L);
	public static WinDef.LPARAM  CMD_VOLUMEUP = new WinDef.LPARAM(655360L);
	public static WinDef.LPARAM  CMD_STOP = new WinDef.LPARAM(851968L);
	public static WinDef.LPARAM  CMD_PREVIOUS = new WinDef.LPARAM(786432L);
	public static WinDef.LPARAM  CMD_NEXT = new WinDef.LPARAM(720896L);
}
