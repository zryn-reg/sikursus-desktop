/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan; //package

/**
 *
 * @author USER
 */
<<<<<<< HEAD
public class LatihanBiayaKursus { //class
    public static void main (String[] args){ //main method
        //Variabel
    String halo = "Biaya Kursus Regza";
    String kode = "JAVA-BSC";
    String nama = "Java Desktop Fundamental";
    double biaya = 2_500_000;
    double diskon = 0.10;
    double regs = 500_000;
    boolean aktif = true;
    double total = biaya;
    if (biaya >= 3_000_000){
       total = (biaya + regs) - ((biaya + regs) *0.15); 
    }
    else if(biaya >= 1_500_000){
       total = (biaya + regs) - ((biaya + regs) *0.10); 
    }
    
    else{
        total = (biaya + regs) - ((biaya + regs) *0.05); 
    }    //output
    System.out.println(halo);
    System.out.println("Kode   : " + kode);
    System.out.println("Kursus : " + nama);
    System.out.println("Aktif  : " + aktif);
    System.out.printf("Total  : Rp%,.0f%n", total );
    }
}
=======
public class LatihanBiayaKursus { //c;ass
    public static void main(String[] args) { //main mehode
      
String kode = "JAVA-BSC"; //variabel
String nama = "Java Desktop Fundamental";
double biaya = 500_000;
double diskon = 0.15;
boolean aktif = true;
double potongan = biaya * diskon;
double total = biaya - potongan;
System.out.println("Kode   : " + kode);
System.out.println("Kursus : " + nama);
System.out.println("Aktif  : " + aktif);
System.out.printf("Total  : Rp%,.0f%n", total); //output
}
}
>>>>>>> origin/master
