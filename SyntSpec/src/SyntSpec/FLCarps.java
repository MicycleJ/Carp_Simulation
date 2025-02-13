package SyntSpec;

/**
 * FLCarps is a class that extends <code>SSCarps</code> with the variable
 * <code>alleleCount</code>, which is 0 by default. Note: This variable is
 * currently UNUSED in the simulation, but is set to 8 in the currently
 * unused function <code>addFLFish</code>.
 */
public class FLCarps
  extends SSCarps
{
  int alleleCount = 0;

  /**
   * FLCarps constructor, takes in alleleCount
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param newLocation		lake object where the fish lives
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   * @param aCount			int alleleCount
   */
  public FLCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender, int aCount) {
    super(newName, newAge, newLocation, ss, fgender);
    this.alleleCount = aCount;
  }

  /**
   * FLCarps constructor, takes in alleleCount, length, and more
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   * @param aCount			int alleleCount
   */
  public FLCarps(String newName, int newAge, int length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, int aCount) {
    super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
    this.alleleCount = aCount;
  }

  /**
   * FLCarps constructor, alleleCount is defaulted to 0
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param newLocation		lake object where the fish lives
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   */
  public FLCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
    this(newName, newAge, newLocation, ss, fgender, 0);
  }

  /**
   * FLCarps constructor, alleleCount is defaulted to 0, also takes in
   * length and lake locations
   * @param newName			string identifier
   * @param newAge			integer age in years
   * @param length			length of the fish in cm
   * @param newLocation		lake object where the fish lives
   * @param bornLake		lake object where the fish was born
   * @param dispersal		lake object for the fish's dispersal pattern
   * @param ss				<code>TRUE</code> if the fish is sterile, else <code>FALSE</code>
   * @param fgender			gender of the fish
   */
  public FLCarps(String newName, int newAge, int length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
    this(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender, 0);
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\FLCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */