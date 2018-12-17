package util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class test   {
public static void main(String[] args) {
        Properties pro = new Properties();

        InputStream ins =  Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties");
        try {
                pro.load(ins);
                String driver = pro.getProperty("brokerList");
                ins.close();
                System.out.println(driver);
        } catch (IOException e) {
                e.printStackTrace();
        }
}
}