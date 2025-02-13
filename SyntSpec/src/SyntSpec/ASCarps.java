package SyntSpec;

/**
 * ASCarps is a class that extends <code>SSCarps</code> with an 
 * AS-specific genotype string and a new method for determining
 * if a fish is "wild" or not
 */
public class ASCarps
  extends SSCarps
{
  String genotypeAS;
  /* Genotype ANNFF = type (allele sail), no sail, no sail, wt, wt
   * Genotype ASSMM = type (allele sail), sail, sail, male-causing, male-causing
  */
  
  /**
   * ASCarp constructor, default genotype of "ANNFF" (wild).
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param newLocation		lake object where the fish lives
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   */
  public ASCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
    super(newName, newAge, newLocation, ss, fgender);
    this.genotypeAS = "ANNFF";
  }

  /**
   * ASCarp constructor, default genotype of "ANNFF" (wild).
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   */
  public ASCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genotypeAS = "ANNFF";
  }

  /**
   * ASCarp constructor, with input genotype and input generation
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   * @param gt				genotype of the fish (examples: ANNFF, ASSMM )
   * @param gen				generation of the fish. -1 if there is no countdown to death
   */
  public ASCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt, int gen) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genotypeAS = gt;
    this.generation = gen;
  }

  /**
   * ASCarp constructor, with input genotype and no input generation
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
  public ASCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genotypeAS = gt;
  }

  /**
   * Convert an SSCarps fish to an ASCarps, and set genotype to "ANNFF"
   * @param o		SSCarps to be converted
   */
  public ASCarps(SSCarps o) {
    super(o.name, o.age, o.length, o.location, o.bornLake, o.dispersalLake, o.isSS, o.gender);
    this.genotypeAS = "ANNFF";
  }

  /**
   * return <code>TRUE</code> if genotype is "ANNFF". Else, return <code>FALSE</code>
   */
  public boolean isWild() {
    return this.genotypeAS.equals("ANNFF");
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\GDCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */