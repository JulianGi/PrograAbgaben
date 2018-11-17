public class Main {

    
    public static void main(String[] args) {
        String mode = "";
        while(!(mode.equals("Decimal") || mode.equals("Binary")))
        {
            mode = SimpleIO.getString("Bitte  waehlen  Sie aus:\n" +
                "Decimal: Dezimalzahl  ins  Zweierkomplement  konvertieren.\n" +
                "Binary: 8-Bit -Zweierkomplement  als  Dezimalzahl  darstellen.");
        }
        if (mode.equals("Decimal"))
        {
            int decimalWert = 128;
            String binaryString = ""; 
            

            while (!(-128 <= decimalWert && decimalWert <= 127))
            {
                decimalWert = SimpleIO.getInt("Bitte geben Sie eine ganze Zahl zwischen -128 und 127 ein.");
            }
            boolean negativ = decimalWert < 0;

            while(decimalWert != 0)
            {
                if(decimalWert % 2 == 0)
                {
                    binaryString = "0" + binaryString;
                }
                else
                {
                    binaryString = "1" + binaryString;
                }
                decimalWert /= 2;
            }
            while(binaryString.length() != 8)
            {
                binaryString = "0" + binaryString;
            }
            

            if(negativ)
            {
                binaryString = zweierKomplement(binaryString);
            }
            SimpleIO.output(binaryString, "Success");

            
        }

        else if (mode.equals("Binary"))
        {
            String binaryWert = "0";
            int decimalWert = 0;
            //Längen Check
            while (binaryWert.length() != 8)
            {
                binaryWert = SimpleIO.getString("Bitte geben Sie eine ganze Zahl im 8-Bit -Zweierkomplement ein.");
            }

            //Binär Check
            for(int i = 0; i < binaryWert.length(); i++)
            {
                if(binaryWert.charAt(i) != '0' && binaryWert.charAt(i) != '1')
                {
                    SimpleIO.output("Es können nur Einsen und Nullen eingegeben werden.","Error");
                    return;
                    
                }
            }
            

            boolean negativ = binaryWert.charAt(0) == '1';
            if(negativ)
            {
                binaryWert = zweierKomplement(binaryWert);
            }

            int counter = binaryWert.length();
            for(int i = 0; i < binaryWert.length(); i++)
            {
                counter--;
                if(binaryWert.charAt(i) == '1')
                {
                    decimalWert += pow(2,counter);
                }
                
            }
            
            if(negativ)
            {
                decimalWert *= -1;
            }
            
            String result = "" + decimalWert;

            SimpleIO.output(result,"Success");

        }
        

    }
    public static int pow(int basis, int exponent)
    {
        if(exponent == 0)
        {
            return 1;
        }

        int temp = basis;
        for(int i = 0; i < exponent - 1; i++)
        {
            temp *= basis;
        }
        return temp;
    }

    public static String zweierKomplement(String bString)
    {
        //Flippen der bits
        String flippedString = "";
        for(int i = 0; i < bString.length(); i++)
        {
            if(bString.charAt(i) == '1')
            {
                flippedString = flippedString + '0';
            }
            else
            {
                flippedString = flippedString + '1';
            }
        }

        //Eins addieren
        return(binärAddieren(flippedString, "00000001"));
        
    
    }
    public static String binärAddieren(String a, String b)
    {   
        
        String result = "";
        boolean carry = false;
        for(int i = a.length() - 1; i>=0; i--)
        {
            int x = a.charAt(i);
            int y = b.charAt(i);
            if(x+y == 98)
            {
                if (carry)
                {
                    result = "1" + result;
                }
                else
                {
                    result = "0" + result;
                    carry = true;
                }
                
                
            }
            else if(x+y == 97)
            {
                if (carry)
                {
                    result = "0" +result;
                }
                else
                {
                    result = "1" +result;
                }
            }
            else if(x+y == 96)
            {
                if (carry)
                {
                    result = "1" +result;
                    carry = false;
                }
                else
                {
                    result = "0" +result;
                }
            }
        }



        return result;
    }
   
}
