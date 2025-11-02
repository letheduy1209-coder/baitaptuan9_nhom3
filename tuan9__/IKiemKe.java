// Kiểm kê kho


// Yêu cầu Tuần 8: Thiết kế Interface IKiemKe
public interface IKiemKe {
    // Phương thức: boolean kiemTraTonKho(int soLuongToiThieu);
    // Kiểm tra xem số lượng tồn kho có đủ so với số lượng tối thiểu hay không.
    boolean kiemTraTonKho(int soLuongToiThieu);

    // Phương thức: void capNhatViTri(String viTriMoi);
    // Cập nhật vị trí lưu trữ trong kho của cuốn sách
    void capNhatViTri(String viTriMoi);
}