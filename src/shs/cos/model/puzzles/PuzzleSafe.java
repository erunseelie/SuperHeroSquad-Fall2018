package shs.cos.model.puzzles;

public class PuzzleSafe extends Puzzle{
    @Override
    public boolean attempt(String input) {
        if (input.equals(this.getAnswer())) {
            this.setCounter(0);
            return true;
        } else {
            this.setCounter(this.getCounter() + 1);
            return false;
        }
    }
}
