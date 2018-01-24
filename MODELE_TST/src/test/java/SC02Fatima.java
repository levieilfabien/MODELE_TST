package test.java;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import constantes.Actions;
import exceptions.SeleniumException;
import main.bean.CasEssaiModeleBean;
import main.constantes.Cibles;
import main.constantes.Constantes;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;
import outils.SeleniumOutils;

public class SC02Fatima extends SC00Modele {
	@Test
	public void accesTest() throws SeleniumException {
		
		//Description du scénario
		CasEssaiModeleBean scenario0 = new CasEssaiModeleBean();
		
		///////////////////////////////////////////////////Configuration////////////////////////////////////////////////
	    SeleniumOutils outil = obtenirDriver(scenario0);		
	    
		///////////////////////////////////////////////////EXECUTION////////////////////////////////////////////////
		String url = Constantes.URL_SAMY_FATIMA;
		String titre = Constantes.TITRE_PAGE_SAMY;

		// Accès à google
		outil.chargerUrl(url);
		
		// Attente de l'affichage du titre de la page
		outil.attendreChargementPage(titre);
		
		// Faire une saisie dans le formulaire de Mock
		outil.action(Actions.SELECTIONNER, Cibles.SELECTION_CIVILITE_SAMY, "HOMME"); 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SELECTION_NOM_SAMY, "LEMONSIEUR"); 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SELECTION_PRENOM_SAMY, "KEVIN"); 
		//Saisir la date naissance
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SELECTION_dateNaissance_SAMY, "12/12/1970"); 
		//Saisir le mail
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SELECTION_email_SAMY, "ichiroubh@gmail.com"); 
		//Saisir le numéro de portable 
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SELECTION_telPortable_SAMY, "0610109436"); 
	}
	
	/**
	 * Permet d'obtenir la boite a outil Selenium associe a un driver pour le scenario donne.
	 * @param scenario0 le scenario concerne.
	 * @return la boite a outil Selenium associee au scenario.
	 */
	public SeleniumOutils obtenirDriver(CasEssaiModeleBean scenario0) {
		//Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();

		if (scenario0.getRepertoireTelechargement() == null) { 
			String repertoire = creerRepertoireTelechargement(scenario0, profile);
			scenario0.setRepertoireTelechargement(repertoire);
			this.setRepertoireTelechargement(repertoire);
		}
		// Initialisation du driver
		//FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		FirefoxImpl driver = new FirefoxImpl(profile);
		
		driver.get(Constantes.URL_SAMY_FATIMA);

	    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
	    outil.setRepertoireRacine(scenario0.getRepertoireTelechargement());
		
		return outil;
	}
}



