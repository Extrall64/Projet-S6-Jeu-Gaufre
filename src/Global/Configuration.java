package Global;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

public class Configuration {
	private static Configuration instance = null;
	Properties prop;
	
	public static InputStream charge(String nom) {
		return ClassLoader.getSystemClassLoader().getResourceAsStream(nom);
	}

	private Configuration() {
		prop = new Properties();
		try {
			InputStream propIn = charge("default.cfg");
			prop.load(propIn);
			String home = System.getProperty("user.home");
			FileInputStream f = new FileInputStream(home + File.separator + ".gaufre");
			prop = new Properties(prop);
			prop.load(f);
		} catch (Exception e) {
			System.err.println("Erreur lors de la lecture de la configuration : " + e);
		}
	}

	public static Configuration instance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	public String lis(String cle) {
		String resultat = prop.getProperty(cle);
		if (resultat == null)
			throw new NoSuchElementException("Propriete " + cle + " non definie");
		return resultat;
	}
	
	public int largeur() {
		String type = lis("Largeur");
		return Integer.parseInt(type);
	}
	
	public int hauteur() {
		String type = lis("Hauteur");
		return Integer.parseInt(type);
	}
 }