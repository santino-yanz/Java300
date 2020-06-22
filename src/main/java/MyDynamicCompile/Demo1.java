package MyDynamicCompile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Classname Demo1
 * @Description TODO 6.0后 引入动态编译
 *              使用场景 浏览器执行用户写入的代码
 * @Date 6/9/20 16:18
 * @Created by mcy
 */

// 调用JavaCompiler动态编译 调用run方法
public class Demo1 {

    public static void main(String[] args) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, "/Users/mcy/Desktop/HelloWorld.java");

        System.out.println(result==0?"success":"failure");

        // 通过IO流， 将字符串代码存储为临时文件 进行编译
        String content = "";


        // 动态运行编译好的类
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("java -cp /Users/mcy/Desktop/ HelloWorld");  // classPath + className

        InputStream in =  process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String info = "";
        while ((info = reader.readLine()) != null) {
            System.out.println(info);
        }



    }

}
