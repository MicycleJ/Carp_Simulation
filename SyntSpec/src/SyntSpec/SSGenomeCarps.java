package SyntSpec;

/**
 * SSGenomeCarps is a class that extends <code>SSCarps</code> with
 * its own set genome, where a wild genome is one with all lowercase letters
 */
public class SSGenomeCarps
  extends SSCarps
{
  private String genome;
  
  /**
   * SSGenomeCarps constructor, with default genome of ""
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param newLocation		lake object where the fish lives
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   */
  public SSGenomeCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
    super(newName, newAge, newLocation, ss, fgender);
    this.genome = "";
  }

  /**
   * SSGenomeCarps constructor, takes in length and locations, and sets
   * genome to ""
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   */
  public SSGenomeCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genome = "";
  }

  /**
   * SSGenomeCarps constructor, with input genotype and input generation
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   * @param gt				genotype of the fish (can be anything?)
   * @param gen				generation, used with GENE_GENERATION_DEATH.
   * 						-1 if there should be no countdown to death
   */
  public SSGenomeCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt, int gen) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genome = gt;
    this.generation = gen;
  }

  /**
   * SSGenomeCarps constructor, with input genotype and no input generation
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   * @param gt				genotype of the fish (examples: ANNFF, ASSMM )
   */
  public SSGenomeCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genome = gt;
  }

  /**
   * Convert an SSCarps fish to an SSGenomeCarps, and set the genome to ""
   * @param o		SSCarps to be converted
   */
  public SSGenomeCarps(SSCarps o) {
    super(o.name, o.age, o.length, o.location, o.bornLake, o.dispersalLake, o.isSS, o.gender);
    this.genome = "";
  }

  /**
   * Generate an SSGenomeCarps, given an SSGenomeCarps
   * @param o		fish
   */
  public SSGenomeCarps(SSGenomeCarps o) {
    super(o.name, o.age, o.length, o.location, o.bornLake, o.dispersalLake, o.isSS, o.gender, o.genome);
  }

  /**
   * Set the genome to be all lowercase
   */
  public void makeWild() {
    this.genome = wildGenome(this.genome);
  }

  /**
   * Get the current genome
   * @return		this.genome
   */
  public String getGenome() {
    return this.genome;
  }

  /**
   * Set this.genome to be the given string
   * @param newG		string to become the new genome
   */
  public void setGenome(String newG) {
    this.genome = newG;
  }

  /**
   * Get only the parts of the genome that exist in the input letters string.
   * ex: if this.genome = "AmcSSFF", this.getGenome("cAF") returns "AcFF"
   * @param letters		the letters the final string will be made of
   * @return			the genome, after removing all characters not in the input
   */
  public String getGenome(String letters) {
    return this.genome.replaceAll("[^" + letters + "]", "");
  }

  /**
   * Get only the parts of the genome that DON'T appear in the input letters string
   * ex: if this.genome = "AmcNNMM", this.getGenomeWithout("cAN") returns "mMM"
   * @param letters		the letters that will be removed from the final string
   * @return			the genome, after removing all characters in letters
   */
  public String getGenomeWithout(String letters) {
    return this.genome.replaceAll("[" + letters + "]", "");
  }

  /**
   * return <code>TRUE</code> if the genotype is all lower-case
   */
  public boolean isWild() {
    return this.genome.equals(wildGenome(this.genome));
  }

  /**
   * Given a string, get the lowercase version of it
   * @param in		input string
   * @return		in.toLowerCase()
   */
  public String wildGenome(String in) {
    return in.toLowerCase();
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SSGenomeCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */