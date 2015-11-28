package com.prama.phoebe;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	private JCheckBox detailCheckBox;
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
												"Puis, choisissez dans la liste parmi toutes les localisations proposées votre destination.\n"+
												"Vous pourrez alors parmi tous les chemins possibles celui que vous voulez emprunter pour y arriver.\n"+
												"\n"+
												"Signification des sigles :\n"+
												"(NI) : Non Implémenté, bâtiment ne faisant pas partie du jeu)\n"+
												"(!) : Bâtiment inconnu impossible à charger.");
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
	
	private ActionListener checkBoxActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setPathList();
			drawWindow();
		}
	};
	
	private MouseListener sinnohMapPanelMouseListener = new MouseListener () {
		@Override
		public void mouseClicked(MouseEvent mouse) {
			int mouseX = (mouse.getX() - 2) / 14;
			int mouseY = (mouse.getY() + 6) / 14;
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
		this.setSize(800, 375);
		this.setLocationRelativeTo(null);
		this.setTitle("Phoebe");
		
		panel = new JPanel();
		panel.setLayout(null);
		
		label1 = new JLabel();
		label1.setText("Cliquez sur une ville ou choisissez une localisation.");
		label1.setBounds(440, 0, 300, 20);
		
		label2 = new JLabel();
		label2.setBounds(440, 49, 200, 20);
		label2.setText("Choisissez votre chemin :");
		
		sinnohMapPanel = new ImagePanel(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/prama/phoebe/assets/carte.PNG"));
		sinnohMapPanel.setBounds(0, 0, 432, 336);
		sinnohMapPanel.addMouseListener(sinnohMapPanelMouseListener);
		
		pramaLink = new JButton();
		pramaLink.setBounds(567, 315, 209, 20);
		pramaLink.setText("Aller sur le site de PRAMA");
		pramaLink.addActionListener(pramaLinkListener);
		
		helpButton = new JButton();
		helpButton.setBounds(440, 315, 120, 20);
		helpButton.setText("Besoin d'aide ?");
		helpButton.addActionListener(helpListener);
		
		detailCheckBox = new JCheckBox();
		detailCheckBox.setText("Mode détaillé");
		detailCheckBox.setBounds(640, 69, 100, 20);
		detailCheckBox.addActionListener(checkBoxActionListener);
		
		drawWindow();
		
		this.setVisible(true);
	}
	
	public void drawWindow() {
		panel.removeAll();
		
		setPathList();
		
		int temp = 0;
		
		if(comboBoxTownLocations != null)
			temp = comboBoxTownLocations.getSelectedIndex();
		
		comboBoxTownLocations = new JComboBox<String>();
		comboBoxTownLocations.setBounds(440, 20, 300, 20);
		
		for(int i = 0; i < townLocationsVector.size(); i++) {
			if(townLocationsVector.size() == 559)
				if(Map.getTownFromLocation(i) != 0)
					comboBoxTownLocations.addItem(townLocationsVector.get(i) + " : (" + Map.noms[townLocationsVector.get(Map.getTownFromLocation(i))] + ") " + Map.noms[townLocationsVector.get(i)]);
				else
					comboBoxTownLocations.addItem(townLocationsVector.get(i) + " : " + Map.noms[townLocationsVector.get(i)]);
			else
				comboBoxTownLocations.addItem(townLocationsVector.get(i) + " : " + Map.noms[townLocationsVector.get(i)]);
		}
		if(comboBoxTownLocations.getItemCount() > temp && temp >= 0)
			comboBoxTownLocations.setSelectedIndex(temp);
		
		comboBoxTownLocations.addActionListener(comboBoxTownLocationsActionListener);
		
		scrollPane1 = new JScrollPane(list1);
		scrollPane1.setBounds(440, 95, 337, 214);
		scrollPane1.setViewportView(list1);
		
		comboBoxPaths = new JComboBox<String>();
		comboBoxPaths.setBounds(440, 69, 200, 20);
		
		if (pathfinder != null)
			if(pathfinder.paths.size() > 0) {
				for(int i = 0; i < pathfinder.paths.size(); i++)
					comboBoxPaths.addItem("Chemin " + i);
				if(pathfinder.paths.size() > 1)
					label1.setText(pathfinder.paths.size() + " chemins trouvés.");
				else
					label1.setText("1 chemin trouvé.");
			}
			else
				label1.setText("Aucun chemin trouvé pour cette localisation.");
		
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
		if(comboBoxPaths.getItemCount() > 0) {
			panel.add(comboBoxPaths);
			panel.add(detailCheckBox);
		}
		
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
	
	private void setPathList() {
		Vector<String> listItems = new Vector<String>();
		if(pathVector.size() != 0) {
			if(detailCheckBox.isSelected()) {
				listItems.add("Rentrez dans la void par " + Map.noms[pathVector.get(0)] + " (" + Map.noms[Map.getTownFromLocation(pathVector.get(0))] + ").");
				
				if(pathVector.size() >= 2) {
					listItems.add("Montez jusqu'à trouver " + Map.noms[pathVector.get(1)] + " (" + Map.noms[Map.getTownFromLocation(pathVector.get(1))] + ").");
				
				
					int i = 2;
					while(i < pathVector.size()) {
						listItems.add("Sauvegardez et redémarrez le jeu.");
						listItems.add("Faites un pas en bas et un pas en haut pour arriver à");
						listItems.add(Map.noms[pathVector.get(i)] + " (" + Map.noms[Map.getTownFromLocation(pathVector.get(i))] + ").");
						i++;
					}
					
					listItems.add("Sauvegardez et redémarrez le jeu.");
					listItems.add("Descendez jusqu'à rencontrer un mur invisible.");
					listItems.add("Faites XABB");
					listItems.add("Vous êtes arrivé.");
				} else {
					listItems.add("Vous êtes arrivé.");
				}
			} else {
				for(int i = 0; i < pathVector.size(); i++) {
					listItems.add(pathVector.get(i) + " : " + Map.noms[pathVector.get(i)] + " (" + Map.noms[Map.getTownFromLocation(pathVector.get(i))] + ").");
				}
			}
		}
		
		list1 = new JList<String>(listItems);
		
	}
}
