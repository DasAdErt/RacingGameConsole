import java.util.Random;
import java.util.Scanner;

public class RacingGame {

    public static void main(String[] args) throws InterruptedException {

        // задаем параметры игры
        int trackLength = 20;
        int refreshRate = 1000; // время в миллисекундах между обновлениями игры
        int trackSeparatorLength = trackLength - 4; // длина разделителя между трассами

        // получаем количество автомобилей от пользователя
        Scanner scanner = new Scanner(System.in);
        int carCount = 0;
        while (carCount < 2 || carCount > 5) {
            System.out.println("How many cars would you like to race (2-5)?");
            carCount = scanner.nextInt();
        }

        // создаем массивы для хранения позиций и символов автомобилей
        int[] carPositions = new int[carCount];
        char[] carSymbols = {'A', 'B', 'C', 'D', 'E'};

        // выводим инструкцию для пользователя
        System.out.println("Welcome to Racing Game!");

        // выводим первый разделитель трасс
        for (int i = 0; i < trackSeparatorLength; i++) {
            System.out.print("=");
        }
        System.out.println();

        // запускаем гонку
        while (true) {

            // очищаем консоль перед каждым выводом
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // перемещаем каждый автомобиль на случайное расстояние от 1 до 3
            Random random = new Random();
            for (int i = 0; i < carCount; i++) {
                carPositions[i] += random.nextInt(3) + 1;

                // проверяем, достиг ли автомобиль финишной линии
                if (carPositions[i] >= trackLength) {
                    System.out.println("Car " + carSymbols[i] + " wins!");
                    return;
                }
            }

            // выводим текущее состояние гонки
            for (int i = 0; i < carCount; i++) {
                for (int j = 0; j < trackLength; j++) {
                    if (j == carPositions[i]) {
                        System.out.print(carSymbols[i]);
                    } else {
                        System.out.print("-");
                    }
                }
                System.out.println();
            }

            // выводим разделитель трасс
            for (int i = 0; i < trackSeparatorLength; i++) {
                System.out.print("=");
            }
            System.out.println();

            // ждем некоторое время перед каждым обновлением
            Thread.sleep(refreshRate);
        }
    }
}