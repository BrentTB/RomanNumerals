import java.util.HashMap;
import javax.swing.JOptionPane;

public class RomanNumerals {

    public static void main(String[] args) {
            HashMap change = new HashMap();
            
            change.put("I", 1);
            change.put(""+1,"I");
            
            String addOn="";
            for (int i = 0; i < 7; i++) {//the max number is  1000^(max num)
            long mult = (long) Math.pow(1000, i);
                
            change.put(addOn+'V', ""+mult*5);//adding a hashmap key for all the main Roman Numerals 
            change.put(addOn+'X', ""+mult*10);//and adding the capability to go to the max long value
            change.put(addOn+'L', ""+mult*50);
            change.put(addOn+'C', ""+mult*100);
            change.put(addOn+'D', ""+mult*500);
            change.put(addOn+'M', ""+mult*1000);
            change.put(""+mult*5, addOn+'V');
            change.put(""+mult*10, addOn+'X');
            change.put(""+mult*50, addOn+'L');
            change.put(""+mult*100, addOn+'C');
            change.put(""+mult*500, addOn+'D');
            change.put(""+mult*1000, addOn+'M');
                    addOn+="_";
        }

              String help="";  
        while(!help.equals("R") && !help.equals("N")){
        
         help = JOptionPane.showInputDialog("Convert to or from Roman Numerals?\n\nr - Roman Numerals to normal\nn - normal to Roman Numerals").toUpperCase();
        }
        char convert = help.charAt(0);
        
        
        
        if(convert == 'R'){//roman to normal
            String roman = JOptionPane.showInputDialog("Enter the Roman Numerals\nWrite 5 as V, 5 000 as _V, 5 000 000 as __V, etc\nadding _ before a symbol represents that number *1000").toUpperCase();
            long[] number= new long[roman.length()];
          
            long total=0;
            int length=roman.length();
            int displaced=0;
            for (int i = 0; i < length; i++) {
                int substringHelp=0;
                while(roman.charAt(i+displaced)=='_'){//counts however many *1000's are next
                    
                length--;//as you aren't going to check these in the hashmap, reduce the amount of times looped
                displaced++;//used to get to the right place in the String 
                    substringHelp--;//for each _, the substring will have to start 1 letter sooner
                    
                }
                
                number[i]=Long.parseLong(change.get(roman.substring(i+displaced+substringHelp,i+displaced+1)+"")+"");//get the value of the Roman Numeral
                                                                                                                     //including all _'s
            }
            
            for (int i = 0; i < roman.length()-1; i++) {//-1 as we check for one ahead in the code
              
                
                if(number[i]<number[i+1]){//if the number is less, minus them (such as IV, I is subtracted from V)
                total += number[i+1]-number[i];
                    i++;//done as two numbers are being used, not one
                }else{//if the number after this one is greater or the same, add it(such as XI, I is added to X)
                total+=number[i];
                }}
               
                
            
            System.out.println(total);//the final product
        }else{//normal to roman
            String type = JOptionPane.showInputDialog("Enter the number you want to convert.\neg: 21794 \nEnter a letter followed by a number for all numbers from 1 to that number\n"
                    + "eg: a21794\nEnter two letters followed by two numbers seporated by a space for all numbers from the first number to the second\neg: aa2000 2018");
            long start=1, end;
            if(!Character.isDigit(type.charAt(0))){//if the user wants to convert multiple numbers
                
                if(!Character.isDigit(type.charAt(1))){//if the start number is not 1
                
                    String[] nums = type.substring(2).split(" ");
                    start = Long.parseLong(nums[0]);//setting the start and end number for the loop
                    end = Long.parseLong(nums[1]);
                }else{
                
            end = Long.parseLong(type.substring(1));
                }
                
                
                
            }else{//only asking to find one numeral
            end = Long.parseLong(type);
            start = end;
            }
            
            for (long normal = start; normal <= end; normal++) {//for every number between the start and end
            String roman="";
            int[] number= new int[(normal+"").length()];
            
            
                String help2 =normal+"";
            for (int i = (normal+"").length()-1; i >=0 ; i--) {
                number[i]=Integer.parseInt( (help2).charAt(i)+"");//this digit with no multiplyer (5000 saved as 5)
            }
            
              for (int i = 0; i < (normal+"").length(); i++) {
                  
                  
                  
                  //TODO: optimise this code
                  
                  
                long mult = (long) Math.pow(10, (normal+"").length()-1-i);//each digit is multiplied by 10 more than the last, to get back the zero's lost earlier
              
                
                
                if(number[i]==4 || number[i] == 9){//convert the full number into Roman Numerals
                    roman += change.get(1 * mult + "") + "" + change.get((number[i]+1) * mult + "");
                    }else{
                        int repeat = number[i]+(number[i]>4? -5: 0);
                        roman+= number[i]>4 ? change.get(5 * mult+""):"";
                            for (int z = 0; z < repeat; z++) {
                                roman += change.get(1 * mult + "") + "";
                            }
                    }
              
                
            }

            while (roman.length()<60){//to make the results line up neatly
            roman+=" ";
            
            }
            System.out.println(roman +normal);
            
         
        }
            if(end>3999){//explanation message for if any _'s have been used
                System.out.println("");
                System.out.println("_ represents the following symbol * 1 000\neg: _V = 5 000\neg:__V = 5 000 000");
            }
    
    }
        
    }
    
}
