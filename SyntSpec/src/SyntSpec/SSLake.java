/*    */ package SyntSpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSLake
/*    */ {
/*    */   public double winterKillProbabilty;
/*    */   public boolean isWinterKilled = false;
/*    */   public boolean isSeining = false;
/*    */   public String lakeName;
/*    */   public double lakeArea;
/*    */   
/*    */   public SSLake(String name, double winterProb, double area) {
/* 27 */     this.lakeName = name;
/* 28 */     this.winterKillProbabilty = winterProb;
/* 29 */     this.lakeArea = area;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void trySeining() {
/* 35 */     double rand = Math.random();
/*    */     
/* 37 */     if (rand < SimConfigs.SeiningOccurProbability) {
/*    */       
/* 39 */       this.isSeining = true;
/*    */     }
/*    */     else {
/*    */       
/* 43 */       this.isSeining = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void tryWinterkill() {
/* 52 */     double rand = Math.random();
/*    */     
/* 54 */     if (rand < this.winterKillProbabilty) {
/*    */       
/* 56 */       this.isWinterKilled = true;
/*    */     }
/*    */     else {
/*    */       
/* 60 */       this.isWinterKilled = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     return this.lakeName;
/*    */   }
/*    */ }


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SSLake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */