package com.prama.phoebe;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

public class PhoebeWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel label1;
	private JLabel label2;
	private JButton pramaLink;
	private JButton helpButton;
	private JList<String> list1;
	public JComboBox<String> comboBoxTownLocations;
	private JComboBox<String> comboBoxPaths;
	private JScrollPane scrollPane1;
	private ImagePanel sinnohMapPanel;
	
	public Vector<Integer> townLocationsVector = new Vector<Integer>();
	public Vector<Integer> pathVector = new Vector<Integer>();
	public int selectedPath = 0;
	
	public Pathfinder pathfinder;
	
	private ActionListener comboBoxTownLocationsActionListener = new ActionListener () {
	    public void actionPerformed(ActionEvent e) {
	    	pathfinder = new Pathfinder(townLocationsVector.get(getSelectedLocation()).shortValue());
	    	selectedPath = 0;
	    	if(!pathfinder.paths.isEmpty())
	    		pathVector = pathfinder.paths.get(selectedPath).toVector();
	    	else
	    		pathVector = new Vector<Integer>();
	    	drawWindow();
	    }
	};
	
	private ActionListener helpListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "Cliquez sur une ville sur la carte pour n'avoir dans la liste que les localisations de cette ville dans la liste.\n"+
												"Puis, choisissez dans la liste parmi toutes les localisations propos�es votre destination.\n"+
												"Vous pourrez alors parmi tous les chemins possibles celui que vous voulez emprunter pour y arriver.");
		}
		
	};
	
	private ActionListener comboBoxPathsActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			selectedPath = comboBoxPaths.getSelectedIndex();
			pathVector = pathfinder.paths.get(selectedPath).toVector();
			drawWindow();
		}
	};
	
	private MouseListener sinnohMapPanelMouseListener = new MouseListener () {
		@Override
		public void mouseClicked(MouseEvent mouse) {
			int mouseX = (mouse.getX() - 1) / 7;
			int mouseY = (mouse.getY() + 3) / 7;
			short[] locs = Map.getTownLocations(Map.mapArray[mouseY * 30 + mouseX]);
			townLocationsVector = new Vector<Integer>();
			pathfinder = null;
			pathVector = new Vector<Integer>();
			
			if(locs != null) {
				for(int i = 0; i < locs.length; i++) {
					townLocationsVector.add((int)locs[i]);
				}
			} else {
				for(int i = 0; i < 559; i++) {
					townLocationsVector.add(i);
				}
			}
			drawWindow();
			label1.setText("Choisissez votre destination (" + Map.noms[Map.mapArray[mouseY * 30 + mouseX]] + ") :");
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private ActionListener pramaLinkListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
	    	try {
				Desktop.getDesktop().browse(new URI("http://www.prama-initiative.com/"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	};
	
	public PhoebeWindow() {		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(540, 400);
		this.setLocationRelativeTo(null);
		this.setTitle("Phoebe");
		
		panel = new JPanel();
		panel.setLayout(null);
		
		label1 = new JLabel();
		label1.setText("Cliquez sur une ville ou choisissez une localisation.");
		label1.setBounds(220, 0, 300, 20);
		
		label2 = new JLabel();
		label2.setBounds(220, 70, 200, 20);
		label2.setText("Choisissez votre chemin :");
		
		list1 = new JList<String>();
		list1.setBounds(220, 120, 300, 220);
		
		sinnohMapPanel = new ImagePanel(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/prama/phoebe/assets/carte.bmp"));
		sinnohMapPanel.setBounds(0, 0, 216, 168);
		sinnohMapPanel.addMouseListener(sinnohMapPanelMouseListener);
		
		pramaLink = new JButton();
		pramaLink.setBounds(1, 320, 200, 20);
		pramaLink.setText("Aller sur le site de PRAMA");
		pramaLink.addActionListener(pramaLinkListener);
		
		helpButton = new JButton();
		helpButton.setBounds(1, 299, 200, 20);
		helpButton.setText("Besoin d'aide ?");
		helpButton.addActionListener(helpListener);
		
		drawWindow();
		
		this.setVisible(true);
	}
	
	public void drawWindow() {
		panel.removeAll();
		
		Vector<String> listItems = new Vector<String>();
		
		for(int i = 0; i < pathVector.size(); i++) {
			listItems.add(pathVector.get(i) + " : " + Map.noms[pathVector.get(i)]);
		}
		
		list1 = new JList<String>(listItems);
		
		int temp = 0;
		
		if(comboBoxTownLocations != null)
			temp = comboBoxTownLocations.getSelectedIndex();
		
		comboBoxTownLocations = new JComboBox<String>();
		comboBoxTownLocations.setBounds(220, 20, 300, 20);
		
		for(int i = 0; i < townLocationsVector.size(); i++) {
			comboBoxTownLocations.addItem(townLocationsVector.get(i) + " : " + Map.noms[townLocationsVector.get(i)]);
		}
		if(comboBoxTownLocations.getItemCount() > temp && temp >= 0)
			comboBoxTownLocations.setSelectedIndex(temp);
		
		comboBoxTownLocations.addActionListener(comboBoxTownLocationsActionListener);
		
		scrollPane1 = new JScrollPane(list1);
		scrollPane1.setBounds(220, 120, 300, 220);
		scrollPane1.setViewportView(list1);
		
		comboBoxPaths = new JComboBox<String>();
		comboBoxPaths.setBounds(220, 90, 200, 20);
		
		if (pathfinder != null)
			if(pathfinder.paths.size() > 0) {
				for(int i = 0; i < pathfinder.paths.size(); i++)
					comboBoxPaths.addItem("Chemin " + i);
				if(pathfinder.paths.size() > 1)
					label1.setText(pathfinder.paths.size() + " chemins trouv�s.");
				else
					label1.setText("1 chemin trouv�.");
			}
			else
				label1.setText("Aucun chemin trouv� pour cette localisation.");
		
		if(selectedPath >= 0 && comboBoxPaths.getItemCount() > selectedPath)
			comboBoxPaths.setSelectedIndex(selectedPath);
		
		comboBoxPaths.addActionListener(comboBoxPathsActionListener);
		
		panel.add(label1);
		if(!pathVector.isEmpty())
			panel.add(label2);
		if(!pathVector.isEmpty())
			panel.add(scrollPane1);
		panel.add(sinnohMapPanel);
		panel.add(pramaLink);
		if(comboBoxTownLocations.getItemCount() > 0)
			panel.add(comboBoxTownLocations);
		panel.add(helpButton);
		if(comboBoxPaths.getItemCount() > 0)
			panel.add(comboBoxPaths);
		
		this.setContentPane(panel);
	}
	
	public void setLabel(String s) {
		label2.setText(s);
	}
	
	public int getSelectedLocation() {
		return comboBoxTownLocations.getSelectedIndex();
	}
	
	public int getSelectedPath() {
		return comboBoxPaths.getSelectedIndex();
	}
}