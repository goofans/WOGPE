package me.WatchingYoutube.ParticleEditor;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;

public class About {

	protected static Shell shlAbout;
	
	private static Image imageLabel = new Image(Display.getCurrent(), SWTResourceManager.getImage(Main.class, "/me/WatchingYoutube/ParticleEditor/assets/29a6b31959dea93978c3eae5fa92596e.png").getImageData().scaledTo(85, 85));
	

	/**
	 * Create contents of the window.
	 * @throws IOException 
	 * @wbp.parser.entryPoint
	 */
	public static void openAbout() throws IOException {
		Display display = Display.getDefault();
		shlAbout = new Shell(SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shlAbout.setSize(569, 482);
		shlAbout.setText("About");
		
		Label lblNewLabel = new Label(shlAbout, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 10, 533, 340);
		lblNewLabel.setText("Thank you for using World of Goo Particle Editor\r\n\r\nThis project was made possible by the entire World Of Goo community and the amazing things they have done even nearly 12 years later.\r\n\r\nI hinted at this project in 2017 and have finally done it in 2020, only took me 3 years lol.\r\n\r\nNow some information below.\r\n\r\nLicenses + Acknowledgments:\r\n\r\nWorld Of Goo Particle Editor is licensed with GNU GPL V3, see the license.txt file for more information.\r\n\r\nWorld Of Goo Particle Editor uses code with permission from GooTool to handle Encryption and Decryption.\r\nAll Gootool Software, Files and Associated Doccuments are Copyright (c) 2008, 2009, 2010, 2019 David C A Croft. See LICENSE.gootool.txt for the license.\r\n\r\nWorld Of Goo Particle Editor uses Cryptographic functions from the BouncyCastle library Copyright (c) 2000 - 2020 The Legion Of The Bouncy Castle (http://www.bouncycastle.org). See LICENSE.bouncycastle.txt for the license.");
		
		Button btnClose = new Button(shlAbout, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAbout.close();
			}
		});
		btnClose.setBounds(468, 416, 75, 25);
		btnClose.setText("Close");
		
		Button btnDiscord = new Button(shlAbout, SWT.BORDER);

		/* Saving this for future use
		String imageurl = "https://cdn.discordapp.com/icons/359111541565685760/29a6b31959dea93978c3eae5fa92596e.png?size=256";
		
		URL url = new URL(imageurl);
		BufferedImage c = ImageIO.read(url);
		
		ImageData image = ImageResource.convertToSWT(c);
		
		Image imageLabel = new Image(Display.getDefault(), image.scaledTo(120, 120));
		*/
		
		
		btnDiscord.setImage(imageLabel);
		
		btnDiscord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https://discord.gg/6BEecnD"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnDiscord.setToolTipText("Join the Goofans Discord");
		btnDiscord.setBounds(10, 356, 85, 85);
		
		Button btnGoofans = new Button(shlAbout, SWT.BORDER);
		
		
		
		
		btnGoofans.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("http://goofans.com/user/28142"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnGoofans.setToolTipText("Check out my goofans page on The Information Superhighway");
		btnGoofans.setBounds(101, 356, 85, 85);
		
		InputStream is = new URL("http://goofans.com/sites/default/files/pictures/picture-28142.png").openStream();
		
		Image goofanslabel = new Image(Display.getCurrent(), is);
		
		btnGoofans.setImage(goofanslabel);
		shlAbout.open();
		shlAbout.layout();
		
		while (!shlAbout.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
			
			if (Main.isRunning = false) {
				display.dispose();
				shlAbout.dispose();
			}
			
		}
		
	}
}
