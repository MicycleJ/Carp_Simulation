package SyntSpec;
import java.util.ArrayList;

/**
 * WorkerThread is a class that allows for multithreading of the
 * function <code>simulateFishReproduction</code>, wherein correct
 * babies are produced for each mother-father pairing
 */
class WorkerThread
  implements Runnable
{
  private int start;
  private int end;
  private ArrayList<SSCarps> males;
  private ArrayList<SSCarps> females;
  private ArrayList<SSCarps> myBabies;
  private ArrayList<SSCarps> finalBabies;
  
  /**
   * WorkerThread constructor, determines the number of fish each thread
   * must handle, and carrying the necessary variables to perform
   * reproduction
   * @param femaleStart			int starting point for a single thread, out of list of females
   * @param femaleEnd			int endpoint for a single thread, out of list of females
   * @param adultMales			list of fathers
   * @param adultFemales		list of mothers
   * @param potentialBabies		list of potential baby fish (no identifying information)
   * @param finalBabyFish		output list of babyfish, to be added to across threads
   */
  public WorkerThread(int femaleStart, int femaleEnd, ArrayList<SSCarps> adultMales, ArrayList<SSCarps> adultFemales, ArrayList<SSCarps> potentialBabies, ArrayList<SSCarps> finalBabyFish) {
    this.start = femaleStart;
    this.end = femaleEnd;
    this.males = adultMales;
    this.females = adultFemales;
    this.myBabies = potentialBabies;
    this.finalBabies = finalBabyFish;
  }
  
  /**
   * run <code>SSCarp.makeBaby</code> for the set of offspring the thread
   * is in charge of, and add offspring to this.finalBabies
   */
  public void run() {
    ArrayList<SSCarps> tempFinalBabies = new ArrayList<>(this.end - this.start + 2);
    
    for (int i = this.start; i < this.end; i++) {
      
      SSCarps b = SSCarp.makeBaby(i, this.myBabies, this.females, this.males);
      if (b != null)
      {
        tempFinalBabies.add(b);
      }
    } 
    
    synchronized (this.finalBabies) {
      
      this.finalBabies.addAll(tempFinalBabies);
    } 
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\WorkerThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */