import consumer.JavaKafkaSimpleConsumerAPI;
import consumer.KafkaBrokerInfo;
import consumer.KafkaTopicPartitionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JavaKafkaSimpleConsumerAPITest {
    public static void main(String[] args) {

        JavaKafkaSimpleConsumerAPI example = new JavaKafkaSimpleConsumerAPI();
        long maxReads = 10;
        String topic = "test2";
        int partitionID = 1;

        KafkaTopicPartitionInfo topicPartitionInfo = new KafkaTopicPartitionInfo(topic, partitionID);
        List<KafkaBrokerInfo> seeds = new ArrayList<KafkaBrokerInfo>();
        seeds.add(new KafkaBrokerInfo("192.168.2.207", 9092));

        try {
            example.run(maxReads, topicPartitionInfo, seeds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取该topic所属的所有分区ID列表
        System.out.println(example.fetchTopicPartitionIDs(seeds, topic, 100000, 64 * 1024, "client-id"));
    }
}
