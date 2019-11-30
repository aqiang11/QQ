package com.Dao;

public class MessageDeal {
    public String producegetMessage(String message) {
        char[] messagechar = message.toCharArray();
        char[] line = new char[messagechar.length];
        for (int i = 0, j = 0; i < messagechar.length; i++) {
            if (messagechar[i] != '⏩') {
                line[j++] = messagechar[i];
            } else
                line[j++] = '\n';
        }                    //对字符进行处理得到字符串
        message = String.valueOf(line);

        String text1 = "<html>";                    //设置换行;
        char[] text = message.toCharArray();

        for (int i = 0, j = 1; i < text.length; i++, j++) {
            if ((char) text[i] == '\n' || j % 40 == 0)
                text1 += "<br/>";
            text1 += String.valueOf(text[i]);

        }
        message = text1;
        return  message;
    }
    public String getsentMessage(String message){
        char[] text=message.toCharArray();
        String text2="";
        for(int i=0,j=1;i<text.length;i++,j++){
            if (text[i]!='\n')
                text2+= String.valueOf(text[i]);
            else
                text2+='⏩';
        }
        text2+="<html/>";             //得到要发送的内容的文本

        return text2;
    }
    public String getLocalstring(String message) {
        String text1 = "<html>";                    //设置换行;
        char[] text = message.toCharArray();

        for (int i = 0, j = 1; i < text.length; i++, j++) {
            if ((char) text[i] == '\n' || j % 40 == 0)
                text1 += "<br/>";
            text1 += String.valueOf(text[i]);

        }
        text1 += "<html/>";             //得到要发送的内容的文本  放在本地浏览记录
        return text1;
    }


}
