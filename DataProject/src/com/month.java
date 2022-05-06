package com;

public class month {
     protected int month;
     protected day[] days = new day[28]; // her ayın 28 gününe randevu alınabilir, son günler temizlik vs

     public month(){

     }
     public month(int k){
          month = k;
     }
}
