package tq.arxsoft.metalmaths.operation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdditionTest {

    @Test
    void return_correct_formula_2_plus_2() {
        //given
        Addition addition = new Addition(2,2, ExerciseType.MathAnswer);

        //when
        String result = addition.getQeustion();
        String expected = "2 + 2";

        //then
        assertEquals(expected, result);

    }

    @Test
    void get_3_possible_answers() {
        //given
        Exercise addition = new Addition(2,2, ExerciseType.MathAnswer);

        //when
        List<String> answers = addition.getAnswerWithOnlyOneCorrect();

        //then
        int numberOfCorrectAnswer = 0;
        for( String number : answers ) {
            if( number.equals("4") ) {
                numberOfCorrectAnswer++;
            }
        }

        assertEquals(1, numberOfCorrectAnswer);

    }

}