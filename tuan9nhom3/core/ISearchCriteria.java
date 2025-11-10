
import model.Sach;

@FunctionalInterface
public interface ISearchCriteria {
    // Phương thức kiểm tra xem cuốn sách có thỏa mãn tiêu chí không
    boolean matches(Sach sach);
}