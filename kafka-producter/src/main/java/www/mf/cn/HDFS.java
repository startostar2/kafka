package www.mf.cn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

public class HDFS {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void writeHDFS(FileSystem hadoopFS, String message, String hdfsDir) {
        if (message == null && message == "") {
            logger.error("message is null");
            return;
        } else {
            //创建好相应的目录
            if (hadoopFS == null) return;
            if (!message.endsWith("\n")) message = new String(message + "\n");
            try {
                //创建目录
                if (!hadoopFS.exists(new Path("/" + hdfsDir))) hadoopFS.mkdirs(new Path("/" + hdfsDir));
                //根据时间创建文件
                String fileName = hdfsDir + "/" + (new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())) + ".txt";
                Path dst = new Path(fileName);
                if (!hadoopFS.exists(dst)) {
                    FSDataOutputStream output = hadoopFS.create(dst);
                    output.close();
                }
                //写入hdfs目录
                InputStream in = new ByteArrayInputStream(message.getBytes("UTF-8"));
                OutputStream out = hadoopFS.append(dst);
                //message不能大于5M
                IOUtils.copyBytes(in, out, 5242880, true);
            } catch (Exception e) {
                logger.error("文件写入失败" + message, e);
            }
        }
    }

    //获取HDFS链接，并创建目录
    public FileSystem getHDFS() {
        InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream("kafka.properties");
        FileSystem hadoopFS = null;
        try {
            Configuration hdfsConf;
            Properties pro = new Properties();
            String URL;
            String hdfsUri1;
            String hdfsUri2;
            pro.load(ins);
            URL = pro.getProperty("URL");
            hdfsUri1 = pro.getProperty("hdfsUri1");
            hdfsUri2 = pro.getProperty("hdfsUri2");
            ins.close();
            hdfsConf = new Configuration();
            //指定nameservice配置
            hdfsConf.set("dfs.nameservices", "nameservice1");
            //指定nameservice服务下有几个namenode
            hdfsConf.set("dfs.ha.namenodes.nameservice1", "nn1,nn2");
            //指定各个namenode节点的访问链接
            hdfsConf.set("dfs.namenode.rpc-address.nameservice1.nn1", hdfsUri1);
            hdfsConf.set("dfs.namenode.rpc-address.nameservice1.nn2", hdfsUri2);
            hdfsConf.set("dfs.client.failover.proxy.provider.nameservice1", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
            hadoopFS = FileSystem.get(new URI(URL), hdfsConf, "hdfs");
            return hadoopFS;
        } catch (Exception e) {
            logger.error("HDFS获取配置文件失败，或HDFS创建目录失败", e);
            return hadoopFS;
        }
    }
}
