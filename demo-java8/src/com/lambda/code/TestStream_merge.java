package com.lambda.code;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.maxBy;

/**
 * @Author:zhuzhou
 * @Date: 2019/10/10  17:10
 * merge:求总分
 **/
public class TestStream_merge {
    private static List<StudentScore> buildStudents(){
        return Arrays.asList(
                new StudentScore("张三","语文",52),
                new StudentScore("张三","数学",62),
                new StudentScore("张三","语文",72),
                new StudentScore("李四","语文",52),
                new StudentScore("李四","数学",62),
                new StudentScore("李四","语文",72),
                new StudentScore("王五","语文",52),
                new StudentScore("王五","数学",64),
                new StudentScore("王五","语文",72)
        );
    }

    public static void main(String[] args) {
        List<StudentScore> studentScores = buildStudents();
        Map<String,Integer> map = new HashMap<>();
        studentScores.stream().forEach(e-> map.merge(
                e.getStuName(),
                e.getScore(),
                Integer::sum));
        System.out.println(map);
        Map<String, Double> collect = studentScores.stream().collect(Collectors.groupingBy(StudentScore::getStuName, Collectors.averagingDouble(StudentScore::getScore)));
        System.out.println(collect);
    }

}

class StudentScore{
    private String stuName;
    private String subject;
    private int score;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public StudentScore(String stuName, String subject, int score) {
        this.stuName = stuName;
        this.subject = subject;
        this.score = score;
    }

    public StudentScore() {
    }

    @Override
    public String toString() {
        return "StudentScore{" +
                "stuName='" + stuName + '\'' +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                '}';
    }
}
