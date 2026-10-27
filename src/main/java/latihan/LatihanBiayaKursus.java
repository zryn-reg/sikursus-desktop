
package latihan;

public class LatihanBiayaKursus {

    public static void main(String[] args) {

        // Variabel
        String halo = "Biaya Kursus Regza";
        String kode = "JAVA-BSC";
        String nama = "Java Desktop Fundamental";

        double biaya = 2_500_000;
        double regs = 500_000;
        boolean aktif = true;

        double subtotal = biaya + regs;
        double diskon;

        // Menentukan diskon
        if (biaya >= 3_000_000) {
            diskon = 0.15;
        } else if (biaya >= 1_500_000) {
            diskon = 0.10;
        } else {
            diskon = 0.05;
        }

        // Menghitung total
        double potongan = subtotal * diskon;
        double total = subtotal - potongan;

        // Output
        System.out.println(halo);
        System.out.println("Kode   : " + kode);
        System.out.println("Kursus : " + nama);
        System.out.println("Aktif  : " + aktif);
        System.out.printf("Total  : Rp%,.0f%n", total);
    }
}
