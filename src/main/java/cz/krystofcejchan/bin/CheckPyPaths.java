package cz.krystofcejchan.bin;

import cz.krystofcejchan.main.onstart.IOnStart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CheckPyPaths implements IOnStart {
    @Override
    public void doYourPart() throws Exception {
        Class<?> cl = PyPaths.class;
        List<Object> allObjects = new ArrayList<>();
        for (java.lang.reflect.Field f : cl.getDeclaredFields()) {
            f.setAccessible(true);
            Object o = f.get(this);
            allObjects.add(o);
        }
        allObjects.remove(0);
        for (Object obj : allObjects) {
            System.out.println(new File(obj.toString()).getAbsolutePath());
            if (!new File(obj.toString()).canRead()) throw new FileNotFoundException();
        }

    }
}
