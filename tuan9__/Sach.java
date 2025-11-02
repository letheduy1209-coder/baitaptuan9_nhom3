// Lớp trừu tượng


import IGiaBan; // Mặc dù Sach không triển khai trực tiếp, nhưng để định nghĩa TinhGiaBan() abstract
import IKiemKe; // Mặc dù Sach không triển khai trực tiếp, nhưng để định nghĩa kiemTraTonKho() abstract
import IGiaBan;
import KiemKe;

// Yêu cầu Tuần 7: Cập nhật lớp Cơ sở thành Abstract Class
// Yêu cầu Tuần 8: Khai báo abstract class Sach và triển khai IGiaBan, IKiemKe
public abstract class Sach implements IGiaBan, IKiemKe {
    // Thuộc tính (attributes) - Yêu cầu Tuần 5, 6
    private String maSach; // bookID
    private String tieuDe; // title
    private String tacGia; // author
    private int namXuatBan; // publication year (Cập nhật kiểu String cho linh hoạt)
    private int soLuong; // quantity
    private String viTriKho; // Yêu cầu Tuần 8: Bổ sung thuộc tính

    // Constructor - Yêu cầu Tuần 5, 6, 7 (Hàm tạo đầy đủ tham số)
    public Sach(String maSach, String tieuDe, String tacGia, String namXuatBan, int soLuong, String viTriKho) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
        this.viTriKho = viTriKho;
    }

    // Constructor mặc định
    public Sach() {}

    // Getters và Setters - Yêu cầu Tuần 5, 7, 8 (Tính đóng gói)
    // Chỉ viết một vài ví dụ, trong code hoàn chỉnh phải có cho tất cả các thuộc tính
    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(String namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public String getViTriKho() {
        return viTriKho;
    }

    public void setViTriKho(String viTriKho) {
        this.viTriKho = viTriKho;
    }


    // Yêu cầu Tuần 5: Tạo một phương thức hienThiThongTin()
    // Yêu cầu Tuần 7: Thêm phương thức trừu tượng public abstract void HienThiThongTin()
    // Yêu cầu Tuần 8: Thay thế bằng phương thức toString() được ghi đè ở lớp con
    // Tuy nhiên, để đáp ứng yêu cầu Tuần 5/7, ta vẫn có thể định nghĩa 1 phương thức cụ thể hoặc trừu tượng
    
    // Ghi đè phương thức toString() - Yêu cầu Tuần 8: Cập nhật toString()
    @Override
    public String toString() {
        return String.format("| %-10s | %-30s | %-20s | %-15s | %-8d | %-12s |",
                             maSach, tieuDe, tacGia, namXuatBan, soLuong, viTriKho);
    }
    
    // Yêu cầu Tuần 8: Triển khai IKiemKe (Logic triển khai)
    @Override
    public boolean kiemTraTonKho(int soLuongToiThieu) {
        // Trả về true nếu số lượng hiện tại >= số lượng tối thiểu
        return this.soLuong >= soLuongToiThieu;
    }

    @Override
    public void capNhatViTri(String viTriMoi) {
        this.viTriKho = viTriMoi;
        System.out.println("-> Đã cập nhật vị trí kho mới cho sách " + this.maSach + " sang: " + viTriMoi);
    }
    
    // Phương thức in thông tin cơ bản của sách
    public void inThongTinCoBan() {
        System.out.println("Ma Sach: " + maSach);
        System.out.println("Tieu De: " + tieuDe);
        System.out.println("Tac Gia: " + tacGia);
        System.out.println("Nam Xuat Ban: " + namXuatBan);
        System.out.println("So Luong: " + soLuong);
        System.out.println("Vi Tri Kho: " + viTriKho);
    }
}