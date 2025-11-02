// Quản lý sách


import Sach;
import java.util.List;

// Yêu cầu Tuần 8: Tạo Interface IQuanLySach
public interface IQuanLySach {
    // Thêm một cuốn sách mới vào danh sách
    void themSach(Sach sach);

    // Xóa một cuốn sách theo mã sách
    boolean xoaSach(String maSach);

    // Cập nhật thông tin của một cuốn sách theo mã sách
    boolean capNhatSach(String maSach, Sach sachMoi);

    // Tìm kiếm sách theo mã sách
    Sach timKiemTheoMaSach(String maSach);

    // Hiển thị toàn bộ danh sách sách
    void hienThiDanhSach();

    // Lấy danh sách sách để thực hiện các chức năng khác nếu cần
    List<Sach> getDanhSachSach();
}