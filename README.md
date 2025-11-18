# 🌐 VINBOOK

## ✨ Tổng Quan Dự Án

Đây là một ứng dụng web **mạng xã hội** đầy đủ chức năng, được xây dựng để cung cấp cho người dùng một nền tảng để kết nối, chia sẻ nội dung và tương tác với bạn bè. Ứng dụng mô phỏng các tính năng cốt lõi của một trang mạng xã hội hiện đại, tập trung vào trải nghiệm người dùng mượt mà và hiệu suất đáng tin cậy.

### 🎯 Các Tính Năng Chính

* **Quản Lý Tài Khoản Người Dùng:**
    * Đăng ký tài khoản mới với xác thực email/tên người dùng.
    * Đăng nhập/Đăng xuất an toàn.
    * Cập nhật và quản lý hồ sơ cá nhân (ảnh đại diện, tiểu sử).
* **Quản Lý Bài Đăng (Post):**
    * Tạo, chỉnh sửa và xóa bài đăng (bao gồm văn bản và hình ảnh).
    * Xem **News Feed** hiển thị các bài đăng mới nhất từ bạn bè.
* **Tương Tác Xã Hội (Engagement):**
    * **Like/Bỏ Like** bài viết theo thời gian thực.
    * **Bình Luận (Comment)** và trả lời bình luận.

---

## ⚙️ Cấu Trúc Hệ Thống (Architecture)

Dự án sử dụng kiến trúc **Microservices** đơn giản/kiến trúc **Ba tầng** (Three-Tier Architecture) với giao tiếp qua **RESTful API**.



* **Frontend:** Giao diện người dùng được xây dựng hoàn toàn bằng **ReactJS**, chịu trách nhiệm hiển thị dữ liệu và xử lý tương tác của người dùng.
* **Backend:** Sử dụng **Java Spring Boot**, cung cấp các **API REST** để xử lý logic kinh doanh, xác thực người dùng, và quản lý dữ liệu.
* **Database:** [PostgreSQL/MySQL] được sử dụng để lưu trữ dữ liệu người dùng, bài đăng, lượt thích và bình luận.
* **Deployment:** Sử dụng **Docker** để đóng gói và triển khai ứng dụng.

---

## 🛠️ Công Nghệ Được Sử Dụng

| Hạng Mục | Công Nghệ | Phiên Bản | Mô Tả |
| :--- | :--- | :--- | :--- |
| **Frontend** | **ReactJS** | 18+ | Xây dựng giao diện người dùng. |
| | HTML5/CSS3/JavaScript | ES6+ | Cấu trúc và logic cơ bản. |
| **Backend** | **Java Spring Boot** | 3.x+ | Xây dựng API mạnh mẽ và bảo mật. |
| | **Spring Data JPA** | 3.x+ | Tương tác với cơ sở dữ liệu. |
| | **Spring Security** | 6.x+ | Quản lý xác thực và ủy quyền. |
| **Cơ sở dữ liệu** | **MySQL** | - | Lưu trữ dữ liệu quan hệ. |
| **Triển khai** | **Docker** | - | Đóng gói môi trường nhất quán. |
| **Testing** | **Postman** | - | Kiểm thử API Backend. |

---

## 🤝 Đóng Góp (Contributing)

Mọi sự đóng góp đều được chào đón và đánh giá cao! Nếu bạn có đề xuất cải tiến, phát hiện lỗi hoặc muốn bổ sung tính năng, vui lòng làm theo quy trình sau:

1.  **Fork** Repository này về tài khoản GitHub của bạn.
2.  Tạo một **Branch** mới cho tính năng hoặc sửa lỗi của bạn: 
    ```bash
    git checkout -b feature/tinh-nang-moi
    # hoặc
    git checkout -b fix/sua-loi-dang-nhap
    ```
3.  **Commit** các thay đổi của bạn với thông điệp rõ ràng: 
    ```bash
    git commit -m 'feat: Thêm chức năng chia sẻ bài viết'
    # hoặc
    git commit -m 'fix: Khắc phục lỗi hiển thị avatar'
    ```
4.  **Push** lên Branch bạn vừa tạo: 
    ```bash
    git push origin feature/tinh-nang-moi
    ```
5.  Mở một **Pull Request (PR)** đến branch `main` (hoặc `master`) của repository gốc. Vui lòng mô tả chi tiết các thay đổi trong PR.

---

## 📄 Giấy Phép (License)

Dự án này được phân phối dưới giấy phép **MIT License**. Điều này có nghĩa là bạn được tự do sử dụng, sao chép, sửa đổi, hợp nhất, xuất bản, phân phối và cấp phép lại (sublicense) phần mềm này, miễn là bạn giữ lại thông báo bản quyền và giấy phép ban đầu.

Để biết thêm chi tiết, vui lòng xem nội dung đầy đủ của [MIT License tại đây](https://opensource.org/licenses/MIT) hoặc đoạn trích sau:
