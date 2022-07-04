package bin;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;
import java.io.FileReader;
import java.io.StringWriter;

public class RunPyScript02 {
    public static void givenPythonScriptEngineIsAvailable_whenScriptInvoked_thenOutputDisplayed() throws Exception {
        StringWriter writer = new StringWriter();
        ScriptContext context = new SimpleScriptContext();
        context.setWriter(writer);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("python");
        engine.eval(new FileReader(("C:\\Users\\kryst\\git\\repository3\\discordbottest\\src\\main\\java\\external_files\\py_scripts\\soundfiletotext.py")), context);
        System.out.println(writer.toString().trim());
    }
}
