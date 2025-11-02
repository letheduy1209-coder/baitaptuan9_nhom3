// Sách giáo trình


// Yêu cầu Tuần 6, 7, 8: Kế thừa từ lớp Sach
public class SachGiaoTrinh extends Sach {
    // Bổ sung thuộc tính - Yêu cầu Tuần 6, 7
    private String monHoc; // Môn học
    private String capDo; // Cấp độ (VD: Đại học, Phổ thông)

    // Hàm tạo - Yêu cầu Tuần 6: Xây dựng hàm tạo đầy đủ tham số
    public SachGiaoTrinh(String maSach, String tieuDe, String tacGia, String namXuatBan, int soLuong, String viTriKho, 
                         String monHoc, String capDo) {
        // Gọi constructor của lớp cha - Yêu cầu Tuần 6: Sử dụng super
        super(maSach, tieuDe, tacGia, namXuatBan, soLuong, viTriKho);
        this.monHoc = monHoc;
        this.capDo = capDo;
    }

    // Getters và Setters cho thuộc tính riêng
    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public String getCapDo() {
        return capDo;
    }

    public void setCapDo(String capDo) {
        this.capDo = capDo;
    }

    // Ghi đè (Override) phương thức toString() - Yêu cầu Tuần 6, 8
    @Override
    public String toString() {
        return super.toString() + 
               String.format(" %-20s | %-12s | %-15.2f |", 
               monHoc, capDo, TinhGiaBan());
    }

    // Ghi đè (Override) TinhGiaBan() - Yêu cầu Tuần 7, 8
    // SachGiaoTrinh: Giá bán = (Giá niêm yết - 5.000 VNĐ) * (Số năm đã xuất bản + 2025 - namXuatBan)
    @Override
    public double TinhGiaBan() {
        try {
            int namXuatBanInt = Integer.parseInt(getNamXuatBan());
            int giaNiemYet = 50000; // Giả sử giá niêm yết cố định là 50.000 VNĐ
            int soNamDaXuatBan = 2025 - namXuatBanInt;
            
            // Đảm bảo số năm không âm
            if (soNamDaXuatBan < 0) soNamDaXuatBan = 0; 
            
            // Tính theo công thức: (50000 - 5000) * (số năm + 1)
            // Cần làm rõ "Giá niêm yết" trong yêu cầu, tạm lấy 50000.
            return (giaNiemYet - 5000) * (1.0 + soNamDaXuatBan * 0.1); // Thêm logic nhân với số năm cho hợp lý
        } catch (NumberFormatException e) {
            System.err.println("Lỗi định dạng năm xuất bản: " + getNamXuatBan());
            return 0.0;
        }
    }
}