public class Main {
    public static void main(String[] args) {
        String mode = SimpleIO.getString("Bitte  waehlen  Sie aus:\n" +
                "Decimal: Dezimalzahl  ins  Zweierkomplement  konvertieren.\n" +
                "Binary: 8-Bit -Zweierkomplement  als  Dezimalzahl  darstellen.");

        if (mode.equals("Decimal"))
        {
            int decimalWert = 128;
            String binaryString = ""; 
            

            while (!(-128 < decimalWert && decimalWert < 127))
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
                //Flippen der bits
                String flippedString = "";
                for(int i = 0; i < binaryString.length(); i++)
                {
                    if(binaryString.charAt(i) == '1')
                    {
                        flippedString = flippedString + '0';
                    }
                    else
                    {
                        flippedString = flippedString + '1';
                    }
                }

                //Eins addieren
                String plusString = "";
                for(int i = flippedString.length() - 1; i > 0; i--)
                {
                    if(flippedString.charAt(i) == '1')
                    {
                        plusString ="0" + plusString ;
                    }
                    else
                    {
                        plusString = "1" + plusString ;
                        for(int x = i - 1; x >= 0; x--)
                        {
                            plusString = flippedString.charAt(x) + plusString;
                        }
                        break;
                    }
                }
                binaryString = plusString;
            }
            SimpleIO.output(binaryString, "Success");

            
        }

        else if (mode.equals("Binary"))
        {
            String binaryWert = SimpleIO.getString("Bitte geben Sie eine ganze Zahl im 8-Bit -Zweierkomplement ein.");
            if (binaryWert.length() != 8)
            {
                
            }
        }
        else{
            SimpleIO.output("Bitte entweder Decimal oder Binary eingeben.","Error");
        }
    }
}
