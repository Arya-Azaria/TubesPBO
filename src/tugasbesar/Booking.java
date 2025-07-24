package tugasbesar;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class Booking {
    private String kota;
    private String waktu;
    private String film;
    private String type;
    private String kursi;
    private boolean konfirmasi = false;

    public void simpanPilihan(BookingView form) {
        try {
            kota = form.getjTempat().getSelectedItem().toString();
            waktu = form.getjWaktu().getSelectedItem().toString();
            film = form.getjFilm().getSelectedItem().toString();
            type = form.getjType().getSelectedItem().toString();

            // Ambil kursi yang dicentang
            kursi = "";
            if (form.getA1().isSelected()) kursi += "A1 ";
            if (form.getA2().isSelected()) kursi += "A2 ";
            if (form.getA3().isSelected()) kursi += "A3 ";
            if (form.getA4().isSelected()) kursi += "A4 ";
            if (form.getA5().isSelected()) kursi += "A5 ";
            if (form.getA6().isSelected()) kursi += "A6 ";
            
            if (form.getB1().isSelected()) kursi += "B1 ";
            if (form.getB2().isSelected()) kursi += "B2 ";
            if (form.getB3().isSelected()) kursi += "B3 ";
            if (form.getB4().isSelected()) kursi += "B4 ";
            if (form.getB5().isSelected()) kursi += "B5 ";
            if (form.getB6().isSelected()) kursi += "B6 ";
            
            if (form.getC1().isSelected()) kursi += "C1 ";
            if (form.getC2().isSelected()) kursi += "C2 ";
            if (form.getC3().isSelected()) kursi += "C3 ";
            if (form.getC4().isSelected()) kursi += "C4 ";
            if (form.getC5().isSelected()) kursi += "C5 ";
            if (form.getC6().isSelected()) kursi += "C6 ";
            
            // Validasi input
            if (kota.isEmpty() || waktu.isEmpty() || film.isEmpty() || type.isEmpty() || kursi.isEmpty()) {
                throw new NumberFormatException(); // akan tertangkap di catch
            }

            // Tampilkan hasil di TextArea
            String hasil = "Kota   : " + kota + "\n"
                         + "Waktu  : " + waktu + "\n"
                         + "Film   : " + film + "\n"
                         + "Tipe   : " + type + "\n"
                         + "Kursi  : " + kursi + "\n";

            form.getjTextKonfirmasi().setText(hasil);
            konfirmasi = true;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(form,
                "Semua data dan kursi harus diisi!",
                "TERJADI KESALAHAN",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void struk(BookingView form) {
    // Ambil pilihan user
        if (konfirmasi == false){
            JOptionPane.showMessageDialog(form,
                "Silahkan Konfirmasi Terlebih Dahulu",
                "TERJADI KESALAHAN",
                JOptionPane.ERROR_MESSAGE);
        }else{
            String kota = form.getjTempat().getSelectedItem().toString();
            String waktu = form.getjWaktu().getSelectedItem().toString();
            String film = form.getjFilm().getSelectedItem().toString();
            String type = form.getjType().getSelectedItem().toString();

            String kursi = "";
            int jumlahKursi = 0;

            // Cek kursi yang dicentang dan hitung jumlah
            JCheckBox[] semuaKursi = {
                form.getA1(), form.getA2(), form.getA3(), form.getA4(), form.getA5(), form.getA6(),
                form.getB1(), form.getB2(), form.getB3(), form.getB4(), form.getB5(), form.getB6(),
                form.getC1(), form.getC2(), form.getC3(), form.getC4(), form.getC5(), form.getC6()
            };

            for (JCheckBox cb : semuaKursi) {
                if (cb.isSelected()) {
                    kursi += cb.getText() + " ";
                    jumlahKursi++;
                }
            }

            // Validasi jika tidak ada kursi dipilih
            if (jumlahKursi == 0) {
                JOptionPane.showMessageDialog(form, "Silakan pilih kursi terlebih dahulu!");
                return;
            }

            // Tentukan harga berdasarkan tipe
            int hargaPerKursi = 0;
            switch (type) {
                case "REGULER (2D) - 35.000": hargaPerKursi = 35000; break;
                case "SWEET BOX - 60.000": hargaPerKursi = 60000; break;
                case "IMAX - 120.000": hargaPerKursi = 120000; break;

            }

            int total = jumlahKursi * hargaPerKursi;

            // Format harga jadi Rupiah
            java.text.NumberFormat nf = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("id", "ID"));
            String totalRupiah = nf.format(total);

            // Tampilkan struk
            form.getjTextStruk().setText(
                "==== STRUK PEMESANAN ====\n" +
                "KOTA   : " + kota + "\n" +
                "FILM   : " + film + "\n" +
                "WAKTU  : " + waktu + "\n" +
                "TYPE   : " + type + "\n" +
                "JUMLAH : " + jumlahKursi + " tiket\n" +
                "TOTAL  : " + totalRupiah + "\n" +
                "==========================");
            
            //hapus teks konfirmasi
            form.getjTextKonfirmasi().setText("");
            konfirmasi = false;
        }
        
    }


    // Getter untuk data QR Code atau Struk
    public String getDataTransaksi() {
        return kota + ";" + waktu + ";" + film + ";" + type + ";" + kursi;
    }
}
