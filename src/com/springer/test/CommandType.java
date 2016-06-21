package com.springer.test;

/**
 * Created by bijal on 21/06/2016.
 */
public enum CommandType {

    C("C"){
        @Override
        public String toString() {
            return "CREATE";
        }
    },
    L("L"){
        @Override
        public String toString() {
            return "LINE";
        }
    },
    R("R"){
        @Override
        public String toString() {
            return "RECTANGLE";
        }
    },

    B("B") {
        @Override
        public String toString() {
            return "BUCKET_FILL";
        }
    },
    Q("Q"){
        @Override
        public String toString() {
            return "QUIT";
        }
    },
    ;

    private final String mType;

    private CommandType(String s) {
        this.mType = s;
    }

    public String toString() {
        return this.name();
    }
}
