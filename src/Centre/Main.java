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

/*Dans cette interface nous rempla�ons le panel principal de la fen�tre f par des panneaus secondaires situ� dans les diff�rentes classes MainAdministrateur,
 * MainM�decin, MainTechnicien et Main Statistiques, o� leurs constructeurs repr�sentent la page d'accueil des interfaces Administrateur, M�decin, Technicien et Statistiques.
 * Chaque m�thode pr�sente dans ces classes renvoy�es des JPanel qui permettent de prendre la place du panel principal.
  Lorsqu'on atterrit avec un bouton en r�alit�, le panneau principal du frame f est donc juste remplac�.*/
 

//Cette classe va nous permettre de cr�er une page ou la personne pourra faire le choix entre 4 boutons pour pouvoirs acc�der � differentes fonctionnalit�s
public class Main extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JFrame f=new JFrame();

	//Ce constructeur permet d'initialiser le panel principal compos� d'un label et de 4 boutons qui menent vers des classe d'interface secondaire differentes*/
	public Main() {
		
		//On cr�er les attribus de la page, dans ces attribus il y aura la cr�ation des bouttons, les titres, la couleur des fonds, la couleur des label et la position de tout ces �l�ments
		f.setTitle("Menu Principale");
		JLabel title=new JLabel("Qui �tes vous ?");
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
				//Quand le bouton Administrateur est appuy�, la panneau principale de la fenetre f est remplac� par le constructeur de la classe MainAdministrateur
				f.setContentPane(new MainAdministrateur());
				//les methodes repaint() et revalidate() permettent de rafraichir le fenetre si elle vient � �tre modifier.
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
				//Quand le bouton Administrateur est appuy�, la panneau principale de la fenetre f est remplac� par le constructeur de la classe MainMedecin
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
				//Quand le bouton Administrateur est appuy�, la panneau principale de la fenetre f est remplac� par le constructeur de la classe MainTechnicien
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
				//Quand le bouton Administrateur est appuy�, la panneau principale de la fenetre f est remplac� par le constructeur de la classe MainStatistiques
				f.setContentPane(new MainStatistiques());
				f.repaint();
				f.revalidate();
			}
		});
	}
	
	
	//Cette methode est un main elle nous permet d'executer la frame f principale et de la carateris� avec des attributs comme sa taille, son fond, son style etc...
	public static void main(String[] args) {
		//UIManager nous permet de mettre en place un style pr�d�finis de fenetre
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
