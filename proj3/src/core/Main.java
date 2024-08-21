package core;
import tileengine.TETile;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // build your own worlpublic String getInput(){
        String input = Main.getString();
        TETile[][] tet = AutograderBuddy.getWorldFromInput(input);
    }
    private static String getString(){
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }
}
