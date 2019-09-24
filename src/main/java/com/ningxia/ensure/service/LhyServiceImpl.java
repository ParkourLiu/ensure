package com.ningxia.ensure.service;


import com.ningxia.ensure.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    //基础信息接口，插入基础信息并返回jar包
    @Override
    public List<Map<String, Object>> basicInfo(Map<String, Object> map) throws Exception {
        return testDao.s_table_info();
    }

}
