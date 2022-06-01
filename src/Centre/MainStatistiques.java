package Centre;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Cette classe possède l'ensemble des pages présentes dans l'interface de statistique 
public class MainStatistiques extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panneauPrincipale;
	
	//Ce constructeur représente la page principale de l'interface statistiques c'est le panneau principale contenant le panneau panneauPrincipale où sont contenus les statistiques
	public MainStatistiques() {
		Main.f.setTitle("Statistiques");
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		
		JLabel title=new JLabel("Statistiques");
		title.setFont(new Font("Arial",Font.BOLD,40));
		title.setBounds(250,30,550,50);
		title.setForeground(Color.WHITE);
		
		this.add(title);
		
		//Le panneau general est initialisé avec la panneau de la methode generale()
		panneauPrincipale=generale();
		panneauPrincipale.setBounds(30,150,680,300);
		panneauPrincipale.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.WHITE));
		this.add(panneauPrincipale);
		
		JButton returnButton=new JButton("Retour");
		returnButton.setBounds(550,612,110,40);
		this.add(returnButton);
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton Retour est appuyé, le panneau principal de la fenetre f présent dans la classe Main est remplacer par le constructeur Main()
				Main.f.setContentPane(new Main());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		
	}
	
	//Cette methode renvoie un panel qui affiche les statistiques generales
	public JPanel generale() {
		JPanel generale=new JPanel(),menu=new JPanel(),panneau=new JPanel();
		JButton generaleButton=new JButton("GENERALE"),pathologieButton=new JButton("PATHOLOGIE"),appareilButton=new JButton("APPAREIL"),appareilOctroyeButton=new JButton("APPAREIL(OCTROYE)");
		
		generale.setLayout(new BorderLayout());
		
		generaleButton.setBackground(Color.YELLOW);
		generaleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton GENERALE est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode generale()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(generale());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		pathologieButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton PATHOLOGIE est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode pathologie()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(pathologie());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		appareilButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton APPAREIL est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode appareil()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(appareil());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		appareilOctroyeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton APPAREIL(OCTROYE) est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode appareilOctroye()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(appareilOctroye());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		
		menu.setLayout(new GridLayout(1,3));
		menu.setBackground(getBackground());
		menu.add(generaleButton);
		menu.add(pathologieButton);
		menu.add(appareilButton);
		menu.add(appareilOctroyeButton);
		
		panneau.setLayout(new GridLayout(4,2));
		panneau.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.WHITE));
		
		String[][] titles= {
				{"Nombres Patients",String.valueOf(Statistiques.nbrPatient())},
				{"Nombres Consultations",String.valueOf(Statistiques.nbrConsultation())},
				{"Nombres d'Appareils Octroyé ",String.valueOf(Statistiques.nbrAppareilOctroye())},
				{"<html><div style='text-align:center;'>Moyenne Consultation<br>Par Patient</div></html>",String.valueOf(Statistiques.moyenneConsultationPatient())}
				
			};
		
		//boucle qui affiche les statistiques stocker dans le tableau titles
		for(int i=0;i<titles.length;i++) {
			for(int j=0;j<titles[i].length;j++) {
				JPanel panel=new JPanel();
				JLabel label=new JLabel(titles[i][j]);
				label.setFont(new Font("Arial",Font.BOLD,17));
				if(j!=0) {
					panel.setBackground(Color.WHITE);
				}
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(label);
				panneau.add(panel);
				
			}
		}
		generale.add(menu,BorderLayout.NORTH);
		generale.add(panneau);
		return generale;
		
	}
	
	//Cette methode renvoie un panel qui affiche les statistiques des pathologies
	public JPanel pathologie() {
		JPanel pathologie=new JPanel(),menu=new JPanel(),panneau=new JPanel();
		JButton generaleButton=new JButton("GENERALE"),pathologieButton=new JButton("PATHOLOGIE"),appareilButton=new JButton("APPAREIL"),appareilOctroyeButton=new JButton("APPAREIL(OCTROYE)");
		
		pathologie.setLayout(new BorderLayout());
		
		pathologieButton.setBackground(Color.YELLOW);
		generaleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton GENERALE est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode generale()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(generale());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		pathologieButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton PATHOLOGIE est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode pathologie()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(pathologie());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		appareilButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton APPAREIL est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode appareil()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(appareil());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		appareilOctroyeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton APPAREIL(OCTROYE) est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode appareilOctroye()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(appareilOctroye());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		
		menu.setLayout(new GridLayout(1,3));
		menu.setBackground(getBackground());
		menu.add(generaleButton);
		menu.add(pathologieButton);
		menu.add(appareilButton);
		menu.add(appareilOctroyeButton);
		
		panneau.setLayout(new GridLayout(5,3));
		panneau.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.WHITE));
		
		String[][] titles= {
				{"Consultation Infection",String.valueOf(Statistiques.nbrPathologie("INFECTION")),String.valueOf(Statistiques.pourcentagePathologie("INFECTION")+"%")},
				{"Consultation Ligament",String.valueOf(Statistiques.nbrPathologie("LIGAMENT")),String.valueOf(Statistiques.pourcentagePathologie("LIGAMENT")+"%")},
				{"Consultation Torticolis",String.valueOf(Statistiques.nbrPathologie("TORTICOLIS")),String.valueOf(Statistiques.pourcentagePathologie("TORTICOLIS")+"%")},
				{"Consultation Entorce",String.valueOf(Statistiques.nbrPathologie("ENTORCE")),String.valueOf(Statistiques.pourcentagePathologie("ENTORCE")+"%")},
				{"Consultation Membre cassé",String.valueOf(Statistiques.nbrPathologie("MEMBRE CASSÉ")),String.valueOf(Statistiques.pourcentagePathologie("MEMBRE CASSÉ")+"%")}
			};
		
		//boucle qui affiche les statistiques stocker dans le tableau titles
		for(int i=0;i<titles.length;i++) {
			for(int j=0;j<titles[i].length;j++) {
				JPanel panel=new JPanel();
				JLabel label=new JLabel(titles[i][j]);
				label.setFont(new Font("Arial",Font.BOLD,17));
				if(j!=0) {
					panel.setBackground(Color.WHITE);
				}
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(label);
				panneau.add(panel);
				
			}
		}
		pathologie.add(menu,BorderLayout.NORTH);
		pathologie.add(panneau);
		return pathologie;
	}
	
	//Cette methode renvoie un panel qui affiche les statistiques des appareils
	public JPanel appareil() {
		JPanel appareillage=new JPanel(),menu=new JPanel(),panneau=new JPanel();
		JButton generaleButton=new JButton("GENERALE"),pathologieButton=new JButton("PATHOLOGIE"),appareilButton=new JButton("APPAREIL"),appareilOctroyeButton=new JButton("APPAREIL(OCTROYE)");
		
		appareillage.setLayout(new BorderLayout());
		
		appareilButton.setBackground(Color.YELLOW);
		generaleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton GENERALE est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode generale()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(generale());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		pathologieButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton PATHOLOGIE est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode pathologie()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(pathologie());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		appareilButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton APPAREIL est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode appareil()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(appareil());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		appareilOctroyeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton APPAREIL(OCTROYE) est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode appareilOctroye()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(appareilOctroye());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		
		menu.setLayout(new GridLayout(1,3));
		menu.setBackground(getBackground());
		menu.add(generaleButton);
		menu.add(pathologieButton);
		menu.add(appareilButton);
		menu.add(appareilOctroyeButton);
		
		panneau.setLayout(new GridLayout(5,3));
		panneau.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.WHITE));
		
		String[][] titles= {
				{"Aucun appareil",String.valueOf(Statistiques.nbrAppareil("AUCUN")),String.valueOf(Statistiques.pourcentageAppareil("AUCUN")+"%")},
				{"Attelles",String.valueOf(Statistiques.nbrAppareil("ATTELLES")),String.valueOf(Statistiques.pourcentageAppareil("ATTELLES")+"%")},
				{"Prothese Externe",String.valueOf(Statistiques.nbrAppareil("PROTHESE EXTERNE")),String.valueOf(Statistiques.pourcentageAppareil("PROTHESE EXTERNE")+"%")},
				{"Platre",String.valueOf(Statistiques.nbrAppareil("PLATRE")),String.valueOf(Statistiques.pourcentageAppareil("PLATRE")+"%")},
				{"Fauteuil Roulant",String.valueOf(Statistiques.nbrAppareil("FAUTEUIL ROULANT")),String.valueOf(Statistiques.pourcentageAppareil("FAUTEUIL ROULANT")+"%")}
			};
		
		//boucle qui affiche les statistiques stocker dans le tableau titles
		for(int i=0;i<titles.length;i++) {
			for(int j=0;j<titles[i].length;j++) {
				JPanel panel=new JPanel();
				JLabel label=new JLabel(titles[i][j]);
				label.setFont(new Font("Arial",Font.BOLD,17));
				if(j!=0) {
					panel.setBackground(Color.WHITE);
				}
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(label);
				panneau.add(panel);
				
			}
		}
		appareillage.add(menu,BorderLayout.NORTH);
		appareillage.add(panneau);
		return appareillage;
	}
	
	//Cette methode renvoie un panel qui affiche les statistiques des appareils octroye
	public JPanel appareilOctroye() {
		JPanel appareillage=new JPanel(),menu=new JPanel(),panneau=new JPanel();
		JButton generaleButton=new JButton("GENERALE"),pathologieButton=new JButton("PATHOLOGIE"),appareilButton=new JButton("APPAREIL"),appareilOctroyeButton=new JButton("APPAREIL(OCTROYE)");
		
		appareillage.setLayout(new BorderLayout());
		
		appareilOctroyeButton.setBackground(Color.YELLOW);
		generaleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton GENERALE est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode generale()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(generale());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		pathologieButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton PATHOLOGIE est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode pathologie()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(pathologie());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		appareilButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton APPAREIL est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode appareil()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(appareil());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		appareilOctroyeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton APPAREIL(OCTROYE) est appuyé, le panneauprincipale de la classe MainStatistiques est remplacer par le panneau de la methode appareilOctroye()
				panneauPrincipale.removeAll();
				panneauPrincipale.add(appareilOctroye());
				panneauPrincipale.repaint();
				panneauPrincipale.revalidate();
			}
		});
		
		menu.setLayout(new GridLayout(1,3));
		menu.setBackground(getBackground());
		menu.add(generaleButton);
		menu.add(pathologieButton);
		menu.add(appareilButton);
		menu.add(appareilOctroyeButton);
		
		panneau.setLayout(new GridLayout(4,3));
		panneau.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.WHITE));
		
		String[][] titles= {
				{"Attelles",String.valueOf(Statistiques.nbrAppareilOctroye("ATTELLES")),String.valueOf(Statistiques.pourcentageAppareilOctroye("ATTELLES")+"%")},
				{"Prothese Externe",String.valueOf(Statistiques.nbrAppareilOctroye("PROTHESE EXTERNE")),String.valueOf(Statistiques.pourcentageAppareilOctroye("PROTHESE EXTERNE")+"%")},
				{"Platre",String.valueOf(Statistiques.nbrAppareilOctroye("PLATRE")),String.valueOf(Statistiques.pourcentageAppareilOctroye("PLATRE")+"%")},
				{"Fauteuil Roulant",String.valueOf(Statistiques.nbrAppareilOctroye("FAUTEUIL ROULANT")),String.valueOf(Statistiques.pourcentageAppareilOctroye("FAUTEUIL ROULANT")+"%")}
			};
		
		//boucle qui affiche les statistiques stocker dans le tableau titles
		for(int i=0;i<titles.length;i++) {
			for(int j=0;j<titles[i].length;j++) {
				JPanel panel=new JPanel();
				JLabel label=new JLabel(titles[i][j]);
				label.setFont(new Font("Arial",Font.BOLD,17));
				if(j!=0) {
					panel.setBackground(Color.WHITE);
				}
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(label);
				panneau.add(panel);
				
			}
		}
		appareillage.add(menu,BorderLayout.NORTH);
		appareillage.add(panneau);
		return appareillage;
	}
}
