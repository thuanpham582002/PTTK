package com.thuan.pttk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuan.pttk.entity.book.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
