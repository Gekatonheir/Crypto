import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CryptoStart {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println(" Для шифрования введите 1. Для расшифровки введите 2. Для взлома грубой силой введите 3.");
        int choice = scanner1.nextInt();
        if (choice == 1) {
            //начало шифрования
            System.out.println("Начинаем шифрование: введите путь к файлу.");
            try {
                Scanner scanner = new Scanner(System.in);
                String filesName = scanner.nextLine();
                int numOfTxt = filesName.indexOf(".txt");            //получаем индекс расширения файла в его пути
                StringBuffer strBuffer = new StringBuffer(filesName);
                strBuffer.insert(numOfTxt, "Copy");              //создаем имя нового файла на основе старого, помещая новую часть перед расширением
                String filesNameCopy = strBuffer.toString();         //кастуем обратно в строку
                Path pathFileName = Path.of(filesName);             // получаем объект path исходного файла
                Path pathFileNameCopy = Path.of(filesNameCopy);     // получаем объект path зашифрованного файла
                System.out.println("Введите ключ в виде целого числа.");
                int key = scanner1.nextInt();
                String text = Files.readString(pathFileName);       // получаем исходный файл в виде строки
                StringBuffer tempText = new StringBuffer("");
                for (int i = 0; i < text.length(); i++) {               // в цикле смещаем каждый символ в строке на указанный ключ
                    tempText.append(confuse(text.charAt(i), key));      // и создаем новую зашифрованную строку
                }
                Files.createFile(pathFileNameCopy);                       // создаем новый файл для зашифрованного текста
                Files.writeString(pathFileNameCopy, tempText);          // записываем строку в файл
                scanner.close();
                System.out.println("Файл успешно зашифрован!");
            } catch (IOException e) {
                System.out.println("Файл не найден, попробуйте ещё раз!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Неверный формат ввода, попробуйте ещё раз!");
            }
        } else if (choice == 2) {
            System.out.println("Начинаем расшифровку: введите путь к файлу.");
            try {
                Scanner scanner = new Scanner(System.in);
                String filesName2 = scanner.nextLine();
                int numOfTxt = filesName2.indexOf(".txt");
                StringBuffer strBuffer = new StringBuffer(filesName2);
                strBuffer.insert(numOfTxt, "OriginalByKey");
                String filesNameOriginal = strBuffer.toString();
                Path pathFileName2 = Path.of(filesName2);
                Path pathFileNameOriginal = Path.of(filesNameOriginal);
                System.out.println("Введите ключ в виде целого числа.");
                int key = scanner1.nextInt();
                String text = Files.readString(pathFileName2);
                StringBuffer tempText = new StringBuffer("");
                for (int i = 0; i < text.length(); i++) {
                    tempText.append(backToBegin(text.charAt(i), key));
                }
                Files.createFile(pathFileNameOriginal);
                Files.writeString(pathFileNameOriginal, tempText);
                scanner.close();
                System.out.println("Файл успешно расшифрован!");
            } catch (IOException e) {
                System.out.println("Файл не найден, попробуйте ещё раз!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Неверный формат ввода, попробуйте ещё раз!");
            }
        } else if (choice == 3) {
            System.out.println("Работа в режиме brute force- друга Цезаря, который предал его, используя грубую силу. Введите путь к файлу.");
            try {
                Scanner scanner = new Scanner(System.in);
                String filesName3 = scanner.nextLine();
                int numOfTxt = filesName3.indexOf(".txt");
                StringBuffer strBuffer = new StringBuffer(filesName3);
                strBuffer.insert(numOfTxt, "OriginalByBruteForce");
                String filesNameOriginalByBruteForce = strBuffer.toString();
                Path pathFileName3 = Path.of(filesName3);
                Path pathFileNameOriginalByBruteForce = Path.of(filesNameOriginalByBruteForce);
                String text = Files.readString(pathFileName3);
                StringBuffer tempText = new StringBuffer("");
                for (int i = 0; i < exodus.length(); ++i) {
                    for (int j = 0; j < text.length(); ++j) {
                        tempText.append(backToBegin(text.charAt(j), i));
                    }
                    if (tempText.toString().contains(". ") && tempText.toString().contains(", ") && !tempText.toString().contains(":.") && !tempText.toString().contains(" ,") && !tempText.toString().contains(" ."))
                        break;
                    else
                        tempText = new StringBuffer("");
                }
                Files.createFile(pathFileNameOriginalByBruteForce);
                Files.writeString(pathFileNameOriginalByBruteForce, tempText);
                scanner.close();
                System.out.println("Файл успешно расшифрован c помощью грубой силы!");
            } catch (IOException e) {
                System.out.println("Файл не найден, попробуйте ещё раз!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Неверный формат ввода, попробуйте ещё раз!");
            }
        }
    }

    private static final String exodus = " АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя!?,-:\".";

    public static char confuse(char symbol, int key) {
        if (exodus.indexOf(symbol) != -1) {
            return exodus.charAt((exodus.indexOf(symbol) + key) % exodus.length());
        } else {
            return symbol;
        }
    }

    public static char backToBegin(char symbol, int key) {
        if (exodus.indexOf(symbol) != -1) {
            return exodus.charAt((exodus.indexOf(symbol) + (exodus.length() - (key % exodus.length()))) % exodus.length());
        } else {
            return symbol;
        }
    }
}