package Model;

import java.util.ArrayList;

import com.sun.media.jfxmedia.control.VideoFormat;

import Model.Salle.enumDescription;

public class Carte {
	
	private Salle[] salles;
	
	public Carte(int lvl) {
		switch (lvl) {
		case 1:
			this.salles = lvl1();
			break;
		case 2:
			this.salles = lvl2();
			break;
		case 3:
			this.salles = lvl3();
			break;
		default:
			break;
		}
	}

	private Salle[] lvl3() {
		Salle[] lvl3 = new Salle[81];
		
		return lvl3;
	}

	private Salle[] lvl2() {
		Salle[] lvl2 = new Salle[81];
		
		return lvl2;
	}

	private Salle[] lvl1() {
		
		Salle[] lvl1 = new Salle[81];
		
		// 1er colonne de salles
		lvl1[0] = new Salle(enumDescription.SALLE, 1, "Vous entrée en premiere année de STRI, frais comme un gardon, vous attaquez cette formation avec de bonnes résolutions");
		lvl1[1] = new Salle(Salle.enumDescription.MUR, 2, "");
		lvl1[2] = new Salle(enumDescription.SORTIE, 3, "Vous passez cette premiere année de STRI avec une excellente moyenne !!");
		lvl1[3] = new Arene(new Monstre("Mme Galy", 100, 100, new Arme("l'abaque de Smith", 100, 10) , 10), 4, "Pour passer en 2ème année STRI, vous devez comprendre l'adaptation à l'impédence et maitriser les parametres S");
		lvl1[4] = new Arene(new Monstre("Un partiel de Télécom", 20, 10, null, 6), 5, "Un partiel de télécom vous attend... Serez vous résoudre ses équations?");
		lvl1[5] = new Salle(enumDescription.SALLE, 6,"reviser ou dormir, tel est la question...");
		lvl1[6] = new Arene(new Monstre("Un partiel sur les antènes", 30, 50, null, 7), 7, "Vous priez les dieux ancien de vous venir en aide");
		lvl1[7] = new Salle(enumDescription.SALLE, 8,"reviser ou dormir, tel est la question...");
		lvl1[8] = new Salle(enumDescription.SALLE, 9, "");
		
		// list d'objet de la boutique du marchand
		java.util.List<Objet> marchandises = new ArrayList<>();
		marchandises.add(new Arme("un compas", 100, 1));
		marchandises.add(new Arme("Un stylo qui fuit", 200, 1));
		marchandises.add(new Arme("Une vieille calculatrice", 1500, 2));
		marchandises.add(new Arme("Un Latitude 5480", 5000, 5));
		marchandises.add(new Armure("Un excuse", 500, 20));
		marchandises.add(new Armure("Un certificat médical", 2000, 100));
		
		// 2eme colonne de salles
		lvl1[9] = new Boutique(marchandises, "M. Aoun le marchand est prêt à vous aider pour cette année difficile contre quelques piecettes, si vous avez des questions postez les sur le forum");
		lvl1[10] = new Salle(enumDescription.MUR, 11, "");
		lvl1[11] = new Salle(enumDescription.MUR, 12, "");
		lvl1[12] = new Salle(enumDescription.MUR, 13, "");
		lvl1[13] = new Salle(enumDescription.MUR, 14, "");
		lvl1[14] = new Salle(enumDescription.MUR, 15, "");
		lvl1[15] = new Salle(enumDescription.MUR, 16, "");
		lvl1[16] = new Salle(enumDescription.SALLE, 17, "Un moment de repos, peut être avez vous le temps de prendre un café?");
		lvl1[17] = new Arene(new Monstre("un DM de C",20, 200, null, 5), 18, "Maitrisez vous malloc ?");
		
		// 3eme colonne de salles
		lvl1[18] = new Salle(enumDescription.SALLE, 19, "Un jour ferié !!");
		lvl1[19] = new Salle(enumDescription.SALLE, 20, "Une journée ensoleillée");
		lvl1[20] = new Arene(new Monstre("un TD de Windows", 5, 20, null, 2), 21, "vous téléchargez Sysinternals pour réussir ce TD");
		lvl1[21] = new Salle(enumDescription.SALLE, 22, "Un jour de repos...");
		lvl1[22] = new Salle(enumDescription.SALLE, 23, "Un petit tour au BK");
		lvl1[23] = new Arene(new Monstre("un TD d'HTML", 20, 100, null, 6), 24, "<p> les balises c'est la vie </p>");
		lvl1[24] = new Salle(enumDescription.MUR, 25, "");
		lvl1[25] = new Salle(enumDescription.MUR, 26, "");
		lvl1[26] = new Arene(new Monstre("Un projet Java", 50, 200, new Arme("Inner class", 200, 7), 10), 27, "réussir ce projet vous demandera de sacrifier plusieurs nuits...");
		
		// 4eme colonne de salles
		lvl1[27] = new Arene(new Monstre("un TD de Linux", 8, 40, null, 3), 28, "there is no place like 127.0.0.1");
		lvl1[28] = new Salle(enumDescription.MUR, 29, "");
		lvl1[29] = new Salle(enumDescription.MUR, 30, "");
		lvl1[30] = new Salle(enumDescription.MUR, 31, "");
		lvl1[31] = new Arene(new Monstre("un TD de Graphe", 10, 100, null, 5), 32, "l'algorithme de Bellman-Ford vous sauvez peut être ?");
		lvl1[32] = new Salle(enumDescription.MUR, 33, "");
		lvl1[33] = new Salle(enumDescription.MUR, 34, "");
		lvl1[34] = new Salle(enumDescription.SALLE, 35, "erreur de planning, pas de cours !");
		lvl1[35] = new Arene(new Monstre("un partiel de Graphe", 50, 200, null, 8), 36, "La puit de cette année semble être par là, avec un peu de chance...");
		
		// 5eme colonne de salles
		lvl1[36] = new Arene(new Monstre("un TD de télcom", 20, 100, null, 6), 37, "Un bilan de liaison sur vos résultats est pour l'instant positif");
		lvl1[37] = new Salle(enumDescription.MUR, 38, "");
		lvl1[38] = new Salle(enumDescription.SALLE, 39, "Une petite période en entreprise, un calme bien mérité !");
		lvl1[39] = new Salle(enumDescription.MUR, 40, "");
		lvl1[40] = new Salle(enumDescription.SALLE, 41, "Une longue période en entreprise vous ressource");
		lvl1[41] = new Salle(enumDescription.SALLE, 42, "Premier cours à 10h, du temps pour dormir");
		lvl1[42] = new Arene(new Monstre("un TP de télécom", 50, 200, null, 7), 43, "Oserez vous demander de l'aide ?");
		lvl1[43] = new Salle(enumDescription.SALLE, 44, "Le Samedi est un jour pour réviser !! a moins que ...");
		lvl1[44] = new Salle(enumDescription.MUR, 45, "");
		
		// 6eme colonne de salles
		lvl1[45] = new Salle(enumDescription.SALLE, 46, "Un café bien chaud au RU");
		lvl1[46] = new Salle(enumDescription.SALLE, 47, "Petit déjeuné du BDE, le moment de se débarasser de ces centimes !");
		lvl1[47] = new Salle(enumDescription.SALLE, 48, "Je me demande quel goût à cette craie ?");
		lvl1[48] = new Salle(enumDescription.MUR, 49, "");
		lvl1[49] = new Arene(new Monstre("Un devoir d'anglais", 1, 1, null, 1), 50, "Gougeulle translayte");
		lvl1[50] = new Salle(enumDescription.MUR, 51, "");
		lvl1[51] = new Salle(enumDescription.MUR, 52, "");
		lvl1[52] = new Salle(enumDescription.SALLE, 53, "le Dimanche c'est le jour du seigneur et de la guelle de bois");
		lvl1[53] = new Salle(enumDescription.MUR, 53, "");
		
		// 7eme colonne de salles
		lvl1[54] = new Salle(enumDescription.SALLE, 55, "Une petite partie de foot au soleil");
		lvl1[55] = new Salle(enumDescription.MUR, 56, "");
		lvl1[56] = new Arene(new Monstre("Un partiel de Télécom", 50, 200, null, 5), 57, "Trouver l'équation d'une droite c'est niveau colège !");
		lvl1[57] = new Salle(enumDescription.SALLE, 58, "En lisant le canard enchainé vous gagnez 2h de repos");
		lvl1[58] = new Salle(enumDescription.SALLE, 59, "Vous dédicacez votre année au drive");
		lvl1[59] = new Salle(enumDescription.MUR, 60, "");
		lvl1[60] = new Salle(enumDescription.MUR, 61, "");
		lvl1[61] = new Salle(enumDescription.SALLE, 62, "Ce matin le reveil n'a pas sonné... enfin... c'est ce que vous allez dire");
		lvl1[62] = new Salle(enumDescription.MUR, 63, "");
		
		// 8eme colonne de salles
		lvl1[63] = new Salle(enumDescription.SALLE, 64, "une petite partie de blackhole.io, ça passe le temps");
		lvl1[64] = new Salle(enumDescription.MUR, 65, "");
		lvl1[65] = new Salle(enumDescription.MUR, 66, "");
		lvl1[66] = new Salle(enumDescription.SALLE, 67, "prendre un peu l'air, ça fait du bien !");
		lvl1[67] = new Salle(enumDescription.MUR, 68, "");
		lvl1[68] = new Salle(enumDescription.MUR, 69, "");
		lvl1[69] = new Salle(enumDescription.MUR, 70, "");
		lvl1[70] = new Arene(new Monstre("Un partiel de réseaux", 70, 300, null, 7), 71, "Vous vous apprétez à rescuciter les technologies des temps anciens");
		lvl1[71] = new Salle(enumDescription.MUR, 72, "");
		
		// 9eme colonne de salles 
		lvl1[72] = new Salle(enumDescription.SALLE, 73, "protip : ubbereat livre jusqu'aux amphis");
		lvl1[73] = new Salle(enumDescription.SALLE, 74, "Une petite partie d'un jeux débile et chronophage sur smartphone");
		lvl1[74] = new Salle(enumDescription.MUR, 75, "");
		lvl1[75] = new Arene(new Monstre("Un partiel d'UML", 70, 200, null, 6), 76, "Saurez vous différencier l'aggrégation de la composition dans le cas commander un burger?");
		lvl1[76] = new Salle(enumDescription.SALLE, 77, "Vous recevez un mail de Jackie, le cours est annulé ");
		lvl1[77] = new Arene(new Monstre("un éléve agacant", 10, 200, null, 1), 78, "Il pense avoir raison sur l'enseignant...");
		lvl1[78] = new Salle(enumDescription.MUR, 79, "");
		lvl1[79] = new Arene(new Monstre("Un DM de réseaux", 30, 200, null, 5), 80, "Vous préparez vos offrances pour le drive");
		lvl1[80] = new Salle(enumDescription.SALLE, 81, "En fin d'année les horaires sont bien plus sympa");
						
		return lvl1;
	}
}
