package ua.goit.main;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(MockitoExtension.class)
class ActionListTest {
    private final HashMap<String, Integer> actionMap = new HashMap<String, Integer>() {{
        put("A", 3);
        put("C", 6);
    }};

    private final ActionList actionList = new ActionList(actionMap);

    @Mock
    // actually we don't mock here anything
    //you should mock those two objects
    // InMemoryStorage storageWithoutAction
    // and
    // InMemoryStorage storageWithAction
    private ActionList actionList2;

    @Test
    void testCalculateTotalCostValueNullReturnThrowException() {
        String argument = null;
        assertThrows(IllegalArgumentException.class, () -> actionList.calculateTotalCost(argument));
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0} expected={1}")
    @MethodSource("dataProvider")
    void testCalculateTotalCostShouldReturnValuesWithAction(String input,
                                                  BigDecimal expected) {
        BigDecimal result = actionList.calculateTotalCost(input);
        assertEquals(expected, result);
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments(" ABCCCCCCDAkhggBA", new BigDecimal("17.25")),
                arguments(" ABCDAkhggBA", new BigDecimal("13.25")),
                arguments(" ABCDABADABBCCCA", new BigDecimal("28.00"))

        );
    }

    @ParameterizedTest(name = "#{index} - Run test with args={0} expected={1}")
    @MethodSource("dataProvider1")
    void testCalculateTotalCostShouldReturnValuesWithoutAction(String input,
                                                               BigDecimal expected) {
        BigDecimal result = actionList.calculateTotalCost(input);
        assertEquals(expected, result);
    }

    static Stream<Arguments> dataProvider1() {
        return Stream.of(
                arguments(" ABCD", new BigDecimal("7.25")),
                arguments(" jbjb9797=-/..", new BigDecimal("0.00")),
                arguments(" ABCDA", new BigDecimal("8.50"))
        );
    }

    @Test
    void testCalculateTotalCostShouldReturnTrue(){
        actionList2 = new ActionList(actionMap);
        BigDecimal output = actionList2.calculateTotalCost(" ABCD");
        boolean result = Objects.equals(output, new BigDecimal("7.25"));
        assertTrue(result);
        assertEquals(new BigDecimal("7.25"), output);
    }
}
