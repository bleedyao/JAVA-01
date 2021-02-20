package com.bleedyao;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
// 需要定义 set get 方法
@Data
public class School implements ISchool {
    private List<Student> list;

    public void printInfo() {
        System.out.println(Arrays.toString(list.toArray()));
    }
}
