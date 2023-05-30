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

@TableName("record")
public class Record {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "book_id")
    private String bookId;

    @TableField(value = "user_id")
    private String userId;

    @TableField("time")
    private String time;
}
