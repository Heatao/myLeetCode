package InstanceCode.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

// 不懂的随时看WordCount原有例子和源码
public class WordCountDriver {
    // 固定7步
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1 获取job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2 设置jar包路径
        job.setJarByClass(WordCountDriver.class);           // 这里会通过反射获取jar包所在的路径

        // 3 关联mapper和reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 4 设置map输出的K V类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 5 设置最终输出的K V类型
        // 这里应该和reducer的输出类型一致
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // job.setCombinerClass(WordCountCombiner.class);
        // 因为代码和mapper一样，所以直接用reducer也可以
        job.setCombinerClass(WordCountReducer.class);

        // reduce去掉则无shuffle和combiner
        // job.setNumReduceTasks(0);

        // 6 设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 7 提交job
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}

