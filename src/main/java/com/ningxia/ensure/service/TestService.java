package com.ningxia.ensure.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TestService {
    //服务器调用接口
    List<Map<String, Object>> basicInfo(Map<String, Object> map) throws Exception;//基础信息
}
