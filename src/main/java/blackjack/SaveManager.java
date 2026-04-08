package blackjack;
import java.io.*;

public class SaveManager {
      private static final String SAVE_FILE = "save.txt";

      public static void save(Player player) {
          try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE))) {
              writer.write(String.valueOf(player.getChips()));
              writer.newLine();
              writer.write(String.valueOf(player.getWins()));
              writer.newLine();
              writer.write(String.valueOf(player.getLosses()));
          } catch (IOException e) {
              System.out.println("Save failed: " + e.getMessage());
  }
}

      public static int[] load() {
          try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
              int chips = Integer.parseInt(reader.readLine());
              int wins = Integer.parseInt(reader.readLine());
              int losses = Integer.parseInt(reader.readLine());
              return new int[]{chips, wins, losses};
          } catch (IOException | NumberFormatException e) {
              System.out.println("Load failed: " + e.getMessage());
              return new int[]{500, 0, 0};
          }
  }
}
