#!/bin/bash
awk '{for(i=1;i<=NF;i++){asso_array[$i]++;}};END{for(w in asso_array){print w,asso_array[w];}}' words.txt | sort -rn -k2

cat words.txt | xargs -n1 | sort | uniq -c | sort -rn | awk '{print $2,$1}'

# xargs命令是用于给其他命令传递参数的一个过滤器，也是组合多个命令的一个工具。-n选项，指定 输出时每行输出的列数，还可以-d指定分割符
# 使用uniq统计词频需要被统计文本相同字符前后在一起，所以先排序。uniq -c表示同时输出出现次数
# sort -nr 其中-n表示把数字当做真正的数字处理，默认是按照字符排序的。-r是降序排列