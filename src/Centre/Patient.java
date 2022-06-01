package Centre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//Cette classe concentre toute les méthodes qui relie un patient au fichier des patients qui sont présents dans l'interface MainPatient
public class Patient{
	
	//Ces variables représentes les caracteristiques du patient
	private String nom,prenom,birth,numeroSecu;
	
	//Cette variable represente l'emplacement du fichier où sont stocké les patients
	private final String filePath="patients.txt";
	
	public Patient() {
	}
	
	//Constructeur de création d'un patient selon c'est caractéristique(nom,prenom,date de naissance et son numero de securité sociale
	public Patient(String nom,String prenom,String birth,String numeroSecu) {
		this.nom=nom;
		this.prenom=prenom;
		this.birth=birth;
		this.numeroSecu=numeroSecu;
	}
	
	//methode qui verifie si le format de la date est bonne
	public boolean verifDate() {
		boolean verif=false;
		try {
			String nombre[] = birth.split("/");
			if(nombre.length==3 && nombre[0].length()==2 && nombre[1].length()==2 && nombre[2].length()==4  && (Integer.parseInt(nombre[1])<=12 && Integer.parseInt(nombre[1])>0) && (Integer.parseInt(nombre[0])<=31 && Integer.parseInt(nombre[0])>0) && Integer.parseInt(nombre[2])<2022) {
				verif=true;
			}
		}catch(NumberFormatException|IndexOutOfBoundsException e) {
			verif=false;
		}
		return verif;
	}
	
	//methode qui verifie si la chaine de caractere ne contient pas de chiffre ou de caracteres spéciaux
	public boolean verifString(String s) {
		int recurrence=0;
		boolean verif=false;
		for(int i=0;i<s.length();i++) {
			if(((int)s.charAt(i)>=65 && (int)s.charAt(i)<=90) || ((int)s.charAt(i)>=97 && (int)s.charAt(i)<=122)) {
				recurrence++;
			}
		}
		if(recurrence==s.length() && s.length()>0) {
			verif=true;
		}
		return verif;
	}
	
	//methode qui verifie si le nombre entier ne contient pas de lettre ou de caracteres speciaux
	public boolean verifInt(String s) {
		int recurrence=0;
		boolean verif=false;
		for(int i=0;i<s.length();i++) {
			if(((int)s.charAt(i)>=48 && (int)s.charAt(i)<=57)) {
				recurrence++;
			}
		}
		if(recurrence==s.length() && s.length()>0) {
			verif=true;
		}
		return verif;
	}
	
