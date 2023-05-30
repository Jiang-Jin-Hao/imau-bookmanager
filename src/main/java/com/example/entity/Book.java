package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@TableName("book")
public class Book {

    // IdType.ASSIGN_ID 雪花算法
    @TableId(value = "book_id", type = IdType.ASSIGN_ID)
    private String bookId;

    @TableField(value = "book_name")
    private String bookName;

    @TableField("book_isbn")
    private String bookIsbn;

    @TableField("book_price")
    private String bookPrice;

    @TableField("book_write")
    private String bookWriter;
}
