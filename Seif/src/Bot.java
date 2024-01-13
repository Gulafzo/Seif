import DataModel.TurnResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Bot {
    private List<Number[]> _variants;
    private Number[] _currentTurn;

    public Bot(){
        _variants = new ArrayList<>();
        _currentTurn = null;
    }


    public void InitGameStart() {
        for (int digit = 123; digit <= 9999; digit++) {//начила цикл со 123, чтобы избежать чисел с начальными нулями не знаю на сколько правильно
            int digit1 = digit / 1000;
            int digit2 = (digit / 100) % 10;
            int digit3 = (digit / 10) % 10;
            int digit4 = digit % 10;

            //условие для уникальные число
            if (digit1 != digit2 && digit1 != digit3 && digit1 != digit4 &&
                    digit2 != digit3 && digit2 != digit4 && digit3 != digit4) {
                Number[] variant = new Number[]{digit1, digit2, digit3, digit4};
                _variants.add(variant);
            }
        }
    }

    public String getTurn() {
        Random generator = new Random();
        int turnIndex = generator.nextInt(_variants.size());
        _currentTurn = _variants.get(turnIndex);

        return Utilities.TurnArrayToString(_currentTurn);
    }

    public void setTurnResult(TurnResult result) {
        //_variants = _variants.stream().filter(x->Utilites.getRightCount(_currentTurn, x) == result.CorrectDigits).toList();
       // _variants = _variants.stream().filter(x->Utilites.getInPlaceCount(_currentTurn, x) == result.DigitsInPlace).toList();

       // можно ли  объединить эти условия в одно и применить фильтрацию только один раз?

        // но это не делает бота быстрым
       //
        _variants = _variants.stream().filter(x ->
                Utilities.getRightCount(_currentTurn, x) == result.CorrectDigits
                        && Utilities.getInPlaceCount(_currentTurn, x) == result.DigitsInPlace).toList();
    }
}
