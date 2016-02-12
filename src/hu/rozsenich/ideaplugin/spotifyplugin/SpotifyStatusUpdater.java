package hu.rozsenich.ideaplugin.spotifyplugin;

import com.intellij.openapi.util.IconLoader;
import com.sun.jna.platform.win32.WinDef;

import javax.swing.*;

/**
 * Created by rozsenichb on 11/02/2016.
 */
public class SpotifyStatusUpdater implements Runnable {

	private boolean stop = false;
	private boolean playing = false;
	private boolean open = false;

	private JLabel label;
	private PlayPause playPauseAction;
	private Prev prevAction;
	private Next nextAction;

	private Icon spotifyActiveIcon;
	private Icon spotifyInactiveIcon;
	private Icon playIcon;
	private Icon pauseIcon;

	public SpotifyStatusUpdater(JLabel label, PlayPause playPauseAction, Prev prevAction, Next nextAction) {
		this.label=label;
		this.playPauseAction = playPauseAction;
		this.prevAction = prevAction;
		this.nextAction = nextAction;

		spotifyActiveIcon = IconLoader.getIcon("/icons/spotify.png");
		spotifyInactiveIcon = IconLoader.getIcon("/icons/spotify-inactive.png");
	}

	private WinDef.HWND findSpotifyWindow() {
		WinDef.HWND hwnd = MUser32.INSTANCE.FindWindow("SpotifyMainWindow", null);
		open = hwnd != null;
		updateControlls();
		return hwnd;
	}

	@Override
	public void run() {
		while(!stop) {
			WinDef.HWND hwnd = findSpotifyWindow();

			if(hwnd != null) {
				char[] buffer = new char[512];
				MUser32.INSTANCE.GetWindowText(hwnd, buffer, buffer.length);
				String title = charArryToStringTrimmed(buffer);
				label.setText(title);
				determinePlayState(title);
				setStatusBarIconBasedOnPlayState();
				setPlayPlauseIconBasedOnPlayState();
			}

			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		stop = true;
	}

	private String charArryToStringTrimmed(char[] buffer) {
		String sbuffer = new String();
		int i = 0;
		while(buffer[i] != '\u0000' && i<buffer.length) {
			sbuffer += buffer[i++];
		}
		return sbuffer.contains("Spotify") ? "" : sbuffer;
	}

	private void determinePlayState(String sbuffer) {
		playing = sbuffer.length() > 0;
	}

	private boolean isPlaying() {
		return playing;
	}

	private void setStatusBarIconBasedOnPlayState() {
		label.setIcon(isPlaying() ? spotifyActiveIcon : spotifyInactiveIcon);
	}

	private void setPlayPlauseIconBasedOnPlayState() {
		playPauseAction.setPlaying(isPlaying());
	}

	private void updateControlls() {
		playPauseAction.setActive(open);
		prevAction.setActive(open);
		nextAction.setActive(open);
	}
}
