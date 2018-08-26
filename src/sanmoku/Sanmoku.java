package sanmoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sanmoku {

   static String kuro = "●";
   static String siro = "〇";
   static String kara = "　";
   
   static int gyo = 0;
   static int retu = 0;
   
   static boolean win = false;
   static boolean draw = false;
   
   static String[][] koma = new String[3][3];
   
   //karaのkomaを用意するやつ
   static {
       for(int i = 0; i < koma.length; i++){
           for(int j = 0; j < koma[i].length; j++){
               koma[i][j]=kara;
           }
        }
    }
   
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        disp(); //初期表示       
    
        do {            
       //黒の番
     
            System.out.println("\nあなたの番です");
            you();
            checkBlack();
            
            if (win == true) {
                System.out.println("黒の勝ちです");
                return;
            }
            
            System.out.println("=============================");
            checkdraw();
            
            if (draw == true) {
               System.out.println("引き分けです");
               return;
            }
            
            //白の番
            System.out.println("\nコンピュータの番です（エンターキーを押してください）");
            br.readLine();
            comp();
            checkWhite();
        
            if (win == true) {
                System.out.println("白の勝ちです");
                return;
            }  
            System.out.println("=============================");
        } while (win == false || draw == false);
        
        System.out.println("ゲーム終了です");
    }
    
    //現在の盤面を表示するやつ
    static void disp(){
        System.out.format("　　１　２　３　\n");  
        System.out.format("　┏━┳━┳━┓\n");  
        System.out.format("１┃%s┃%s┃%s┃\n",koma[0][0],koma[0][1],koma[0][2]);  
        System.out.format("　┣━╋━╋━┫\n");  
        System.out.format("２┃%s┃%s┃%s┃\n",koma[1][0],koma[1][1],koma[1][2]);  
        System.out.format("　┣━╋━╋━┫\n");  
        System.out.format("３┃%s┃%s┃%s┃\n",koma[2][0],koma[2][1],koma[2][2]);  
        System.out.format("　┗━┻━┻━┛\n");  
    }
  
  //人間
  static void you() throws IOException{
        String str;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        try {     
            do{

                do {
                    System.out.print("行番号 > ");
                    str = br.readLine();
                    gyo = Integer.parseInt(str);
                    }while (gyo < 1 || gyo > 3 );
                
                do {          
                    System.out.print("列番号 > ");
                    str = br.readLine();
                    retu = Integer.parseInt(str);
                } while (retu < 1 || retu > 3);    
          
                if (!koma[gyo -1][retu -1].equals(kara)) {
                    System.out.println("\nそこは打てません");
                }
            }while(!koma[gyo -1][retu -1].equals(kara));//弾く処理
            
            koma[gyo-1][retu-1] = kuro;
            disp();
              
      } catch (IOException | NumberFormatException e) {
            System.out.println(e + "が報告されました");
      }

  }
 
  //コンピューター
   static void comp(){
        //攻撃
        for(int i = 0; i < 3; i++){      
    
                        
            if (koma[i][0].equals(siro) && koma[i][2].equals(siro)) {
                //●空●
                if (koma[i][1].equals(kara)) {
                koma[i][1] = siro;
                disp();
                return;
                }
            
            }else if(koma[i][0].equals(siro) && koma[i][1].equals(siro)){
                //●●空
                if(koma[i][2].equals(kara)){
                koma[i][2] = siro;
                disp();
                return;
                }
                
            }else if(koma[i][1].equals(siro) && koma[i][2].equals(siro)){
                //空●●
                if(koma[i][0].equals(kara)){
                koma[i][0] = siro;
                disp();
                return;
                }
            }
        }
        
        for (int j = 0; j < 3; j++) {
            
            if (koma[0][j].equals(siro) && koma[2][j].equals(siro)) {
                    //●
                    //空
                    //● 
                    if(koma[1][j].equals(kara)){
                    koma[1][j] = siro;
                     disp();
                     return;
                    }
                }else if (koma[0][j].equals(siro) && koma[1][j].equals(siro)) {
                    //●
                    //●
                    //空 
                    if(koma[2][j].equals(kara)){
                     koma[2][j] = siro;
                     disp();
                     return;
                    }
                }else if (koma[1][j].equals(siro) && koma[2][j].equals(siro)) {
                    //空
                    //●
                    //● 
                    if(koma[0][j].equals(kara)){
                     koma[0][j] = siro;
                     disp();
                     return;
                    }
           }
       }
         if (koma[0][0].equals(siro) && koma[1][1].equals(siro)) {
               //●
               //　●
               //　　空
               if(koma[2][2].equals(kara)){
               koma[2][2] = siro;
               disp();
               return;
               }
               
           }else if(koma[1][1].equals(siro) && koma[2][2].equals(siro)){
               //空 
               //　●
               //　　●
               if(koma[0][0].equals(kara)){
               koma[0][0] = siro;
               disp();
               return;
               }
               
           }else if(koma[0][0].equals(siro) && koma[2][2].equals(siro)){
               //●
               //　空
               //　　●
               if(koma[1][1].equals(kara)){
               koma[1][1] = siro;
               disp();
               return;
               }
               
           }else if(koma[0][2].equals(siro) && koma[1][1].equals(siro)){
               //　　●
               //　●
               //空
               if(koma[2][0].equals(kara)){
               koma[2][0] = siro;
               disp();
               return;
               }
               
           }else if(koma[2][0].equals(siro) && koma[1][1].equals(siro)){
               //　　空
               //　●
               //●
               if(koma[0][2].equals(kara)){
               koma[0][2] = siro;
               disp();
               return;
               }
               
           }else if(koma[0][2].equals(siro) && koma[2][0].equals(siro)){
               //　　●
               //　空
               //●
               if(koma[1][1].equals(kara)){
               koma[1][1] = siro;
               disp();
               return;
               }
           }
       
       //koma　[i(行)]　[j(列)]
       //防御パターン
       for(int i = 0; i < 3; i++){      
            //●空●　で横に揃っている場合           
           if (koma[i][0].equals(kuro) && koma[i][2].equals(kuro)) {
               if (koma[i][1].equals(kara)) {
               koma[i][1] = siro;
               disp();
               return;
               }
           }else if(koma[i][0].equals(kuro) && koma[i][1].equals(kuro)){
               //●●空
               if(koma[i][2].equals(kara)){
               koma[i][2] = siro;
               disp();
               return;
               }
           }else if(koma[i][1].equals(kuro) && koma[i][2].equals(kuro)){
               //空●●
               if(koma[i][0].equals(kara)){
               koma[i][0] = siro;
               disp();
               return;
               }
           }
       }
            
           for (int j = 0; j < 3; j++) {
                 if (koma[0][j].equals(kuro) && koma[2][j].equals(kuro)) {
                    //●
                    //空
                    //● 
                    if(koma[1][j].equals(kara)){
                    koma[1][j] = siro;
                     disp();
                     return;
                    }
                }else if (koma[0][j].equals(kuro) && koma[1][j].equals(kuro)) {
                    //●
                    //●
                    //空 
                    if(koma[2][j].equals(kara)){
                     koma[2][j] = siro;
                     disp();
                     return;
                    }
                }else if (koma[1][j].equals(kuro) && koma[2][j].equals(kuro)) {
                    //空
                    //●
                    //● 
                    if(koma[0][j].equals(kara)){
                     koma[0][j] = siro;
                     disp();
                     return;
                    }
           }
       }
           //以下クロス阻止
           if (koma[0][0].equals(kuro) && koma[1][1].equals(kuro)) {
               //●
               //　●
               //　　空
               if(koma[2][2].equals(kara)){
               koma[2][2] = siro;
               disp();
               return;
               }
               
           }else if(koma[1][1].equals(kuro) && koma[2][2].equals(kuro)){
               //空 
               //　●
               //　　●
               if(koma[0][0].equals(kara)){
               koma[0][0] = siro;
               disp();
               return;
               }
               
           }else if(koma[0][0].equals(kuro) && koma[2][2].equals(kuro)){
               //●
               //　空
               //　　●
               if(koma[1][1].equals(kara)){
               koma[1][1] = siro;
               disp();
               return;
               }
               
           }else if(koma[0][2].equals(kuro) && koma[1][1].equals(kuro)){
               //　　●
               //　●
               //空
               if(koma[2][0].equals(kara)){
               koma[2][0] = siro;
               disp();
               return;
               }
               
           }else if(koma[2][0].equals(kuro) && koma[1][1].equals(kuro)){
               //　　空
               //　●
               //●
               if(koma[0][2].equals(kara)){
               koma[0][2] = siro;
               disp();
               return;
               }
               
           }else if(koma[0][2].equals(kuro) && koma[2][0].equals(kuro)){
               //　　●
               //　空
               //●
               if(koma[1][1].equals(kara)){
               koma[1][1] = siro;
               disp();
               return;
               }
           }
                         
           //初手
           else if (koma[0][0].equals(kuro) || koma[0][2].equals(kuro) || koma[2][0].equals(kuro) || koma[2][2].equals(kuro)) {
               //黒が一手目を隅に置いたら真ん中に打つ
               if(koma[1][1].equals(kara)){
               koma[1][1] = siro;
               disp();
               return;
               }
           }
   
           
        
           //黒が一手目を真ん中に打ったら隅に打つ
            if(koma[1][1].equals(kuro)){
               if (koma[0][0].equals(kara) ) {
                   //左上
                   koma[0][0] = siro;
                   disp();
                   return;
               }else if (koma[0][2].equals(kara) ) {
                   //右上
                   koma[0][2] = siro;
                   disp();
                   return;
               }else if (koma[2][0].equals(kara) ) {
                   //左下
                   koma[2][0] = siro;
                   disp();
                   return;
               }else if (koma[2][2].equals(kara) ) {
                   //右下
                   koma[2][2] = siro;
                   disp();
                   return;       
               }
           }
           
           //黒が辺に置いた場合
           if (koma[1][0].equals(kuro) || koma[0][1].equals(kuro) || koma[2][1].equals(kuro) || koma[1][2].equals(kuro)) {
               if(koma[1][1].equals(kara)){
               koma[1][1] = siro;
               disp();
               return;
               }
           }
           
           //黒白共にどこも揃っていなかったら辺を取りに行く
           if (koma[0][1].equals(kara)) {
               koma[0][1] = siro;
               disp();
               return;
               
           }else if(koma[1][0].equals(kara)){
               koma[1][0] = siro;
               disp();
               return;
               
           }else if(koma[1][2].equals(kara)){
               koma[1][2] = siro;
               disp();
               return;
               
           }else if(koma[2][1].equals(kara)){
               koma[2][1] = siro;
               disp();
               return;
           }
 
              
       }
   
   
  //黒の盤面を判定するやつ
   static void checkBlack(){
    
       for (int j = 0; j < 3; j++)
       {
           //縦に揃ってるかの判定 
           if (koma[0][j].equals(kuro) && koma[1][j].equals(kuro) && koma[2][j].equals(kuro)) {
               win = true;
               return;
           }
           
           //横に揃っているか判定
           else if (koma[j][0].equals(kuro) && koma[j][1].equals(kuro) && koma[j][2].equals(kuro)){
               win = true;
               return;
           } 
       }
       
           //クロスに揃っているか判定
           if (koma[0][0].equals(kuro) && koma[1][1].equals(kuro) && koma[2][2].equals(kuro)){
               win = true;
               return;
           }
           if (koma[0][2].equals(kuro) && koma[1][1].equals(kuro) && koma[2][0].equals(kuro)){
               win = true;
               return;
           }
    }
  
  //白の盤面を判定するやつ
   static void checkWhite(){
    
     
       for (int j = 0; j < 3; j++)
       {
           //縦に揃ってるかの判定 
           if (koma[0][j].equals(siro) && koma[1][j].equals(siro) && koma[2][j].equals(siro)) {
               win = true;
               return;
           }
           
           //横に揃っているか判定
            if (koma[j][0].equals(siro) && koma[j][1].equals(siro) && koma[j][2].equals(siro)){
               win = true;
               return;
           } 
       }   
   
       //クロスに揃っているか判定
           if (koma[0][0].equals(siro) && koma[1][1].equals(siro) && koma[2][2].equals(siro)){
               win = true;
               return;
           }
           if (koma[0][2].equals(siro) && koma[1][1].equals(siro) && koma[2][0].equals(siro)){
               win = true;
               return;
           }
   }
   
   static void checkdraw(){
        if (!koma[0][0].equals(kara) && !koma[0][1].equals(kara) && !koma[0][2].equals(kara) &&
            !koma[1][0].equals(kara) && !koma[1][1].equals(kara) && !koma[1][2].equals(kara) &&
            !koma[2][0].equals(kara) && !koma[2][1].equals(kara) && !koma[2][2].equals(kara) ) {
           draw = true;
       }
   }
   
}
