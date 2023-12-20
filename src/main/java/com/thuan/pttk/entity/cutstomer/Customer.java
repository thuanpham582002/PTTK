package com.thuan.pttk.entity.cutstomer;

import java.util.List;

import com.thuan.pttk.entity.User;
import com.thuan.pttk.entity.cart.Cart;
import com.thuan.pttk.entity.order.Order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", foreignKey = @ForeignKey(name = "fk_customer_address_id"))
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fullname_id", referencedColumnName = "fullname_id", foreignKey = @ForeignKey(name = "fk_customer_fullname_id"))
    private Fullname fullname;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "fk_customer_user_id"))
    private User user;

    @OneToOne(mappedBy = "customer")
    private Cart cart;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orderList;
}
