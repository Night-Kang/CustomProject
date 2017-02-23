package com.night.customproject.common.database.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Night on 9/24/16.
 * Description: 联系人
 */
@DatabaseTable(tableName = "contacts")
public class Contact implements Serializable{
    private static final long serialVersionUID = 2626826048794054543L;
    private static final String ID="id";

    //数据库主键
    @DatabaseField(generatedId=true,columnName=ID)
    private int id;
    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;
    @DatabaseField(columnName = "age")
    private int age;
    @DatabaseField(defaultValue = "male")
    private String sex;

    public Contact() {

    }

    public Contact(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
