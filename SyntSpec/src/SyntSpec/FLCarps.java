/*    */ package SyntSpec;
/*    */ 
/*    */ public class FLCarps
/*    */   extends SSCarps
/*    */ {
/* 17 */   int alleleCount = 0;
/*    */ 
/*    */   
/*    */   public FLCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender, int aCount) {
/* 21 */     super(newName, newAge, newLocation, ss, fgender);
/* 22 */     this.alleleCount = aCount;
/*    */   }
/*    */ 
/*    */   
/*    */   public FLCarps(String newName, int newAge, int length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, int aCount) {
/* 27 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 28 */     this.alleleCount = aCount;
/*    */   }
/*    */ 
/*    */   
/*    */   public FLCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
/* 33 */     this(newName, newAge, newLocation, ss, fgender, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public FLCarps(String newName, int newAge, int length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
/* 38 */     this(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender, 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\FLCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */