package cz.krystofcejchan.main.onstart;

import cz.krystofcejchan.external_files.py_scripts.PyPaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CheckPyPaths implements IOnStart {
    @Override
    public void doYourPart() throws Exception {
        Class<?> cl = PyPaths.class;
        List<Object> allObjects = new ArrayList<Object>();
        for (java.lang.reflect.Field f : cl.getDeclaredFields()) {
            f.setAccessible(true);
            Object o = f.get(this);
            allObjects.add(o);
        }
        allObjects.remove(0);
        for (Object o : allObjects) {
            if (!new File(o.toString()).canRead()) throw new FileNotFoundException();
        }

    }
}
