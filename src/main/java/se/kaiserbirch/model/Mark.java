package se.kaiserbirch.model;

public enum Mark {
    CIRCLE('O'),
    CROSS('X'),
    BLANK(' ');
    private final char mark;
    Mark(char initMark){
        this.mark = initMark;
    }

    public boolean isMarked(){
        return this != BLANK;
    }
    public char getMark(){
        return this.mark;
    }

    @Override
    public String toString() {
        return String.valueOf(mark);
    }
}
