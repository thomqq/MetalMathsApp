package tq.arxsoft.metalmaths.parsers;

import tq.arxsoft.metalmaths.operation.Exercise;
import tq.arxsoft.metalmaths.operation.ExerciseType;
import tq.arxsoft.metalmaths.operation.FlashCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlashParser implements ExerciseParser{

    public List<? extends Exercise> parse(Map<String, List<String>> items, ExerciseType exerciseType) {
        List<FlashCard> flashCards = new ArrayList<>();
        for( String item : items.get("word") ) {
            String question = item;
            String answer = item;
            FlashCard flashCard = new FlashCard(question, answer);
            flashCards.add(flashCard);
        }
        return flashCards;
    }

}
