package shs.cos.utils;

import shs.cos.utils.IO;

import java.io.File;
import java.util.TreeMap;

public class IOTester {

    public static void main(String[] args) {

        IO io = new IO();

        TreeMap<String, String> map = new TreeMap<>();
        File file = new File("res/output.txt");
        file.getParentFile().mkdirs();

        map.put("C1", "You enter a dark room.");
        map.put("Necro", "You cautiously push through the oversized doorway.");

        io.writeFile(map, file);

        map.clear();
        map = io.readFile(file);
        System.out.println(map);

        IO savegame = new IO();
        TreeMap<String, String> mapGameSave = new TreeMap<>();

        mapGameSave.put("name", "Matthew");
        mapGameSave.put("HP", "20");

        File fileSavegame = new File("res/savegame.sav");
        savegame.writeFile(mapGameSave ,fileSavegame);

    }

}
