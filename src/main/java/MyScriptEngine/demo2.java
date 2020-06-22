package MyScriptEngine;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Classname demo2
 * @Description TODO
 * @Date 6/10/20 22:33
 * @Created by mcy
 */
public class demo2 {

    public static void main(String[] args) throws IOException, ScriptException {
        // 获得引擎对象
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");

        // 执行js文件

        scriptEngine.eval(new FileReader("test.js"));
//        URL url = demo2.class.getClassLoader().getResource("test.js"); //  无效

//        assert url != null;
//        FileReader fileReader = new FileReader(url.getPath());
//        scriptEngine.eval(fileReader);
//        fileReader.close();
//


    }
}
