// Sách tiểu thuyết


// Yêu cầu Tuần 6, 7, 8: Kế thừa từ lớp Sach
public class SachTieuThuyet extends Sach {
    // Bổ sung thuộc tính - Yêu cầu Tuần 6, 7
    private String theLoai; // Thể loại (VD: Lãng mạn, Khoa học viễn tưởng)
    private boolean laSachSeries; // Sách có thuộc loại series hay không

    // Hàm tạo - Yêu cầu Tuần 6: Xây dựng hàm tạo đầy đủ tham số
    public SachTieuThuyet(String maSach, String tieuDe, String tacGia, String namXuatBan, int soLuong, String viTriKho, 
                          String theLoai, boolean laSachSeries) {
        // Gọi constructor của lớp cha - Yêu cầu Tuần 6: Sử dụng super
        super(maSach, tieuDe, tacGia, namXuatBan, soLuong, viTriKho);
        this.theLoai = theLoai;
        this.laSachSeries = laSachSeries;
    }

    // Getters và Setters cho thuộc tính riêng
    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public boolean isLaSachSeries() {
        return laSachSeries;
    }

    public void setLaSachSeries(boolean laSachSeries) {
        this.laSachSeries = laSachSeries;
    }

    // Ghi đè (Override) phương thức toString() - Yêu cầu Tuần 6, 8
    @Override
    public String toString() {
        String seriesStatus = laSachSeries ? "Series" : "Le";
        return super.toString() + 
               String.format(" %-20s | %-12s | %-15.2f |", 
               theLoai, seriesStatus, TinhGiaBan());
    }

    // Ghi đè (Override) TinhGiaBan() - Yêu cầu Tuần 7, 8
    // SachTieuThuyet: Giá bán = Giá gốc + Giá gốc * 15.000 VNĐ (Nếu là series: Giá gốc tăng thêm 15.000 VNĐ, ngược lại: giữ nguyên)
    @Override
    public double TinhGiaBan() {
        double giaGoc = 45000.0; // Giả sử giá gốc cố định là 45.000 VNĐ
        
        if (laSachSeries) {
            // Giá gốc tăng thêm 15.000 VNĐ
            return giaGoc + 15000.0;
        } else {
            // Giữ nguyên giá gốc
            return giaGoc;
        }
    }
}