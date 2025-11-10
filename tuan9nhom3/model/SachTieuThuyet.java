

public class SachTieuThuyet extends Sach {
    private String theLoai;
    private boolean laSachSeries;

    public SachTieuThuyet(String maSach, String tieuDe, String tacGia, String namXuatBan, int soLuong, String viTriKho, 
                          String theLoai, boolean laSachSeries) {
        super(maSach, tieuDe, tacGia, namXuatBan, soLuong, viTriKho);
        this.theLoai = theLoai;
        this.laSachSeries = laSachSeries;
    }

    public String getTheLoai() { return theLoai; }
    public boolean isLaSachSeries() { return laSachSeries; }

    @Override
    public double TinhGiaBan() {
        double giaGoc = 45000.0;
        if (laSachSeries) {
            return giaGoc + 15000.0;
        } else {
            return giaGoc;
        }
    }

    @Override
    public String toString() {
        String seriesStatus = laSachSeries ? "Series" : "Le";
        return super.toString() + 
               String.format(" %-20s | %-12s | %-15.2f |", 
               theLoai, seriesStatus, TinhGiaBan());
    }
}