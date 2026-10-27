package latihan;

public class latihanterarah {

    public static void main(String[] args) {

        String kode = "JAVA-BSC";
        String nama = "Java Desktop Fundamental";

        double biaya = 3_800_000;
        double regis = 500_000;

        double diskonq = 0.05;  // 5%
        double diskon  = 0.10;  // 10%
        double diskonw = 0.15;  // 15%

        boolean aktif = true;

        double total = biaya + regis;

        if (total >= 3_000_000) {

            double potongan = total * diskonw;
            double totalBayar = total - potongan;

            System.out.println("Total      : Rp" + total);
            System.out.println("Diskon 15% : Rp" + potongan);
            System.out.println("Total Bayar: Rp" + totalBayar);
            System.out.println("Status     : MAHAL");

        } else {

            double potongan = total * diskon;
            double totalBayar = total - potongan;

            System.out.println("Total      : Rp" + total);
            System.out.println("Diskon 10% : Rp" + potongan);
            System.out.println("Total Bayar: Rp" + totalBayar);
            System.out.println("Status     : TERJANGKAU");
        }
    }
}