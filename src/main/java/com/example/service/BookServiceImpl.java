package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.BookDao;
import com.example.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

// 出现任何异常都回滚
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl {

    @Autowired
    BookDao bookDao;

    public void addBook(Book book) {
        bookDao.insert(book);
    }

    public void delBookById(Book book) {
        bookDao.deleteById(book.getBookId());
    }

    public void updateBook(Book book) {
        UpdateWrapper<Book> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("book_id", book.getBookId());

        bookDao.update(book, updateWrapper);
    }

    public List<Book> findBookByBookName(Book book) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("book_name", book.getBookName());

        return bookDao.selectList(queryWrapper);
    }

    public Book findBookByIsbn(Book book) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_isbn", book.getBookIsbn());

        return bookDao.selectOne(queryWrapper);
    }

}
