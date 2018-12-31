package com.sina.sinagle;

public  class Test {
    //单例饿汉式
    class User{
        private static User u;
        private User(){}
        public synchronized static User getInstance(){
            if(u==null){
                u=new User();
            }
            return u;
        }
    }
}
