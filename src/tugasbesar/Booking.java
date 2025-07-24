package tugasbesar;
import javax.swing.JOptionPane;

public class Booking {
    private String kota;
    private String waktu;
    private String film;
    private String type;
    
    
    public void simpanPilihan(BookingView form){
        try{
        
        
        }catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(form, 
                    "data Harus Diisi", 
                    "TERJADI KESALAHAN",
                    JOptionPane.ERROR_MESSAGE);
        }
    
    
    
    }
    
}
