import model.Sach;
import model.SachGiaoTrinh;
import model.SachTieuThuyet;
import service.IQuanLySach;
import service.QuanLySachImpl;
import core.ISearchCriteria;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static IQuanLySach quanLySach;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        quanLySach = new QuanLySachImpl();
        khoiTaoDuLieuMau();
        hienThiMenu();
    }
    
    private static void khoiTaoDuLieuMau() {
        System.out.println("-> Khởi tạo dữ liệu mẫu...");
        quanLySach.themSach(new SachGiaoTrinh("GT001", "Cơ Sở Lập Trình", "Nguyễn Văn A", "2023", 15, "Khu A-01", "Tin Học", "Đại học"));
        quanLySach.themSach(new SachGiaoTrinh("GT002", "Giải Tích 1", "Trần Thị B", "2020", 5, "Khu A-02", "Toán", "Đại học"));
        quanLySach.themSach(new SachTieuThuyet("TT001", "Hoàng Hôn Phương Đông", "Lê Văn C", "2024", 25, "Khu B-10", "Lãng Mạn", false));
        quanLySach.themSach(new SachTieuThuyet("TT002", "Người Du Hành Không Gian", "Phạm Thu D", "2021", 8, "Khu B-11", "Khoa Học Viễn Tưởng", true));
        System.out.println("-> Hoàn tất khởi tạo dữ liệu mẫu.");
    }
    
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
            System.out.println("7. Tìm kiếm nâng cao theo tiêu chí (NEW)");
            System.out.println("0. Thoát chương trình");
            System.out.print("Nhập lựa chọn của bạn: ");
            
            try {
                luaChon = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                luaChon = -1; 
            }

            switch (luaChon) {
                case 1: thucHienThemSach(); break;
                case 2: quanLySach.hienThiDanhSach(); break;
                case 3: thucHienTimKiem(); break;
                case 4: thucHienCapNhat(); break;
                case 5: thucHienXoa(); break;
                case 6: thucHienKiemKe(); break;
                case 7: thucHienTimKiemNangCao(); break; // <-- Gọi hàm Lọc Nâng cao
                case 0: System.out.println("Đã thoát chương trình. Tạm biệt!"); break;
                default: System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (luaChon != 0);
    }

    // TÍNH NĂNG MỚI: Lọc theo tiêu chí
    private static void thucHienTimKiemNangCao() {
        System.out.println("\n--- TÌM KIẾM NÂNG CAO ---");
        System.out.println("1. Tìm sách có số lượng < 10 (Sách tồn kho thấp).");
        System.out.println("2. Tìm sách Giáo Trình xuất bản từ 2023 trở lên.");
        System.out.print("Chọn tiêu chí lọc (1/2): ");
        
        String choice = scanner.nextLine();
        List<Sach> ketQua;
        ISearchCriteria criteria; 
        
        if (choice.equals("1")) {
            // Lambda 1: Tiêu chí lọc sách có số lượng < 10
            criteria = sach -> sach.getSoLuong() < 10;
            System.out.println("-> Kết quả lọc sách tồn kho thấp:");
        } else if (choice.equals("2")) {
            // Lambda 2: Tiêu chí lọc sách Giáo Trình VÀ Năm XB >= 2023
            criteria = sach -> 
                sach instanceof SachGiaoTrinh && Integer.parseInt(sach.getNamXuatBan()) >= 2023;
            System.out.println("-> Kết quả lọc Sách Giáo Trình mới:");
        } else {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        ketQua = quanLySach.timKiemTheoTieuChi(criteria);

        if (ketQua.isEmpty()) {
            System.out.println("Không tìm thấy sách nào thỏa mãn tiêu chí.");
            return;
        }
        
        for (Sach sach : ketQua) {
            System.out.println("   [ID: " + sach.getMaSach() + "] " + sach.getTieuDe() + " (SL: " + sach.getSoLuong() + ")");
        }
    }
    
    // ... (Các hàm khác như thucHienThemSach, thucHienTimKiem, thucHienCapNhat, thucHienXoa, thucHienKiemKe như trong các file trước đó) ...
    private static void thucHienThemSach() {
         try {
            System.out.print("Bạn muốn thêm loại sách nào? (1: Giáo Trình, 2: Tiểu Thuyết): ");
            int loai = Integer.parseInt(scanner.nextLine());
            System.out.print("Mã sách: ");
            String maSach = scanner.nextLine();
            
            System.out.print("Tiêu đề: "); String tieuDe = scanner.nextLine();
            System.out.print("Tác giả: "); String tacGia = scanner.nextLine();
            System.out.print("Năm xuất bản: "); String namXB = scanner.nextLine();
            System.out.print("Số lượng: "); int soLuong = Integer.parseInt(scanner.nextLine());
            System.out.print("Vị trí kho: "); String viTriKho = scanner.nextLine();
            
            Sach sachMoi = null;
            if (loai == 1) {
                System.out.print("Môn học: "); String monHoc = scanner.nextLine();
                System.out.print("Cấp độ: "); String capDo = scanner.nextLine();
                sachMoi = new SachGiaoTrinh(maSach, tieuDe, tacGia, namXB, soLuong, viTriKho, monHoc, capDo);
            } else if (loai == 2) {
                System.out.print("Thể loại: "); String theLoai = scanner.nextLine();
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
            System.err.println("Lỗi nhập liệu: Vui lòng kiểm tra lại định dạng số và chuỗi.");
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

        try {
            System.out.println("--- Nhập thông tin mới cho sách " + maSach + " ---");
            System.out.print("Tiêu đề mới: "); String tieuDe = scanner.nextLine();
            System.out.print("Tác giả mới: "); String tacGia = scanner.nextLine();
            System.out.print("Năm xuất bản mới: "); String namXB = scanner.nextLine();
            System.out.print("Số lượng mới: "); int soLuong = Integer.parseInt(scanner.nextLine());
            System.out.print("Vị trí kho mới: "); String viTriKho = scanner.nextLine();
            
            Sach sachMoi = null;
            if (sachCu instanceof SachGiaoTrinh) {
                System.out.print("Môn học mới: "); String monHoc = scanner.nextLine();
                System.out.print("Cấp độ mới: "); String capDo = scanner.nextLine();
                sachMoi = new SachGiaoTrinh(maSach, tieuDe, tacGia, namXB, soLuong, viTriKho, monHoc, capDo);
            } else if (sachCu instanceof SachTieuThuyet) {
                System.out.print("Thể loại mới: "); String theLoai = scanner.nextLine();
                System.out.print("Là Sách Series (y/n) mới: ");
                boolean isSeries = scanner.nextLine().equalsIgnoreCase("y");
                sachMoi = new SachTieuThuyet(maSach, tieuDe, tacGia, namXB, soLuong, viTriKho, theLoai, isSeries);
            }
            
            if (sachMoi != null) {
                quanLySach.capNhatSach(maSach, sachMoi);
            }
        } catch (Exception e) {
             System.err.println("Lỗi nhập liệu: Vui lòng kiểm tra lại định dạng số và chuỗi.");
        }
    }

    private static void thucHienXoa() {
        System.out.print("Nhập mã sách cần xóa: ");
        String maSach = scanner.nextLine();
        quanLySach.xoaSach(maSach);
    }
    
    private static void thucHienKiemKe() {
        try {
            System.out.println("\n--- CHỨC NĂNG KIỂM KÊ VÀ KHO HÀNG ---");
            System.out.print("Nhập số lượng tồn kho tối thiểu (vd: 10): ");
            int minSL = Integer.parseInt(scanner.nextLine());
            
            ((QuanLySachImpl) quanLySach).hienThiSachKiemKe(minSL);
            
            System.out.println("\nBạn có muốn cập nhật vị trí cho một cuốn sách không? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                System.out.print("Nhập mã sách cần cập nhật vị trí: ");
                String maSach = scanner.nextLine();
                Sach sach = quanLySach.timKiemTheoMaSach(maSach);
                
                if (sach != null) {
                    System.out.print("Nhập vị trí kho mới: ");
                    String viTriMoi = scanner.nextLine();
                    sach.capNhatViTri(viTriMoi);
                } else {
                    System.out.println("Không tìm thấy sách.");
                }
            }
        } catch (Exception e) {
             System.err.println("Lỗi nhập liệu: Vui lòng nhập số nguyên cho số lượng tối thiểu.");
        }
    }
}