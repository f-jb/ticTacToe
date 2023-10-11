package se.kaiserbirch.model;

public class Square {
    private State state = State.BLANK;
    void setState(State state){
        this.state = state;
    }

    @Override
    public String toString() {
        return state.toString();
    }

}
