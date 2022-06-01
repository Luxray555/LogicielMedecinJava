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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

//Cette classe possède l'ensemble des pages présentes dans l'interface du technicien
public class MainTechnicien extends JPanel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//listPanel est la panel qui contient la liste des Patient et des Consultations lorsque l'on recherche un patient ou que l'on recherche une consultation
	private JPanel listPanel;
	private JScrollPane consultationListPanel;

	//Ce constructeur représente la page principale de l'interface technicien  qui n'est rien d'autre que le panneau de recherche d'un patient
	public MainTechnicien() {
		Main.f.setTitle("Technicien");
		this.setLayout(null);
		this.setBackground( Color.GREEN);
		
		JLabel title=new JLabel("Octroyé une consultation"), namePatient=new JLabel("Nom du patient:");
		JTextField searchTextFieldPatient=new JTextField(10);
		JButton searchButton=new JButton("Chercher"),returnButton=new JButton("Retour");
		
		title.setFont(new Font("Arial",Font.BOLD,40));
		title.setBounds(125,30,550,50);
		title.setForeground(Color.WHITE);
		this.add(title);
		
		namePatient.setBounds(75,150,250,40);
		namePatient.setFont(new Font("Arial",Font.BOLD,27));
		namePatient.setForeground(Color.WHITE);
		this.add(namePatient);
		
		searchTextFieldPatient.setBounds(295,157,250,30);
		searchTextFieldPatient.setFont(new Font("Arial",Font.BOLD,17));
		this.add(searchTextFieldPatient);
		
		searchButton.setBounds(555,155,100,35);
		this.add(searchButton);
		
		listPanel=new JPanel();
		listPanel=listPatientPanel(searchTextFieldPatient.getText());
		consultationListPanel=new JScrollPane(listPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		consultationListPanel.setBounds(70,190,600,400);
		this.add(consultationListPanel);
		
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
		consultationPatientPanel.setBackground( Color.GREEN);
		
		title.setFont(new Font("Arial",Font.BOLD,40));
		title.setBounds(10,10,550,40);
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
				Main.f.setContentPane(new MainTechnicien());
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
		ArrayList<JPanel> consultationPanel=new ArrayList<>();
		
		//Cette boucle permet d'ajouter au panel listPatient (qui est un panneau déroulant) tout les Patients selon le format: NomMedecin Date informationBouton supprimerBouton
		for(int i=0;i<consultationList.size();i++) {
			int nbr=i;
			consultationPanel.add(new JPanel());
			consultationPanel.get(i).setLayout(new FlowLayout());
			consultationPanel.get(i).setBackground(Color.BLACK);
			
			JLabel info=new JLabel("Nom Medecin : "+consultationList.get(i).getNomMedecin()+" | Date : "+consultationList.get(i).getDate()+" | "+consultationList.get(i).getOctroye());
			info.setForeground(Color.WHITE);
			info.setFont(new Font("Arial",Font.BOLD,13));
			consultationPanel.get(i).add(new JLabel().add(info));
			
			JButton informationButton=new JButton("Informations"),octroyeButton=new JButton();
			if(consultationList.get(i).getOctroye().equals("OCTROYE")) {
				octroyeButton.setText("Ne plus octroyer");
			}else{
				octroyeButton.setText("Octroyer");
			}
			consultationPanel.get(i).add(informationButton);
			consultationPanel.get(i).add(octroyeButton);
			
			informationButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					//Quand le bouton Information est appuyé, le panneau principal est remplacé par le panneau d'information du consultationList.get(i)
					Main.f.setContentPane(informationConsultationPanel(consultationList.get(nbr)));
					Main.f.revalidate();
					Main.f.repaint();
				}
			});
			
			octroyeButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					/*Quand le bouton Octroye ou Ne plus octroye est appuyé, la consultionList.get(i) est alors OCTROYE ou EN ATTENTE respectivement mais si la consultation
					 n'a aucun appareil alors elle ne pourra pas etre octroye*/
					if (consultationList.get(nbr).getOctroye().equals("NON")) {
						JOptionPane.showMessageDialog(new JFrame(),"Impossible, aucun appareillage.");
					}else {
						int result;
						if(consultationList.get(nbr).getOctroye().equals("OCTROYE")) {
							result = JOptionPane.showConfirmDialog(new JFrame(), "Etes-vous sur de ne plus octroyer l'appareillage ?");
						}else{
							result = JOptionPane.showConfirmDialog(new JFrame(), "Etes-vous sur d'octroyer l'appareillage ?");
						}
						if (result == 0) {
							consultationList.get(nbr).octroyeConsultation();		
							listPanel.removeAll();
							listPanel=listConsultationPatientPanel(nomPatient,prenomPatient,nomMedecin,date);
							listPanel.repaint();
							listPanel.revalidate();
						}
					}
				}
			});
			
			listPanel.add(consultationPanel.get(i));
			}
		}else {
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
		JLabel consultationLabel[] = {new JLabel("Nom Patient : "+c.getNomPatient()),new JLabel("Nom Medecin : "+c.getNomMedecin()), new JLabel("Date : "+c.getDate()),new JLabel("Pathologie diagnostiqué : "+c.getPathologie()),new JLabel("Appareillage : "+c.getAppareillage()),new JLabel("Octroye : "+c.getOctroye())};
		JButton octroyeButton =new JButton(),returnButton =new JButton("Retour");
		
		informationConsultationPanel.setLayout(null);
		informationConsultationPanel.setBackground( Color.GREEN);
		
		informationPanel.setBounds(40,60,650,450);
		informationPanel.setLayout(new BoxLayout(informationPanel,BoxLayout.Y_AXIS));
		informationPanel.setBackground( Color.GREEN);
		informationPanel.setBorder(BorderFactory.createTitledBorder("INFORMATIONS CONSULTATION"));
		informationConsultationPanel.add(informationPanel);
		for(int i=0;i<6;i++) {
			JPanel panel=new JPanel();
			panel.setBackground( Color.GREEN);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			consultationLabel[i].setFont(new Font("Arial",Font.BOLD,26));
			consultationLabel[i].setForeground(Color.WHITE);
			panel.add(consultationLabel[i]);
			informationPanel.add(panel);
		}
		
		if(c.getOctroye().equals("OCTROYE")) {
			octroyeButton.setText("Ne plus octroyer");
		}else{
			octroyeButton.setText("Octroyer");
		}
		octroyeButton.setBounds(290,600,150,60);
		informationConsultationPanel.add(octroyeButton);
		octroyeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				/*Quand le bouton Octroye ou Ne plus octroye est appuyé, la consultionList.get(i) est alors OCTROYE ou EN ATTENTE respectivement mais si la consultation
				 n'a aucun appareil alors elle ne pourra pas etre octroye*/
				if (c.getOctroye().equals("NON")) {
					JOptionPane.showMessageDialog(new JFrame(),"Impossible, aucun appareillage.");
				}else {
					int result;
					if(c.getOctroye().equals("OCTROYE")) {
						result = JOptionPane.showConfirmDialog(new JFrame(), "Etes-vous sur de ne plus octroyer l'appareillage ?");
					}else{
						result = JOptionPane.showConfirmDialog(new JFrame(), "Etes-vous sur d'octroyer l'appareillage ?");
					}
					if (result == 0) {
						c.octroyeConsultation();
						if(c.getOctroye().equals("OCTROYE")) {
							Main.f.setContentPane(informationConsultationPanel(new Consultation(c.getNomPatient(),c.getPrenomPatient(),c.getNomMedecin(),c.getDate(),c.getPathologie(),c.getAppareillage(),"EN ATTENTE")));
						}else {
							Main.f.setContentPane(informationConsultationPanel(new Consultation(c.getNomPatient(),c.getPrenomPatient(),c.getNomMedecin(),c.getDate(),c.getPathologie(),c.getAppareillage(),"OCTROYE")));
						}
						Main.f.repaint();
						Main.f.revalidate();
					}
				}
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
}