package cn.delayed.redis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employer {
    private String name;
    private int age;
    private String wife;
    private Double salary;
    private String putTime;

    public void setPutTime() {
        this.putTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
    }
}
