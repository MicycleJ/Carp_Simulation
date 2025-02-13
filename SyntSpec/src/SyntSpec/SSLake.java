package SyntSpec;

/**
 * SSLake objects represent a lake, its area, and other parameters concerning it
 */
public class SSLake
{
  public double winterKillProbabilty;
  public boolean isWinterKilled = false;
  public boolean isSeining = false;
  public String lakeName;
  public double lakeArea;
  
  /**
   * SSLake constructor, takes in a name for the lake, the probability
   * that the winter will lead to fish death, and its area in hectares
   * @param name			string name for the lake
   * @param winterProb		probability that fish will die off during the winter
   * @param area			double, in hectares
   */
  public SSLake(String name, double winterProb, double area) {
	//TODO make winterProb an input parameter / simconfig
    this.lakeName = name;
    this.winterKillProbabilty = winterProb;
    this.lakeArea = area;
  }

  /**
   * Attempt seining, based on parameter "SeiningOccurProbability"
   */
  public void trySeining() {
    double rand = Math.random();
    
    if (rand < SimConfigs.SeiningOccurProbability) {
      
      this.isSeining = true;
    }
    else {
      
      this.isSeining = false;
    } 
  }

  /**
   * Attempt killing off fish during the winter
   */
  public void tryWinterkill() {
    double rand = Math.random();
    
    if (rand < this.winterKillProbabilty) {
      
      this.isWinterKilled = true;
    }
    else {
      
      this.isWinterKilled = false;
    } 
  }

  /**
   * Return the name of the lake
   */
  public String toString() {
    return this.lakeName;
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SSLake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */