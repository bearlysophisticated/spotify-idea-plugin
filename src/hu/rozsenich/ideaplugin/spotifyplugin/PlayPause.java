package hu.rozsenich.ideaplugin.spotifyplugin;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.util.IconLoader;
import com.sun.jna.platform.win32.WinDef;

import javax.swing.*;

/**
 * Created by rozsenichb on 10/02/2016.
 */
public class PlayPause extends AbstractSpotifyCommandAction {

	private boolean playing = false;

	Icon playIcon = IconLoader.getIcon("/icons/play.png");
	Icon pauseIcon = IconLoader.getIcon("/icons/pause.png");

	@Override
	WinDef.LPARAM command() {
		return COMMANDS.CMD_PLAYPAUSE;
	}

	@Override
	public void update(AnActionEvent e) {
		e.getPresentation().setEnabled(active);
		e.getPresentation().setIcon(isPlaying() ? pauseIcon : playIcon);
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	private boolean isPlaying() {
		return playing;
	}
}
