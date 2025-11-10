
import core.IGiaBan;
import core.IKiemKe;

public abstract class Sach implements IGiaBan, IKiemKe {
    private String maSach;
    private String tieuDe;
    private String tacGia;
    private String namXuatBan;
    private int soLuong;
    private String viTriKho;

    public Sach(String maSach, String tieuDe, String tacGia, String namXuatBan, int soLuong, String viTriKho) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
        this.viTriKho = viTriKho;
    }

    public String getMaSach() { return maSach; }
    public void setMaSach(String maSach) { this.maSach = maSach; }
    public String getTieuDe() { return tieuDe; }
    public String getTacGia() { return tacGia; }
    public String getNamXuatBan() { return namXuatBan; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public String getViTriKho() { return viTriKho; }
    public void setViTriKho(String viTriKho) { this.viTriKho = viTriKho; }
    
    @Override
    public boolean kiemTraTonKho(int soLuongToiThieu) {
        return this.soLuong >= soLuongToiThieu;
    }

    @Override
    public void capNhatViTri(String viTriMoi) {
        this.viTriKho = viTriMoi;
        System.out.println("-> Đã cập nhật vị trí kho mới cho sách " + this.maSach + " sang: " + viTriMoi);
    }
    
    @Override
    public abstract double TinhGiaBan(); 
    
    @Override
    public String toString() {
        return String.format("| %-10s | %-30s | %-20s | %-15s | %-8d | %-12s |",
                             maSach, tieuDe, tacGia, namXuatBan, soLuong, viTriKho);
    }
    
    public void inThongTinCoBan() {
        System.out.println("Ma Sach: " + maSach + ", Tieu De: " + tieuDe + ", Tac Gia: " + tacGia);
        System.out.println("SL: " + soLuong + ", Nam XB: " + namXuatBan + ", Vi Tri Kho: " + viTriKho);
    }
}