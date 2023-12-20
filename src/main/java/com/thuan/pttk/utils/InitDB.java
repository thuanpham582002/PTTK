package com.thuan.pttk.utils;

import java.util.List;

import com.thuan.pttk.entity.Role;
import com.thuan.pttk.entity.User;
import com.thuan.pttk.entity.book.Author;
import com.thuan.pttk.entity.book.Book;
import com.thuan.pttk.entity.book.Publisher;
import com.thuan.pttk.entity.clothes.Clothes;
import com.thuan.pttk.entity.clothes.Origin;
import com.thuan.pttk.entity.clothes.Trademark;
import com.thuan.pttk.entity.cutstomer.Address;
import com.thuan.pttk.entity.cutstomer.Customer;
import com.thuan.pttk.entity.cutstomer.Fullname;
import com.thuan.pttk.entity.electronic.Computer;
import com.thuan.pttk.entity.electronic.Mobile;
import com.thuan.pttk.entity.shoes.ForMan;
import com.thuan.pttk.entity.shoes.ForWoman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thuan.pttk.repository.AuthorRepository;
import com.thuan.pttk.repository.BookRepository;
import com.thuan.pttk.repository.ClothesRepository;
import com.thuan.pttk.repository.CustomerRepository;
import com.thuan.pttk.repository.ElectronicRepository;
import com.thuan.pttk.repository.PublisherRepository;
import com.thuan.pttk.repository.RoleRepository;
import com.thuan.pttk.repository.ShoesRepository;
import com.thuan.pttk.repository.UserRepository;

@Service
public class InitDB implements CommandLineRunner {

