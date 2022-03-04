package group3.assignment.database;

public class Data {
    public static final String INSERT_EMPLOYEE = "INSERT INTO Employee(idEmployee, name, password) VALUES " +
            "('admin', 'Nguyen Admin', 'admin')," +
            "('namnv', 'Nguyen Van Nam', '123456')," +
            "('teonv', 'Nguyen Van Teo', '123456')," +
            "('nott', 'Tran Thi No', '123456')";

    public static final String INSERT_MEMBER = "INSERT INTO Member (name, dob) VALUES " +
            "('Trần Tuấn Công','2000')," +
            "('Lý Ngọc Sang','1997')," +
            "('Nguyễn Đình Thi','1998')," +
            "('Trần Quốc Thông','1987')," +
            "('Trần Ngọc Trà','1986')," +
            "('Đoàn Hữu Đoan','2000')," +
            "('Hoàng Minh Phú','2001')," +
            "('Lê Gia Bảo','2002')," +
            "('Lê Văn Minh Ngọc','2003')," +
            "('Dương Quang Nhân Lực','2001')," +
            "('Đinh Viết Phong','2000')," +
            "('Nguyễn Thái Quý','2000')," +
            "('Trần Văn Danh','2006')," +
            "('Trần Tiến Đạt','1998')," +
            "('Nguyễn Hưng','1999')," +
            "('Huỳnh Quang Trường','2000')," +
            "('Nguyễn Hoàng Hưng','2003')," +
            "('Trần Quốc Phương','2000')," +
            "('Nguyễn Văn Nhật','2006')," +
            "('Hồ Nhật Đức','1999')," +
            "('Phan Minh Nhân','2003')," +
            "('Nguyễn Chí Cường','1998')," +
            "('Nguyễn Nam Hiếu','1988')," +
            "('Nguyễn Hồng Nam','1987')," +
            "('Trần Đình Toàn','2001')," +
            "('Đoàn Văn Lộc','2008')," +
            "('Nguyễn Thái Luật','1997')," +
            "('Lê Thị Thanh Trúc','1999')," +
            "('Trần Thị Ngọc Hương','2000')";

    public static final String INSERT_BOOK_CATEGORY = "INSERT INTO BookCategory (nameBookCategory) VALUES " +
            "('Tiếng Anh cơ bản')," +
            "('Tiếng Anh nâng cao')," +
            "('Lập trình cơ bản')," +
            "('Lập trình nâng cao')," +
            "('Lập trình android')," +
            "('Lập trình java')," +
            "('Thơ')," +
            "('Tiểu thuyết')," +
            "('Tạp chí')," +
            "('Lập trình web')";
    public static final String INSERT_BOOK = "INSERT INTO Book (name, rent, idBookCategory) VALUES " +
            "('Lập trình java cơ bản',2000,'3')," +
            "('Lập trình java nâng cao',2500,'6')," +
            "('lap trinh mang voi java',1000,'5')," +
            "('Lập trình Desktop vời Swing',2000,'3')," +
            "('Tiếng Anh cho bé',300,'1')," +
            "('Ôn tập tiếng anh giao tiếp',5000,'2')," +
            "('Lập trình Python',2000,'3')," +
            "('Dự án công nghệ Servlet/JSP',7000,'5')," +
            "('Nghe tiếng anh cho bé',2000,'1')," +
            "('Dự án công nghệ Springboot',2000,'6')," +
            "('Design Pattern Java',2000,'4')," +
            "('Design Pattern Python',2000,'4')," +
            "('Design Pattern C#',2000,'4')," +
            "('Design Pattern Go',2000,'4')," +
            "('Thơ Nhật',1000,'7')," +
            "('Thơ Trung Quốc',1500,'7')," +
            "('Tuyển tập 100 bài thơ hay',2000,'7')," +
            "('Những bài thơ hay nhất của Xuân Quỳnh',2000,'7')," +
            "('Tạp chí du lịch',3000,'9')," +
            "('Tạp chí kinh tế',3500,'9')," +
            "('Tạp chí văn hoá',4500,'9')," +
            "('Không gia đình',5000,'8')," +
            "('Ông già và biển cả',7000,'8')," +
            "('Rừng Na Uy',8000,'8')," +
            "('Hồng Lâu Mộng',7000,'8')," +
            "('Số đỏ',2000,'8')," +
            "('Lập trình SQL server',2000,'3')";
    public static final String INSERT_CARD = "INSERT INTO Card (idEmployee, idMember, idBook, price, date, returnBook) VALUES " +
            "('admin', 1, 1 ,2000, '2022/01/07', 1)," +
            "('admin', 2, 2 ,2500, '2022/02/08', 0)," +
            "('admin', 3, 3 ,1000, '2022/01/09', 0)," +
            "('admin', 4, 4 ,2000, '2022/02/10', 1)," +
            "('admin', 5, 5 ,300, '2022/01/11', 1)," +
            "('admin', 6, 6 ,5000, '2022/01/12', 0)," +
            "('admin', 7, 7 ,2000, '2022/01/13', 0)," +
            "('admin', 8, 8 ,2000, '2022/02/14', 1)," +
            "('admin', 9, 9 ,2000, '2022/01/15', 0)," +
            "('admin', 10, 10 ,2000, '2022/02/16', 0)," +
            "('admin', 11, 11 ,2000, '2022/02/17', 1)," +
            "('admin', 12, 12 ,2000, '2022/01/18', 0)," +
            "('admin', 13, 13 ,2000, '2022/02/18', 0)," +
            "('admin', 14, 14 ,2000, '2022/01/05', 0)";
}
