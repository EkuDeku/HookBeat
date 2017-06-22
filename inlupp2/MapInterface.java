package inlupp2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MapInterface extends JFrame {
	
	private int x, y;
	private Boolean addBool;
	private ArrayList<Plats> places = new ArrayList<Plats>();
	private JButton newButton = new JButton("Ny"), searchButton = new JButton("Sök"),
			hideButton = new JButton("Göm"), removeButton = new JButton("Ta bort"),
			coordinateButton = new JButton("Koordinater"), hideCatButton = new JButton("Göm kategori");
	private JRadioButton namedLabel = new JRadioButton("Namngett"), describedLabel = new JRadioButton("Beskriven");
	private JLabel categoryLabel = new JLabel("Kategorier");
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Arkiv");
	private JMenuItem newMapItem = new JMenuItem("Ny karta"),loadPlacesItem = new JMenuItem("Ladda in platser"), 
			saveItem = new JMenuItem("Spara"), exitItem = new JMenuItem("Avsluta");
	private JFileChooser jfc = new JFileChooser();
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG", "png");
	private File openFile, newFile;
	private String longName;
	private ImagePanel iP;
	private JPanel centralPanel = new JPanel();
	private JScrollPane sP;
	private JTextField tF = new JTextField("Sök", 12);  
	private String categoryChoice[] ={
			"Buss",
			"Tunnelbana",
			"Tåg",
			" "
	}; 
	private JList<String> categoryList = new JList<>(categoryChoice); 

	
	public static void main(String[] args){
		new MapInterface();
	}
	
	public MapInterface(){
		super("Inlupp2");

		JPanel topPanel = new JPanel(), rightPanel = new JPanel(), buttonPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(Color.DARK_GRAY);
		add(rightPanel, BorderLayout.EAST);
		rightPanel.setBackground(Color.DARK_GRAY);
		add(centralPanel, BorderLayout.CENTER);
		centralPanel.setBackground(Color.DARK_GRAY);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBackground(Color.DARK_GRAY);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		//MenuBar
		menuBar.add(menu);
		menu.add(newMapItem);
		NewMapListener newMap = new NewMapListener();
		newMapItem.addActionListener(newMap);
		menu.add(loadPlacesItem);
		SaveListener saveL = new SaveListener();
		saveItem.addActionListener(saveL);
		menu.add(saveItem);
		ExitListener exit = new ExitListener();
		exitItem.addActionListener(exit);
		menu.add(exitItem);
		topPanel.add(menuBar);
		setJMenuBar(menuBar);
		
		//TopSide
		ButtonGroup badGame = new ButtonGroup();
		topPanel.add(newButton);
		NewButtonListener nBL = new NewButtonListener();
		newButton.addActionListener(nBL);
		buttonColour(newButton);
		buttonPanel.add(namedLabel);
		badGame.add(namedLabel);
		buttonPanel.add(describedLabel);	
		badGame.add(describedLabel);
		namedLabel.setSelected(true);
		namedLabel.setBackground(Color.DARK_GRAY);
		describedLabel.setBackground(Color.DARK_GRAY);
		namedLabel.setFocusPainted(false);
		describedLabel.setFocusPainted(false);
		namedLabel.setForeground(Color.white);
		describedLabel.setForeground(Color.white);
		topPanel.add(buttonPanel);
		topPanel.add(tF);
		topPanel.add(searchButton);
		buttonColour(searchButton);
		topPanel.add(hideButton);
		buttonColour(hideButton);
		topPanel.add(removeButton);
		buttonColour(removeButton);
		topPanel.add(coordinateButton);
		buttonColour(coordinateButton);
		
		
		//RightSide
		rightPanel.add(categoryLabel);
		categoryLabel.setForeground(Color.white);
		rightPanel.add(categoryList);
		categoryList.setBackground(Color.LIGHT_GRAY);
		rightPanel.add(hideCatButton);
		buttonColour(hideCatButton);

		//Central
		//Mouse
	    centralPanel.addMouseListener(new MouseAdapter(){
	            public void mousePressed(MouseEvent e){
	                setX(e.getX()+9);
	                setY(e.getY()+68);
	                paintComponent(getGraphics());
	                centralPanel.validate();
	            }
	        });
		

		//Window
		setSize(600, 400);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	//Stuff
	protected void paintComponent(Graphics g){
        super.paintComponents(g);
        if(addBool == true){
        	g.setColor(Color.RED);
        	int[] xPos = {getX()-15,getX(),getX()+15};
        	int[] yPos = {getY(),getY()+30,getY()};
        	g.fillPolygon(xPos, yPos, 3);
        	addBool = false;
        	if(namedLabel.isSelected()){
        		String tempName = JOptionPane.showInputDialog("Named place:");
        		NamedPlace newPlace = new NamedPlace(getX(), getY(), tempName, "Cat");
        		System.out.print(tempName);
        		places.add(newPlace);
        	}else{
        		JPanel descPanel = new JPanel();
        		JTextField nameField = new JTextField(20);
        		descPanel.add(nameField);
        		String tempStrin = JOptionPane.showInputDialog(descPanel);
            	DescribedPlace newPlace = new DescribedPlace(getX(), getY(), nameField.getText(), "Doggo", tempStrin);
            	System.out.println(nameField.getText());
            	System.out.println(tempStrin);
            	places.add(newPlace);
        }

        }
    }
	
	public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
	
	
	//Listeners
	class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			dispose();
		}
	}

	class NewMapListener implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			fileDialog();
			if(iP != null){
				centralPanel.removeAll();
			}
			sP = new JScrollPane(iP = new ImagePanel(longName));
			centralPanel.add(sP);
			centralPanel.validate();
		}
	}
	
	class PlaceLoaderListener implements ActionListener{
		public void actionPerformed(ActionEvent ave){

		}
	}
	
	class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			saveDialog();
		}
	}
	
	class loadListener implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			fileDialog();
		}
	}
	
	class NewButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent ave){

	            addBool = true;
	            System.out.println("Adding thing");

	        }

	    }
	
	class TempListener implements ActionListener{
		public void actionPerformed(ActionEvent ave){

		}
	}
	
	
	//Dialogs
	public void fileDialog(){
		jfc.setFileFilter(filter);
		int svar = jfc.showOpenDialog(MapInterface.this);
		if (svar == JFileChooser.APPROVE_OPTION){
			openFile = jfc.getSelectedFile();
			longName = openFile.getAbsolutePath();
		}
	}
	
	public void saveDialog(){
		int svar = jfc.showSaveDialog(MapInterface.this);
		if (svar == JFileChooser.APPROVE_OPTION){
			try{newFile.createNewFile();
			}catch (IOException e) {
			    e.printStackTrace();
			}
		}
	}
	
	
	//Image
	class ImagePanel extends JPanel {

		private ImageIcon image;
		public ImagePanel(String path){
				image = new ImageIcon(path);				
				setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
		}
		
		public void paintComponent(Graphics g){
			g.drawImage(image.getImage(), 0, 0, this);
		}
		
		public int getImageWidth() {
			return image.getIconWidth();
		}
		
		public int getImageHeight() {
			return image.getIconHeight();
		}
		
	}
	
	
	//A E S T H E T I C S
	public void buttonColour(JButton c){
		c.setBackground(Color.GRAY);
		c.setFocusPainted(false);
		c.setBorderPainted(false);
	}
	
	
}