	//methode qui ajoute le patient dans le fichier filePath en verifiant s'il est déjà existant dans le fichier et si les informations sont correctes
	public void addPatient(){
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(filePath);
			br=new BufferedReader(fr);
			String line=null;
			boolean verif=true;
			while((line = br.readLine()) != null) {
				String tabPatient[]=line.split(";");
				if((tabPatient[0].equals(nom.toUpperCase()) && tabPatient[1].equals(prenom.toUpperCase())) || (tabPatient[2].equals(numeroSecu))) {
					verif=false;
					JOptionPane.showMessageDialog(new JFrame(),"Le patient est déjà enregistré.");
				}
			}
			br.close();
			fr.close();
			if( verifDate()==false || verifString(nom)==false || verifString(prenom)==false || verifInt(numeroSecu)==false) {
				verif=false;
				JOptionPane.showMessageDialog(new JFrame(),"Informations Invalides.");
			}
			if(verif==true) {
				FileWriter fw=new FileWriter(filePath,true);
				fw.append(getPatient()+"\n");
				fw.close();
				JOptionPane.showMessageDialog(new JFrame(),"Le patient a bien été ajouter.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*methode qui retourne une liste de patient selon le nom en argument. Si l'argument inscrit a une longueur inférieur à celle du nom du patient seulement le nombre de lettre de l'argument
	 sont alors verifiés*/
	public ArrayList<Patient> searchPatient(String nom){
		ArrayList<Patient> patientList=new ArrayList<>();
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(filePath);
			br=new BufferedReader(fr);
			String line=null;
			while((line = br.readLine()) != null) {
				String tabPatient[]=line.split(";");
				try {
					if((nom.toUpperCase().equals(tabPatient[0].substring(0,nom.length())))) {
						patientList.add(new Patient(tabPatient[0],tabPatient[1],tabPatient[2],tabPatient[3]));
					}else if((nom.toUpperCase().equals(tabPatient[0].substring(0,nom.length()-1)))) {
						patientList.add(new Patient(tabPatient[0],tabPatient[1],tabPatient[2],tabPatient[3]));
					}
				}catch(StringIndexOutOfBoundsException e) {
				}
			}
			br.close();
			fr.close();
			return patientList;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return patientList;
	}
	
	//methode qui retourne un patient selon les arguments du nom du patient et de son prenom
	public Patient searchPatient(String nom,String prenom) {
		Patient p=null;
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(filePath);
			br=new BufferedReader(fr);
			String line=null;
			while((line = br.readLine()) != null) {
				String tabPatient[]=line.split(";");
				if((tabPatient[0].equals(nom) && tabPatient[1].equals(prenom))) {
					p=new Patient(tabPatient[0],tabPatient[1],tabPatient[2],tabPatient[3]);
				}
			}
			br.close();
			fr.close();
			return p;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	/*methode qui retourne un boolean qui verifie si la modification de patient c'est bien réalisé ou pas. Cette méthode modifie le patient par un patient placer en argument.
	 Cette modification ne se fait que si les informations entrer son bonnes, q'ils ont été changer et que si personne ne possède déjà le nouveau nom et prenom ou numéro de sécurité sociale*/
	public boolean editPatient(Patient p) {
		boolean edit=false;
		if(getPatient().equals(p.getPatient())) {
			JOptionPane.showMessageDialog(new JFrame(),"Les informations sont inchangés.");
		}else {
			ArrayList<Patient> patientList=new ArrayList<>();
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr = new FileReader(filePath);
				br=new BufferedReader(fr);
				String line=null;
				boolean verif=true;
				if( p.verifDate()==false || verifString(p.nom)==false || verifString(p.prenom)==false || verifInt(p.numeroSecu)==false) {
					verif=false;
					JOptionPane.showMessageDialog(new JFrame(),"Informations Invalides.");
				}
				while((line = br.readLine()) != null) {
					String tabPatient[]=line.split(";");
					if(((tabPatient[0].equals(p.nom.toUpperCase()) && tabPatient[1].equals(p.prenom.toUpperCase()))) && (!nom.equals(p.nom) || !prenom.equals(p.prenom))) {
						verif=false;
						JOptionPane.showMessageDialog(new JFrame(),"Impossible, un patient à déjà ce nom et prenom.");
						break;
					}
					if(tabPatient[3].equals(p.numeroSecu) && !numeroSecu.equals(p.numeroSecu)) {
						verif=false;
						JOptionPane.showMessageDialog(new JFrame(),"Impossible, un patient à déjà ce numéro de sécurité sociale.");
						break;
					}
					if(!getPatient().equals(line)) {
						patientList.add(new Patient(tabPatient[0],tabPatient[1],tabPatient[2],tabPatient[3]));
					}else {
						patientList.add(new Patient(p.getNom(),p.getPrenom(),p.getBirth(),p.getNumeroSecu()));
					}
				}
				br.close();
				fr.close();
				if(verif==true) {
					FileWriter fw=new FileWriter(filePath);
					for(int i=0;i<patientList.size();i++) {
						fw.append(patientList.get(i).getPatient()+"\n");
					}
					fw.close();
					JOptionPane.showMessageDialog(new JFrame(),"Le patient a bien été modifier.");
					edit=true;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return edit;
	}
	
	//methode qui supprime un patient du fichier filePath si celui ci existe
	public void deletePatient() {
		FileReader fr;
		BufferedReader br;
		try {
			fr=new FileReader(filePath);
			br=new BufferedReader(fr);
			ArrayList<Patient> patientList=new ArrayList<>();
			String line;
			while((line = br.readLine()) != null) {
				String tabPatient[] = line.split(";");
				if(tabPatient[0].equals(nom) && tabPatient[1].equals(prenom) && tabPatient[2].equals(birth) && tabPatient[3].equals(numeroSecu)) {
				}else {
					patientList.add(new Patient(tabPatient[0],tabPatient[1],tabPatient[2],tabPatient[3]));
				}
			}
			br.close();
			fr.close();
			FileWriter fw=new FileWriter(filePath);
			for(int i=0;i<patientList.size();i++) {
				fw.append(patientList.get(i).getPatient()+"\n");
			}
			fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//getter et setter
	public String getPatient() {
		return nom.toUpperCase()+";"+prenom.toUpperCase()+";"+birth+";"+numeroSecu;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getBirth() {
		return birth;
	}

	public String getNumeroSecu() {
		return numeroSecu;
	}
}
