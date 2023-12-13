package me.WatchingYoutube.ParticleEditor;

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

public class OpenFX {
	
	public static String[] selecteditem;

	/**
	 * Open the window.
	 * @throws Exception 
	 * @wbp.parser.entryPoint
	 */
	public static void openDialog() throws Exception {
		Display display = Display.getCurrent();
		Shell shell = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE));
		shell.setSize(458, 307);
		shell.setText("Open Effects");

			Label lblSelectAnEffect = new Label(shell, SWT.NONE);
			lblSelectAnEffect.setBounds(10, 10, 125, 15);
			lblSelectAnEffect.setText("Select an effect to edit:");
			
			Button btnCancel = new Button(shell, SWT.NONE);
			btnCancel.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.close();
				}
			});
			btnCancel.setBounds(359, 241, 75, 25);
			btnCancel.setText("Cancel");
			
			XMLReader.ParseEntireFile(XMLReader.usingBufferedReader(Main.currentXML.getAbsolutePath()));
			
			Button btnOk = new Button(shell, SWT.NONE);
			btnOk.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					for (int i = 0; i < selecteditem.length; i++) {
						Main.name = (selecteditem[i]);
						System.out.println(Main.name);
						}
					try {
						
						XMLReader.ParseSingleEffect(Main.name);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Main.windowtitle = "World Of Goo Particle Editor | " + Main.name;
					
					Main.cleanUpTree();
					
					Main.currentEffectOpen = true;
					
					Main.createNewElements = true;
					
					Main.createNewElements();
					
					try {
						Main.prepareImagesToCanvas();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					/*Thread fxThread = new Thread() {
						
						public void run() {
						FXGraphicsView.startAnimation();
						}
					};
					
					fxThread.start();
					*/

					shell.close();
					
				}
			});
			btnOk.setEnabled(false);
			btnOk.setGrayed(true);
			btnOk.setBounds(278, 241, 75, 25);
			btnOk.setText("Ok");
			
			List list = new List(shell, SWT.BORDER | SWT.V_SCROLL);
			list.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					btnOk.setGrayed(false);
					btnOk.setEnabled(true);
					selecteditem = list.getSelection();
					for (int i = 0; i < selecteditem.length; i++) {
				          System.out.println(selecteditem[i]);
				        }
			}});
			java.util.Collections.sort(Main.fullparticleslist);
			list.setItems(Main.fullparticleslist.toArray(new String[Main.fullparticleslist.size()]));
			list.setBounds(10, 31, 424, 204);

	
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			
			
			if (!display.readAndDispatch()) {
				display.sleep();
			}
			if (Main.isRunning = false) {
				display.dispose();
				shell.dispose();
			}
		}
	}
}

