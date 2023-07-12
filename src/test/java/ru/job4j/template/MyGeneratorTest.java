package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.tdd.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class MyGeneratorTest {
    @Test
    public void whenMapArgsNumberIsEqualToStringArgsNumberThenCorrect() {
        Generator generator = new MyGenerator();
        String inputString = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "you");
        assertThat(generator.produce(inputString, args)).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }

    @Test
    public void whenMapArgsNumberIsGreaterThenStringArgsNumberThenGetException() {
        Generator generator = new MyGenerator();
        String inputString = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        args.put("subject", "you");
        args.put("price", "10");
        assertThatThrownBy(() -> generator.produce(inputString, args)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMapArgsNumberIsLessThenStringArgsNumberThenGetException() {
        Generator generator = new MyGenerator();
        String inputString = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr");
        assertThatThrownBy(() -> generator.produce(inputString, args)).
                isInstanceOf(IllegalArgumentException.class);
    }
}