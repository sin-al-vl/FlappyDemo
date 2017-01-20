package com.mygame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Александр on 27.12.2015.
 */

public class Saver {
    private static String FILE_RECORDS = "records.rip";

    private int recPoint;

    public Saver()
    {
        try {
            FileInputStream fis = new FileInputStream(FILE_RECORDS);
            ObjectInputStream is = new ObjectInputStream(fis);
            recPoint = (Integer) is.readObject();
            //recPoint = Integer.parseInt(String.copyValueOf(Character.toChars(fis.read())));
            fis.close();
        } catch (Exception e) {
            recPoint = 0;
            //Toast.makeText(mContext, "Произошла ошибка чтения таблицы рекордов", Toast.LENGTH_LONG);
        }
    }

    public int getRecPoint() {
        return recPoint;
    }

    public void setRecPoint(int recPoint) {
        this.recPoint = recPoint;
        WriteRecord();
    }

    private void WriteRecord ()
    {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_RECORDS);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(recPoint);
            os.close();
        } catch (Exception e) {
            //Toast.makeText(mContext, "Произошла ошибка записи в таблицу рекордов", Toast.LENGTH_LONG);
        }
    }
}
