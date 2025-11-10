

import java.lang.Math;

public class SachGiaoTrinh extends Sach {
    private String monHoc;
    private String capDo;

    public SachGiaoTrinh(String maSach, String tieuDe, String tacGia, String namXuatBan, int soLuong, String viTriKho, 
                         String monHoc, String capDo) {
        super(maSach, tieuDe, tacGia, namXuatBan, soLuong, viTriKho);
        this.monHoc = monHoc;
        this.capDo = capDo;
    }

    public String getMonHoc() { return monHoc; }
    public String getCapDo() { return capDo; }
    
    @Override
    public double TinhGiaBan() {
        try {
            int namXuatBanInt = Integer.parseInt(getNamXuatBan());
            int giaNiemYet = 50000; 
            int soNamDaXuatBan = Math.max(0, 2025 - namXuatBanInt); 
            // Giả định công thức tính giá
            return (giaNiemYet - 5000) * (1.0 + soNamDaXuatBan * 0.1); 
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               String.format(" %-20s | %-12s | %-15.2f |", 
               monHoc, capDo, TinhGiaBan());
    }
}