package www.mf.cn;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Kafka {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Producer<String, String> KafkaProducter() {
        Producer<String, String> producer;
        String zookeeper;
        String kafkaBroker;
        Properties pro = new Properties();
        Properties props = new Properties();
        InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream("kafka.properties");
        try {
            //获取配置
            pro.load(ins);
            zookeeper = pro.getProperty("zookeeper");
            kafkaBroker = pro.getProperty("kafkaBroker");
            ins.close();

            props.put("zookeeper.connect", zookeeper);
            //此处配置的是kafka的端口
            props.put("metadata.broker.list", kafkaBroker);
            //配置value的序列化类
            props.put("serializer.class", "kafka.serializer.StringEncoder");
            //配置key的序列化类
            props.put("key.serializer.class", "kafka.serializer.StringEncoder");
            props.put("request.required.acks", "1");
            producer = new Producer<String, String>(new ProducerConfig(props));
            return producer;
        } catch (IOException e) {
            logger.error("kafka配置文件获取失败", e);
            return null;
        }
    }
}
