package com.jobcandt.jobwebite.services;

class Test
{
    public void show(int n1,float n2){
        System.out.println("int-float");
    }
    public void show(float n1,int n2){
        System.out.println("float-int");
    }
    public static void main(String[] args){
        Test test = new Test();
        //test.show(1,2);
    }
}