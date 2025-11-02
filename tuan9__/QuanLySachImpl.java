//Lớp triển khai IQuanLySach
import Sach;
import SachGiaoTrinh;
import SachTieuThuyet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Yêu cầu Tuần 6: Tạo lớp QuanLySach
// Yêu cầu Tuần 8: Lớp QuanLySachImpl triển khai IQuanLySach
public class QuanLySachImpl implements IQuanLySach {
    // Sử dụng List để quản lý danh sách các loại sách (Tính đa hình)
    private List<Sach> danhSachSach;
    private final Scanner scanner = new Scanner(System.in);

    public QuanLySachImpl() {
        this.danhSachSach = new ArrayList<>();
    }

    // Hàm phụ trợ để tạo sách
    private Sach nhapThongTinSach(boolean isCapNhat) {
        String maSach, tieuDe, tacGia, namXuatBan, viTriKho, monHoc, capDo, theLoai, seriesInput;
        int soLuong;
        boolean laSachSeries;
        int loaiSach;
        
        System.out.println("\n--- Nhập thông tin sách ---");

        if (!isCapNhat) {
            System.out.print("Mã sách (BookID): ");
            maSach = scanner.nextLine();
        } else {
            maSach = "TEMP"; // Dùng tạm, mã sách được truyền vào
        }
        
        System.out.print("Tiêu đề (Title): ");
        tieuDe = scanner.nextLine();
        System.out.print("Tác giả (Author): ");
        tacGia = scanner.nextLine();
        System.out.print("Năm xuất bản (YYYY): ");
        namXuatBan = scanner.nextLine();
        System.out.print("Số lượng (Quantity): ");
        soLuong = Integer.parseInt(scanner.nextLine());
        System.out.print("Vị trí kho (Storage Location): ");
        viTriKho = scanner.nextLine();

        System.out.print("Chọn loại sách (1: Giáo Trình, 2: Tiểu Thuyết): ");
        loaiSach = Integer.parseInt(scanner.nextLine());

        if (loaiSach == 1) { // Sách Giáo Trình
            System.out.print("Môn học: ");
            monHoc = scanner.nextLine();
            System.out.print("Cấp độ (Đại học/Phổ thông): ");
            capDo = scanner.nextLine();
            
            // Nếu là cập nhật, trả về đối tượng mới để thay thế
            if (isCapNhat) maSach = null; // Bỏ qua maSach trong constructor khi cập nhật
            return new SachGiaoTrinh(maSach, tieuDe, tacGia, namXuatBan, soLuong, viTriKho, monHoc, capDo);
        } else if (loaiSach == 2) { // Sách Tiểu Thuyết
            System.out.print("Thể loại: ");
            theLoai = scanner.nextLine();
            System.out.print("Là Sách Series (y/n): ");
            seriesInput = scanner.nextLine();
            laSachSeries = seriesInput.equalsIgnoreCase("y");
            
            // Nếu là cập nhật, trả về đối tượng mới để thay thế
            if (isCapNhat) maSach = null; // Bỏ qua maSach trong constructor khi cập nhật
            return new SachTieuThuyet(maSach, tieuDe, tacGia, namXuatBan, soLuong, viTriKho, theLoai, laSachSeries);
        } else {
            System.err.println("Loại sách không hợp lệ. Thêm/Cập nhật thất bại.");
            return null;
        }
    }


    // Yêu cầu Tuần 6: thêm
    @Override
    public void themSach(Sach sach) {
        if (sach != null) {
            // Kiểm tra trùng mã sách
            if (timKiemTheoMaSach(sach.getMaSach()) != null) {
                System.out.println("LỖI: Mã sách đã tồn tại. Thêm thất bại.");
                return;
            }
            this.danhSachSach.add(sach);
            System.out.println("-> Thêm sách thành công: " + sach.getTieuDe());
        }
    }

    // Yêu cầu Tuần 6: xóa
    @Override
    public boolean xoaSach(String maSach) {
        Sach sachToRemove = timKiemTheoMaSach(maSach);
        if (sachToRemove != null) {
            this.danhSachSach.remove(sachToRemove);
            System.out.println("-> Xóa sách thành công: " + maSach);
            return true;
        }
        System.out.println("LỖI: Không tìm thấy sách với mã: " + maSach);
        return false;
    }

    // Yêu cầu Tuần 6: cập nhật
    @Override
    public boolean capNhatSach(String maSach, Sach sachMoi) {
        for (int i = 0; i < danhSachSach.size(); i++) {
            if (danhSachSach.get(i).getMaSach().equalsIgnoreCase(maSach)) {
                
                // Giữ lại mã sách cũ và gán cho đối tượng mới
                sachMoi.setMaSach(maSach); 
                danhSachSach.set(i, sachMoi);
                System.out.println("-> Cập nhật sách thành công: " + maSach);
                return true;
            }
        }
        System.out.println("LỖI: Không tìm thấy sách với mã: " + maSach + ". Cập nhật thất bại.");
        return false;
    }
    
    // Yêu cầu Tuần 6: tìm kiếm theo maSach
    @Override
    public Sach timKiemTheoMaSach(String maSach) {
        for (Sach sach : danhSachSach) {
            if (sach.getMaSach().equalsIgnoreCase(maSach)) {
                return sach;
            }
        }
        return null;
    }

    // Yêu cầu Tuần 6: hiển thị danh sách
    @Override
    public void hienThiDanhSach() {
        if (danhSachSach.isEmpty()) {
            System.out.println("Danh sách sách hiện đang trống.");
            return;
        }
        
        System.out.println("\n" + "-".repeat(128));
        System.out.println(String.format("| %-10s | %-30s | %-20s | %-15s | %-8s | %-12s | %-20s | %-12s | %-15s |",
                             "MA SACH", "TIEU DE", "TAC GIA", "NAM XB", "SLUONG", "VI TRI KHO", "THUOC TINH RIENG 1", "TT RIENG 2", "GIA BAN"));
        System.out.println("-".repeat(128));

        for (Sach sach : danhSachSach) {
            // Sử dụng tính đa hình: sach.toString() sẽ gọi đến toString() của lớp con tương ứng
            System.out.println(sach.toString()); 
        }
        System.out.println("-".repeat(128));
    }
    
    // Yêu cầu Tuần 8: Phương thức hiển thị thông tin sách được kiểm kê
    public void hienThiSachKiemKe(int soLuongToiThieu) {
        List<Sach> sachCanKiemKe = danhSachSach.stream()
            .filter(sach -> !sach.kiemTraTonKho(soLuongToiThieu))
            .collect(Collectors.toList());
            
        if (sachCanKiemKe.isEmpty()) {
            System.out.println("Tất cả sách đều đạt số lượng tồn kho tối thiểu (" + soLuongToiThieu + ").");
            return;
        }
        
        System.out.println("\n--- DANH SÁCH SÁCH CẦN KIỂM KÊ (SL < " + soLuongToiThieu + ") ---");
        System.out.println(String.format("| %-10s | %-30s | %-8s | %-12s |",
                             "MA SACH", "TIEU DE", "SLUONG", "VI TRI KHO"));
        System.out.println("-".repeat(66));
        
        for (Sach sach : sachCanKiemKe) {
            System.out.println(String.format("| %-10s | %-30s | %-8d | %-12s |",
                             sach.getMaSach(), sach.getTieuDe(), sach.getSoLuong(), sach.getViTriKho()));
        }
        System.out.println("-".repeat(66));
    }

    @Override
    public List<Sach> getDanhSachSach() {
        return danhSachSach;
    }
}