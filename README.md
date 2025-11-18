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


