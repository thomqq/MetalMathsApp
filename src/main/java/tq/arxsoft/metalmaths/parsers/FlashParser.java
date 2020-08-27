package tq.arxsoft.metalmaths.parsers;

import tq.arxsoft.metalmaths.operation.Exercise;
import tq.arxsoft.metalmaths.operation.ExerciseType;
import tq.arxsoft.metalmaths.operation.FlashCard;
import tq.arxsoft.metalmaths.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlashParser implements ExerciseParser{

    public List<? extends Exercise> parse(Map<String, List<String>> items, ExerciseType exerciseType) {
        List<FlashCard> flashCards = new ArrayList<>();
        for( String item : items.get("word") ) {

            String[] langAndWord = StringUtil.extractLanguage(item);

            String question = langAndWord[1];
            String answer = langAndWord[1];
            FlashCard flashCard = new FlashCard(question, answer, langAndWord[0]);
            flashCards.add(flashCard);
        }
        return flashCards;
    }

}
