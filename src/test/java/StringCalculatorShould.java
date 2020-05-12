import org.junit.Ignore;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorShould {
    /**
     * http://codingdojo.org/kata/StringCalculator/
     *
     * To do list
     * 1º "" -> "0"
     * 2º "1" -> "1"
     * 3º "3,2" -> "5"
     * 4º "1\n2,3" -> "6"
     * 5º "175.2,\n35" -> "Number expected but '\n' found at position 6"
     * 6º "1,3," -> "Number expected but EOF found"
     * 7º "//;\n1;2" -> "3"
     * "//|\n1|2|3" should return "6"
     * "//sep\n2sep3" should return "5"
     * "//|\n1|2,3" is invalid and should return the message "'|' expected but ',' found at position 3."
     * 8º "-1,2" is invalid and should return the message "Negative not allowed : -1"
     *  "2,-4,-5" is invalid and should return the message "Negative not allowed : -4, -5"
     */

    @Test
    public void return_0_if_string_empty() {
        StringCalculator calculator = new StringCalculator();

        assertThat(calculator.add("")).isEqualTo("0");
    }

    @Test
    public void return_same_value_if_found_one_value_in_string(){
        StringCalculator calculator = new StringCalculator();

        assertThat(calculator.add("1")).isEqualTo("1");
        assertThat(calculator.add("7")).isEqualTo("7");
        assertThat(calculator.add("44")).isEqualTo("44");
    }

    @Test
    public void return_sum_if_more_than_two_numbers(){
        StringCalculator calculator = new StringCalculator();

        assertThat(calculator.add("1,3")).isEqualTo("4");
        assertThat(calculator.add("1,3,2")).isEqualTo("6");
    }

    @Test
    public void add_new_line_as_new_separator(){
        StringCalculator calculator = new StringCalculator();

        assertThat(calculator.add("1\n2,3")).isEqualTo("6");
    }
    @Test
    public void return_message_when_expects_number(){
        StringCalculator calculator = new StringCalculator();

        assertThat(calculator.add("175.2,\n35")).isEqualTo("Number expected but '\\n' found at position 6");
        assertThat(calculator.add("175.2,35,\n3")).isEqualTo("Number expected but '\\n' found at position 9");
    }

    @Test
    public void return_message_when_last_char_is_a_delimiter(){
        StringCalculator calculator = new StringCalculator();

        assertThat(calculator.add("1,3,")).isEqualTo("Number expected but EOF found");
    }

    @Test
    public void return_result_with_personalized_delimiters(){
        StringCalculator calculator = new StringCalculator();

        assertThat(calculator.add("//;\n1;2")).isEqualTo("3");
        assertThat(calculator.add("//sep\n2sep3")).isEqualTo("5");
        assertThat(calculator.add("//#\n1#2,3")).isEqualTo("'#' expected but ',' found at position 3");

    }

    @Test
    public void return_negative_values_not_allowed(){
        StringCalculator calculator = new StringCalculator();

        assertThat(calculator.add("-1,2")).isEqualTo("Negative not allowed : -1");
        assertThat(calculator.add("2,-4,-5")).isEqualTo("Negative not allowed : -4, -5");
    }
}
