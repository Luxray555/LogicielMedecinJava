package Centre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Cette classe concentre toute les méthodes de calcul de statistique qui sont présents dans l'interface MainStatistiques
public class Statistiques {
	//Ces variables sont representes les emplacements des fichiers où sont stocké les patients et les consultations
	private final static String filePathPatient="patients.txt",filePathConsultation="consultations.txt";
	
	//Cette methode renvoie le nombre de patient
	public static int nbrPatient() {
		FileReader fr=null;
		BufferedReader br=null;
		int nbr=0;
		try {
			fr = new FileReader(filePathPatient);
			br=new BufferedReader(fr);
			while(br.readLine()!=null) {
				nbr++;
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return nbr;
	}
	
	//Cett methode renvoie le nombde de consultation
	public static int nbrConsultation() {
		FileReader fr=null;
		BufferedReader br=null;
		int nbr=0;
		try {
			fr = new FileReader(filePathConsultation);
			br=new BufferedReader(fr);
			while(br.readLine()!=null) {
				nbr++;
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return nbr;
	}
	
	//Cette methode renvoie le nombre d'appareil octroye
	public static int nbrAppareilOctroye() {
		FileReader fr=null;
		BufferedReader br=null;
		int nbr=0;
		try {
			fr = new FileReader(filePathConsultation);
			br=new BufferedReader(fr);
			String line;
			while((line=br.readLine())!=null) {
				String tabConsultation[]=line.split(";");
				if(tabConsultation[6].equals("OCTROYE")) {
					nbr++;
				}
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return nbr;
	}
	
	//Cette methode renvoie la moyenne de consultation par patient
	public static float moyenneConsultationPatient() {
		return (float)Math.round(((float)nbrConsultation()/nbrPatient())*100)/100;
	}
	
	//Cette methode renvoie le nombre de pathologie placer en argument
	public static int nbrPathologie(String pathologie) {
		FileReader fr=null;
		BufferedReader br=null;
		int nbr=0;
		try {
			fr = new FileReader(filePathConsultation);
			br=new BufferedReader(fr);
			String line;
			while((line=br.readLine())!=null) {
				String tabConsultation[]=line.split(";");
				if(tabConsultation[4].equals(pathologie)) {
					nbr++;
				}
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return nbr;
	}
	
	//Cette methode renvoie le pourcentage de la pathologie placer en argument sur le nombre de consultation total
	public static float pourcentagePathologie(String pathologie) {
		return Math.round(((float)nbrPathologie(pathologie)/nbrConsultation())*100);
	}
	
	//Cette methode renvoie le nombre d'appareil placer en argument
	public static int nbrAppareil(String appareil) {
		FileReader fr=null;
		BufferedReader br=null;
		int nbr=0;
		try {
			fr = new FileReader(filePathConsultation);
			br=new BufferedReader(fr);
			String line;
			while((line=br.readLine())!=null) {
				String tabConsultation[]=line.split(";");
				if(tabConsultation[5].equals(appareil)) {
					nbr++;
				}
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return nbr;
	}
	
	//Cette methode renvoie le pourcentage de l'appareil placer en argument sur le nombre de consultation total
	public static float pourcentageAppareil(String appareil) {
		return Math.round(((float)nbrAppareil(appareil)/nbrConsultation())*100);
	}
	
	//Cette methode renvoie le nombre d'appareil octroye placer en argument
	public static int nbrAppareilOctroye(String appareil) {
		FileReader fr=null;
		BufferedReader br=null;
		int nbr=0;
		try {
			fr = new FileReader(filePathConsultation);
			br=new BufferedReader(fr);
			String line;
			while((line=br.readLine())!=null) {
				String tabConsultation[]=line.split(";");
				if(tabConsultation[5].equals(appareil) && tabConsultation[6].equals("OCTROYE")) {
					nbr++;
				}
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return nbr;
	}
	
	//Cette methode renvoie le pourcentage de l'appareil octroyer placer en argument sur le nombre d'appareil octroye
	public static float pourcentageAppareilOctroye(String appareil) {
		return Math.round(((float)nbrAppareilOctroye(appareil)/nbrAppareilOctroye())*100);
	}
}
