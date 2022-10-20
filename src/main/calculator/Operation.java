package calculator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Vladislav Konovalov
 */
public enum Operation {
    ADD("+", 1) {
        @Override
        public double eval(double n1, double n2) {
            return n1 + n2;
        }
    },

    SUBTRACT("-", 1) {
        @Override
        public double eval(double n1, double n2) {
            return n1 - n2;
        }
    },

    MULTIPLY("*", 2) {
        @Override
        public double eval(double n1, double n2) {
            return n1 * n2;
        }
    },

    DIVISION("/", 2) {
        @Override
        public double eval(double n1, double n2) {
            return n1 / n2;
        }
    };

    private final String sign;
    private final int priority;
    private static final Map<String, Operation> OPERATION_MAP;

    static {
        Map<String, Operation> map = new HashMap<>();
        for (Operation operation : Operation.values()) {
            map.put(operation.getSign(), operation);
        }
        OPERATION_MAP = Collections.unmodifiableMap(map);
    }

    Operation(String sign, int priority) {
        this.sign = sign;
        this.priority = priority;
    }

    public String getSign() {
        return sign;
    }

    public int getPriority() {
        return priority;
    }

    public static Operation getBySign(String sign) {
        return OPERATION_MAP.get(sign);
    }

    public static Set<String> getAllSigns() {
        return OPERATION_MAP.keySet();
    }

    public static boolean isOperation(String sign) {
        return getBySign(sign) != null;
    }

    public abstract double eval(double n1, double n2);
}
