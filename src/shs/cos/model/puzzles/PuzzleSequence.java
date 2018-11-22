package shs.cos.model.puzzles;

import java.util.Arrays;

public class PuzzleSequence extends Puzzle{
    @Override
    public boolean attempt(String input) {
//        String[] attempt = input.split(",");
//        String[] answer = this.getAnswer().split(",");
//        for (int i = 0; i < attempt.length; i++) {
//            String[] one = attempt[i].split(" ");
//            String[] two = answer[i].split(" ");
//            if (!(Arrays.equals(one, two))) return false;
//        }
        return input.equals(this.getAnswer());
    }
}
