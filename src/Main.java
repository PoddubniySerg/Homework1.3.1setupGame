import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {

    //Строка для записи логов
    static StringBuilder log = new StringBuilder();

    public static void main(String[] args) {

        String[] gamesDirectories = {"src", "res", "savegames", "temp"};//список папок директории Games
        String[] srcFiles = {"main", "test"};//папки директории src
        String[] mainFiles = {"Main.java", "Utils.java"};//файлы директории main
        String[] resFiles = {"drawables", "vectors", "icons"};//папки директории res
        String[] tempFile = {"temp.txt"};//файл директории temp
        String path = "Games/";//путь к папке Games относительно папки проекта

        //создаем по очереди файлы и папки
        createDirectories(gamesDirectories, path);
        createDirectories(srcFiles, path + "src/");
        createDirectories(mainFiles, path + "src/main/");
        createDirectories(resFiles, path + "res/");
        createDirectories(tempFile, path + "temp/");

        //пробуем записать наш лог в файл temp.txt
        try (FileWriter fileWriter = new FileWriter(path + "/temp/temp.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(log.toString());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void createDirectories(String[] filesList, String path) {

        //перебираем наименования папок/файлов
        for (String name : filesList) {

            //добавим в лог дату и время события
            log.append(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));

            //пробуем создать файл/папку
            if (name.contains(".")) {
                File file = new File(path, name);
                try {
                    log.append(file.createNewFile() ? " создание файла " : "неудачная попытка создать файл ");
                } catch (IOException exception) {
                    log.append(exception.getMessage());
                }
            } else {
                File file = new File(path + name);
                log.append(file.mkdir() ? " создание папки " : " неудачная попытка создать папку ");
            }

            //дописываем в лог путь и имя
            log
                    .append(path)
                    .append(name)
                    .append('\n');
        }
    }
}