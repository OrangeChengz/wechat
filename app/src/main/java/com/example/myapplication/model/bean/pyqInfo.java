package com.example.myapplication.model.bean;

public class pyqInfo {
    private String name;//朋友圈发布者名称
    private  String message;//朋友圈内容
    public pyqInfo(){

    }
    public pyqInfo(String name,String message){
        this.name=name;
        this.message=message;
    }
    public String getName(){
        return name;
    }
    public String getMessage(){
        return  message;
    }
    public void setName(String name){this.name = name;}
    public void setMessage(String message){this.message = message;}
    @Override
    public String toString() {
        return "pyqInfo{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
