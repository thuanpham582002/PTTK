package com.thuan.pttk.utils;

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
import com.thuan.pttk.entity.mobile.Brand;
import com.thuan.pttk.entity.mobile.MobilePhone;
import com.thuan.pttk.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private MobilePhoneRepository mobilePhoneRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // init a role and a user
        Role rolecustomer = Role.builder().name("ROLE_CUSTOMER").build();
        rolecustomer = roleRepository.save(rolecustomer);

        User user = User.builder().username("user").password(passwordEncoder.encode("user")).accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).isEnabled(true).roles(List.of(rolecustomer)).build();
        user = userRepository.save(user);

        Fullname fullName = Fullname.builder().firstName("Nguyen").middleName("Van").lastName("A").build();
        Address address = Address.builder().city("Ha Noi").district("Ha Dong").nation("Viet Nam").numberHouse(20).street("Nguyen Trai").build();
        Customer customer = Customer.builder().address(address).fullname(fullName).user(user).build();
        customerRepository.save(customer);

        Author author = Author.builder().biography("Tieu su").name("Nguyen Thi B").nation("Viet Nam").build();
        authorRepository.save(author);
        Publisher publisher = Publisher.builder().address("Tran Phu - Ha Dong - Ha Noi").name("Nha san xuat so 1").build();
        publisherRepository.save(publisher);
        Book book1 = Book.builder().author(author).description("Mo ta sach 1").publisher(publisher).quantity(50).size("A4").title("Sach thieu nhi").type("Kieu 1").price(123.0f).url("https://d1j8r0kxyu9tj8.cloudfront.net/images/1567492611Rj5siYiYrkqcvX8.jpg").name("Sach tieu hoc 1").build();
        Book book2 = Book.builder().author(author).description("Mo ta sach 2").publisher(publisher).quantity(100).url("https://cf.shopee.vn/file/88ab2a247c443ccd54f81afd2c76b298").name("Sach tieu hoc 2").size("A5").title("Sach khoa hoc").type("Kieu 2").price(22f).build();
        bookRepository.saveAll(List.of(book1, book2));

        Origin origin = Origin.builder().address("Mo Lao - Ha Dong - Ha Noi").nation("Viet Nam").build();
        Trademark trademark = Trademark.builder().address("Mo Lao - Ha Dong - Ha Noi").name("Trademark 1").build();
        Clothes clothes1 = Clothes.builder().barcode("111222333111").color("green").description("Mo ta quan ao 1").material("Vai bong").name("Ao len mua dong").origin(origin).price(123.12f).size("L").url("https://htmediagroup.vn/wp-content/uploads/2021/12/Ao-pijama-6-min.jpg").trademark(trademark).type("Loai 1").yearOfManufacture(2020).build();
        Clothes clothes2 = Clothes.builder().barcode("111222333222").color("red").description("Mo ta quan ao 2").material("Vai lua").name("Ao coc mua he").origin(origin).price(100.12f).size("XL").trademark(trademark).type("Loai 2").yearOfManufacture(2020).url("https://tcorder.vn/wp-content/uploads/2015/12/ao-thun-nam-ca-tinh-thoi-trang-2.jpg").build();
        clothesRepository.saveAll(List.of(clothes1, clothes2));

        Brand brand = Brand.builder().address("Mo Lao - Ha Dong - Ha Noi").company("Apple").name("Apple").build();
        brandRepository.save(brand);

        MobilePhone mobile1 = MobilePhone.builder().brand(brand).type("Dien thoai di dong").screenSize(6.5f).model("Iphone 12").warranty("12 thang").color("Black").url("https://cdn.mediamart.vn/images/product/din-thoi-vivo-v2204---y16-4128gb-vang_b035cae7.jpg").description("Mo ta dien thoai 1").price(500f).build();
        MobilePhone mobile2 = MobilePhone.builder().brand(brand).type("Dien thoai di dong").screenSize(6.5f).model("Iphone 12").warranty("12 thang").color("Black").description("Mo ta dien thoai 2").url("https://hc.com.vn/i/ecommerce/media/GS.008504_FEATURE_93851.jpg").price(1000f).build();
        mobilePhoneRepository.saveAll(List.of(mobile1, mobile2));
    }

}
