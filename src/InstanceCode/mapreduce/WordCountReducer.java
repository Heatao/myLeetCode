package InstanceCode.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * KEYIN map阶段输入的key的类型：Text
 * VALUEDIN map阶段输入的value类型：IntWritable
 * KEYOUT 输出的key，Text
 * VALUEOUT 输出的value，IntWritable
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable outV = new IntWritable();

    /*
    这里Iterable不是一个迭代器，而是一个类似集合的东东，可以values.iterator()获取迭代器
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        // atguigu, (1,1)
        // 累加
        for (IntWritable value : values) {
            sum += value.get();
        }

        outV.set(sum);
        // 写出
        context.write(key, outV);
    }
}
