import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class StringCalculator {
    public StringCalculator() {
    }

    public String add(String text) {
        //Test 1
        if (text.isEmpty()) {
            return "0";
        }

        return sumNumbers(text);
    }

    private String sumNumbers(String text) {
        //Test 2
        boolean containCommaOrNewLine = (text.contains(",") || text.contains("\n"));
        if (!containCommaOrNewLine) {
            return text;
        }

        String personalizedDelimiter = "";
        String formattedText = "";
        String[] arrayNumbers;

        //Test 7
        if (text.substring(0, 2).equals("//")) {
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '\n') {
                    personalizedDelimiter = text.substring(2, i);
                    formattedText = text.substring(i + 1, text.length());

                    break;
                }
            }

            if (formattedText.contains(",")) {
                return checkNotPermittedDelimiter(formattedText, personalizedDelimiter);
            }
            arrayNumbers = formattedText.split(personalizedDelimiter);
        } else {
            //Test 4
            arrayNumbers = text.split(",|\n");
        }

        //Test 8
        if (text.contains("-")) {
            return containsNegativeNumbers(arrayNumbers);
        }

        //Test 6
        if (text.charAt(text.length() - 1) == ',') {
            return "Number expected but EOF found";
        }

        //Test 5
        if (text.contains(",\n")) {
            return checkNotPermittedNewLine(text);
        }

        //Test 3
        return String.valueOf((int) sumMultipleNumbers(arrayNumbers, arrayNumbers.length));
    }

    private String checkNotPermittedNewLine(String text) {
        int notPermittedInputIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.substring(i, i + 2).equals(",\n")) {
                notPermittedInputIndex = i + 1;
                break;
            }
        }
        return "Number expected but '\\n' found at position " + notPermittedInputIndex;
    }

    private String containsNegativeNumbers(String[] arrayNumbers) {
        String notAllowedValues = " ";
        for (int i = 0; i < arrayNumbers.length; i++) {
            if (parseInt(arrayNumbers[i]) < 0) {
                notAllowedValues = notAllowedValues + arrayNumbers[i] + ", ";
            }
        }

        notAllowedValues = notAllowedValues.substring(0, notAllowedValues.length() - 2);

        return "Negative not allowed :" + notAllowedValues;
    }

    private String checkNotPermittedDelimiter(String formattedText, String personalizedDelimiter) {
        int notPermittedInputIndex = 0;
        for (int i = 0; i < formattedText.length(); i++) {
            if (formattedText.substring(i, i + 1).equals(",")) {
                notPermittedInputIndex = i;
                break;
            }
        }
        return "'" + personalizedDelimiter + "' expected but ',' found at position " + notPermittedInputIndex;
    }

    private double sumMultipleNumbers(String[] arrayNumbers, int lenght) {
        if (lenght <= 0) {
            return 0;
        }

        return (sumMultipleNumbers(arrayNumbers, lenght - 1) + Double.parseDouble(arrayNumbers[lenght - 1]));
    }
}
