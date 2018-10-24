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
            while (!(-128 <= decimalWert && decimalWert <= 127))
            {
                decimalWert = SimpleIO.getInt("Bitte geben Sie eine ganze Zahl zwischen -128 und 127 ein.");
            }
            
            String binaryString = DecToBin(decimalWert);
            SimpleIO.output(binaryString, "Success");            
        }

        else if (mode.equals("Binary"))
        {
            String binaryWert = "0";

            // Laengen Check
            while (binaryWert.length() != 8)
            {
                binaryWert = SimpleIO.getString("Bitte geben Sie eine ganze Zahl im 8-Bit -Zweierkomplement ein.");
            }

            // Binaer Check
            for(int i = 0; i < binaryWert.length(); i++)
            {
                if(binaryWert.charAt(i) != '0' && binaryWert.charAt(i) != '1')
                {
                    SimpleIO.output("Es kÃ¶nnen nur Einsen und Nullen eingegeben werden.","Error");
                    return;
                    
                }
            }
            
            int decimalWert = BinToDec(binaryWert);
            SimpleIO.output(decimalWert + "", "Success");
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
    

    public static int BinToDec (String binaryWert)
    {
    	int decimalWert = 0;
    	
    	boolean negativ = binaryWert.charAt(0) == '1';
    	
    	if(negativ)
    	{
    		String normBinary = removeSign(binaryWert);

            // Without sign, abs of 128 is possible
            // First sign is always only set for -128
    		
    		normBinary = zweierKomplement(binaryWert);

            System.out.println("BinToDec:" + binaryWert + " got turned into sec Komp. " + normBinary + " of length " + normBinary.length());
            binaryWert = normBinary;
    	}
        
        for(int i = 0; i < binaryWert.length(); i++)
        {
            if(binaryWert.charAt(i) == '1')
                decimalWert += pow(2, binaryWert.length()-i-1);
        }
        
        if(negativ)
            decimalWert *= -1;
        
        System.out.println("BinToDec:" + binaryWert + " got converted into decimal " + decimalWert + "");
        
        
        return decimalWert;
    }

    
    public static String DecToBin (int decimalWert)
    {
    	boolean negativ = decimalWert < 0;
        if (negativ) 
        	decimalWert = -decimalWert;
    	
    	String binaryString = "";
    	
    	int bits = 8;
        for (int i = bits-1; i >= 0; i--)
        {
            binaryString = binaryString + ((decimalWert / pow(2, i)) % 2);
        }
        
        // We now have an unsigned int, without sign but allows for (-)128

        System.out.println("DecToBin:" + decimalWert + " got turned into " + binaryString + " of length " + binaryString.length());
        
        if(negativ)
        {
        	binaryString = zweierKomplement(binaryString);
        	// We now have a 2er Komplement, still without sign
            
            binaryString = addSign(binaryString);
            // Now with proper sign, since -128 has been packed to 7 bits
            
            System.out.println("DecToBin:" + decimalWert + " got turned into sec Komp. " + binaryString + " of length " + binaryString.length());   
        }
         	
        return binaryString;
    }

    public static String zweierKomplement(String binaryString)
    {
    	// Sign-Bit wird gelöscht, um -128 möglich zu machen
    	// Invertiere erst alle Bits
    	// Addiere 1: Alle Bits bis zur ersten 0 von rechts werden NOCHMAL invertiert
    	
        String secKomplement = "";
        boolean added = false;
        for(int i = binaryString.length() - 1; i >= 0; i--)
        {
        	boolean one = binaryString.charAt(i) == '0'; // Flip
        	if(one) // 1
        	{ // Continue until we can add
        		secKomplement = (added? "1" : "0") + secKomplement;
        	}
            else // 0
            { // Add 1 if not yet added
            	secKomplement = (added? "0" : "1") + secKomplement;
            	added = true;
            }
        }
        
        return secKomplement;
    
    }
    
    public static String removeSign (String bin)
    {
    	return "0" + bin.substring(1);
    }
    public static String addSign (String bin)
    {
    	return "1" + bin.substring(1);
    }
}
