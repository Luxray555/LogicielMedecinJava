package Centre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Consultation{
	
	//Ces variables représentes les caracteristiques du patient
	private String nomPatient,prenomPatient,nomMedecin,date,pathologie,appareillage,octroye;
	
	//Cette variable represente l'emplacement du fichier où sont stocké les consultations
	private final String filePath="consultations.txt";
	
	public Consultation() {
		
	}
	
	//Constructeur de création d'une consultation selon c'est caractéristique(nomPatient,prenomPatient,date consultation,pathologie,appareillage et l'octroyage
	public Consultation(String nomPatient,String prenomPatient,String nomMedecin,String date,String pathologie,String appareillage,String octroye) {
		this.nomPatient=nomPatient;
		this.prenomPatient=prenomPatient;
		this.nomMedecin=nomMedecin;
		this.date=date;
		this.pathologie=pathologie;
		this.appareillage=appareillage;
		this.octroye=octroye;
	}
	
	//methode qui verifie si le format de la date est bonne
	public boolean verifDate() {
		boolean verif=false;
		try {
			String nombre[] = date.split("/");
			if(nombre.length==3 && nombre[0].length()==2 && nombre[1].length()==2 && nombre[2].length()==4  && (Integer.parseInt(nombre[1])<=12 && Integer.parseInt(nombre[1])>0) && (Integer.parseInt(nombre[0])<=31 && Integer.parseInt(nombre[0])>0) && Integer.parseInt(nombre[2])>2021) {
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
	
	//methode qui ajoute la consultation dans le fichier filePath en verifiant s'il est déjà existant dans le fichier et si les informations sont correctes
	public void addConsultation() {
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(filePath);
			br=new BufferedReader(fr);
			String line=null;
			boolean verif=true;
			while((line = br.readLine()) != null) {
				String tabConsultation[]=line.split(";");
				if(tabConsultation[0].equals(nomPatient.toUpperCase()) && tabConsultation[1].equals(prenomPatient.toUpperCase()) && tabConsultation[3].equals(date)) {
					verif=false;
					JOptionPane.showMessageDialog(new JFrame(),"Impossible, ce patient à déjà une consultation ce jour là.");
				}
			}
			if( verifDate()==false || verifString(nomPatient)==false || verifString(nomPatient)==false || verifString(nomMedecin)==false ) {
				verif=false;
				JOptionPane.showMessageDialog(new JFrame(),"Informations Invalides.");
			}
			if(verif==true && (new Patient()).searchPatient(nomPatient.toUpperCase(),prenomPatient.toUpperCase())!=null) {
				FileWriter fw=new FileWriter(filePath,true);
				fw.append(getConsultation()+"\n");
				fw.close();
				JOptionPane.showMessageDialog(new JFrame(),"La consultation a bien été ajouter.");
			}else if((new Patient()).searchPatient(nomPatient.toUpperCase(),prenomPatient.toUpperCase())==null) {
				JOptionPane.showMessageDialog(new JFrame(),"Le patient "+nomPatient.toUpperCase()+" "+prenomPatient.toUpperCase()+" n'existe pas.");
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//methode qui retourne une consultation selon les arguments du nom du patient, de son prenom du patient, le nom du medecin et la date de consultation
	public Consultation searchConsultation(String nomPatient,String prenomPatient,String nomMedecin,String date) {
		Consultation c=null;
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(filePath);
			br=new BufferedReader(fr);
			String line=null;
			while((line = br.readLine()) != null) {
				String tabConsultation[]=line.split(";");
				if((tabConsultation[0].equals(nomPatient) && tabConsultation[1].equals(prenomPatient) && tabConsultation[2].equals(nomMedecin) && tabConsultation[3].equals(date))) {
					c=new Consultation(tabConsultation[0],tabConsultation[1],tabConsultation[2],tabConsultation[3],tabConsultation[4],tabConsultation[5],tabConsultation[6]);
				}
			}
			br.close();
			fr.close();
			return c;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public Consultation searchConsultation(String nomPatient,String prenomPatient) {
		Consultation c=null;
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(filePath);
			br=new BufferedReader(fr);
			String line=null;
			while((line = br.readLine()) != null) {
				String tabConsultation[]=line.split(";");
				if((tabConsultation[0].equals(nomPatient) && tabConsultation[1].equals(prenomPatient))) {
					c=new Consultation(tabConsultation[0],tabConsultation[1],tabConsultation[2],tabConsultation[3],tabConsultation[4],tabConsultation[5],tabConsultation[6]);
				}
			}
			br.close();
			fr.close();
			return c;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	/*methode qui retourne une liste de consultation selon le nom du patient, le prenom de patient, le nom de medecin et la date de consultation  en argument. Si l'argument du nom de
	  medecin et de la date inscrit a une longueur inférieur à celle du nom du medecin et de la date de consultation du patient seulement le nombre de lettre de l'argument sont alors verifiés*/
	public ArrayList<Consultation> searchConsultationList(String nomPatient,String prenomPatient,String nomMedecin,String date){
		ArrayList<Consultation> consultationList=new ArrayList<>();
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(filePath);
			br=new BufferedReader(fr);
			String line=null;
			while((line = br.readLine()) != null) {
				String tabConsultation[]=line.split(";");
				try {
					if(nomPatient.toUpperCase().equals(tabConsultation[0].substring(0,nomPatient.length())) && prenomPatient.toUpperCase().equals(tabConsultation[1].substring(0,prenomPatient.length())) && nomMedecin.toUpperCase().equals(tabConsultation[2].substring(0,nomMedecin.length())) && date.toUpperCase().equals(tabConsultation[3].substring(0,date.length()))) {
						consultationList.add(new Consultation(tabConsultation[0],tabConsultation[1],tabConsultation[2],tabConsultation[3],tabConsultation[4],tabConsultation[5],tabConsultation[6]));
					}
				}catch(StringIndexOutOfBoundsException e) {
				}
			}
			br.close();
			fr.close();
			return consultationList;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return consultationList;
	}
	
	
	/*methode qui retourne un boolean qui verifie si la modification d'une consultation c'est bien réalisé ou pas. Cette méthode modifie la consultation par une consultation placer en argument.
	 Cette modification ne se fait que si les informations entrer son bonnes, q'ils ont été changer et que si personne ne possède pas déjà une consultation le meme jour*/
	public boolean editConsultation(Consultation c) {
		boolean edit=false;
		if(getConsultation().equals(c.getConsultation())) {
			JOptionPane.showMessageDialog(new JFrame(),"Les informations sont inchangés.");
		}else {
			ArrayList<Consultation> consultationList=new ArrayList<>();
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr = new FileReader(filePath);
				br=new BufferedReader(fr);
				String line=null;
				boolean verif=true;
				if(c.verifDate()==false || verifString(c.nomMedecin)==false) {
					verif=false;
					JOptionPane.showMessageDialog(new JFrame(),"Informations Invalides.");
				}
				while((line = br.readLine()) != null) {
					String tabConsultation[]=line.split(";");
					if((((tabConsultation[0].equals(c.nomPatient.toUpperCase()) && tabConsultation[1].equals(c.prenomPatient.toUpperCase()))) && tabConsultation[3].equals(c.date) && !date.equals(c.date)) && nomMedecin.equals(c.nomMedecin)) {
						verif=false;
						JOptionPane.showMessageDialog(new JFrame(),"Impossible, ce patient à déjà une consultation ce jour là.");
						break;
					}
					if(!getConsultation().equals(line)) {
						consultationList.add(new Consultation(tabConsultation[0],tabConsultation[1],tabConsultation[2],tabConsultation[3],tabConsultation[4],tabConsultation[5],tabConsultation[6]));
					}else {
						consultationList.add(new Consultation(c.getNomPatient(),c.getPrenomPatient(),c.getNomMedecin(),c.getDate(),c.getPathologie(),c.getAppareillage(),c.getOctroye()));
					}
				}
				if(verif==true) {
					FileWriter fw=new FileWriter(filePath);
					for(int i=0;i<consultationList.size();i++) {
						fw.append(consultationList.get(i).getConsultation()+"\n");
					}
					fw.close();
					JOptionPane.showMessageDialog(new JFrame(),"La consultation a bien été modifier.");
					edit=true;
				}
				br.close();
				fr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return edit;
	}
	
	//methode qui octroye une consultation si elle était en attente ou qui la met en attente si elle était octroyé
	public void octroyeConsultation() {
		if(getOctroye().equals("EN ATTENTE")) {
			editConsultation(new Consultation(getNomPatient(),getPrenomPatient(),getNomMedecin(),getDate(),getPathologie(),getAppareillage(),"OCTROYE"));
		}else if(getOctroye().equals("OCTROYE")) {
			editConsultation(new Consultation(getNomPatient(),getPrenomPatient(),getNomMedecin(),getDate(),getPathologie(),getAppareillage(),"EN ATTENTE"));
		}
	}
	
	//methode qui supprime une consultation du fichier filePath si celle ci existe
	public void deleteConsultation() {
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader(filePath);
			br=new BufferedReader(fr);
			ArrayList<Consultation> consultationList=new ArrayList<>();
			String line;
			while((line = br.readLine()) != null) {
				String tabConsultation[] = line.split(";");
				if(tabConsultation[0].equals(nomPatient) && tabConsultation[1].equals(prenomPatient) && tabConsultation[2].equals(nomMedecin) && tabConsultation[3].equals(date) && tabConsultation[4].equals(pathologie) && tabConsultation[5].equals(appareillage) && tabConsultation[6].equals(octroye)) {
				}else {
					consultationList.add(new Consultation(tabConsultation[0],tabConsultation[1],tabConsultation[2],tabConsultation[3],tabConsultation[4],tabConsultation[5],tabConsultation[6]));
				}
			}
			br.close();
			fr.close();
			FileWriter fw=new FileWriter(filePath);
			for(int i=0;i<consultationList.size();i++) {
				fw.append(consultationList.get(i).getConsultation()+"\n");
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
	public String getConsultation() {
		return nomPatient.toUpperCase()+";"+prenomPatient.toUpperCase()+";"+nomMedecin.toUpperCase()+";"+date+";"+pathologie+";"+appareillage+";"+octroye;
	}

	public String getNomPatient() {
		return nomPatient;
	}
	
	public String getPrenomPatient() {
		return prenomPatient;
	}

	public String getNomMedecin() {
		return nomMedecin;
	}

	public String getDate() {
		return date;
	}

	public String getPathologie() {
		return pathologie;
	}

	public String getAppareillage() {
		return appareillage;
	}

	public String getOctroye() {
		return octroye;
	}

	public void setOctroye(String octroye) {
		this.octroye = octroye;
	}
}