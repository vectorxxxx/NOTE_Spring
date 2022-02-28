package com.vectorx.spring5.s5_xml.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Stu {
    private String[] arrs;
    private List<String> lists;
    private List<Course> lists2;
    private Map<String, String> maps;
    private Set<String> sets;

    public void setArrs(String[] arrs) {
        this.arrs = arrs;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public void setLists2(List<Course> lists2) {
        this.lists2 = lists2;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }

    public List<Course> getLists2() {
        return lists2;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "arrs=" + Arrays.toString(arrs) +
                ", lists=" + lists +
                ", maps=" + maps +
                ", sets=" + sets +
                '}';
    }
}