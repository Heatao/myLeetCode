package InstanceCode.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

// <KEYIN, VALUEIN, KEYOUT, VALUEOUT>

/**
 * KEYIN map阶段输入的key的类型：LongWritable
 * VALUEDIN map阶段输入的value类型：Text
 * KEYOUT 输出的key，Text
 * VALUEOUT 输出的value，IntWritable
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {            // 这里为什么加类的变量呢

    private Text outK = new Text();                 // 因为这里是一行行处理的，为了避免内存浪费，所以放到这里
    private IntWritable outV = new IntWritable(1);

    /*
    看源码可以知道，map是一行一行处理的
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 获取一行
        String line = value.toString();

        // 切割
        String[] words = line.split(" ");

        for (String word : words) {
            outK.set(word);
            context.write(outK, outV);
        }
    }
}
