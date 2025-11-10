

import model.Sach;
import model.SachGiaoTrinh;
import model.SachTieuThuyet;
import core.ISearchCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuanLySachImpl implements IQuanLySach {
    private List<Sach> danhSachSach = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void themSach(Sach sach) {
        if (sach != null) {
            if (timKiemTheoMaSach(sach.getMaSach()) != null) {
                System.out.println("LỖI: Mã sách đã tồn tại. Thêm thất bại.");
                return;
            }
            this.danhSachSach.add(sach);
            System.out.println("-> Thêm sách thành công: " + sach.getTieuDe());
        }
    }

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

    @Override
    public boolean capNhatSach(String maSach, Sach sachMoi) {
        for (int i = 0; i < danhSachSach.size(); i++) {
            if (danhSachSach.get(i).getMaSach().equalsIgnoreCase(maSach)) {
                sachMoi.setMaSach(maSach); 
                danhSachSach.set(i, sachMoi);
                System.out.println("-> Cập nhật sách thành công: " + maSach);
                return true;
            }
        }
        System.out.println("LỖI: Không tìm thấy sách với mã: " + maSach + ". Cập nhật thất bại.");
        return false;
    }
    
    @Override
    public Sach timKiemTheoMaSach(String maSach) {
        for (Sach sach : danhSachSach) {
            if (sach.getMaSach().equalsIgnoreCase(maSach)) {
                return sach;
            }
        }
        return null;
    }

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
            System.out.println(sach.toString()); 
        }
        System.out.println("-".repeat(128));
    }
    
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
    
    // TÍNH NĂNG MỚI: Triển khai Lọc theo Tiêu chí
    @Override
    public List<Sach> timKiemTheoTieuChi(ISearchCriteria criteria) {
        return danhSachSach.stream()
            .filter(criteria::matches)
            .collect(Collectors.toList());
    }
}