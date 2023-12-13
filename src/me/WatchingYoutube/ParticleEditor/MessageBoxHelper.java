package me.WatchingYoutube.ParticleEditor;

import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageBoxHelper {
	
	static Display display = Display.getCurrent();
	static Shell shell = new Shell(display);
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void giveErrorMessage(String string) throws Exception {
	   
	    MessageBox messageBox = new MessageBox(shell.getShell(), SWT.ICON_ERROR | SWT.OK);
	    messageBox.setMessage(string);
	    messageBox.setText("Confirm Delete");
	    int response = messageBox.open();
	    if (response == SWT.OK) {
	        try {
	            shell.dispose();
	        }
	        catch (Exception e) {
	            throw new Exception("Failed to close the Message Box for " + shell.getShell() + ".", e);
	        }
	    }
	}
	
	public static void givePlayErrorMessage(String string) {
		MessageBox messageBox = new MessageBox(Main.shlWorldOfGoo.getShell(), SWT.ICON_ERROR | SWT.OK);
		messageBox.setText("This particle has Critical Errors!");
		messageBox.setMessage("There are critical errors with your effect that will cause World Of Goo to crash, you must fix these before you can play test the effect." + 
		System.lineSeparator() + System.lineSeparator() + string);
		Runnable sound = (Runnable)Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
		
		if (sound != null) {
			sound.run();
		}
		messageBox.open();
		
		

	}
	
	public static void giveDeleteMessage(int i) {
		MessageBox messageBox = new MessageBox(Main.shlWorldOfGoo.getShell(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
		messageBox.setText("Delete this particle");
		messageBox.setMessage("Are you sure you wish to delete this particle, this action cannot be undone.");
		Runnable sound = (Runnable)Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
		
		if (sound != null) {
			sound.run();
		}
		int response = messageBox.open();
		
		if(response == SWT.YES) {
			
			Main.deleteParticle(i);
			
		} else if(response == SWT.NO) {
			
			shell.dispose();
			
		}
		
		

	}
	
}
