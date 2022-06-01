package Centre;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import  java.awt.event.ActionListener;
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

//Cette classe possède l'ensemble des pages présentes dans l'interface de l'administrateur
public class MainAdministrateur extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel title;
	//listPanel est la panel qui contient la liste des Patient lorsque l'on recherche un patient
	private JPanel listPanel;
	private JScrollPane patientListPanel;
	
	//Ce constructeur représente la page principale de l'interface administrateur
	public MainAdministrateur() {
		Main.f.setTitle("Administrateur");
		this.setLayout(null);
		this.setBackground( Color.decode("#341ff4"));
		title=new JLabel("Administrateur");
		JButton addButton=new JButton("Ajouter un Patient"),searchButton=new JButton("Rechercher un Patient"),returnButton=new JButton("Retour");

		title.setFont(new Font("Arial",Font.BOLD,50));
		title.setBounds(190,145,380,50);
		title.setForeground(Color.WHITE);
		
		addButton.setFont(new Font("Arral",Font.BOLD,30));
		addButton.setBounds(180,250,370,60);
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Le bouton Ajouter un Patient remplace le panneau principale de la fenetre f par le panneau de la methode addPatientPanel(), c'est à dire le panneau du formulaire d'ajout
				Main.f.setContentPane(addPatientPanel());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		
		searchButton.setFont(new Font("Arial",Font.BOLD,30));
		searchButton.setBounds(180,340,370,60);
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
				//Le bouton Rechercher un Patient remplace le panneau principale de la fenetre f par le panneau du constructeur de la classe Main() qui est le menu principal
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
	

	//Cette méthode renvoie le panneau du formulaire d'ajout de patient
	public JPanel addPatientPanel() {
		JPanel addPatientPanel=new JPanel(),text = new JPanel();
		JLabel title=new JLabel("Ajouter un patient"),patientLabel[]= {new JLabel("Nom :"),new JLabel("Prenom :"),new JLabel("Date de naissance (dd/mm/yyyy) :"),new JLabel("Numéro de sécurité sociale :")};
		JTextField patientTextField[]= {new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10)};
		JButton acceptButton=new JButton("Ajouter"),returnButton=new JButton ("Retour");
		
		addPatientPanel.setLayout(null);
		addPatientPanel.setBackground( Color.decode("#341ff4"));
		title.setFont(new Font("Arial",Font.BOLD,50));
		title.setBounds(150,50,430,50);
		title.setForeground(Color.WHITE);
		addPatientPanel.add(title);
		
		text.setBounds(40,135,650,400);
		text.setLayout(new BoxLayout(text,BoxLayout.Y_AXIS));
		text.setBackground( Color.decode("#341ff4"));
		text.setBorder(BorderFactory.createTitledBorder("FORMULAIRE"));
		addPatientPanel.add(text);
		
		//Cette boucle permet l'affichage du formulaire d'ajout, plus précissement d'afficher les Labels côte à côte aux TextFields dans le panneau text (formulaire)
		for(int i=0;i<patientLabel.length;i++) {
			JPanel panel=new JPanel();
			panel.setBackground( Color.decode("#341ff4"));
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			patientLabel[i].setFont(new Font("Arial",Font.BOLD,25));
			patientLabel[i].setForeground(Color.WHITE);
			patientTextField[i].setFont(new Font("Arial",Font.BOLD,20));
			panel.add(patientLabel[i]);
			panel.add(patientTextField[i]);
			text.add(panel);
		}
		
		acceptButton.setBounds(290,600,150,60);
		addPatientPanel.add(acceptButton);
		acceptButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton Ajouter est appuyé, le patient caractérisé avec les textes présents dans les TextFields est crées ou pas selon les critéres présents dans la méthode addPatient()
					(new Patient(patientTextField[0].getText(),patientTextField[1].getText(),patientTextField[2].getText(),patientTextField[3].getText())).addPatient();
			}
		});
		
		returnButton.setBounds(550,612,110,40);
		addPatientPanel.add(returnButton);
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton retour est appuyé, le panneau principal de la fenetre f présent dans la classe Main est remplacer par le constructeur MainAdministrateur()
				Main.f.setContentPane(new MainAdministrateur());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		return addPatientPanel;
	}
	
	//Cette methode renvoie le panneau qui nous permet de rechercher un patient
	public JPanel searchPatientPanel() {
		JPanel searchPatientPanel=new JPanel();
		JLabel title=new JLabel("Rechercher un patient"), name=new JLabel("Nom :");
		JTextField searchTextField=new JTextField();
		JButton searchButton=new JButton("Chercher"),returnButton=new JButton("Retour");
		
		searchPatientPanel.setLayout(null);
		searchPatientPanel.setBackground( Color.decode("#341ff4"));
		
		title.setFont(new Font("Arial",Font.BOLD,40));
		title.setBounds(150,50,440,50);
		title.setForeground(Color.WHITE);
		searchPatientPanel.add(title);
		
		name.setBounds(147,142,100,40);
		name.setFont(new Font("Arial",Font.BOLD,33));
		name.setForeground(Color.WHITE);
		searchPatientPanel.add(name);
		
		searchTextField.setBounds(250,147,220,35);
		searchTextField.setFont(new Font("Arial",Font.BOLD,22));
		searchPatientPanel.add(searchTextField);
		
		searchButton.setBounds(485,147,100,35);
		searchPatientPanel.add(searchButton);
		
		listPanel=new JPanel();
		listPanel=listPanel(searchTextField.getText());
		patientListPanel=new JScrollPane(listPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		patientListPanel.setBounds(140,190,450,400);
		searchPatientPanel.add(patientListPanel);
		
		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				/*Quand le bouton Chercher est appuyé, le panneau de la liste de patient affiche seulement les patients ayant les memes caracteres au debut de leurs nom que le texte
				  présent dans le textField*/
				listPanel.removeAll();
				listPanel=listPanel(searchTextField.getText());
				listPanel.repaint();
				listPanel.revalidate();
			}
			
		});
		
		returnButton.setBounds(550,612,110,40);
		searchPatientPanel.add(returnButton);
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Quand le bouton Retour est appuyé, le panneau principal de la fenetre f présent dans la classe Main est remplacer par le constructeur MainAdministrateur()
				Main.f.setContentPane(new MainAdministrateur());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		
		return searchPatientPanel;
	}
	
	/*Cette méthopde représente la panel où la liste des patients y est inscrit dans le panel searchPatient(), elle dépend d'un argument qui n'est que ce qui se trouve dans le textField
	  du panneau searchPatientPanel().*/
	public JPanel listPanel(String nom) {
		ArrayList<Patient> patientList;
		//Ici, patientList est une liste qui contient tout les patient ayant un nom commençant par les mêmes caractéres que la variable nom en arguments de la méthode listPanel
		patientList=(new Patient()).searchPatient(nom);
		if(patientList.size()>0) {
		listPanel.setLayout(new GridLayout(100, 1,5,5));
		ArrayList<JPanel> patientPanel=new ArrayList<>();
		//Cette boucle permet d'ajouter au panel listPatient (qui est un panneau déroulant) tout les Patients selon le format: NomPatient PrenomPatient informationBouton modifierBouton supprimerBouton
		for(int i=0;i<patientList.size();i++) {
			int nbr=i;
			patientPanel.add(new JPanel());
			patientPanel.get(i).setLayout(new FlowLayout());
			patientPanel.get(i).setBackground(Color.BLACK);
			
			JLabel name=new JLabel(patientList.get(i).getNom()+" "+patientList.get(i).getPrenom());
			name.setForeground(Color.WHITE);
			name.setFont(new Font("Arial",Font.BOLD,15));
			patientPanel.get(i).add(new JLabel().add(name));
			
			JButton informationButton=new JButton("Information"),editButton=new JButton("Modifier"),deleteButton=new JButton("Supprimer");
			patientPanel.get(i).add(informationButton);
			patientPanel.get(i).add(editButton);
			patientPanel.get(i).add(deleteButton);
			
			informationButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					//Quand le bouton Information est appuyé, le panneau principal est remplacé par le panneau d'information du patientList.get(i)
					Main.f.setContentPane(informationPatientPanel(patientList.get(nbr)));
					Main.f.revalidate();
					Main.f.repaint();
				}
			});
			
			editButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					//Quand le bouton Modifier est appuyé, le panneau principal est remplacé par le panneau de modification du patientList.get(i)
					Main.f.setContentPane(editPatientPanel(patientList.get(nbr)));
					Main.f.revalidate();
					Main.f.repaint();
				}
			});
			
			deleteButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					//Quand le bouton Supprimer est appuyé, le patientList.get(i) est alors supprimé ici, si l'administrateur confirme la suppression dans le pop up qui s'affiche ensuite
					if((new Consultation()).searchConsultation(patientList.get(nbr).getNom(),patientList.get(nbr).getPrenom())==null) {
						int result = JOptionPane.showConfirmDialog(new JFrame(), "Etes-vous de supprimer le patient ?");
						if (result == 0) {
							patientList.get(nbr).deletePatient();
							listPanel.removeAll();
							listPanel=listPanel(nom);
							listPanel.repaint();
							listPanel.revalidate();
						}
					}else {
						JOptionPane.showMessageDialog(new JFrame(),"Impossible, une consultation est prévue.");
					}
				}
			});
			
			listPanel.add(patientPanel.get(i));
			}
		}else {
			//Si la liste est vide alors un label s'affiche qui nous dit "Aucun Patient"
			JLabel aucun=new JLabel("Aucun Patient");
			listPanel.setLayout(null);
			aucun.setFont(new Font("Arial",Font.BOLD,30));
			aucun.setBounds(130,150,200,50);
			listPanel.add(aucun);
		}
		return listPanel;
	}
	
	//Cette methode renvoie le panneau d'information du patient placer en argument
	public JPanel informationPatientPanel(Patient p) {
		JPanel informationPatientPanel =new JPanel(),informationPanel =new JPanel();
		JLabel patient = new JLabel((p.getNom()+" "+p.getPrenom()));
		JLabel patientInfo[] = {new JLabel("Date de naissance : "+p.getBirth()),new JLabel("Numéro Sécurité Sociale : "+p.getNumeroSecu())};
		JButton editButton =new JButton("Modifier"),returnButton =new JButton("Retour");
		
		informationPatientPanel.setLayout(null);
		informationPatientPanel.setBackground( Color.decode("#341ff4"));
		
		patient.setFont(new Font("Arial",Font.BOLD,50));
		patient.setBounds(10,10,700,50);
		patient.setForeground(Color.WHITE);
		informationPatientPanel.add(patient);
		
		informationPanel.setBounds(40,90,650,450);
		informationPanel.setLayout(new BoxLayout(informationPanel,BoxLayout.Y_AXIS));
		informationPanel.setBackground( Color.decode("#341ff4"));
		informationPanel.setBorder(BorderFactory.createTitledBorder("INFORMATIONS PATIENT"));
		informationPatientPanel.add(informationPanel);
		for(int i=0;i<patientInfo.length;i++) {
			JPanel panel=new JPanel();
			panel.setBackground( Color.decode("#341ff4"));
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			patientInfo[i].setFont(new Font("Arial",Font.BOLD,26));
			patientInfo[i].setForeground(Color.WHITE);
			panel.add(patientInfo[i]);
			informationPanel.add(panel);
		}
		
		editButton.setBounds(290,600,150,60);
		informationPatientPanel.add(editButton);
		editButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Lorsque le bouton Modifier est appuyé, le panneau principal de la fenetre principale est remplacer par le panneau de modification du Patient p
				Main.f.setContentPane(editPatientPanel(p));
				Main.f.revalidate();
				Main.f.repaint();
			}
		});
		
		returnButton.setBounds(550,612,110,40);
		informationPatientPanel.add(returnButton);
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Lorsque le bouton Retour est appuyé, le panneau principal de la fenetre principale est remplacer par celui de recherche de patient
				Main.f.setContentPane(searchPatientPanel());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		
		return informationPatientPanel;
	}
	
	//Cette méthode renvoie un panneau  de modification du patient p placer en arguments à la méthode
	public JPanel editPatientPanel(Patient p) {
		JPanel editPatientPanel=new JPanel(),text = new JPanel();
		JLabel title=new JLabel("Modifier le patient"),patientLabel[]= {new JLabel("Nom :"),new JLabel("Prenom :"),new JLabel("Date de naissance (dd/mm/yyyy) :"),new JLabel("Numéro de sécurité sociale :")};
		JTextField patientTextField[]= {new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10)};
		String informationPatient[]= {p.getNom(),p.getPrenom(),p.getBirth(),p.getNumeroSecu()};
		JButton editButton=new JButton("Modifier"),returnButton=new JButton ("Retour");
		
		editPatientPanel.setLayout(null);
		editPatientPanel.setBackground( Color.decode("#341ff4"));
		title.setFont(new Font("Arial",Font.BOLD,50));
		title.setBounds(150,50,450,50);
		title.setForeground(Color.WHITE);
		editPatientPanel.add(title);
		
		text.setBounds(40,135,650,400);
		text.setLayout(new BoxLayout(text,BoxLayout.Y_AXIS));
		text.setBackground( Color.decode("#341ff4"));
		text.setBorder(BorderFactory.createTitledBorder("Formulaire"));
		editPatientPanel.add(text);
		
		//Cette boucle permet l'affichage du formulaire de modification, plus précissement d'afficher les Labels côte à côte aux TextFields dans le panneau text (formulaire)
		for(int i=0;i<patientLabel.length;i++) {
			JPanel panel=new JPanel();
			panel.setBackground( Color.decode("#341ff4"));
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			patientLabel[i].setFont(new Font("Arial",Font.BOLD,24));
			patientLabel[i].setForeground(Color.WHITE);
			patientTextField[i].setText(informationPatient[i]);
			patientTextField[i].setFont(new Font("Arial",Font.BOLD,20));
			panel.add(patientLabel[i]);
			panel.add(patientTextField[i]);
			text.add(panel);
		}
		
		editButton.setBounds(290,600,150,60);
		editPatientPanel.add(editButton);
		editButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Lorsque le bouton Modifier est appuyé, le patient est modifié ou non selon les critéres de la méthode editPatient
				Patient p1=new Patient(patientTextField[0].getText(),patientTextField[1].getText(),patientTextField[2].getText(),patientTextField[3].getText());
				/*Si le patient est modifier alors le panneau principal de la fenetre principale est remplacer par le panneau de modification du patient qui contient les nouvelles informations.
				 * On fait cela pour que l'administrateur puisse remodifier de suite le patient si il le faut sinon la modification prendra comme ancienne informations celle faite 2 modifications avant
				 */
				if(p.editPatient(p1)) {
					Main.f.setContentPane(editPatientPanel(p1));
					Main.f.repaint();
					Main.f.revalidate();
				};
			}
		});
		
		returnButton.setBounds(550,612,110,40);
		editPatientPanel.add(returnButton);
		returnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//Le bouton Retour remplace le panneau principal de la fenetre f par le panneau de recherche de patient
				Main.f.setContentPane(searchPatientPanel());
				Main.f.repaint();
				Main.f.revalidate();
			}
		});
		return editPatientPanel;
	}
}