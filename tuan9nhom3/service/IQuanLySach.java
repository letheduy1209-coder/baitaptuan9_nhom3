
import model.Sach;
import core.ISearchCriteria;
import java.util.List;

public interface IQuanLySach {
    void themSach(Sach sach);
    boolean xoaSach(String maSach);
    boolean capNhatSach(String maSach, Sach sachMoi);
    Sach timKiemTheoMaSach(String maSach);
    void hienThiDanhSach();
    List<Sach> getDanhSachSach();
    
    // Phương thức Lọc Nâng cao
    List<Sach> timKiemTheoTieuChi(ISearchCriteria criteria); 
}