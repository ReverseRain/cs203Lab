package DSAA_Lab.fall_lab0;

import java.util.Scanner;

public class Mahhjong {
    static Boolean hu = false;
    static String[][] dictionary = {{"1w", "2w", "3w", "4w", "5w", "6w", "7w", "8w", "9w",},
            {"1b", "2b", "3b", "4b", "5b", "6b", "7b", "8b", "9b"}, {"1s", "2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s"},
            {"1z", "2z", "3z", "4z", "5z", "6z", "7z"}};
    static int[][] split = {{-2, -1}, {-1, 1}, {1, 2}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            String str = sc.next();
            String[] tiles = new String[14];
            int[][] barrel = new int[4][9];
            for (int j = 0; j < 14; j++) {
                tiles[j] = str.substring(2 * j, 2 * j + 2);
                for (int k = 0; k < dictionary.length; k++) {
                    Boolean ha = false;
                    for (int l = 0; l < dictionary[k].length; l++) {
                        if (dictionary[k][l].equals(tiles[j])) {
                            barrel[k][l]++;
                            ha = true;
                            break;
                        }
                    }
                    if (ha) {
                        break;
                    }
                }
            }

            search(barrel, 0);
            if (hu) {
                System.out.println("Blessing of Heaven");
            } else {
                System.out.println("Bad luck");
            }
            hu = false;
        }
    }

    static void search(int[][] barrel, int time) {
        if (time == 4) {
            for (int i = 0; i < barrel.length; i++) {
                for (int j = 0; j < barrel[0].length; j++) {
                    if (barrel[i][j] == 2) {
                        hu = true;
                    }
                }
            }
        } else {
            for (int i = 0; i < barrel.length - 1; i++) {
                for (int j = 0; j < barrel[0].length; j++) {
                    if (barrel[i][j] != 0) {
                        for (int k = 0; k < split.length; k++) {
                            if (j + split[k][0] >= 0 && j + split[k][1] < barrel[0].length
                                    && barrel[i][j + split[k][0]] > 0 && barrel[i][j + split[k][1]] > 0) {
                                barrel[i][j + split[k][0]]--;
                                barrel[i][j + split[k][1]]--;
                                barrel[i][j]--;
                                time++;
                                search(barrel, time);
                                barrel[i][j + split[k][0]]++;
                                barrel[i][j + split[k][1]]++;
                                barrel[i][j]++;
                                time--;
                            }
                        }
                    }
                    if (barrel[i][j] >= 3) {
                        barrel[i][j] -= 3;
                        time++;
                        search(barrel, time);
                        barrel[i][j] += 3;
                        time--;
                    }
                }
            }
            for (int i = 0; i < barrel[3].length; i++) {
                if (barrel[3][i] >= 3) {
                    barrel[3][i] -= 3;
                    time++;
                    search(barrel, time);
                    barrel[3][i] += 3;
                    time--;
                }
            }
        }
    }
}
