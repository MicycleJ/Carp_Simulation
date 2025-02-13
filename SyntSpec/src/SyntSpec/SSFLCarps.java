package SyntSpec;

/**
 * SSFLCarps is a class that extends <code>SSCarps</code> with genotypes
 * at 3 loci; a P, T, and L locus.
 */
public class SSFLCarps
  extends SSCarps
{
  String genotypeP;
  String genotypeT;
  String genotypeL;
  
  /**
   * SSFLCarps constructor, defaults to "pp" "tt" and "ll" genotypes
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param newLocation		lake object where the fish lives
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   */
  public SSFLCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
    super(newName, newAge, newLocation, ss, fgender);
    this.genotypeP = "pp";
    this.genotypeT = "tt";
    this.genotypeL = "ll";
  }
  
  /**
   * SSFLCarps constructor, default genotypes of "pp" "tt" and "ll", 
   * and takes in length and lake locations
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   */
  public SSFLCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genotypeP = "pp";
    this.genotypeT = "tt";
    this.genotypeL = "ll";
  }
  
  /**
   * SSFLCarps constructor, with input genotype in the form "ppttll", or 
   * "PPTTLL" for a completely transgenic individual
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   * @param gt				genotype of the fish (examples: ppttll, PPTTLL, pPtTlL)
   */
  public SSFLCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genotypeP = gt.substring(0, 2);
    this.genotypeT = gt.substring(2, 4);
    this.genotypeL = gt.substring(4, 6);
  }
  
  /**
   * SSFLCarps constructor, with input genotype and generation
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   * @param gt				genotype of the fish (examples: ppttll, PPTTLL, pPtTlL)
   * @param gen				generation of the fish, used with GENE_GENERATION_DEATH.
   * 						-1 if there is no countdown to death
   */
  public SSFLCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt, int gen) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genotypeP = gt.substring(0, 2);
    this.genotypeT = gt.substring(2, 4);
    this.genotypeL = gt.substring(4, 6);
    this.generation = gen;
  }
  
  /**
   * Convert an SSCarps fish to an SSFLCarps, and set genotypes to
   * "pp" "tt" and "ll", the wildtype genome.
   * @param o		SSCarps fish to be converted
   */
  public SSFLCarps(SSCarps o) {
    super(o.name, o.age, o.length, o.location, o.bornLake, o.dispersalLake, o.isSS, o.gender);
    this.genotypeP = "pp";
    this.genotypeT = "tt";
    this.genotypeL = "ll";
  }
  
  /**
   * return <code>TRUE</code> if "ppttll". Else, <code>FALSE</code>
   */
  public boolean isWild() {
    return (this.genotypeP.equals("pp") && this.genotypeT.equals("tt") && this.genotypeL.equals("ll"));
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SSFLCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */