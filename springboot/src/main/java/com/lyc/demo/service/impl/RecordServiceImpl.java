package com.lyc.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.demo.entity.Record;
import com.lyc.demo.mapper.RecordMapper;
import com.lyc.demo.service.IRecordService;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {
}
