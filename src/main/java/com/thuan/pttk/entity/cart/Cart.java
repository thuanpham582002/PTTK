package com.thuan.pttk.entity.cart;

import java.util.List;

import com.thuan.pttk.entity.cutstomer.Customer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tbl_cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItemList;

    public void addCartItem(CartItem cartItem) {
        if (this.cartItemList != null) {
            cartItemList.add(cartItem);
        } else {
            System.out.println(">> CARTITEMLIST NULL CartJava 46");
        }
    }

    public int getSizeCart() {
        if (this.cartItemList == null) {
            return 0;
        } else {
            return this.cartItemList.size();
        }
    }

    public float getTotalPrice() {
        if (this.cartItemList == null || this.cartItemList.isEmpty()) {
            return 0f;
        } else {
            float total = 0f;
            for (CartItem cartItem : cartItemList) {
                total = total + cartItem.getProduct().getPrice();
            }
            return total;
        }
    }
}
