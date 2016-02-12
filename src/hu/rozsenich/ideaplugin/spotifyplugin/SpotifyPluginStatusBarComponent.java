package hu.rozsenich.ideaplugin.spotifyplugin;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.WindowManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by rozsenichb on 11/02/2016.
 */
public class SpotifyPluginStatusBarComponent extends AbstractProjectComponent
		implements ProjectComponent {

	private JLabel label = new JLabel("", IconLoader.getIcon("/icons/spotify-inactive.png"), SwingConstants.LEADING);
	private Thread statusUpdaterThread;
	private SpotifyStatusUpdater spotifyStatusUpdater;

	private PlayPause playPauseAction;
	private Prev prevAction;
	private Next nextAction;

	protected SpotifyPluginStatusBarComponent(Project project) {
		super(project);
	}

	@Override
	public void projectOpened() {
		WindowManager.getInstance().getStatusBar(myProject).addCustomIndicationComponent(label);
		statusUpdaterThread.start();
	}

	@Override
	public void projectClosed() {

	}

	@Override
	public void initComponent() {
		playPauseAction = (PlayPause) ActionManager.getInstance().getAction("SpotifyPlugin.playpause");
		prevAction = (Prev) ActionManager.getInstance().getAction("SpotifyPlugin.prev");
		nextAction = (Next) ActionManager.getInstance().getAction("SpotifyPlugin.next");
		spotifyStatusUpdater = new SpotifyStatusUpdater(label, playPauseAction, prevAction, nextAction);
		statusUpdaterThread = new Thread(spotifyStatusUpdater);
	}

	@Override
	public void disposeComponent() {
		spotifyStatusUpdater.stop();
		statusUpdaterThread.interrupt();
	}

	@NotNull
	@Override
	public String getComponentName() {
		return "SpotifyPlugin.StatusBarComponent";
	}
}
