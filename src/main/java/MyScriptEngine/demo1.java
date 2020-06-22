package MyScriptEngine;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

/**
 * @Classname demo1
 * @Description TODO 6.0后 引入脚本引擎
 * @Date 6/10/20 21:57
 * @Created by mcy
 */

// Rhino 使用java编写javascript
public class demo1 {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        // 获得引擎对象
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");

        // 定义变量 存储到引擎的上下文
        scriptEngine.put("msg", "hello");
        String str = "var user = {name: 'yanz', age: 19, schools: ['peking',]};";

        str += "print(user.name);";

        // 执行代码
        scriptEngine.eval(str);
        scriptEngine.eval("msg = 'world'");
        System.out.println(scriptEngine.get("msg"));  // 上下文变量既可以被java调用 也可以被javascript调用

        // 定义函数
        scriptEngine.eval("function add(a,b){var sum = a + b; return sum}");

        // 执行js函数 转为Invocable调用函数
        Invocable invocable = (Invocable) scriptEngine;
        Object object = invocable.invokeFunction("add", new Object[]{12, 23});
        System.out.println(object);

        // 导入其他的java包 使用其他包中的java类
        String jsCode = "importPackage(java.util); var list = Arrays.asList([\"a\", \"b\"]);";

        scriptEngine.eval(jsCode);

        List<String> list1 = (List<String>) scriptEngine.get("list");
        for (String s :
                list1) {
            System.out.println(s);
        }
    }
}
