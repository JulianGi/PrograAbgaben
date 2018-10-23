
public class Main {
    public static void main(String[] args) {
        String mode = SimpleIO.getString("Bitte  waehlen  Sie aus:\n" +
                "Decimal: Dezimalzahl  ins  Zweierkomplement  konvertieren.\n" +
                "Binary: 8-Bit -Zweierkomplement  als  Dezimalzahl  darstellen.");

        if (mode.equals("Decimal"))
        {
            int decimalWert = SimpleIO.getInt("Bitte geben Sie eine ganze Zahl zwischen -128 und 127 ein.");

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
