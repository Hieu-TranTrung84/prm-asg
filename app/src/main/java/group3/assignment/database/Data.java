package group3.assignment.database;

public class Data {
    public static final String INSERT_EMPLOYEE = "INSERT INTO Employee(idEmployee, name, password) VALUES " +
            "('admin', 'Nguyen Admin', 'admin')," +
            "('namnv', 'Nguyen Van Nam', '123456')," +
            "('teonv', 'Nguyen Van Teo', '123456')," +
            "('nott', 'Tran Thi No', '123456')";

    public static final String INSERT_MEMBER = "INSERT INTO Member (name, dob) VALUES " +
            "('Trần Tuấn Công','2000')," +
            "('Lý Ngọc Sang','2000')," +
            "('Nguyễn Đình Thi','2000')," +
            "('Trần Quốc Thông','2000')," +
            "('Trần Ngọc Trà','2000')," +
            "('Đoàn Hữu Đoan','2000')," +
            "('Hoàng Minh Phú','2000')," +
            "('Lê Gia Bảo','2000')," +
            "('Lê Văn Minh Ngọc','2000')," +
            "('Dương Quang Nhân Lực','2000')," +
            "('Đinh Viết Phong','2000')," +
            "('Nguyễn Thái Quý','2000')," +
            "('Trần Văn Danh','2000')," +
            "('Trần Tiến Đạt','2000')," +
            "('Nguyễn Hưng','2000')," +
            "('Huỳnh Quang Trường','2000')," +
            "('Nguyễn Hoàng Hưng','2000')," +
            "('Trần Quốc Phương','2000')," +
            "('Nguyễn Văn Nhật','2000')," +
            "('Hồ Nhật Đức','2000')," +
            "('Phan Minh Nhân','2000')," +
            "('Nguyễn Chí Cường','2000')," +
            "('Nguyễn Nam Hiếu','2000')," +
            "('Nguyễn Phan Nhật Nguyên','2000')," +
            "('Nguyễn Thanh Tâm','2000')," +
            "('Hoàng Hồng Phúc','2000')," +
            "('Chế Văn Linh','2000')," +
            "('Nguyễn Hồng Nam','2000')," +
            "('Nguyễn Lương Hoàng Vĩ','2000')," +
            "('Đào Duy Hận','2000')," +
            "('Trần Đình Toàn','2000')," +
            "('Đoàn Văn Lộc','2000')," +
            "('Lê Quang Cao Nguyên','2000')," +
            "('Châu Minh Hiếu','2000')," +
            "('Lý Quang Cường','2000')," +
            "('Nguyễn Văn Tấn','2000')," +
            "('Nguyễn Thái Luật','2000')," +
            "('Lê Thị Thanh Trúc','2000')," +
            "('Trần Thị Ngọc Hương','2000')";

    public static final String INSERT_BOOK_CATEGORY = "INSERT INTO BookCategory (nameBookCategory) VALUES " +
            "('Tieng Anh co ban')," +
            "('Tieng Anh nang cao')," +
            "('Lap trinh co ban')," +
            "('Lap trinh android')," +
            "('Lap trinh java')," +
            "('Lap trinh web')";
    public static final String INSERT_BOOK = "INSERT INTO Book (name, rent, idBookCategory) VALUES " +
            "('lap trinh java co ban',2000,'5')," +
            "('lap trinh java nang cao',2000,'5')," +
            "('lap trinh mang voi java',2000,'5')," +
            "('lap trinh destop voi Swing',2000,'3')," +
            "('Du an voi cong nghe MS.NET MVC',2000,'1')," +
            "('Du an voi cong nghe Spring MVC',2000,'1')," +
            "('Du an voi cong nghe Servlet/JSP',2000,'5')," +
            "('Du an voi cong nghe Swing',2000,'6')," +
            "('Du an voi cong nghe WindowForm',2000,'1')," +
            "('Du an voi cong nghe SQl server',2000,'3')," +
            "('Lap trinh MS.NET MVC',2000,'1')," +
            "('Lap trinh Spring MVC',2000,'1')," +
            "('Lap trinh Servlet/JSP',2000,'5')," +
            "('Lap trinh Swing',2000,'6')," +
            "('Lap trinh WindowForm',2000,'1')," +
            "('Lap trinh SQl server',2000,'3')";
    public static final String INSERT_CARD = "INSERT INTO Card (idEmployee, idMember, idBook, price, date, returnBook) VALUES " +
            "('admin', 2, 1 ,2000, '2021/10/07', 1)," +
            "('admin', 3, 2 ,2000, '2021/10/08', 0)," +
            "('admin', 4, 3 ,2000, '2021/10/09', 0)," +
            "('admin', 5, 4 ,2000, '2021/10/10', 1)," +
            "('admin', 6, 5 ,2000, '2021/10/11', 1)," +
            "('admin', 7, 6 ,2000, '2021/10/12', 0)," +
            "('admin', 8, 7 ,2000, '2021/10/13', 0)," +
            "('admin', 9, 8 ,2000, '2021/10/14', 1)," +
            "('admin', 10, 9 ,2000, '2021/10/15', 0)," +
            "('admin', 11, 10 ,2000, '2021/10/16', 0)," +
            "('admin', 12, 11 ,2000, '2021/10/17', 1)," +
            "('admin', 13, 12 ,2000, '2021/10/18', 0)," +
            "('admin', 14, 13 ,2000, '2021/10/19', 1)," +
            "('admin', 15, 14 ,2000, '2021/10/20', 1)," +
            "('admin', 16, 15 ,2000, '2021/10/21', 0)," +
            "('admin', 17, 16 ,2000, '2021/10/22', 0)," +
            "('admin', 18, 1 ,2000, '2021/10/23', 1)," +
            "('admin', 19, 2 ,2000, '2021/10/24', 0)," +
            "('admin', 21, 3 ,2000, '2021/10/25', 0)," +
            "('admin', 22, 4 ,2000, '2021/10/25', 1)," +
            "('admin', 23, 5 ,2000, '2021/11/02', 0)," +
            "('admin', 24, 6 ,2000, '2021/11/03', 1)," +
            "('admin', 25, 7 ,2000, '2021/11/04', 1)," +
            "('admin', 26, 8 ,2000, '2021/11/05', 0)";
}
