package com.github.bitfexl.tetris.game;

import java.util.Scanner;

public class GameRunner {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);

        System.out.println("Tetris ai by bitfexl");
        System.out.println("1) let ai play");
        System.out.println("2) simulate ai");
        System.out.println("3) play yourself");
        System.out.print("Enter option: ");

        String cmd = stdin.nextLine();

        if(cmd.equals("1")) {
            AiGame.main(new String[0]);
        } else if(cmd.equals("2")) {
            System.out.println("be patient");
            AiGame.main(new String[] {"simulate"});
        } else if(cmd.equals("3")) {
            System.out.println("arrow keys... move/rotate");
            System.out.println("space bar... hard drop");
            System.out.println("\"C\"... hold");
            PlayerGame.main(null);
        } else{
            System.out.println("Not a valid option: " + cmd);
        }
    }
}
