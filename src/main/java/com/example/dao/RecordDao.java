package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Book;
import com.example.entity.Record;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordDao  extends BaseMapper<Record> {
}
