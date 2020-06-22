package Bean;

import lombok.Data;

/**
 * @Classname User
 * @Description TODO
 * @Date 6/9/20 12:21
 * @Created by mcy
 */


public @Data class User {
    private int id;
    private String name;
    private int age;

//    public User(int id , String name, int age) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//    }
}