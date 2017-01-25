package migl.lisp;

public abstract class LispBoolean {

    public static final LispBoolean TRUE = new LispBoolean(true) {

        @Override
        public String toString() {
            return "#t";
        }

    };

    public static final LispBoolean FALSE = new LispBoolean(false) {

        @Override
        public String toString() {
            return "#f";
        }

    };

    private final boolean value;

    protected LispBoolean(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LispBoolean) {
            return ((LispBoolean) obj).value == value;
        }
        return false;
    }

    public static LispBoolean valueOf(String s) {
        switch (s.toLowerCase()) {
        case "#t":
            return TRUE;
        case "#f":
            return FALSE;
        default:
            throw new IllegalArgumentException();
        }
    }
}
