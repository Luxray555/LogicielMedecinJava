package Centre;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/*Dans cette interface nous remplaçons le panel principal de la fenêtre f par des panneaus secondaires situé dans les différentes classes MainAdministrateur,
 * MainMédecin, MainTechnicien et Main Statistiques, où leurs constructeurs représentent la page d'accueil des interfaces Administrateur, Médecin, Technicien et Statistiques.
 * Chaque méthode présente dans ces classes renvoyées des JPanel qui permettent de prendre la place du panel principal.
  Lorsqu'on atterrit avec un bouton en réalité, le panneau principal du frame f est donc juste remplacé.*/
 

//Cette classe va nous permettre de créer une page ou la personne pourra faire le choix entre 4 boutons pour pouvoirs accéder à differentes fonctionnalités
public class Main extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame f=new JFrame();

	//Ce constructeur permet d'initialiser le panel principal composé d'un label et de 4 boutons qui menent vers des classe d'interface secondaire differentes*/
	public Main() {
		
		//On créer les attribus de la page, dans ces attribus il y aura la création des bouttons, les titres, la couleur des fonds, la couleur des label et la position de tout ces éléments
		f.setTitle("Menu Principale");
		JLabel title=new JLabel("Qui êtes vous ?");
		JButton adminButton=new JButton("Administrateur"),medecinButton=new JButton("Medecin"),technicienButton=new JButton("Technicien"),statistiquesButton=new JButton("Statistiques");
		this.setLayout(null);
		this.setBackground( Color.GRAY);
		
		title.setFont(new Font("Arial",Font.BOLD,50));
		title.setBounds(190,115,380,50);
		title.setForeground(Color.WHITE);
		this.add(title);
		
		adminButton.setFont(new Font("Arral",Font.BOLD,30));
		adminButton.setBounds(180,210,370,60);
		this.add(adminButton);
		adminButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton Administrateur est appuyé, la panneau principale de la fenetre f est remplacé par le constructeur de la classe MainAdministrateur
				f.setContentPane(new MainAdministrateur());
				//les methodes repaint() et revalidate() permettent de rafraichir le fenetre si elle vient à être modifier.
				f.repaint();
				f.revalidate();
			}
		});
		
		medecinButton.setFont(new Font("Arial",Font.BOLD,30));
		medecinButton.setBounds(180,300,370,60);
		this.add(medecinButton);
		medecinButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton Administrateur est appuyé, la panneau principale de la fenetre f est remplacé par le constructeur de la classe MainMedecin
				f.setContentPane(new MainMedecin());
				f.repaint();
				f.revalidate();
			}
		});
		
		technicienButton.setFont(new Font("Arial",Font.BOLD,30));
		technicienButton.setBounds(180,390,370,60);
		this.add(technicienButton);
		technicienButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton Administrateur est appuyé, la panneau principale de la fenetre f est remplacé par le constructeur de la classe MainTechnicien
				f.setContentPane(new MainTechnicien());
				f.repaint();
				f.revalidate();
			}
		});
		
		statistiquesButton.setFont(new Font("Arial",Font.BOLD,30));
		statistiquesButton.setBackground(Color.BLACK);
		statistiquesButton.setForeground(Color.WHITE);
		statistiquesButton.setBounds(265,480,200,50);
		this.add(statistiquesButton);
		statistiquesButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton Administrateur est appuyé, la panneau principale de la fenetre f est remplacé par le constructeur de la classe MainStatistiques
				f.setContentPane(new MainStatistiques());
				f.repaint();
				f.revalidate();
			}
		});
	}
	
	
	//Cette methode est un main elle nous permet d'executer la frame f principale et de la caraterisé avec des attributs comme sa taille, son fond, son style etc...
	public static void main(String[] args) {
		//UIManager nous permet de mettre en place un style prédéfinis de fenetre
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
		}
		f.setSize(750,750);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setContentPane(new Main());
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
