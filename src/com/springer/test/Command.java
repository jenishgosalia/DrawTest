package com.springer.test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Created by bijal on 21/06/2016.
 */
public class Command {
    private CommandType mType;
    private Queue<String> mData = new ArrayDeque<String>();

    public CommandType getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = CommandType.valueOf(type.toUpperCase());
        System.out.print("setting type:" + mType.toString() + "\n");
    }

    public String getNextData(){
        if (mData.isEmpty()) return  null;
        return mData.poll();
    }

    public void addData(String element) {
        System.out.print("Adding element:" + element + "\n");
        mData.add(element);
    }

    public void addData (List<String> data) {
        System.out.print("Adding Data:" + data + "\n");
        mData.addAll(data);
    }

}
