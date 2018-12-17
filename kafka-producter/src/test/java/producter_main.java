import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import org.apache.hadoop.fs.FileSystem;
import www.mf.cn.HDFS;
import www.mf.cn.Kafka;

import java.io.*;

public class producter_main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Administrator\\Desktop\\a.txt")));
            String s;
            s = br.readLine();
            br.close();
//            System.out.println(s);
            int i = 1;
            //创建HDFS连接
            HDFS hdfs = new HDFS();
            FileSystem hadoopFS = hdfs.getHDFS();
            if (hadoopFS == null) return;
            String hdfsDir = "/user/test/writeHDFS";

            //创建kafka连接
            Kafka kafka = new Kafka();
            Producer<String, String> producer = kafka.KafkaProducter();
            if (hadoopFS == null) return;
            String topic = "test";

            while (true) {
                hdfs.writeHDFS(hadoopFS, s, hdfsDir);
                producer.send(new KeyedMessage<String, String>(topic, s));
                System.out.println(i);
                System.out.println(s);
                i++;
                Thread.sleep(5000);
            }
//            hadoopFS.close();
//            producer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




