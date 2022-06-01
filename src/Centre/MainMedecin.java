package Centre;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

//Cette classe possède l'ensemble des pages présentes dans l'interface du médecin
public class MainMedecin extends JPanel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//listPanel est la panel qui contient la liste des Patient et des Consultations lorsque l'on recherche un patient ou que l'on recherche une consultation
	private JPanel listPanel;
	private JScrollPane consultationListPanel;

	//Ce constructeur représente la page principale de l'interface médecin
	public MainMedecin() {
		Main.f.setTitle("Medecin");
		this.setLayout(null);
		this.setBackground( Color.RED);
		
		JButton addButton=new JButton("Ajouter une Consultation"),searchButton=new JButton("Rechercher une Consultation"),returnButton=new JButton("Retour");
		
		JLabel title=new JLabel("Medecin");
		title.setFont(new Font("Arial",Font.BOLD,50));
		title.setBounds(260,145,350,50);
		title.setForeground(Color.WHITE);
		
		addButton.setFont(new Font("Arial",Font.BOLD,30));
		addButton.setBounds(140,250,440,60);
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				/*Le bouton Ajouter une Consultation remplace le panneau principale de la fenetre f par le panneau de la methode addConsultationPanel(), c'est à dire le panneau
				 * du formulaire d'ajout d'une consultation*/
				Main.f.setContentPane(addConsultationPanel());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		
		searchButton.setFont(new Font("Arial",Font.BOLD,29));
		searchButton.setBounds(140,340,440,60);
		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Le bouton Rechercher un Patient remplace le panneau principale de la fenetre f par le panneau de la methode searchPatientPanel(), c'est à dire le panneau de recherche de patient
				Main.f.setContentPane(searchPatientPanel());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		
		returnButton.setBounds(550,612,110,40);
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Le bouton Retour remplace le panneau principale de la fenetre f par le panneau du constructeur de la classe Main() qui est le menu principal
				Main.f.setContentPane(new Main());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		
		this.add(title);
		this.add(addButton);
		this.add(searchButton);
		this.add(returnButton);
	}

	//Cette méthode renvoie le panneau du formulaire d'ajout d'une consultation
	public JPanel addConsultationPanel() {
		JPanel addConsultationPanel=new JPanel(),text = new JPanel();
		JLabel title=new JLabel("Ajouter une Consultation"),patientLabel[]= {new JLabel("Nom du Patient :"),new JLabel("Prenom du Patient :"),new JLabel("Nom du Medecin :"),new JLabel("Date de Consultation (dd/mm/yyyy) :"),new JLabel("Pathologie Diagnostiquée :"),new JLabel("Appareillage Nécessaire :")};
		JTextField consultationTextField[]= {new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10)};
		String pathologieChoice[]= {"INFECTION","LIGAMENT","TORTICOLIS","ENTORCE","MEMBRE CASSÉ"},appareillageChoice[]= {"AUCUN","ATTELLES","PROTHESE EXTERNE","PLATRE","FAUTEUIL ROULANT"};
		JComboBox<String> cbPathologie = new JComboBox<String>(pathologieChoice),cbAppareillage = new JComboBox<String>(appareillageChoice);
		JButton acceptButton=new JButton("Ajouter"),returnButton=new JButton ("Retour");
		
		addConsultationPanel.setLayout(null);
		addConsultationPanel.setBackground( Color.RED);
		title.setFont(new Font("Arial",Font.BOLD,50));
		title.setBounds(60,50,600,50);
		title.setForeground(Color.WHITE);
		addConsultationPanel.add(title);
		
		text.setBounds(40,135,650,400);
		text.setLayout(new BoxLayout(text,BoxLayout.Y_AXIS));
		text.setBackground( Color.RED);
		text.setBorder(BorderFactory.createTitledBorder("FORMULAIRE"));
		addConsultationPanel.add(text);
		
		//Cette boucle permet l'affichage du formulaire d'ajout, plus précissement d'afficher les Labels côte à côte aux TextFields ou au ComboBox dans le panneau text (formulaire)
		for(int i=0;i<patientLabel.length;i++) {
			JPanel panel=new JPanel();
			panel.setBackground( Color.RED);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			patientLabel[i].setFont(new Font("Arial",Font.BOLD,24));
			patientLabel[i].setForeground(Color.WHITE);
			panel.add(patientLabel[i]);
			if (i<4) {
				consultationTextField[i].setFont(new Font("Arial",Font.BOLD,20));
				panel.add(consultationTextField[i]);
			}
			else if(i==4) {
				cbPathologie.setFont(new Font("Arial",Font.BOLD,20));
				panel.add(cbPathologie);
			}else if(i==5) {
				cbAppareillage.setFont(new Font("Arial",Font.BOLD,20));
				panel.add(cbAppareillage);
			}
			text.add(panel);
		}
		
		acceptButton.setBounds(290,600,150,60);
		addConsultationPanel.add(acceptButton);
		acceptButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				/*Quand le bouton Ajouter est appuyé, une consultation est créée si les critéres de la méthode addConsultation() sont compris si aucun appareil est nécéssaire alors
				 l'octroyage est automatique sur AUCUN sinon il est EN ATTENTE */
				String octroye;
				if(cbAppareillage.getSelectedItem().toString().equals("AUCUN")) {
					octroye="NON";
				}else {
					octroye="EN ATTENTE";
				}
				(new Consultation(consultationTextField[0].getText(),consultationTextField[1].getText(),consultationTextField[2].getText(),consultationTextField[3].getText(),cbPathologie.getSelectedItem().toString(),cbAppareillage.getSelectedItem().toString(),octroye)).addConsultation();
			}
		});
		
		returnButton.setBounds(550,612,110,40);
		addConsultationPanel.add(returnButton);
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton Retour est appuyé, le panneau principal de la fenetre f présent dans la classe Main est remplacer par le constructeur MainMedecin()
				Main.f.setContentPane(new MainMedecin());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		return addConsultationPanel;
	}
	
	//Cette méthode renvoie le panneau de recherche de patient
	public JPanel searchPatientPanel() {
		JPanel searchConsultationPanel=new JPanel();
		JLabel title=new JLabel("Rechercher une consultation"), namePatient=new JLabel("Nom du patient:");
		JTextField searchTextFieldPatient=new JTextField(10);
		JButton searchButton=new JButton("Chercher"),returnButton=new JButton("Retour");
		
		searchConsultationPanel.setLayout(null);
		searchConsultationPanel.setBackground( Color.RED);
		
		title.setFont(new Font("Arial",Font.BOLD,40));
		title.setBounds(95,30,550,50);
		title.setForeground(Color.WHITE);
		searchConsultationPanel.add(title);
		
		namePatient.setBounds(75,150,250,40);
		namePatient.setFont(new Font("Arial",Font.BOLD,27));
		namePatient.setForeground(Color.WHITE);
		searchConsultationPanel.add(namePatient);
		
		searchTextFieldPatient.setBounds(295,157,250,30);
		searchTextFieldPatient.setFont(new Font("Arial",Font.BOLD,17));
		searchConsultationPanel.add(searchTextFieldPatient);
		
		searchButton.setBounds(555,155,100,35);
		searchConsultationPanel.add(searchButton);
		
		listPanel=new JPanel();
		listPanel=listPatientPanel(searchTextFieldPatient.getText());
		consultationListPanel=new JScrollPane(listPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		consultationListPanel.setBounds(70,190,600,400);
		searchConsultationPanel.add(consultationListPanel);
		
		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				/*Quand le bouton Chercher est appuyé, le panneau de la liste de patient affiche seulement les patients ayant les memes caracteres au debut de leurs nom que le texte
				  présent dans le textField*/
				listPanel.removeAll();
				listPanel=listPatientPanel(searchTextFieldPatient.getText());
				listPanel.repaint();
				listPanel.revalidate();
				
			}
			
		});
		
		returnButton.setBounds(550,612,110,40);
		searchConsultationPanel.add(returnButton);
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton Retour est appuyé, le panneau principal de la fenetre f présent dans la classe Main est remplacer par le constructeur MainMedecin()
				Main.f.setContentPane(new MainMedecin());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		
		return searchConsultationPanel;
	}
	
	/*Cette méthopde représente la panel où la liste des patients y est inscrit dans le panel searchPatient(), elle dépend d'un argument qui n'est que ce qui se trouve dans le textField
	  du panneau searchPatientPanel().*/
	public JPanel listPatientPanel(String nomPatient) {
		ArrayList<Patient> patientList;
		//Ici, patientList est une liste qui contient tout les patient ayant un nom commençant par les mêmes caractéres que la variable nom en arguments de la méthode listPanel
		patientList=(new Patient()).searchPatient(nomPatient);
		if(patientList.size()>0) {
		listPanel.setLayout(new GridLayout(100, 1,5,5));
		ArrayList<JPanel> patientPanel=new ArrayList<>();
		
		//Cette boucle permet d'ajouter au panel listPatient (qui est un panneau déroulant) tout les Patients selon le format: NomPatient PrenomPatient consultationsBouton
		for(int i=0;i<patientList.size();i++) {
			int nbr=i;
			patientPanel.add(new JPanel());
			patientPanel.get(i).setLayout(new FlowLayout());
			patientPanel.get(i).setBackground(Color.BLACK);
			
			JLabel info=new JLabel("Nom Patient : "+patientList.get(i).getNom()+" "+patientList.get(i).getPrenom());
			info.setForeground(Color.WHITE);
			info.setFont(new Font("Arial",Font.BOLD,15));
			patientPanel.get(i).add(new JLabel().add(info));
			
			JButton consultationButton=new JButton("Consultations");
			patientPanel.get(i).add(consultationButton);
			
			consultationButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					//Quand le bouton Consultation est appuyé, le panneau principal est remplacé par le panneau de recherche des consultations du patient patientList.get(nbr)
					Main.f.setContentPane(searchConsultationPatientPanel(patientList.get(nbr).getNom(),patientList.get(nbr).getPrenom()));
					Main.f.revalidate();
					Main.f.repaint();
				}
			});
			
			listPanel.add(patientPanel.get(i));
			}
		}else {
			//Si la liste est vide alors un label s'affiche qui nous dit "Aucun Patient"
			JLabel aucun=new JLabel("Aucun Patient");
			listPanel.setLayout(null);
			aucun.setFont(new Font("Arial",Font.BOLD,30));
			aucun.setBounds(190,150,300,50);
			listPanel.add(aucun);
		}
		return listPanel;
	}
	
	//Cette méthode renvoie le panneau de recherche de consultation d'un patient selon son nom et prenom
	public JPanel searchConsultationPatientPanel(String nomPatient,String prenomPatient) {
		JPanel consultationPatientPanel=new JPanel();
		JLabel title=new JLabel(nomPatient+" "+prenomPatient), nameMedecin=new JLabel("Nom du Medecin:"),date=new JLabel("Date(dd/mm/yyyy):");
		JTextField searchTextFieldMedecin=new JTextField(10),searchTextFieldDate=new JTextField(10);
		JButton searchButton=new JButton("Chercher"),returnButton=new JButton("Retour");
		
		consultationPatientPanel.setLayout(null);
		consultationPatientPanel.setBackground( Color.RED);
		
		title.setFont(new Font("Arial",Font.BOLD,40));
		title.setBounds(10,10,650,40);
		title.setForeground(Color.WHITE);
		consultationPatientPanel.add(title);
		
		nameMedecin.setBounds(75,120,250,40);
		nameMedecin.setFont(new Font("Arial",Font.BOLD,27));
		nameMedecin.setForeground(Color.WHITE);
		consultationPatientPanel.add(nameMedecin);
		
		date.setBounds(75,150,250,40);
		date.setFont(new Font("Arial",Font.BOLD,27));
		date.setForeground(Color.WHITE);
		consultationPatientPanel.add(date);
		
		searchTextFieldMedecin.setBounds(315,127,230,30);
		searchTextFieldMedecin.setFont(new Font("Arial",Font.BOLD,17));
		consultationPatientPanel.add(searchTextFieldMedecin);
		
		searchTextFieldDate.setBounds(325,157,220,30);
		searchTextFieldDate.setFont(new Font("Arial",Font.BOLD,17));
		consultationPatientPanel.add(searchTextFieldDate);
		
		searchButton.setBounds(555,135,100,40);
		consultationPatientPanel.add(searchButton);
		
		listPanel=new JPanel();
		listPanel=listConsultationPatientPanel(nomPatient,prenomPatient,searchTextFieldMedecin.getText(),searchTextFieldDate.getText());
		consultationListPanel=new JScrollPane(listPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		consultationListPanel.setBounds(70,190,600,400);
		consultationPatientPanel.add(consultationListPanel);
		
		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				/*Quand le bouton Chercher est appuyé, le panneau de la liste de consultation affiche seulement les consultation du partient ayant les memes caracteres au debut de leurs
				  nomMedecin et date que le texte présent dans le searchTextFieldMedecin et le searchTextFieldDate*/
				listPanel.removeAll();
				listPanel=listConsultationPatientPanel(nomPatient,prenomPatient,searchTextFieldMedecin.getText(),searchTextFieldDate.getText());
				listPanel.repaint();
				listPanel.revalidate();
				
			}
			
		});
		
		returnButton.setBounds(550,612,110,40);
		consultationPatientPanel.add(returnButton);
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton Retour est appuyé, le panneau principal de la fenetre f présent dans la classe Main est remplacer par le panneau de recherche de patient
				Main.f.setContentPane(searchPatientPanel());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		
		return consultationPatientPanel;
	}
	
	/*Cette méthopde représente le panneau où la liste des consultations y est inscrit, dans le panneau searchPatient(), elle dépend de 4 arguments qui sont ce qui se trouve dans
	  le searchTextFieldMedecin et searchTextFieldDate du panneau searchConsultationPatientPanel() et du nom et prenom du patient.*/
	public JPanel listConsultationPatientPanel(String nomPatient,String prenomPatient,String nomMedecin, String date) {
		ArrayList<Consultation> consultationList;
		/*Ici, consultationList est une liste qui contient toutes les consultations d'un patient ayant un nom Medecin et une date commençant par les mêmes caractéres que la variable
		 nomMedecin et date en arguments de la méthode listConsultationPatientPanel*/
		consultationList=(new Consultation()).searchConsultationList(nomPatient,prenomPatient,nomMedecin,date);
		if(consultationList.size()>0) {
		listPanel.setLayout(new GridLayout(100, 1,5,5));
		ArrayList<JPanel> patientPanel=new ArrayList<>();
		
		//Cette boucle permet d'ajouter au panel listPatient (qui est un panneau déroulant) tout les Patients selon le format: NomMedecin Date informationBouton supprimerBouton
		for(int i=0;i<consultationList.size();i++) {
			int nbr=i;
			patientPanel.add(new JPanel());
			patientPanel.get(i).setLayout(new FlowLayout());
			patientPanel.get(i).setBackground(Color.BLACK);
			
			JLabel info=new JLabel("Nom Medecin : "+consultationList.get(i).getNomMedecin()+" | Date : "+consultationList.get(i).getDate());
			info.setForeground(Color.WHITE);
			info.setFont(new Font("Arial",Font.BOLD,15));
			patientPanel.get(i).add(new JLabel().add(info));
			
			JButton informationButton=new JButton("Informations"),deleteButton=new JButton("Supprimer");
			patientPanel.get(i).add(informationButton);
			patientPanel.get(i).add(deleteButton);
			
			informationButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					//Quand le bouton Information est appuyé, le panneau principal est remplacé par le panneau d'information du consultationList.get(i)
					Main.f.setContentPane(informationConsultationPanel(consultationList.get(nbr)));
					Main.f.revalidate();
					Main.f.repaint();
				}
			});
			
			deleteButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					//Quand le bouton Supprimer est appuyé, la consultionList.get(i) est alors supprimé ici, si le medecin confirme la suppression dans le pop up qui s'affiche ensuite
					int result = JOptionPane.showConfirmDialog(new JFrame(), "Etes-vous sur de supprimer la consultation ?");
					if (result == 0) {
						consultationList.get(nbr).deleteConsultation();
						listPanel.removeAll();
						listPanel=listConsultationPatientPanel(nomPatient,prenomPatient,nomMedecin,date);
						listPanel.repaint();
						listPanel.revalidate();
					}
				}
			});
			
			listPanel.add(patientPanel.get(i));
			}
		}else {
			//Si la liste est vide alors un label s'affiche qui nous dit "Aucune Consultation"
			JLabel aucun=new JLabel("Aucune Consultations");
			listPanel.setLayout(null);
			aucun.setFont(new Font("Arial",Font.BOLD,30));
			aucun.setBounds(130,150,350,50);
			listPanel.add(aucun);
		}
		return listPanel;
	}
	
	//Cette methode renvoie le panneau d'information de consultation placer en argument
		public JPanel informationConsultationPanel(Consultation c) {
			JPanel informationConsultationPanel =new JPanel(),informationPanel =new JPanel();
			JLabel consultationLabel[] = {new JLabel("Nom Patient : "+c.getNomPatient()),new JLabel("Prenom Patient : "+c.getPrenomPatient()),new JLabel("Nom Medecin : "+c.getNomMedecin()), new JLabel("Date : "+c.getDate()),new JLabel("Pathologie diagnostiqué : "+c.getPathologie()),new JLabel("Appareillage : "+c.getAppareillage()),new JLabel("Octroye : "+c.getOctroye())};
			JButton editButton =new JButton("Modifier"),returnButton =new JButton("Retour");
			
			informationConsultationPanel.setLayout(null);
			informationConsultationPanel.setBackground( Color.RED);
			
			informationPanel.setBounds(40,60,650,450);
			informationPanel.setLayout(new BoxLayout(informationPanel,BoxLayout.Y_AXIS));
			informationPanel.setBackground( Color.RED);
			informationPanel.setBorder(BorderFactory.createTitledBorder("INFORMATIONS CONSULTATION"));
			informationConsultationPanel.add(informationPanel);
			for(int i=0;i<7;i++) {
				JPanel panel=new JPanel();
				panel.setBackground( Color.RED);
				panel.setLayout(new FlowLayout(FlowLayout.LEFT));
				consultationLabel[i].setFont(new Font("Arial",Font.BOLD,26));
				consultationLabel[i].setForeground(Color.WHITE);
				panel.add(consultationLabel[i]);
				informationPanel.add(panel);
			}
			
			
			editButton.setBounds(290,600,150,60);
			informationConsultationPanel.add(editButton);
			editButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					//Lorsque le bouton Modifier est appuyé, le panneau principal de la fenetre principale est remplacer par le panneau de modification de la consultation c
					Main.f.setContentPane((new MainMedecin()).editConsultationPanel(c));
					Main.f.revalidate();
					Main.f.repaint();
				}
			});
			
			returnButton.setBounds(550,612,110,40);
			informationConsultationPanel.add(returnButton);
			returnButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					//Lorsque le bouton Retour est appuyé, le panneau principal de la fenetre principale est remplacer par celui de recherche de consultation du patient
					Main.f.setContentPane(searchConsultationPatientPanel(c.getNomPatient(),c.getPrenomPatient()));
					Main.f.repaint();
					Main.f.revalidate();
				}
			});
			
			return informationConsultationPanel;
		}
	
	//Cette méthode renvoie un panneau  de modification de la consultation c placer en arguments à la méthode
	public JPanel editConsultationPanel(Consultation c) {
		JPanel editConsultationPanel=new JPanel(),text = new JPanel();
		JLabel title=new JLabel("Modifier le patient"),patientLabel[]= {new JLabel("Nom du Patient : "+c.getNomPatient()),new JLabel("Prenom du Patient : "+c.getPrenomPatient()),new JLabel("Nom du Medecin :"),new JLabel("Date de Consultation (dd/mm/yyyy) :"),new JLabel("Pathologie Diagnostiquée :"),new JLabel("Appareillage Nécessaire :")};
		JTextField patientTextField[]= {new JTextField(10),new JTextField(10)};
		String pathologieChoice[]= {"INFECTION","LIGAMENT","TORTICOLIS","ENTORCE","MEMBRE CASSÉ"},appareillageChoice[]= {"AUCUN","ATTELLES","PROTHESE EXTERNE","PLATRE","FAUTEUIL ROULANT"};
		JComboBox<String> cbPathologie = new JComboBox<String>(pathologieChoice),cbAppareillage = new JComboBox<String>(appareillageChoice);
		String informationConsultation[]= {c.getNomMedecin(),c.getDate(),c.getPathologie(),c.getAppareillage(),c.getOctroye()};
		JButton editButton=new JButton("Modifier"),returnButton=new JButton ("Retour");
		
		editConsultationPanel.setLayout(null);
		editConsultationPanel.setBackground( Color.RED);
		title.setFont(new Font("Arial",Font.BOLD,50));
		title.setBounds(150,50,450,50);
		title.setForeground(Color.WHITE);
		editConsultationPanel.add(title);
		
		text.setBounds(40,135,650,400);
		text.setLayout(new BoxLayout(text,BoxLayout.Y_AXIS));
		text.setBackground( Color.RED);
		text.setBorder(BorderFactory.createTitledBorder("Formulaire"));
		editConsultationPanel.add(text);
		
		//Cette boucle permet l'affichage du formulaire de modification, plus précissement d'afficher les Labels côte à côte aux TextFields dans le panneau text (formulaire)
		for(int i=0;i<patientLabel.length;i++) {
			JPanel panel=new JPanel();
			panel.setBackground( Color.RED);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			patientLabel[i].setFont(new Font("Arial",Font.BOLD,24));
			patientLabel[i].setForeground(Color.WHITE);
			panel.add(patientLabel[i]);
			if(i>1 && i<4) {
				patientTextField[i-2].setText(informationConsultation[i-2]);
				patientTextField[i-2].setFont(new Font("Arial",Font.BOLD,20));
				panel.add(patientTextField[i-2]);
			}else if(i==4) {
				cbPathologie.setFont(new Font("Arial",Font.BOLD,20));
				cbPathologie.setSelectedItem(c.getPathologie());
				panel.add(cbPathologie);
			}else if(i==5) {
				cbAppareillage.setFont(new Font("Arial",Font.BOLD,20));
				cbAppareillage.setSelectedItem(c.getAppareillage());
				panel.add(cbAppareillage);
			}
			text.add(panel);
		}
		editButton.setBounds(290,600,150,60);
		editConsultationPanel.add(editButton);
		editButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				/*Lorsque le bouton Modifier est appuyé, la consultation est modifié ou non selon les critéres de la méthode editConsultation si aucun appareil est nécéssaire alors
				 l'octroyage est automatique sur AUCUN sinon il est EN ATTENTE*/
				String octroye;
				if(cbAppareillage.getSelectedItem().toString().equals("AUCUN")) {
					octroye="NON";
				}else {
					octroye="EN ATTENTE";
				}
				Consultation c1=new Consultation(c.getNomPatient(),c.getPrenomPatient(),patientTextField[0].getText(),patientTextField[1].getText(),cbPathologie.getSelectedItem().toString(),cbAppareillage.getSelectedItem().toString(),octroye);
				/*Si le patient est modifier alors le panneau principal de la fenetre principale est remplacer par le panneau de modification du patient qui contient les nouvelles informations.
				 * On fait cela pour que l'administrateur puisse remodifier de suite le patient si il le faut sinon la modification prendra comme ancienne informations celle faite 2 modifications avant
				 */
				if(c.editConsultation(c1)) {
					Main.f.setContentPane(editConsultationPanel(c1));
					Main.f.repaint();
					Main.f.revalidate();
				}
			}
		});
		
		returnButton.setBounds(550,612,110,40);
		editConsultationPanel.add(returnButton);
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Le bouton Retour remplace le panneau principal de la fenetre f par le panneau de recherche de consultation du patient
				Main.f.setContentPane(informationConsultationPanel(c));
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		return editConsultationPanel;
	}
}