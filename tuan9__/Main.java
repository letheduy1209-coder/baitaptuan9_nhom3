// lớp test
import SachGiaoTrinh;
import SachTieuThuyet;
import IQuanLySach;
import QuanLySachImpl;
import java.util.Scanner;
// ... các import khác nếu cầnimport java.util.Scanner;



// Yêu cầu Tuần 5, 6, 7, 8: Định nghĩa lớp Test (Main)
public class Main {
    private static IQuanLySach quanLySach;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Yêu cầu Tuần 8: Khởi tạo đối tượng quản lý sách
        quanLySach = new QuanLySachImpl();

        // Khởi tạo dữ liệu mẫu
        khoiTaoDuLieuMau();

        // Chạy menu chương trình
        hienThiMenu();
    }
    
    // Khởi tạo dữ liệu mẫu ban đầu
    private static void khoiTaoDuLieuMau() {
        System.out.println("-> Khởi tạo dữ liệu mẫu...");
        // SachGiaoTrinh (maSach, tieuDe, tacGia, namXuatBan, soLuong, viTriKho, monHoc, capDo)
        quanLySach.themSach(new SachGiaoTrinh("GT001", "Cơ Sở Lập Trình", "Nguyễn Văn A", "2023", 15, "Khu A-01", "Tin Học", "Đại học"));
        quanLySach.themSach(new SachGiaoTrinh("GT002", "Giải Tích 1", "Trần Thị B", "2020", 5, "Khu A-02", "Toán", "Đại học"));
        
        // SachTieuThuyet (maSach, tieuDe, tacGia, namXuatBan, soLuong, viTriKho, theLoai, laSachSeries)
        quanLySach.themSach(new SachTieuThuyet("TT001", "Hoàng Hôn Phương Đông", "Lê Văn C", "2024", 25, "Khu B-10", "Lãng Mạn", false));
        quanLySach.themSach(new SachTieuThuyet("TT002", "Người Du Hành Không Gian", "Phạm Thu D", "2021", 8, "Khu B-11", "Khoa Học Viễn Tưởng", true));
        System.out.println("-> Hoàn tất khởi tạo dữ liệu mẫu.");
    }
    
    // Hiển thị Menu - Yêu cầu Tuần 8
    private static void hienThiMenu() {
        int luaChon;
        do {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("CHƯƠNG TRÌNH QUẢN LÝ SÁCH");
            System.out.println("=".repeat(40));
            System.out.println("1. Thêm sách mới");
            System.out.println("2. Hiển thị danh sách sách");
            System.out.println("3. Tìm kiếm sách theo Mã sách");
            System.out.println("4. Cập nhật thông tin sách theo Mã sách");
            System.out.println("5. Xóa sách theo Mã sách");
            System.out.println("6. Kiểm kê và quản lý kho");
            System.out.println("0. Thoát chương trình");
            System.out.print("Nhập lựa chọn của bạn: ");
            
            try {
                luaChon = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                luaChon = -1; // Đặt giá trị không hợp lệ
            }

            switch (luaChon) {
                case 1:
                    thucHienThemSach();
                    break;
                case 2:
                    quanLySach.hienThiDanhSach();
                    break;
                case 3:
                    thucHienTimKiem();
                    break;
                case 4:
                    thucHienCapNhat();
                    break;
                case 5:
                    thucHienXoa();
                    break;
                case 6:
                    thucHienKiemKe();
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (luaChon != 0);
    }
    
    // Các hàm thực thi chức năng
    private static void thucHienThemSach() {
        System.out.print("Bạn muốn thêm loại sách nào? (1: Giáo Trình, 2: Tiểu Thuyết): ");
        try {
            int loai = Integer.parseInt(scanner.nextLine());
            System.out.print("Mã sách: ");
            String maSach = scanner.nextLine();
            System.out.print("Tiêu đề: ");
            String tieuDe = scanner.nextLine();
            System.out.print("Tác giả: ");
            String tacGia = scanner.nextLine();
            System.out.print("Năm xuất bản: ");
            String namXB = scanner.nextLine();
            System.out.print("Số lượng: ");
            int soLuong = Integer.parseInt(scanner.nextLine());
            System.out.print("Vị trí kho: ");
            String viTriKho = scanner.nextLine();
            
            Sach sachMoi = null;
            if (loai == 1) {
                System.out.print("Môn học: ");
                String monHoc = scanner.nextLine();
                System.out.print("Cấp độ: ");
                String capDo = scanner.nextLine();
                sachMoi = new SachGiaoTrinh(maSach, tieuDe, tacGia, namXB, soLuong, viTriKho, monHoc, capDo);
            } else if (loai == 2) {
                System.out.print("Thể loại: ");
                String theLoai = scanner.nextLine();
                System.out.print("Là Sách Series (y/n): ");
                boolean isSeries = scanner.nextLine().equalsIgnoreCase("y");
                sachMoi = new SachTieuThuyet(maSach, tieuDe, tacGia, namXB, soLuong, viTriKho, theLoai, isSeries);
            } else {
                System.out.println("Loại sách không hợp lệ.");
                return;
            }
            
            if (sachMoi != null) {
                quanLySach.themSach(sachMoi);
            }
        } catch (Exception e) {
            System.err.println("Lỗi nhập liệu: " + e.getMessage());
        }
    }
    
    private static void thucHienTimKiem() {
        System.out.print("Nhập mã sách cần tìm: ");
        String maSach = scanner.nextLine();
        Sach sach = quanLySach.timKiemTheoMaSach(maSach);
        if (sach != null) {
            System.out.println("-> Tìm thấy sách:");
            sach.inThongTinCoBan();
            System.out.println("Giá bán ước tính: " + sach.TinhGiaBan() + " VNĐ");
        } else {
            System.out.println("Không tìm thấy sách với mã: " + maSach);
        }
    }
    
    private static void thucHienCapNhat() {
        System.out.print("Nhập mã sách cần cập nhật: ");
        String maSach = scanner.nextLine();
        Sach sachCu = quanLySach.timKiemTheoMaSach(maSach);
        
        if (sachCu == null) {
            System.out.println("Không tìm thấy sách với mã: " + maSach + ". Cập nhật thất bại.");
            return;
        }

        System.out.println("--- Nhập thông tin mới cho sách " + maSach + " ---");
        // Giả lập nhập thông tin mới để tạo đối tượng sáchMoi
        // Đây là phần cần code lại chi tiết hơn để đảm bảo không mất dữ liệu của các thuộc tính riêng
        
        // Ví dụ đơn giản: Yêu cầu nhập lại toàn bộ thông tin
        System.out.print("Tiêu đề mới: ");
        String tieuDe = scanner.nextLine();
        System.out.print("Tác giả mới: ");
        String tacGia = scanner.nextLine();
        System.out.print("Năm xuất bản mới: ");
        String namXB = scanner.nextLine();
        System.out.print("Số lượng mới: ");
        int soLuong = Integer.parseInt(scanner.nextLine());
        System.out.print("Vị trí kho mới: ");
        String viTriKho = scanner.nextLine();
        
        Sach sachMoi = null;
        if (sachCu instanceof SachGiaoTrinh) {
            System.out.print("Môn học mới: ");
            String monHoc = scanner.nextLine();
            System.out.print("Cấp độ mới: ");
            String capDo = scanner.nextLine();
            sachMoi = new SachGiaoTrinh(maSach, tieuDe, tacGia, namXB, soLuong, viTriKho, monHoc, capDo);
        } else if (sachCu instanceof SachTieuThuyet) {
            System.out.print("Thể loại mới: ");
            String theLoai = scanner.nextLine();
            System.out.print("Là Sách Series (y/n) mới: ");
            boolean isSeries = scanner.nextLine().equalsIgnoreCase("y");
            sachMoi = new SachTieuThuyet(maSach, tieuDe, tacGia, namXB, soLuong, viTriKho, theLoai, isSeries);
        }
        
        if (sachMoi != null) {
            quanLySach.capNhatSach(maSach, sachMoi);
        }
    }

    private static void thucHienXoa() {
        System.out.print("Nhập mã sách cần xóa: ");
        String maSach = scanner.nextLine();
        quanLySach.xoaSach(maSach);
    }
    
    private static void thucHienKiemKe() {
        System.out.println("\n--- CHỨC NĂNG KIỂM KÊ VÀ KHO HÀNG ---");
        System.out.print("Nhập số lượng tồn kho tối thiểu (vd: 10): ");
        int minSL = Integer.parseInt(scanner.nextLine());
        
        // Yêu cầu Tuần 8: Hiển thị sách cần kiểm kê (số lượng < tối thiểu)
        ((QuanLySachImpl) quanLySach).hienThiSachKiemKe(minSL);
        
        System.out.println("\nBạn có muốn cập nhật vị trí cho một cuốn sách không? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Nhập mã sách cần cập nhật vị trí: ");
            String maSach = scanner.nextLine();
            Sach sach = quanLySach.timKiemTheoMaSach(maSach);
            
            if (sach != null) {
                System.out.print("Nhập vị trí kho mới: ");
                String viTriMoi = scanner.nextLine();
                // Yêu cầu Tuần 8: Sử dụng phương thức capNhatViTri()
                sach.capNhatViTri(viTriMoi);
            } else {
                System.out.println("Không tìm thấy sách.");
            }
        }
    }
}