        @Autowired
        private UserRepository userRepository;
        @Autowired
        private RoleRepository roleRepository;
        @Autowired
        private CustomerRepository customerRepository;
        @Autowired
        private BookRepository bookRepository;
        @Autowired
        private ClothesRepository clothesRepository;
        @Autowired
        private ElectronicRepository electronicRepository;
        @Autowired
        private ShoesRepository shoesRepository;
        @Autowired
        private AuthorRepository authorRepository;
        @Autowired
        private PublisherRepository publisherRepository;
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) throws Exception {
                // init a role and a user
                Role rolecustomer = Role.builder().name("ROLE_CUSTOMER").build();
                rolecustomer = roleRepository.save(rolecustomer);

                User user = User.builder().username("user")
                                .password(passwordEncoder.encode("user"))
                                .accountNonExpired(true).accountNonLocked(true)
                                .credentialsNonExpired(true).isEnabled(true)
                                .roles(List.of(rolecustomer))
                                .build();
                user = userRepository.save(user);

                Fullname fullName = Fullname.builder().firstName("Nguyen").middleName("Van").lastName("A").build();
                Address address = Address.builder().city("Ha Noi").district("Ha Dong").nation("Viet Nam")
                                .numberHouse(20)
                                .street("Nguyen Trai").build();
                Customer customer = Customer.builder().address(address).fullname(fullName)
                                .user(user)
                                .build();
                customerRepository.save(customer);

                Author author = Author.builder().biography("Tieu su").name("Nguyen Thi B").nation("Viet Nam").build();
                authorRepository.save(author);
                Publisher publisher = Publisher.builder().address("Tran Phu - Ha Dong - Ha Noi")
                                .name("Nha san xuat so 1")
                                .build();
                publisherRepository.save(publisher);
                Book book1 = Book.builder().author(author).description("Mo ta sach 1").publisher(publisher).quantity(50)
                                .size("A4").title("Sach thieu nhi").type("Kieu 1").price(123.0f)
                                .url("https://d1j8r0kxyu9tj8.cloudfront.net/images/1567492611Rj5siYiYrkqcvX8.jpg")
                                .name("Sach tieu hoc 1")
                                .build();
                Book book2 = Book.builder().author(author).description("Mo ta sach 2").publisher(publisher)
                                .quantity(100).url("https://cf.shopee.vn/file/88ab2a247c443ccd54f81afd2c76b298")
                                .name("Sach tieu hoc 2")
                                .size("A5").title("Sach khoa hoc").type("Kieu 2").price(22f).build();
                bookRepository.saveAll(List.of(book1, book2));

                Origin origin = Origin.builder().address("Mo Lao - Ha Dong - Ha Noi").nation("Viet Nam").build();
                Trademark trademark = Trademark.builder().address("Mo Lao - Ha Dong - Ha Noi").name("Trademark 1")
                                .build();
                Clothes clothes1 = Clothes.builder().barcode("111222333111").color("green")
                                .description("Mo ta quan ao 1")
                                .material("Vai bong").name("Ao len mua dong").origin(origin).price(123.12f).size("L")
                                .url("https://htmediagroup.vn/wp-content/uploads/2021/12/Ao-pijama-6-min.jpg")
                                .trademark(trademark).type("Loai 1").yearOfManufacture(2020).build();
                Clothes clothes2 = Clothes.builder().barcode("111222333222").color("red").description("Mo ta quan ao 2")
                                .material("Vai lua").name("Ao coc mua he").origin(origin).price(100.12f).size("XL")
                                .trademark(trademark).type("Loai 2").yearOfManufacture(2020)
                                .url("https://tcorder.vn/wp-content/uploads/2015/12/ao-thun-nam-ca-tinh-thoi-trang-2.jpg")
                                .build();
                clothesRepository.saveAll(List.of(clothes1, clothes2));

                Computer computer1 = Computer.builder().brand("DELL").description("Mo ta may tinh 1").discount(0)
                                .origin("Trung Quoc").power("20W").price(1000f).ram("64GB").room("1TB").size("15.6inch")
                                .name("May tinh xach tay DELL LTT").url("https://laptop88.vn/media/product/8514_vuong_3.jpg")
                                .build();
                Computer computer2 = Computer.builder().brand("ASUS").description("Mo ta may tinh 2").discount(0)
                                .origin("Trung Quoc").power("30W").price(900f).ram("32GB").room("1TB").size("15.6inch")
                                .name("May tinh xach tay ASUS").url("https://laptop88.vn/media/product/8422_samsung_galaxy_chromebook_900x900.jpg")
                                .build();
                Mobile mobile1 = Mobile.builder().accessory("Quyen truy cap").brand("Samsung").camera("100MP")
                                .name("Dien thoai di dong SAMSUNG A30S").url("https://cdn.mediamart.vn/images/product/din-thoi-vivo-v2204---y16-4128gb-vang_b035cae7.jpg")
                                .chip("Exynos9999").description("Mo ta dien thoai 1").discount(0).origin("Han Quoc")
                                .power("10W").price(500f).ram("16GB").resolution("2K").rom("512GB").build();
                Mobile mobile2 = Mobile.builder().accessory("Quyen truy cap").brand("Iphone").camera("50MP")
                                .name("Dien thoai di dong IPhone chinh hang SE")
                                .chip("A15").description("Mo ta dien thoai 2").discount(0).origin("My")
                                .url("https://hc.com.vn/i/ecommerce/media/GS.008504_FEATURE_93851.jpg")
                                .power("20W").price(1000f).ram("8GB").resolution("1K").rom("512GB").build();
                electronicRepository.saveAll(List.of(computer1, computer2, mobile1, mobile2));

                ForMan forMan1 = ForMan.builder().brand("NIKE").color("white").description("Mo ta giay nam 1")
                                .name("NIKE AIR").url("https://streetstyleshop.vn/wp-content/uploads/2022/05/the-thao-nam-chat-10.jpg")
                                .origin("Khong ro").price(200f).size(39).type("Loai 1").build();
                ForWoman forWoman1 = ForWoman.builder().brand("Mary Janes").color("black")
                                .description("Mo ta giay nu 1")
                                .url("https://product.hstatic.net/200000544093/product/b9sho610l_do_3980_da_de__1__c7080d267d11461887049b168d575f79_1024x1024.png")
                                .name("Mary Janes").origin("Viet Nam").price(30f).size(37).soleHeight(10).build();
                shoesRepository.saveAll(List.of(forMan1, forWoman1));

        }

}
