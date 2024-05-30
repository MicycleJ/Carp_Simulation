/*      */ package SyntSpec;
/*      */ import java.util.ArrayList;

/*      */ class WorkerThread
/*      */   implements Runnable
/*      */ {
/*      */   private int start;
/*      */   private int end;
/*      */   private ArrayList<SSCarps> males;
/*      */   private ArrayList<SSCarps> females;
/*      */   private ArrayList<SSCarps> myBabies;
/*      */   private ArrayList<SSCarps> finalBabies;
/*      */   
/*      */   public WorkerThread(int femaleStart, int femaleEnd, ArrayList<SSCarps> adultMales, ArrayList<SSCarps> adultFemales, ArrayList<SSCarps> potentialBabies, ArrayList<SSCarps> finalBabyFish) {
/* 2042 */     this.start = femaleStart;
/* 2043 */     this.end = femaleEnd;
/* 2044 */     this.males = adultMales;
/* 2045 */     this.females = adultFemales;
/* 2046 */     this.myBabies = potentialBabies;
/* 2047 */     this.finalBabies = finalBabyFish;
/*      */   }
/*      */   public void run() {
/* 2050 */     ArrayList<SSCarps> tempFinalBabies = new ArrayList<>(this.end - this.start + 2);
/*      */     
/* 2052 */     for (int i = this.start; i < this.end; i++) {
/*      */       
/* 2054 */       SSCarps b = SSCarp.makeBaby(i, this.myBabies, this.females, this.males);
/* 2055 */       if (b != null)
/*      */       {
/* 2057 */         tempFinalBabies.add(b);
/*      */       }
/*      */     } 
/*      */     
/* 2061 */     synchronized (this.finalBabies) {
/*      */       
/* 2064 */       this.finalBabies.addAll(tempFinalBabies);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\WorkerThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */