import consumer.JavaKafkaConsumerHighAPI;

/**
 * Created by x_deng on 2018/3/1.
 */
public class JavaKafkaConsumerHighAPITest {
    public static void main(String[] args) {
        String zookeeper = "hnode3:2181";
        String groupId = "group1";
        String topic = "test1";
        int threads = 1;

        JavaKafkaConsumerHighAPI example = new JavaKafkaConsumerHighAPI(topic, threads, zookeeper, groupId);
        new Thread(example).start();

        // 执行10秒后结束
//        int sleepMillis = 10000;
//        try {
//            Thread.sleep(sleepMillis);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        // 关闭
//        example.shutdown();
    }
}
