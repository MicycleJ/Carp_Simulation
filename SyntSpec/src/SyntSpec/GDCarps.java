package SyntSpec;

/**
 * GDCarps is a class that extends <code>SSCarps</code> with an 
 * GD-specific genotype string and a new method for determining
 * if a fish is "wild" or not
 */
public class GDCarps
  extends SSCarps
{
  String genotypeGD;
  
  /**
   * GDCarps constructor, genotype defaults to "WW"
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param newLocation		lake object where the fish lives
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   */
  public GDCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
    super(newName, newAge, newLocation, ss, fgender);
    this.genotypeGD = "WW";
  }

  /**
   * GDCarps constructor, genotype defaults to "WW"
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   */
  public GDCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genotypeGD = "WW";
  }

  /**
   * GDCarps constructor, with input genotype and input generation
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   * @param gt				genotype of the fish (examples: GW, LR)
   * @param gen				generation of the fish. -1 if there is no countdown to death
   */
  public GDCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt, int gen) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genotypeGD = gt;
    this.generation = gen;
  }


  /**
   * GDCarps constructor, with input genotype and no input generation
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   * @param gt				genotype of the fish (examples: GG, WR)
   */
  public GDCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.genotypeGD = gt;
  }


  /**
   * convert an SSCarps fish to a GDCarps, and set genotype to "WW"
   * @param o		SSCarps to be converted
   */
  public GDCarps(SSCarps o) {
    super(o.name, o.age, o.length, o.location, o.bornLake, o.dispersalLake, o.isSS, o.gender);
    this.genotypeGD = "WW";
  }


  /**
   * return <code>TRUE</code> if genotype is "WW". Else, return <code>FALSE</code>
   */
  public boolean isWild() {
    return this.genotypeGD.equals("WW");
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\GDCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */