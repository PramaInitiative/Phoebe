package com.prama.phoebe;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private BufferedImage image;
	public ImagePanel(InputStream inputStream) {
		try {
			image = ImageIO.read(inputStream);
		} catch (IOException e) {
			System.out.println("Erreur I/O : Fichier image non trouvé.");
			e.printStackTrace();
		}
	}
	
	// Affichage de la carte
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
	
}
