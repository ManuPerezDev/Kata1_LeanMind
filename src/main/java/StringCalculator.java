import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class StringCalculator {
    public StringCalculator() {
    }

    public String add(String text) {

        if (text.isEmpty()) {
            return "0";
        }

        boolean containCommaOrNewLine = (text.contains(",") || text.contains("\n"));
        if (!containCommaOrNewLine) {
            int result = parseInt(text);
            return String.valueOf(result);
        }

        String personalizedDelimiter = "";
        String formattedText = "";
        String[] arrayNumbers;

        if (text.substring(0, 2).equals("//")) {
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '\n') {
                    personalizedDelimiter = text.substring(2, i);
                    formattedText = text.substring(i + 1, text.length());

                    break;
                }
            }

            if (formattedText.contains(",")) {
                int notPermittedInputIndex = 0;
                for (int i = 0; i < text.length(); i++) {
                    if (formattedText.substring(i, i + 1).equals(",")) {
                        notPermittedInputIndex = i;
                        break;
                    }
                }
                return "'" + personalizedDelimiter + "' expected but ',' found at position " + notPermittedInputIndex;
            }
            arrayNumbers = formattedText.split(personalizedDelimiter);


        } else {
            arrayNumbers = text.split(",|\n");
        }

        if (text.contains("-")) {
            String notAllowedValues = " ";
            for (int i = 0; i < arrayNumbers.length; i++) {
                if (parseInt(arrayNumbers[i]) < 0) {

                    notAllowedValues = notAllowedValues + arrayNumbers[i] + ", ";
                }
            }

            notAllowedValues = notAllowedValues.substring(0, notAllowedValues.length() - 2);

            return "Negative not allowed :" + notAllowedValues;
        }


        if (text.charAt(text.length() - 1) == ',') {
            return "Number expected but EOF found";
        }


        if (text.contains(",\n")) {

            int notPermittedInputIndex = 0;
            for (int i = 0; i < text.length(); i++) {
                if (text.substring(i, i + 2).equals(",\n")) {
                    notPermittedInputIndex = i + 1;
                    break;
                }
            }
            return "Number expected but '\\n' found at position " + notPermittedInputIndex;
        }

        return String.valueOf((int) sumMultipleNumbers(arrayNumbers, arrayNumbers.length));

    }

    private double sumMultipleNumbers(String[] arrayNumbers, int lenght) {
        if (lenght <= 0) {
            return 0;
        }

        return (sumMultipleNumbers(arrayNumbers, lenght - 1) + Double.parseDouble(arrayNumbers[lenght - 1]));
    }
}
