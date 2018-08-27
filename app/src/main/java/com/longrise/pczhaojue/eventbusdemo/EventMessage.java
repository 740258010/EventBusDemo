package com.longrise.pczhaojue.eventbusdemo;

/**
 * @author PCzhaojue
 * @name EventBusDemo
 * @class name：com.longrise.pczhaojue.eventbusdemo
 * @class describe
 * @time 2018/8/2 上午11:11
 * @change
 * @chang time
 * @class describe
 */
public class EventMessage
{
        private String message;
        public  EventMessage(String message){
            this.message=message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

}

