package me.WatchingYoutube.ParticleEditor;

import java.util.ArrayList;
import java.util.Collections;

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

public class NewFX {
	
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
		Shell shlNewEffect = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		shlNewEffect.setSize(458, 235);
		shlNewEffect.setText("New Effect");

			Label lblSelectAnEffect = new Label(shlNewEffect, SWT.NONE);
			lblSelectAnEffect.setBounds(10, 10, 158, 15);
			lblSelectAnEffect.setText("Enter a name for your Effect.");
			
			Button btnCancel = new Button(shlNewEffect, SWT.NONE);
			btnCancel.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shlNewEffect.close();
				}
			});
			btnCancel.setBounds(357, 161, 75, 25);
			btnCancel.setText("Cancel");
			
			text = new Text(shlNewEffect, SWT.BORDER);
			text.setBounds(10, 31, 422, 21);
			
			Label lblSelectWhichType = new Label(shlNewEffect, SWT.NONE);
			lblSelectWhichType.setBounds(10, 58, 201, 15);
			lblSelectWhichType.setText("Select which type of Effect it will be.");
			
			ToolBar toolBar = new ToolBar(shlNewEffect, SWT.RIGHT);
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
			
			Button btnOk = new Button(shlNewEffect, SWT.NONE);
			btnOk.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
						Main.name = (text.getText().toString());
						System.out.println(text.getText().toString());
						
						//Setup
						
						Main.maxparticles = 30;

						
						Main.image.clear();
						
						Main.path.clear();
						Main.imageIndex.clear();
						Main.pathIndex.clear();

						Main.additive.clear();
						Main.dampening.clear();
						Main.directed.clear();
						Main.fade.clear();
						Main.lifespan.clear();
						Main.scale.clear();
						Main.finalscale.clear();
						Main.speed.clear();
						Main.acceleration.clear();
						Main.movedir.clear();
						Main.movedirvar.clear();
						Main.rotation.clear();
						Main.rotspeed.clear();
						
						Main.singleacceleration.clear();
						Main.singleamp.clear();
						Main.singlefreq.clear();
						Main.singlelifespan.clear();
						Main.singlephaseshift.clear();
						Main.singlerotation.clear();
						Main.singlerotspeed.clear();
						Main.singlescale.clear();
						Main.singlespeed.clear();

						Main.amp.clear();
						Main.axis.clear();
						Main.freq.clear();
						Main.phaseshift.clear();
						
						if (tltmAmbientEffect.getSelection() == false) {
							Main.type = "particleeffect";
						} else if (tltmAmbientEffect.getSelection() == true) {
							Main.type = "ambientparticleeffect";
						}
					
					Main.windowtitle = "World Of Goo Particle Editor | " + text.getText().toString();
					
					Main.cleanUpTree();
					
					Main.numberOfParticles = 1;
					
					Main.numberOfAxialSinOffset.clear();
					
					Main.currentEffectOpen = true;
					
					Main.canvas.redraw();
					
					Main.createNewParticle();
					
					shlNewEffect.close();
					
				}
			});
			btnOk.setEnabled(false);
			btnOk.setGrayed(true);
			btnOk.setBounds(276, 161, 75, 25);
			btnOk.setText("Ok");

	
		shlNewEffect.open();
		shlNewEffect.layout();
		while (!shlNewEffect.isDisposed()) {
			
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

