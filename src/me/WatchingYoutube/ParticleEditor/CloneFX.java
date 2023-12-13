package me.WatchingYoutube.ParticleEditor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.transform.TransformerException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class CloneFX {
	
	public static String[] selecteditem;
	private static Text text;
	
	private static boolean effectType;
	private static boolean ready = false;
	

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public static void openDialog() {
		Display display = Display.getCurrent();
		Shell shlCloneEffect = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		shlCloneEffect.setSize(458, 235);
		shlCloneEffect.setText("Clone Effect");

			Label lblSelectAnEffect = new Label(shlCloneEffect, SWT.NONE);
			lblSelectAnEffect.setBounds(10, 10, 158, 15);
			lblSelectAnEffect.setText("Enter a name for your Effect.");
			
			Button btnCancel = new Button(shlCloneEffect, SWT.NONE);
			btnCancel.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shlCloneEffect.close();
				}
			});
			btnCancel.setBounds(357, 161, 75, 25);
			btnCancel.setText("Cancel");
			
			text = new Text(shlCloneEffect, SWT.BORDER);
			text.setBounds(10, 31, 422, 21);
			
			Label lblSelectWhichType = new Label(shlCloneEffect, SWT.NONE);
			lblSelectWhichType.setBounds(10, 58, 201, 15);
			lblSelectWhichType.setText("Select which type of Effect it will be.");
			
			ToolBar toolBar = new ToolBar(shlCloneEffect, SWT.RIGHT);
			toolBar.setBounds(10, 111, 245, 38);
			
			ToolItem tltmCheckItem = new ToolItem(toolBar, SWT.CHECK);
			tltmCheckItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			tltmCheckItem.setImage(SWTResourceManager.getImage(NewFX.class, "/me/WatchingYoutube/ParticleEditor/assets/fire-icon.png"));
			tltmCheckItem.setText("Particle Effect");
			
			ToolItem tltmAmbientEffect = new ToolItem(toolBar, SWT.CHECK);
			tltmAmbientEffect.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
			tltmAmbientEffect.setText("Ambient Effect");
			tltmAmbientEffect.setImage(SWTResourceManager.getImage(NewFX.class, "/me/WatchingYoutube/ParticleEditor/assets/Status-weather-showers-icon.png"));
			
			Button btnOk = new Button(shlCloneEffect, SWT.NONE);
			btnOk.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
						String oldName = Main.name;
					
						Main.name = (text.getText().toString());
						System.out.println(text.getText().toString());
						
						//Setup
						
						String oldType = Main.type;
						
						if (tltmAmbientEffect.getSelection() == false) {
							Main.type = "particleeffect";
						} else if (tltmAmbientEffect.getSelection() == true) {
							Main.type = "ambientparticleeffect";
						}
						
						File fileToEncode = null;
						
					try {
						
						boolean checkFile = XMLReader.CheckEntireFile(Main.currentXML);
						
						if (checkFile == false) {
						
						fileToEncode = SaveHandler.writeNewParticleToXML(Main.currentXML);
						fileToEncode = SaveHandler.writeNewValuesToXML(Main.currentXML);
						
						if (Main.fxFile.getAbsolutePath().endsWith(".bin")) {
						
						byte[] newBytes = AESBin.encryptFile(fileToEncode);
						
						XMLReader.writeBytesToFile(Main.fxFile, newBytes);
						
						System.out.println("Effect: " + Main.name + " created successfully.");
						Main.playSuccessAudio();
						}
						}
					} catch (TransformerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Main.name = oldName;
					
					Main.type = oldType;
					
					shlCloneEffect.close();
					
				}
			});
			btnOk.setEnabled(false);
			btnOk.setGrayed(true);
			btnOk.setBounds(276, 161, 75, 25);
			btnOk.setText("Ok");

	
		shlCloneEffect.open();
		shlCloneEffect.layout();
		while (!shlCloneEffect.isDisposed()) {
			
			if (tltmCheckItem.getSelection() == true) {
				tltmAmbientEffect.setSelection(false);
			}
			if (tltmAmbientEffect.getSelection() == true) {
				tltmCheckItem.setSelection(false);
			}
			if (tltmCheckItem.getSelection() == true || tltmAmbientEffect.getSelection() == true) {
				if (text.getCharCount() >= 3) {
					btnOk.setGrayed(false);
					btnOk.setEnabled(true);
					ready = true;
				} else {
					btnOk.setGrayed(true);
					btnOk.setEnabled(false);
					ready = false;
				}
			} else if (tltmCheckItem.getSelection() == false && tltmAmbientEffect.getSelection() == false) {
				btnOk.setGrayed(true);
				btnOk.setEnabled(false);
				ready = false;
			}

			
			if (!display.readAndDispatch()) {
				display.sleep();	
			}
			if (Main.isRunning = false) {
				display.dispose();
			}
			
			
		}
	}
}

